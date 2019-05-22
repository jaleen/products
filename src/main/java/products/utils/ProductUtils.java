package products.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ProductUtils {

    public static String getFormattedPrice(String currency, String nowPrice){

        DecimalFormat shortDigitFormat = new DecimalFormat(currency+"##0.00");
        DecimalFormat longDigitFormat = new DecimalFormat(currency+"##0.##");
        try {
            BigDecimal bigPrice = new BigDecimal(nowPrice);
            if(bigPrice.compareTo(new BigDecimal(10))==-1){
                return shortDigitFormat.format( bigPrice);
            }
            else {
                return longDigitFormat.format( bigPrice);

            }
        }catch (NumberFormatException ex){
            return nowPrice;
        }


    }
}
