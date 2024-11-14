package luis.sanchez.client_manager.mappers;


import luis.sanchez.client_manager.dto.ClientDto;
import luis.sanchez.client_manager.models.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
