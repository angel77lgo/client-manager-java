package luis.sanchez.client_manager.controller;

import luis.sanchez.client_manager.dto.ClientDto;
import luis.sanchez.client_manager.services.ClientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping()
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @GetMapping()
    public void getAllClients() {
        clientService.getAllClients();
    }
}
