package products.services.pricelabel;

import products.model.ProductPrice;
import products.model.PriceLabelStrategyName;

import java.util.Map;

public interface PriceLabelFormatterStrategy {

    Map<String, PriceLabelFormatterStrategy> strategies = Map.of(
            PriceLabelStrategyName.ShowWasNow.toString(), new ShowWasNowStrategy(),
            PriceLabelStrategyName.ShowWasThenNow.toString(), new ShowWasThenNowStrategy(),
            PriceLabelStrategyName.ShowPercDscount.toString(), new ShowPercDscountStrategy());
    static PriceLabelFormatterStrategy getInstance(String strategy) {
        return strategies.get(strategy);
    }

    String format(ProductPrice price);
}