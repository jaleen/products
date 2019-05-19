package products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String title;
    private List<ColorSwatches> colorSwatches;

    @Getter(AccessLevel.NONE)
    private Price price;


    private String nowPrice;
    public String getNowPrice(){
        return currencySymbols.get(price.getCurrency())+price.getNow();
    }


    private static final Map<String,String> currencySymbols =  Map.of("GBP", "£", "USD", "$", "EUR", "€");
}
