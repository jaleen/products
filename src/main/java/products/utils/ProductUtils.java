package products.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

public class ProductUtils {
    private static final Map<String, String> currencySymbols = Map.of("GBP", "£", "USD", "$", "EUR", "€", "", "£");


    public static String getFormattedPrice(String currency, String price){

        String currencySymbol = currencySymbols.getOrDefault(currency,"£");
        DecimalFormat shortDigitFormat = new DecimalFormat(currencySymbol+"##0.00");
        DecimalFormat longDigitFormat = new DecimalFormat(currencySymbol+"##0.##");
        try {
            BigDecimal bigPrice = new BigDecimal(price);
            if(bigPrice.compareTo(new BigDecimal(10))==-1){
                return shortDigitFormat.format( bigPrice);
            }
            else {
                return longDigitFormat.format( bigPrice);

            }
        }catch (NumberFormatException ex){
            return price;
        }


    }
}
