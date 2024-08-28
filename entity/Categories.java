package entity;

import run.ShopManagement;

import java.util.Scanner;

public class Categories implements ICategories {
    private int categoriesId;
    private String categoriesName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int categoriesId, String categoriesName, String description, boolean catalogStatus) {
        this.categoriesId = categoriesId;
        this.categoriesName = categoriesName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    @Override
    public void inputData(Scanner sc) {
        this.categoriesId = inputCateId(sc);
        this.categoriesName = inputCateName(sc);
        this.description = inputDescription(sc);
        this.catalogStatus = inputCatalogStatus(sc);
    }

    @Override
    public void displayData() {
        System.out.printf("%-5s %-20s %-20s %-10s%n\n", this.categoriesId, this.categoriesName, this.description, this.catalogStatus ? "Available" : "Not Available");
    }

    public int inputCateId(Scanner sc) {
        if (ShopManagement.categoriesList.isEmpty()) {
            return 0;
        } else {
            return ShopManagement.categoriesList.get(ShopManagement.categoriesList.size() - 1).getCategoriesId() + 1;
        }
    }

    public String inputCateName(Scanner sc) {
        System.out.println("Enter Category Name: ");
        do {
            String categoryName = sc.nextLine();
            if (!categoryName.isEmpty() && categoryName.length() <= 50) {

                boolean isExist = false;
                for (Categories c : ShopManagement.categoriesList) {
                    if (c.getCategoriesName().equalsIgnoreCase(categoryName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Category Name: " + categoryName + " already exists");
                } else {
                    return categoryName;
                }

            } else {
                System.err.println("Category Name is empty");
            }
        } while (true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Enter Description: ");
        return sc.nextLine();
    }

    public boolean inputCatalogStatus(Scanner sc) {
        System.out.println("Enter Catalog Status: ");
        do {
            String catalogStatus = sc.nextLine();
            if (catalogStatus.equalsIgnoreCase("true") || catalogStatus.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(catalogStatus);
            } else {
                System.err.println("Invalid Catalog Status(true/false)");
            }
        } while (true);
    }

    public String updateCateName(Scanner sc, int index) {
        System.out.println("Enter Category Name: ");
        do {
            String categoryName = sc.nextLine();
            if (!categoryName.isEmpty() && categoryName.length() <= 50) {
                boolean isExist = false;
                int currentIndexUpdate = -1;
                for (int i = 0; i < ShopManagement.categoriesList.size(); i++) {
                    if (ShopManagement.categoriesList.get(i).getCategoriesName().equalsIgnoreCase(categoryName)) {
                        isExist = true;
                        currentIndexUpdate = i;
                        break;
                    }
                }
                if (isExist) {
                    //check if update on exactly that index
                    if (currentIndexUpdate == index) {
                        return categoryName;
                    }else {
                        System.err.println("Category Name: " + categoryName + " already exists");
                    }
                } else {
                    return categoryName;
                }
            } else {
                System.err.println("Category Name is empty");
            }
        } while (true);
    }
}
