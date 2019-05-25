package products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.math.NumberUtils;
import products.utils.ProductUtils;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String title;
    private List<ColorSwatches> colorSwatches;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductPrice price;
    private String priceLabel;


    @Setter(AccessLevel.NONE)
    private String nowPrice;

    public String getNowPrice() {
        if (price != null && price.getNow()!=null && NumberUtils.isCreatable(price.getNow())) {
            String currency = Optional.ofNullable(price.getCurrency()).orElse("");
            return ProductUtils.getFormattedPrice(currency, price.getNow());
        } else return "";
    }




    public ProductPrice getPrice() {
        if (price == null) {
            price = new ProductPrice();
        }
        return price;
    }
}
