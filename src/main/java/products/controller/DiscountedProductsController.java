package products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import products.model.Product;
import products.services.DiscountedProductsService;

import java.util.List;

@RestController
public class DiscountedProductsController {

    @Autowired
    private DiscountedProductsService service;

    @GetMapping("/categories/{category}/products")
    public List<Product> getDiscountedProducts(@PathVariable("category") String category) {

        return service.getProducts(category);

    }

}
