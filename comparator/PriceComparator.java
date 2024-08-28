package comparator;

import entity.Product;

import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return Float.compare(o2.getPrice(), o1.getPrice());
    }
}
