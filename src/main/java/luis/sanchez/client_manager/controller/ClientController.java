package luis.sanchez.client_manager.controller;

import luis.sanchez.client_manager.dto.ClientDto;
import luis.sanchez.client_manager.models.Client;
import luis.sanchez.client_manager.services.ClientService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
@Tag(name = "Clients", description = "Operations related to clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    @Operation(summary = "Create a new client")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @GetMapping()
    @Operation(summary = "Get all clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a client by id")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a client by id")
    public ResponseEntity<ClientDto> updateClient(@PathVariable String id, @Valid @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by id")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        return clientService.deleteClient(id);
    }
}
