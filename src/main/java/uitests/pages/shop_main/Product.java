package uitests.pages.shop_main;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Comparator;

@Data
@Accessors(chain = true)
public class Product {
    private String name;
    public Double price;
    public Integer ID;

    public static Comparator<Product> getComparatorForSorting(SortDirection sortDirection) {
        switch (sortDirection) {
            case ID_PRODUCT:
                return new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getID().compareTo(o2.getID());
                    }
                };
            case PRICE_LOW_TO_HIGH:
                return new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getPrice().compareTo(o2.getPrice());
                    }
                };
            case PRICE_HIGH_TO_LOW:
                return new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o2.getPrice().compareTo(o1.getPrice());
                    }
                };
            case NAME_A_TO_Z:
                return new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                };
            case NAME_Z_TO_A:
                return new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                };
        }
        return null;
    }

}
