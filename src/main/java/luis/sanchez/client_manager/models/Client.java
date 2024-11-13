package luis.sanchez.client_manager.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "clients")
@Getter
@Setter
public class Client {

    @Id
    private String id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private List<Order> orders;
}
