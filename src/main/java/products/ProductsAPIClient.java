package products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import products.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsAPIClient {

    private final RestTemplate restTemplate;


    @Value("${products.api.key}")
    private String key;

    @Autowired
    public ProductsAPIClient(RestTemplateBuilder restTemplateBuilder, @Value("${products.api.base-url}") String baseUrl) {
        restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
    }

    public List<Product> getProducts(String category) {

        Optional<ProductsList> productsList =  Optional.of(restTemplate.getForObject("/categories/{category}/products?key=" + key,
                ProductsList.class, category));

        if(productsList.isPresent()){
            return productsList.get().getProducts();
        }else {
            return new ArrayList<>();
        }
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductsList {

    ArrayList<Product> products;
}