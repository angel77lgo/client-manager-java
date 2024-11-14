package luis.sanchez.client_manager.services;

import luis.sanchez.client_manager.dto.ClientDto;
import luis.sanchez.client_manager.mappers.ClientMapper;
import luis.sanchez.client_manager.models.Client;
import luis.sanchez.client_manager.repositories.ClientRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        return ResponseEntity.ok().body(clients);
    }

    public ResponseEntity<Client> getClientById(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        return ResponseEntity.ok().body(client);
    }

    @Transactional
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        try {
            Client newClient = clientMapper.toEntity(clientDto);
            clientRepository.save(newClient);
            ClientDto createdClientDto = clientMapper.toDto(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClientDto);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating client", e);
        }
    }

    @Transactional
    public ResponseEntity<ClientDto> updateClient(String id, ClientDto clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        try {
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setSecondLastName(clientDto.getSecondLastName());
            client.setAddress(clientDto.getAddress());

            clientRepository.save(client);

            ClientDto updatedClientDto = clientMapper.toDto(client);

            return ResponseEntity.ok().body(updatedClientDto);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating client", e);
        }
    }

    @Transactional
    public ResponseEntity<Void> deleteClient(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        try {
            clientRepository.delete(client);
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting client", e);
        }
    }
}
