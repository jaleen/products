package products.utils;

import products.model.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product>{

    @Override
    public int compare(Product product1, Product product2) {
        return Double.compare(product1.getPrice().getReduction(),product2.getPrice().getReduction());
    }
}

