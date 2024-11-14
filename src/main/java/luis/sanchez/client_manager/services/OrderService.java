package luis.sanchez.client_manager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import luis.sanchez.client_manager.dto.OrderDto;
import luis.sanchez.client_manager.mappers.OrderMapper;
import luis.sanchez.client_manager.models.Client;
import luis.sanchez.client_manager.models.Order;
import luis.sanchez.client_manager.repositories.ClientRepository;

@Service
public class OrderService {

    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;

    public OrderService(ClientRepository clientRepository, OrderMapper orderMapper) {
        this.clientRepository = clientRepository;
        this.orderMapper = orderMapper;
    }

    private Client getClientById(String clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    private Order getOrderBySku(List<Order> orders, String sku) {
        return orders.stream()
                .filter(order -> order.getSku().equals(sku))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @Transactional
    public ResponseEntity<Order> createOrder(String clientId, OrderDto orderDto) {
        Client client = this.getClientById(clientId);

        try {
            Order order = orderMapper.toEntity(orderDto);

            if (client.getOrders() == null) {
                client.setOrders(new ArrayList<>());
            }

            client.getOrders().add(order);

            clientRepository.save(client);

            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating order", e);
        }
    }

    @Transactional
    public ResponseEntity<Order> updateOrder(String clientId, String sku, OrderDto orderDto) {
        Client client = this.getClientById(clientId);
        Order order = this.getOrderBySku(client.getOrders(), sku);

        try {
            order.setSku(orderDto.getSku());
            order.setQuantity(orderDto.getQuantity());
            order.setPrice(orderDto.getPrice());

            clientRepository.save(client);

            return ResponseEntity.ok().body(order);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating order", e);
        }
    }

    @Transactional
    public ResponseEntity<Void> deleteOrder(String clientId, String sku) {
        Client client = this.getClientById(clientId);
        Order order = this.getOrderBySku(client.getOrders(), sku);

        try {

            client.getOrders().remove(order);

            clientRepository.save(client);

            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting order", e);
        }
    }

}
