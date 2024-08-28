package entity;

import run.ShopManagement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Product implements IProduct{
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int cateId;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int cateId, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.cateId = cateId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public void inputData(Scanner sc) {
        this.productId = inputProductId(sc);
        this.productName = inputProductName(sc);
        this.price = inputPrice(sc);
        this.description = inputDescription(sc);
        this.created = inputCreated(sc);
        this.cateId = inputCateId(sc);
        this.productStatus = inputProductStatus(sc);
    }

    @Override
    public void displayData() {
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-5s %-20s %-10s %-20s %-10s %-10s %-10s%n\n",this.productId,this.productName,vndFormat.format((double) this.price),this.description,sdf.format(this.created),this.cateId,this.productStatus);
    }

    public String inputProductId(Scanner sc) {
        System.out.println("Enter product id (number): ");
        do {
            String productId = sc.nextLine();
            String regex = "C\\d{3}";
            if (productId.matches(regex)) {
                boolean isExists = false;
                for(Product p : ShopManagement.productList){
                    if(p.getProductId().equals(productId)){
                        isExists = true;
                        break;
                    }
                }
                if(!isExists){
                    return productId;
                }else {
                    System.err.println("The product id " + productId + " is already exists");
                }
            }else {
                System.err.println("Invalid product id(Cxxx)");
            }
        }while (true);
    }

    public String inputProductName(Scanner sc) {
        System.out.println("Enter product name: ");
        do {
            String productName = sc.nextLine();
            if(productName.length()>=10 && productName.length()<=50){
                return productName;
            }else {
                System.err.println("Invalid product name(10-50)");
            }
        }while (true);
    }

    public float inputPrice(Scanner sc) {
        System.out.println("Enter product price: ");
        do {
            String price = sc.nextLine();
            try {
                if (Float.parseFloat(price) > 0) {
                    return Float.parseFloat(price);
                } else {
                    System.err.println("Invalid product price (>0). Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid product price value. Try again.");
            }
        }while (true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Enter product description: ");
        return sc.nextLine();
    }

    public Date inputCreated(Scanner sc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Ensures strict parsing
        Date dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.println("Enter product created: (dd/MM/yyyy): ");
            String dateInput = sc.nextLine();
            try {
                dateOfBirth = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }
        return dateOfBirth;
    }

    public int inputCateId(Scanner sc) {
        System.out.println("Enter product cate id: ");
        do {
            String cataId = sc.nextLine();
            try {
                if (Integer.parseInt(cataId) >= 0) {
                    //check if there are any id with same value ->return value
                    boolean isExist = false;
                    for (Categories categories : ShopManagement.categoriesList){
                        if (categories.getCategoriesId() == Integer.parseInt(cataId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        return Integer.parseInt(cataId);
                    }else {
                        System.err.println("There is no such category Id");
                    }
                } else {
                    System.err.println("Invalid catalog Id value. Must enter number > 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid catalog Id value. Try again.");
            }
        }while (true);
    }

    public boolean inputProductStatus(Scanner sc) {
        System.out.println("Enter product status: ");
        do {
            String productStatus = sc.nextLine();
            if (productStatus.equalsIgnoreCase("true") || productStatus.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(productStatus);
            } else {
                System.err.println("Invalid Product Status (true/false). Please try again.");
            }
        }while (true);
    }
}
