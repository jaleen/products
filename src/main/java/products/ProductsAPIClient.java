package products;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import products.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsAPIClient {

    private final RestTemplate restTemplate;

    @Value( "${products.api.base-url}" )
    private String baseUrl;

    @Value( "${products.api.key}" )
    private String key;

    public ProductsAPIClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.rootUri(baseUrl). build();
    }
    public List<Product> getProducts(String category) {

        ProductsList products = restTemplate.getForObject("/categories/{category}/products?key"+key,
                ProductsList.class, category);

        return products.getProducts();
    }


}
@Data
@NoArgsConstructor
class ProductsList {

    ArrayList<Product> products;
}