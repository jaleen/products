package products.services.pricelabel;

import products.model.ProductPrice;
import products.utils.ProductUtils;

public class ShowWasThenNowStrategy implements PriceLabelFormatterStrategy{
    @Override
    public String format(ProductPrice price) {
        return new StringBuilder("Was ")
                .append(ProductUtils.getFormattedPrice(price.getCurrency(),price.getWas()))
                .append(", then1 ")
                .append(ProductUtils.getFormattedPrice(price.getCurrency(),price.getThen1()))
                .append(", now ")
                .append(ProductUtils.getFormattedPrice(price.getCurrency(), price.getNow())).toString();

    }
}
