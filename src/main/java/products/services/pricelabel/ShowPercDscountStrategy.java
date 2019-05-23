package products.services.pricelabel;

import products.model.ProductPrice;
import products.utils.ProductUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShowPercDscountStrategy implements PriceLabelFormatterStrategy {

    private static final BigDecimal hundred = new BigDecimal(100);

    @Override
    public String format(ProductPrice price) {

        String discountValue = discount(new BigDecimal(price.getNow()), new BigDecimal(price.getWas()));
        return new StringBuilder(discountValue + "% off")
                .append(" - now ")
                .append(ProductUtils.getFormattedPrice(price.getCurrency(),price.getNow())).toString();
    }

    private String discount(BigDecimal now, BigDecimal was) {

        BigDecimal pricePercent = now.divide(was, 4, RoundingMode.HALF_EVEN).multiply(hundred);

        BigDecimal discount = hundred.subtract(pricePercent);


        return discount.setScale(2, RoundingMode.HALF_EVEN).toString();


    }

}
