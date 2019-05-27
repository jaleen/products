package products.services;

import org.springframework.stereotype.Service;
import products.ProductsAPIClient;
import products.model.Product;
import products.services.pricelabel.PriceLabelFormatterStrategy;
import products.utils.ProductPriceComparator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountedProductsService {
    private final ProductsAPIClient productsAPIClient;
    private Comparator<Product> priceComparator = new ProductPriceComparator();

    public DiscountedProductsService(ProductsAPIClient productsAPIClient) {

        this.productsAPIClient = productsAPIClient;
    }

    public List<Product> getProducts(String category, String priceLabelStrategy) {

        List<Product> products = productsAPIClient.getProducts(category);

        products = products.parallelStream()
                .filter(product -> product.getPrice().isDiscounted())
                .peek(product -> product.setPriceLabel(
                        PriceLabelFormatterStrategy.getInstance(priceLabelStrategy).format(product.getPrice())))
                .sorted(priceComparator.reversed())
                .collect(Collectors.toList());


        return products;
    }
}
