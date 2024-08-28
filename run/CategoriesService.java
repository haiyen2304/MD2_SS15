package run;

import color.ColorStorage;
import entity.Categories;

import java.util.Scanner;

public class CategoriesService {
    public static void cateMenu(Scanner sc) {
        //create color
        String borderColor = ColorStorage.BLUE;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor + "╔══════════════════════MENU══════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Enter number of category and information    " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Show all category                           " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Update category by Id                       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Delete category by id                       " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "5. Update status category                      " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "6. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addCategory(sc);
                    break;
                case 2:
                    showAllCategories();
                    break;
                case 3:
                    updateCategoryById(sc);
                    break;
                case 4:
                    deleteCategoryById(sc);
                    break;
                case 5:
                    changeStatus(sc);
                    break;
                case 6:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice");
            }

        } while (isOut);
    }

    public static void addCategory(Scanner sc) {
        System.out.println("Enter number of categories: ");
        do{
            String num = sc.nextLine();
            try {
                if(Integer.parseInt(num)>0) {
                    for (int i = 0; i < Integer.parseInt(num); i++) {
                        System.out.println("Category " + (i+1) + " information:");
                        Categories cate = new Categories();
                        cate.inputData(sc);
                        ShopManagement.categoriesList.add(cate);
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
    public static void showAllCategories() {
        System.out.printf("%-5s %-20s %-20s %-10s%n\n","ID","Name","Description","Status");
        for (Categories cate : ShopManagement.categoriesList) {
            cate.displayData();
        }
    }
    public static void updateCategoryById(Scanner sc) {
        System.out.println("Enter id of the category you would like to update: ");
        do {
            String cataId = sc.nextLine();
            try {
                int indexUpdate = findIndexById(Integer.parseInt(cataId));
                if(indexUpdate>=0) {
                    updateMenu(sc, indexUpdate);
                    return;
                }else {
                    System.out.println("Can't find category with id " + cataId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        }while (true);
    }
    public static void deleteCategoryById(Scanner sc) {
        System.out.println("Enter id of the category you would like to delete: ");
        do {
            String cataId = sc.nextLine();
            try {
                int indexDel = findIndexById(Integer.parseInt(cataId));
                if(indexDel>=0) {
                    boolean isExist = false;
                    for(int i =0; i<ShopManagement.productList.size(); i++) {
                        if(ShopManagement.productList.get(i).getCateId() == Integer.parseInt(cataId)){
                            isExist = true;
                            break;
                        }
                    }
                    if(!isExist) {
                        ShopManagement.categoriesList.remove(indexDel);
                        return;
                    }else {
                        System.out.println("Can't delete category with id " + cataId + ". Because it has products");
                        return;
                    }

                }else {
                    System.out.println("Can't find category with id " + cataId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        }while (true);
    }
    public static void changeStatus(Scanner sc) {
        System.out.println("Enter id of the category you would like to change: ");
        do {
            String cataId = sc.nextLine();
            try {
                int indexUpdate = findIndexById(Integer.parseInt(cataId));
                if(indexUpdate>=0) {
                    ShopManagement.categoriesList.get(indexUpdate).setCatalogStatus(ShopManagement.categoriesList.get(indexUpdate).inputCatalogStatus(sc));
                    return;
                }else {
                    System.out.println("Can't find category with id " + cataId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        }while (true);
    }

    private static int findIndexById(int id){
        for (int i = 0; i < ShopManagement.categoriesList.size(); i++) {
            if(ShopManagement.categoriesList.get(i).getCategoriesId() == id){
                return i;
            }
        }
        return -1;
    }

    private static void updateMenu(Scanner sc,int index) {
        String borderColor = ColorStorage.RED;
        String thickBorderbottom = borderColor + "╚════════════════════════════════════════════════╝" + ColorStorage.RESET;
        String thinBorder = borderColor + "║" + ColorStorage.RESET;
        boolean isOut = true;
        do {
            String thickBorder = borderColor +  "╔═════════════════════UPDATE═════════════════════╗" + ColorStorage.RESET;
            System.out.println(thickBorder);
            System.out.println(thinBorder + " " + borderColor + "1. Update category Name                        " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "2. Update descriptions                         " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "3. Update status                               " + thinBorder);
            System.out.println(thinBorder + " " + borderColor + "4. Exit                                        " + thinBorder);
            System.out.println(thickBorderbottom);
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    ShopManagement.categoriesList.get(index).setCategoriesName(ShopManagement.categoriesList.get(index).updateCateName(sc,index));
                    break;
                case 2:
                    ShopManagement.categoriesList.get(index).setDescription(ShopManagement.categoriesList.get(index).inputDescription(sc));
                    break;
                case 3:
                    ShopManagement.categoriesList.get(index).setCatalogStatus(ShopManagement.categoriesList.get(index).inputCatalogStatus(sc));
                    break;
                case 4:
                    isOut = false;
                    break;
                default:
                    System.err.println("Invalid choice");
            }

        } while (isOut);
    }
}
