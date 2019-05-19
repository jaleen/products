package products.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import products.ProductsAPIClient;
import products.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DiscountedProductsServiceTest {

    @MockBean
    private ProductsAPIClient productsAPIClient;

    @Test
    public void whenAProductsExist_thenShouldReturnIt(){

        String category = "600001506";
        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").build());

        when(productsAPIClient.getProducts(category)).thenReturn(expectedProducts);
        DiscountedProductsService service = new DiscountedProductsService(productsAPIClient);


        List<Product> productList = service.getProducts(category);

        assertNotNull(productList);
        assertThat(service.getProducts(category).size(), is(greaterThan(0)));
    }
}
