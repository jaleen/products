package products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import products.model.Product;

import java.util.List;

import static java.lang.System.out;

@SpringBootApplication
public class DiscountedProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountedProductsApplication.class, args);

    }}
