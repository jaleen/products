package products.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseProduct {
    private String productId;
    private String title;
    private ColorSwatches colorSwatches;
    private Price nowPrice;
    private String priceLabel;
}

@Data
@Builder
class Price{

    private String now;

}
