package products;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import products.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsListingClient {

    private final RestTemplate restTemplate;

    @Value( "${products.api.base-url}" )
    private String baseUrl;
    public ProductsListingClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    public List<Product> getProducts(String category) {

        Products products = restTemplate.getForObject("/categories/{category}/products",
                Products.class, category);

        return products.getProducts();
    }

    @Data
    private class Products{

        ArrayList<Product> products;
    }
}
