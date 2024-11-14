package luis.sanchez.client_manager.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String sku;

    private int quantity;

    private double price;
}
