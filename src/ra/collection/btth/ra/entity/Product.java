package ra.collection.btth.ra.entity;

import ra.collection.btth.ra.IShop;
import ra.collection.btth.ra.run.ShopManagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop {
    private String productId;
    private String productName;
    private float price;
    private String title;
    //sản phẩm Sơ mi nam thuộc về danh mục mã là 1 - Danh mục quần áo
    private int catalogId;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float price, String title, int catalogId, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        //1. Mã sản phẩm gồm 5 ký tự bắt đầu là P, các ký tự sau là số, duy nhất
        this.productId = inputProductId(scanner);
        //2. Tên sản phẩm là duy nhất
        this.productName = inputProductName(scanner);
        //3. Giá sản phẩm là số thực, có giá trị > 0
        this.price = inputPrice(scanner);
        this.title = inputTitle(scanner);
        this.catalogId = inputCatalogId(scanner);
        this.status = inputStatus(scanner);
    }

    public String inputProductId(Scanner scanner) {
        String productIdRegex = "P\\d{4";
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String productId = scanner.nextLine();
            if (Pattern.matches(productIdRegex, productId)) {
                boolean isExist = false;
                for (Product product : ShopManagement.listProducts) {
                    if (product.getProductId().equals(productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Định dạng mã sản phẩm không đúng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            boolean isExist = false;
            for (Product product : ShopManagement.listProducts) {
                if (product.getProductName().equals(productName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
            } else {
                return productName;
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            try {
                float price = Float.parseFloat(scanner.nextLine());
                if (price > 0) {
                    return price;
                }
                System.err.println("Giá sản phẩm phải có giá trị lớn hơn 0, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Giá sản phẩm là số thực, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sản phẩm:");
        return scanner.nextLine();
    }

    public int inputCatalogId(Scanner scanner) {
        //0-Danh mục quần áo, 1-Danh mục giầy dép
        /*
         * 1. Danh mục quần áo
         * 2. Danh mục giầy dép
         * Lựa chọn của bạn: 2 --> choice = index + 1
         * */
        System.out.println("Chọn danh mục của sản phẩm:");
        for (int i = 0; i < ShopManagement.listCategories.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, ShopManagement.listCategories.get(i).getCatalogName());
        }
        System.out.print("Lựa chọn của bạn:");
        int choice = Integer.parseInt(scanner.nextLine());
        return ShopManagement.listCategories.get(choice - 1).getCatalogId();
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm:");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %f\n", this.productId, this.productName, this.price);
        System.out.printf("Tiêu đề: %s - Danh mục: %s - Trạng thái: %s\n",
                this.title, getCatalogNameById(this.catalogId), this.status ? "Hoạt động" : "Không hoạt động");
    }

    public String getCatalogNameById(int catalogId) {
        for (Categories catalog : ShopManagement.listCategories) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog.getCatalogName();
            }
        }
        return "";
    }
}
