package products.services;

import org.springframework.stereotype.Service;
import products.ProductsAPIClient;
import products.model.Product;
import products.services.pricelabel.PriceLabelFormatterStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountedProductsService {
    private final ProductsAPIClient productsAPIClient;

    public DiscountedProductsService(ProductsAPIClient productsAPIClient) {

        this.productsAPIClient = productsAPIClient;
    }

    public List<Product> getProducts(String category, String strategy) {

        List<Product> products = productsAPIClient.getProducts(category);

        products = products.stream().filter(product -> product.getPrice().isDiscounted())
                .peek(product -> product.setPriceLabel(
                        PriceLabelFormatterStrategy.getInstance(strategy).format(product.getPrice())))
                .collect(Collectors.toList());

        return products;
    }
}
