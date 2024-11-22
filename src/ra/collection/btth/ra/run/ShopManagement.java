package ra.collection.btth.ra.run;

import ra.collection.btth.ra.entity.Categories;
import ra.collection.btth.ra.entity.Product;

import java.util.*;

public class ShopManagement {
    public static List<Categories> listCategories = new ArrayList<>();
    public static List<Product> listProducts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********SHOP MANAGEMENT*************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayMenuCategories(scanner);
                    break;
                case 2:
                    displayMenuCategories(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayMenuCategories(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("***********CATALOG MANAGEMENT**************");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị thông tin danh mục");
            System.out.println("3. Cập nhật danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của ban:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createCatalog(scanner);
                    break;
                case 2:
                    displayListCategories();
                    break;
                case 3:
                    updateCategories(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");

            }
        } while (isExit);
    }

    public static void createCatalog(Scanner scanner) {
        System.out.println("Nhập số danh mục cần thêm:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Categories catalog = new Categories();
            catalog.inputData(scanner);
            listCategories.add(catalog);
        }
    }

    public static void displayListCategories() {
        for (Categories catalog : listCategories) {
            catalog.displayData();
        }
    }

    public static void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogIdUpdate = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndexByCatalogId(catalogIdUpdate);
        if (indexUpdate >= 0) {
            int choice;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật trạng thái danh mục");
                System.out.println("3. Thoát");
                System.out.print("Lựa chọn của bạn:");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên danh mục cần cập nhật:");
                        listCategories.get(indexUpdate).setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào trạng thái cần cập nhật:");
                        listCategories.get(indexUpdate).setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Kết thúc cập nhật danh mục");
                        break;
                    default:
                        System.err.println("Vui lòng chọn 1-3");
                }
            } while (choice != 3);

        } else {
            System.err.println("Mã danh mục không tôn tại, vui lòng nhập lại");
        }
    }

    public static int getIndexByCatalogId(int catalogId) {
        for (int i = 0; i < listCategories.size(); i++) {
            if (listCategories.get(i).getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogIdDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexByCatalogId(catalogIdDelete);
        if (indexDelete >= 0) {
            //Kiểm tra danh mục đã chứa sản phẩm chưa
            boolean hasProduct = false;
            for (Product product : listProducts) {
                if (product.getCatalogId() == catalogIdDelete) {
                    hasProduct = true;
                    break;
                }
            }
            if (!hasProduct) {
                listCategories.remove(indexDelete);
            } else {
                System.err.println("Danh mục đã chứa sản phẩm, không thể xóa");
            }

        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void displayMenuProduct(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("***********PRODUCT MANAGEMENT**************");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Hiển thị sản phẩm");
            System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    sortProductByPriceASC();
                    break;
                case 6:
                    sortProductByNameASC();
                    break;
                case 7:
                    staticticProductByCatalog();
                    break;
                case 8:
                    searchProductByName(scanner);
                    break;
                case 9:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        } while (isExit);
    }

    public static void sortProductByPriceASC() {
        Collections.sort(listProducts, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        });
    }

    public static void sortProductByNameASC() {
        Collections.sort(listProducts, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        });
    }

    public static void staticticProductByCatalog() {
        for (Categories catalog : listCategories) {
            //Tinh so luong san pham cua danh muc catalog
            int cntProduct = 0;
            for (Product product : listProducts) {
                if (product.getCatalogId() == catalog.getCatalogId()) {
                    cntProduct++;
                }
            }
            System.out.printf("%s: %d sản phẩm\n", catalog.getCatalogName(), cntProduct);
        }
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productNameSearch = scanner.nextLine();
        int cntProduct = 0;
        System.out.println("Các sản phẩm tìm được là:");
        for (Product product : listProducts) {
            if (product.getProductName().toLowerCase().contains(productNameSearch.toLowerCase())) {
                product.displayData();
            }
        }
        System.out.printf("Tìm được %d sản phẩm thỏa mãn\n", cntProduct);
    }
}
