package luis.sanchez.client_manager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    @NotEmpty(message = "firstName is required")
    private String firstName;
    @NotEmpty(message = "lastName is required")
    private String lastName;
    private String secondLastName;
    @NotEmpty(message = "email is required")
    @Email(message = "email is not valid")
    private String email;
    @NotEmpty(message = "address is required")
    private String address;
}
