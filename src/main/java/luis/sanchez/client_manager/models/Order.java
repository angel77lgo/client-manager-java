package luis.sanchez.client_manager.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String sku;

    private String quantity;

    private String price;
}
