package products.services.pricelabel;

import products.model.ProductPrice;
import products.utils.ProductUtils;

public class ShowWasNowStrategy implements PriceLabelFormatterStrategy {
    @Override
    public String format(ProductPrice price) {
        return new StringBuilder("Was ")
                .append(ProductUtils.getFormattedPrice(price.getCurrency(),price.getWas()))
                .append(", now ")
                .append((ProductUtils.getFormattedPrice(price.getCurrency(), price.getNow()))).toString();
    }
}
