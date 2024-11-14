package luis.sanchez.client_manager.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    @NotEmpty(message = "sku is required")
    private String sku;
    @Min(value = 1, message = "quantity must be greater than 0")
    private int quantity;
    @Positive(message = "price must be greater than 0")
    private double price;
}
