package products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import products.model.Product;

import java.util.List;

@RestController
public class DiscountedProductsController {

    @GetMapping("/products")
    public List<Product> getDiscountedProducts(){

        return null;

    }

}
