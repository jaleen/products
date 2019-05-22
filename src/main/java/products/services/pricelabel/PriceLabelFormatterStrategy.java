package products.services.pricelabel;

import products.model.Price;
import products.model.PriceLabelStrategyName;

import java.util.Map;

public interface PriceLabelFormatterStrategy {

    Map<String, PriceLabelFormatterStrategy> strategies = Map.of(
            PriceLabelStrategyName.ShowWasNow.toString(), new ShowWasNowStrategy(),
            PriceLabelStrategyName.ShowWasThenNow.toString(), new ShowWasThenNowStrategy(),
            PriceLabelStrategyName.ShowPercDscount.toString(), new ShowPercDscountStrategy());
    static PriceLabelFormatterStrategy getInstance(String category) {
        return strategies.get(category);
    }

    String format(Price price);
}