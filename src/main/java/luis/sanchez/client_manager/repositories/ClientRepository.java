package luis.sanchez.client_manager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import luis.sanchez.client_manager.models.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

    
}