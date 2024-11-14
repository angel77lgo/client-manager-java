package luis.sanchez.client_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import luis.sanchez.client_manager.dto.OrderDto;
import luis.sanchez.client_manager.models.Order;
import luis.sanchez.client_manager.services.OrderService;

@RestController
@RequestMapping("/order")
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

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
