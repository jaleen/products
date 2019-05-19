package products.model;

import lombok.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    @Setter(AccessLevel.NONE)
    private String nowPrice;
    public String getNowPrice(){
        return getFormattedPrice(currencySymbols.get(price.getCurrency()));
    }

    private String getFormattedPrice(String currency){

        DecimalFormat sformat = new DecimalFormat(currency+"##0.00");
        DecimalFormat lformat = new DecimalFormat(currency+"##0.##");
        try {
            BigDecimal bigPrice = new BigDecimal(price.getNow());
            if(bigPrice.compareTo(new BigDecimal(10))==-1){
                return sformat.format( bigPrice);
            }
            else {
                return lformat.format( bigPrice);

            }
        }catch (NumberFormatException ex){
            return price.getNow();
        }


    }

    private static final Map<String,String> currencySymbols =  Map.of("GBP", "£", "USD", "$", "EUR", "€");
}
