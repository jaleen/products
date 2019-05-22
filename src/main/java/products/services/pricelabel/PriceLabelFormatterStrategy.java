package products.services.pricelabel;

import products.model.Price;

import java.util.Map;

public interface PriceLabelFormatterStrategy {

    Map<String, PriceLabelFormatterStrategy> strategies = Map.of("ShowWasNow", new ShowWasNowStrategy());
    static PriceLabelFormatterStrategy getInstance(String category) {
        return strategies.get(category);
    }

    String format(Price price);
}