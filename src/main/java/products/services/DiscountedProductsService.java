package products.services;

import products.ProductsAPIClient;
import products.model.Product;

import java.util.List;

public class DiscountedProductsService {
    private final ProductsAPIClient productsAPIClient;

    public DiscountedProductsService(ProductsAPIClient productsAPIClient) {

        this.productsAPIClient = productsAPIClient;
    }

    public List<Product> getProducts(String category) {

        return productsAPIClient.getProducts(category);
    }
}
