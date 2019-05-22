package products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import products.utils.ProductUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String title;
    private List<ColorSwatches> colorSwatches;
    private String priceLabel;
    @Getter(AccessLevel.NONE)
    private Price price;

    @Setter(AccessLevel.NONE)
    private String nowPrice;
    public String getNowPrice(){
        String currency = Optional.ofNullable(price.getCurrency()).orElse("");
        return ProductUtils.getFormattedPrice(currency,price.getNow());
    }



    private static final Map<String,String> currencySymbols =  Map.of("GBP", "£", "USD", "$", "EUR", "€", "","£");


    @JsonIgnore
    public Price getPrice() {
        return price;
    }
}
