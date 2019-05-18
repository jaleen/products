package products.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorSwatches {
    private String color;
    private String rgbColor;
    private String skuId;
}
