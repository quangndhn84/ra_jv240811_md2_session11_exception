package ra.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidateInputData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = inputInteger(scanner);
        System.out.println("Số nguyên vừa nhập là: " + number);
        float floatNumber = inputFloat(scanner);
        System.out.println("Số thực vừa nhập là: " + floatNumber);
        Date bod = inputBirthOfDay(scanner);
        System.out.println("Ngày sinh nhật: " + bod);
    }

    //1. Validate dữ liệu nhập vào bắt buộc là kiểu số nguyên
    public static int inputInteger(Scanner scanner) {
        System.out.println("Nhập vào một số nguyên:");
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (Exception ex) {
                System.err.println("Vui lòng nhập lại số nguyên:");
            }
        } while (true);
    }

    //2. Validate dữ liêu nhập vào là số thực
    public static float inputFloat(Scanner scanner) {
        System.out.println("Nhập vào một số thực:");
        do {
            try {
                float number = Float.parseFloat(scanner.nextLine());
                return number;
            } catch (Exception ex) {
                System.err.println("Vui lòng nhập lại số thực:");
            }
        } while (true);
    }

    //3. Validate dữ liệu nhập vào là kiểu date có định dạng là dd/MM/yyyy
    public static Date inputBirthOfDay(Scanner scanner) {
        System.out.println("Nhập vào sinh nhật của bạn:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            try {
                Date bod = sdf.parse(scanner.nextLine());
                int year = bod.getYear() + 1900;
                if (year < 2000) {
                    System.err.println("Ngày sinh phải sau năm 2000, vui lòng nhập lại");
                } else {
                    return bod;
                }

            } catch (Exception ex) {
                System.err.println("Định dạng ngày sinh nhật không đúng, vui lòng nhập lại");
            }
        } while (true);
    }
}
