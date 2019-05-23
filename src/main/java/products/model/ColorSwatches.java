package products.model;

import lombok.*;

import java.util.Map;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorSwatches {
    private String color;
    private String skuId;

    @Getter(AccessLevel.NONE)
    private String basicColor;

    @Setter (AccessLevel.NONE)
    private String rgbcolor;
    public String getRGBColor() {
        if(basicColor!=null) {
            return Optional.ofNullable(rgbs.get(basicColor)).orElse("");
        }else {
            return "";
        }

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
