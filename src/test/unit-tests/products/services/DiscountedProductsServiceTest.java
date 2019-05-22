package products.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import products.ProductsAPIClient;
import products.model.Price;
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


        List<Product> productList = service.getProducts(category, category);

        assertNotNull(productList);
        assertThat(service.getProducts(category, category).size(), is(greaterThan(0)));
    }

    @Test
    public void givenTwoProducts_whenOneIsDiscounted_thenShouldRetrieveOnlyDiscountedOne(){

        String category = "600001506";
        Price samePrice = Price.builder().now("3.00").was("").build();
        Price discountedPrice = Price.builder().now("2.00").was("3.00").build();


        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").price(samePrice).build(),
                Product.builder().productId("3421340").title("Floral Printed Dress").price(discountedPrice).build());

        when(productsAPIClient.getProducts(category)).thenReturn(expectedProducts);
        DiscountedProductsService service = new DiscountedProductsService(productsAPIClient);


        List<Product> productList = service.getProducts(category, category);

        assertThat(productList.size(), is(1));
        assertThat(productList.get(0).getNowPrice(),is("£2.00"));
    }
}
