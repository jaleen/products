package products.model;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorSwatches {
    private String color;
    private String skuId;

    @Getter(AccessLevel.NONE)
    private String basicColor;

    public String getRGBColor() {
        return rgbs.get(basicColor);
    }

    private static final Map<String, String> rgbs = Map.of(
            "Red", "FF0000",
            "Blue", "0000FF",
            "Pink", "FFC0CB",
            "Black", "000000",
            "Green", "00FF00",
            "Grey", "808080",
            "Orange", "FFA500",
            "Purple", "800080",
            "White", "FFFFFF",
            "Yellow", "FFFF00");
}
