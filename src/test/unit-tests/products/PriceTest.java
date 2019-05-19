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

    @Test
    public void givenIntegerPrice_whenPriceIsLessThanTen_thenDecimalValue(){

        Price price = Price.builder().currency("GBP").build();
        price.setNow("2");
        Product product = Product.builder().price(price).build();

        assertThat(product.getNowPrice(),is("£2.00"));
    }
    @Test
    public void givenIntegerPrice_whenPriceIsMoreThanTen_thenDecimalValueWithOut2Zero(){

        Price price = Price.builder().currency("GBP").build();
        price.setNow("10");
        Product product = Product.builder().price(price).build();

        assertThat(product.getNowPrice(),is("£10"));
    }
    @Test
    public void givenIntegerPrice_whenPriceIsMoreThanTen_thenDecimalValueWithFractions(){

        Price price = Price.builder().currency("GBP").build();
        price.setNow("12.12");
        Product product = Product.builder().price(price).build();

        assertThat(product.getNowPrice(),is("£12.12"));
    }
}
