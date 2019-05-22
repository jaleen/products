package products.services.pricelabel;

import products.model.Price;

public class ShowWasNowStrategy implements PriceLabelFormatterStrategy {
    @Override
    public String format(Price price) {
        return new StringBuilder("Was ").append(price.getCurrency()).append(price.getWas()).append(", now ").append(price.getCurrency()).append(price.getNow()).toString();
    }
}
