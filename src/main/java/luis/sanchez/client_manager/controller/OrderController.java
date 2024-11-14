package luis.sanchez.client_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import luis.sanchez.client_manager.dto.OrderDto;
import luis.sanchez.client_manager.models.Order;
import luis.sanchez.client_manager.services.Orderservice;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private Orderservice orderService;

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestParam(name = "clientId") String clientId,
            @Valid @RequestBody OrderDto orderDto) {
        return orderService.createOrder(clientId, orderDto);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Order> updateOrder(@RequestParam(name = "clientId") String clientId,
            @PathVariable String sku, @Valid @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(clientId, sku, orderDto);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteOrder(@RequestParam(name = "clientId") String clientId,
            @PathVariable String sku) {
        return orderService.deleteOrder(clientId, sku);
    }
}
