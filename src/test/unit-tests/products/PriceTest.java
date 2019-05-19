package products;

import org.junit.Test;
import products.model.Price;
import products.model.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PriceTest {

    @Test
    public void whenAProductHaveAPrice_thenRetrieveItWithCurrency(){

        Price price = Price.builder().currency("GBP").build();
        price.setNow("10");
        Product product = Product.builder().price(price).build();

        assertThat(product.getNowPrice(),is("£10"));
    }
}
