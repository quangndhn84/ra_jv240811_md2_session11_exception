package ra.collection.btth.ra.entity;

import ra.collection.btth.ra.IShop;
import ra.collection.btth.ra.run.ShopManagement;

import java.util.Scanner;

public class Categories implements IShop {
    private int catalogId;
    private String catalogName;
    private boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        //1. Mã danh mục phải là số nguyên lớn hơn 0 và không trùng lặp
        this.catalogId = inputCatalogId(scanner);
        //2. Tên danh mục duy nhất
        this.catalogName = inputCatalogName(scanner);
        //3. Trạng thái danh mục chỉ nhận giá trị true hoặc false
        this.status = inputStatus(scanner);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục:");
        do {
            try {
                int catalogId = Integer.parseInt(scanner.nextLine());
                if (catalogId > 0) {
                    boolean isExist = false;
                    for (Categories catalog : ShopManagement.listCategories) {
                        if (catalog.getCatalogId() == catalogId) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Mã danh mục đã tồn tại, vui lòng nhập lại");
                    } else {
                        return catalogId;
                    }
                } else {
                    System.err.println("Mã danh mục phải có giá trị lớn hơn 0, vui lòng nhập lại");
                }
            } catch (Exception ex) {
                System.err.println("Mã danh mục là số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            boolean isExist = false;
            for (Categories catalog : ShopManagement.listCategories) {
                if (catalog.getCatalogName().equals(catalogName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
            } else {
                return catalogName;
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái chỉ nhận giá trị true || false, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.status ? "Hoạt động" : "Không hoạt động");
    }
}
