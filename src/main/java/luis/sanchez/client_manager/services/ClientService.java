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

    public void getAllClients() {
       List<Client> clients = clientRepository.findAll();
       System.out.println(clients);
    }

    @Transactional
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        Client newClient = clientMapper.toEntity(clientDto);
        clientRepository.save(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }
}
