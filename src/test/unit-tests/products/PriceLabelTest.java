package products;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import products.model.Price;
import products.model.PriceLabelStrategyName;
import products.model.Product;
import products.services.DiscountedProductsService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PriceLabelTest {
    @MockBean
    private ProductsAPIClient productsAPIClient;
    @Test
    public void givenDiscountedPrice_whenShowWasNow_thenPriceLabelFormattedForShowWasNow(){

        String category = "600001506";
        Price discountedPrice = Price.builder().currency("£").now("2.00").was("3.00").build();
        Price notDiscountedPrice = Price.builder().currency("£").now("4.00").build();

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").price(discountedPrice).build(),
                Product.builder().productId("4525086").title("Blue Tasha Vest Dress").price(notDiscountedPrice).build());

        when(productsAPIClient.getProducts(category)).thenReturn(expectedProducts);
        DiscountedProductsService service = new DiscountedProductsService(productsAPIClient);

        String strategy = PriceLabelStrategyName.ShowWasNow.toString();
        Product product = service.getProducts(category, strategy).get(0);

        assertThat(product.getPriceLabel(), is("Was £3.00, now £2.00"));
    }
    @Test
    public void givenDiscountedPrice_whenShowWasThenNow_thenPriceLabelFormattedForShowWasThenNow(){

        String category = "600001506";
        Price discountedPrice = Price.builder().currency("£").was("3.00").then("2.50").now("2.00").build();
        Price notDiscountedPrice = Price.builder().currency("£").now("4.00").build();

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").price(discountedPrice).build(),
                Product.builder().productId("4525086").title("Blue Tasha Vest Dress").price(notDiscountedPrice).build());

        when(productsAPIClient.getProducts(category)).thenReturn(expectedProducts);
        DiscountedProductsService service = new DiscountedProductsService(productsAPIClient);

        String strategy = PriceLabelStrategyName.ShowWasThenNow.toString();
        Product product = service.getProducts(category, strategy).get(0);

        assertThat(product.getPriceLabel(), is("Was £3.00, then £2.50, now £2.00"));
    }
    @Test
    public void givenDiscountedPrice_whenShowPercDiscount_thenPriceLabelFormattedForShowPercDiscount(){

        String category = "600001506";
        Price discountedPrice = Price.builder().currency("£").was("3.00").now("2.00").build();
        Price notDiscountedPrice = Price.builder().currency("£").now("4.00").build();

        List<Product> expectedProducts = Arrays.asList(Product.builder().productId("3525085").title("hush Tasha Vest Dress").price(discountedPrice).build(),
                Product.builder().productId("4525086").title("Blue Tasha Vest Dress").price(notDiscountedPrice).build());

        when(productsAPIClient.getProducts(category)).thenReturn(expectedProducts);
        DiscountedProductsService service = new DiscountedProductsService(productsAPIClient);

        String strategy = PriceLabelStrategyName.ShowPercDscount.toString();
        Product product = service.getProducts(category, strategy).get(0);

        assertThat(product.getPriceLabel(), is("33.33% off - now £2.00"));
    }
}
