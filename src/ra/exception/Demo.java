package ra.exception;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào số thứ nhất:");
        //NumberFormatException --> Chương trình bị dừng lại --> Runtime --> Unchecked Exception
        int firstNumber;
        try {
            firstNumber = Integer.parseInt(scanner.nextLine());
        } catch (Exception ex) {
            ex.printStackTrace();
            firstNumber = 1;
        }

        System.out.println("Nhập vào số thứ hai:");
        int secondNumber = Integer.parseInt(scanner.nextLine());
        //Xử lý ngoại lệ với throw và thows
        int result = divTwoNumbers(firstNumber, secondNumber);
        //ArithmeticException: / by zero --> chương trình dừng lại --> Runtime --> Unchecked Exception
        //--> Exception Handling: try...catch...finally
        try {
            //Khối lệnh bắt các lỗi ngoại lệ
            int div = firstNumber / secondNumber;
            System.out.printf("Thương của 2 số %d và %d là %d\n", firstNumber, secondNumber, div);
        } catch (ArithmeticException ex) {
            //Khối lệnh xử lý lỗi ngoại lệ
            System.out.println("Nội dung lỗi ngoại lệ: " + ex.getMessage());
            System.out.println("Lớp xử lý ngoại lệ + nội dung lỗi ngoại lệ: " + ex.toString());
            ex.printStackTrace();//In ra nội dung của toString() với System.err
            int div = 0;
            System.out.println("Thương của 2 số không thực hiện được do secondNumber = 0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Khối thực hiện dù có exception xảy ra hay không
            System.out.println("Chương trình kết thúc");
        }


        //Set Driver cho kết nối đến CSDL
        //ClassNotFoundException --> compile --> CheckedException --> bắt buộc phải xử lý ngoại lệ
//        try {
//            Class.forName("DRIVER CSDL");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static int divTwoNumbers(int firstNumber, int secondNumber) throws Exception {
//        if (secondNumber == 0) {
//            throw new ArithmeticException("Lỗi chia cho 0");
//        }
        int div = firstNumber / secondNumber;
        return div;
    }


}
