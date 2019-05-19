package products;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import products.model.Product;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscountedProductsTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void whenProductsExist_thenShouldReturn200() throws Exception {
        this.mvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void whenProductsExist_thenShouldReturnProducts() throws Exception {
        MvcResult result = this.mvc.perform(get("/categories/600001506/products")).andExpect(status().isOk())
        .andReturn();
        ObjectMapper mapper = new ObjectMapper();

        List<Product> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});

        assertThat(actual.size(), is(greaterThan(0)));
    }
}
