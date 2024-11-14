package luis.sanchez.client_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import luis.sanchez.client_manager.dto.OrderDto;
import luis.sanchez.client_manager.mappers.OrderMapper;
import luis.sanchez.client_manager.models.Client;
import luis.sanchez.client_manager.models.Order;
import luis.sanchez.client_manager.repositories.ClientRepository;

@Service
public class Orderservice {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ClientRepository clientRepository;


    private Client getClientById(String clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty()) {
            return null;
        }

        return optionalClient.get();
    }

    private Order getOrderBySku(List<Order> orders, String sku) {
        Optional<Order> optionalOrder = orders.stream().filter(o -> o.getSku().equals(sku)).findFirst();

        if (optionalOrder.isEmpty()) {
            return null;
        }

        return optionalOrder.get();
    }

    @Transactional
    public ResponseEntity<Order> createOrder(String clientId, OrderDto orderDto) {
        Client client = this.getClientById(clientId);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderMapper.toEntity(orderDto);

        if (client.getOrders() == null) {
            client.setOrders(new ArrayList<>());
        }

        client.getOrders().add(order);

        clientRepository.save(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Transactional
    public ResponseEntity<Order> updateOrder(String clientId, String sku, OrderDto orderDto) {
        Client client = this.getClientById(clientId);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Order order = this.getOrderBySku(client.getOrders(), sku);

        order.setSku(orderDto.getSku());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());

        clientRepository.save(client);

        return ResponseEntity.ok().body(order);
    }

    @Transactional
    public ResponseEntity<Void> deleteOrder(String clientId, String sku) {
        Client client = this.getClientById(clientId);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        Order order = this.getOrderBySku(client.getOrders(), sku);

        client.getOrders().remove(order);

        clientRepository.save(client);

        return ResponseEntity.noContent().build();
    }

}
