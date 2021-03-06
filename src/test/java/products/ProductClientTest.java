package products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import products.model.ColorSwatches;
import products.model.Product;
import products.model.ProductList;
import products.model.ProductPrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
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
    @Value("${products.api.base-url}")
    private String baseUrl;

    @Test
    public void givenSomeProductsAreThere_whenGET_thenRetrieveThem() throws Exception {

        ProductPrice discountedPrice = ProductPrice.builder().now("2.00").was("3.00").currency("GBP").build();

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").price(discountedPrice).build());
        ProductList productList = ProductList.builder().products(expectedProducts).build();
        String expectedProductJson =
                objectMapper.writeValueAsString(productList);

        this.server.expect(requestTo("/categories/600001506/products?key="+key))
                .andRespond(withSuccess(expectedProductJson, MediaType.APPLICATION_JSON));


        List<Product> actualProducts = client.getProducts("600001506");

        assertThat(actualProducts.get(0).getProductId(), is(expectedProducts.get(0).getProductId()));


    }


    @Test
    public void givenAProduct_whenSwatchesExist_thenRetrieveSwatchesAsWell() throws Exception {

        ProductPrice discountedPrice = ProductPrice.builder().now("2.00").was("3.00").currency("GBP").build();

        List<ColorSwatches> colorSwatches = new ArrayList<>();
        colorSwatches.add(ColorSwatches.builder().color("Hibiscus").skuId("237348324").build());
        colorSwatches.add(ColorSwatches.builder().color("French Blue").skuId("237348235").build());

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").colorSwatches(colorSwatches).price(discountedPrice).build());
        ProductList productList = ProductList.builder().products(expectedProducts).build();
        String expectedProductJson =
                objectMapper.writeValueAsString(productList);

        this.server.expect(requestTo("/categories/600001506/products?key="+key))
                .andRespond(withSuccess(expectedProductJson, MediaType.APPLICATION_JSON));


        List<Product> actualProducts = client.getProducts("600001506");

        assertEquals(expectedProducts.get(0).getColorSwatches(),actualProducts.get(0).getColorSwatches());
    }




}

