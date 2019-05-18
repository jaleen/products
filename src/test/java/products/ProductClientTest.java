package products;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import products.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(ProductsAPIClient.class)
public class ProductClientTest {

    @Autowired
    private ProductsAPIClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Value( "${products.api.key}" )
    private String key;


    @Test
    public void givenSomeProductsAreThere_whenGET_thenRetrieveThem() throws Exception {

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").build());
        ProductList productList = ProductList.builder().products(expectedProducts).build();
        String expectedProductJson =
                objectMapper.writeValueAsString(productList);

        this.server.expect(requestTo("/categories/600001506/products?key"+key))
                .andRespond(withSuccess(expectedProductJson, MediaType.APPLICATION_JSON));


        List<Product> actualProducts = client.getProducts("600001506");

        assertThat(actualProducts, is(expectedProducts));


    }

}

@Data
@Builder
class ProductList {
    private List<Product> products;
}
