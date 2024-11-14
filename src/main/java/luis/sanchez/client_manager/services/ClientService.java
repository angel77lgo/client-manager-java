package luis.sanchez.client_manager.services;

import luis.sanchez.client_manager.dto.ClientDto;
import luis.sanchez.client_manager.mappers.ClientMapper;
import luis.sanchez.client_manager.models.Client;
import luis.sanchez.client_manager.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ResponseEntity<List<Client>> getAllClients() {
       List<Client> clients = clientRepository.findAll();
       
       return ResponseEntity.ok().body(clients);
    }

    public ResponseEntity<Client> getClientById(String id){
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @Transactional
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        Client newClient = clientMapper.toEntity(clientDto);
        clientRepository.save(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }

    @Transactional
    public ResponseEntity<ClientDto> updateClient(String id, ClientDto clientDto) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setSecondLastName(clientDto.getSecondLastName());
        client.setAddress(clientDto.getAddress());

        clientRepository.save(client);

        ClientDto updatedClientDto = clientMapper.toDto(client);
       
        return ResponseEntity.ok().body(updatedClientDto);
    }

    @Transactional
    public ResponseEntity<Void> deleteClient(String id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.delete(client);
        return ResponseEntity.noContent().build();
    }
}
