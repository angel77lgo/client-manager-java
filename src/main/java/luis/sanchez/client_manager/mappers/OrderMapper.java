package luis.sanchez.client_manager.mappers;

import org.mapstruct.Mapper;

import luis.sanchez.client_manager.dto.OrderDto;
import luis.sanchez.client_manager.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
