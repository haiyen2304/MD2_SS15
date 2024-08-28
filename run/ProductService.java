package run;

import color.ColorStorage;
import comparator.PriceComparator;
import entity.Product;

import java.util.Scanner;

public class ProductService {
    public static void productMenu(Scanner sc) {
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor + "╔═════════════════PRODUCT MANAGEMENT═════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Enter number of products and information    " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Show all products                           " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Sort products by price                      " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Update products by id                       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "5. Delete products by id                       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "6. Search products by name                     " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "7. Search products by price (a to b)           " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "8. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    showAllProducts();
                    break;
                case 3:
                    sortProductByPrice(sc);
                    break;
                case 4:
                    updateProductById(sc);
                    break;
                case 5:
                    deleteProductById(sc);
                    break;
                case 6:
                    searchProductByName(sc);
                    break;
                case 7:
                    searchProductByPrice(sc);
                    break;
                case 8:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice");
            }

        } while (isOut);
    }

    public static void addProduct(Scanner sc) {
        System.out.println("Enter number of products: ");
        do{
            String num = sc.nextLine();
            try {
                if(Integer.parseInt(num)>0) {
                    for (int i = 0; i < Integer.parseInt(num); i++) {
                        System.out.println("Product " + (i+1) + " information:");
                        Product product = new Product();
                        product.inputData(sc);
                        ShopManagement.productList.add(product);
                    }
                    return;
                }else {
                    System.out.println("Invalid input number");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number value. Try again.");
            }
        }while (true);
    }
    public static void showAllProducts() {
        System.out.printf("%-5s %-20s %-10s %-20s %-10s %-10s %-10s%n\n","ID","Name","Price","Description","Created","CateId","Status");
        for (Product product : ShopManagement.productList) {
            product.displayData();
        }
    }
    public static void sortProductByPrice(Scanner sc) {
        ShopManagement.productList.sort(new PriceComparator());
        for (Product product : ShopManagement.productList) {
            product.displayData();
        }
    }
    public static void updateProductById(Scanner sc) {
        System.out.println("Enter product id to update: ");
        String id = sc.nextLine();
        for (Product product : ShopManagement.productList) {
            if(product.getProductId().equals(id)){
                int indexUpdate = findIndexById(id);
                updateProductMenu(sc,indexUpdate);
                return;
            }else {
                System.out.println("There is no such product with id: " + id);
            }
        }
    }
    public static void deleteProductById(Scanner sc) {
        System.out.println("Enter product id to delete: ");
        String id = sc.nextLine();
        for (Product product : ShopManagement.productList) {
            if(product.getProductId().equals(id)){
                int indexDel = findIndexById(id);
                ShopManagement.productList.remove(indexDel);
                return;
            }else {
                System.out.println("There is no such product with id: " + id);
            }
        }
    }
    public static void searchProductByName(Scanner sc) {
        System.out.println("Enter product name to search: ");
        String name = sc.nextLine();
        for (Product product : ShopManagement.productList) {
            if(product.getProductName().toLowerCase().contains(name.toLowerCase())){
                product.displayData();
            }
        }
    }
    public static void searchProductByPrice(Scanner sc) {
        System.out.println("Enter product price to search (first): ");
        float firstPrice = 0;
        do{
            String firstMoney = sc.nextLine();
            try {
                firstPrice = Float.parseFloat(firstMoney);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number value. Try again.");
            }
        }while (true);
        System.out.println("Enter product price to search (second): ");
        float secondPrice = 0;
        do{
            String secondMoney = sc.nextLine();
            try {
                secondPrice = Float.parseFloat(secondMoney);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number value. Try again.");
            }
        }while (true);

        for (Product product : ShopManagement.productList) {
            if (product.getPrice()>=firstPrice&&product.getPrice()<=secondPrice){
                product.displayData();
            }
        }
    }

    private static int findIndexById(String id) {
        for (int i = 0; i < ShopManagement.productList.size(); i++) {
            if (ShopManagement.productList.get(i).getProductId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static void updateProductMenu(Scanner sc,int index) {
        String borderColor = ColorStorage.RED;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor +  "╔═════════════════════UPDATE═════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Update product Name                         " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Update price                                " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Update description                          " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Update created                              " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "5. Update category id                          " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "6. Update status                               " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "7. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    ShopManagement.productList.get(index).setProductName(ShopManagement.productList.get(index).inputProductName(sc));
                    break;
                case 2:
                    ShopManagement.productList.get(index).setPrice(ShopManagement.productList.get(index).inputPrice(sc));
                    break;
                case 3:
                    ShopManagement.productList.get(index).setDescription(ShopManagement.productList.get(index).inputDescription(sc));
                    break;
                case 4:
                    ShopManagement.productList.get(index).setCreated(ShopManagement.productList.get(index).inputCreated(sc));
                    break;
                case 5:
                    ShopManagement.productList.get(index).setCateId(ShopManagement.productList.get(index).inputCateId(sc));
                    break;
                case 6:
                    ShopManagement.productList.get(index).setProductStatus(ShopManagement.productList.get(index).inputProductStatus(sc));
                    break;
                case 7:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice");
            }

        } while (isOut);
    }
}
