package products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestClientTest(ProductsListingClient.class)
public class ProductClientTest {

    @Autowired
    private ProductsListingClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup()
            throws Exception {


    }

    @Test
    public void givenSomeProductsAreThere_whenGET_thenRetrieveThem() throws Exception {

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").build());
        String expectedProductJson =
                objectMapper.writeValueAsString(expectedProducts);

        this.server.expect(requestTo("/categories/600001506/products"))
                .andRespond(withSuccess(expectedProductJson, MediaType.APPLICATION_JSON));


        List<Product> actualProducts = client.getProducts("600001506");

        assertThat(actualProducts, is(expectedProducts));


    }
}
