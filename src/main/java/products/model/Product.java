package products.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Product {
    private String productId;
    private String title;
    private ColorSwatches colorSwatches;
    private String nowPrice;
    private String priceLabel;
}
