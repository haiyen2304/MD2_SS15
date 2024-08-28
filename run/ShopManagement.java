package run;

import color.ColorStorage;
import entity.Categories;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopManagement {
    public static List<Categories> categoriesList = new ArrayList<Categories>();
    public static List<Product> productList = new ArrayList<Product>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor + "╔════════════════════SHOP MENU═══════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Categories Management                       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Products Management                         " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    CategoriesService.cateMenu(sc);
                    break;
                case 2:
                    ProductService.productMenu(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }

        } while (true);
    }
}
