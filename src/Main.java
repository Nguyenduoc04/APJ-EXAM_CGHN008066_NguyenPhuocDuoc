import service.ContactService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactService service = new ContactService();

        while (true) {
            System.out.println("\n========== CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ==========");
            System.out.println("1. Xem danh sách danh bạ");
            System.out.println("2. Thêm mới danh bạ");
            System.out.println("3. Cập nhật danh bạ");
            System.out.println("4. Xóa danh bạ");
            System.out.println("5. Tìm kiếm danh bạ");
            System.out.println("6. Đọc danh bạ từ file");
            System.out.println("7. Lưu danh bạ vào file");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    service.display();
                    break;
                case "2":
                    service.add(sc);
                    break;
                case "3":
                    service.update(sc);
                    break;
                case "4":
                    service.delete(sc);
                    break;
                case "5":
                    System.out.print("Nhập số điện thoại hoặc tên cần tìm: ");
                    service.search(sc.nextLine());
                    break;
                case "6":
                    System.out.print("Đọc từ file sẽ xóa dữ liệu hiện tại. Bạn có chắc không? (Y/N): ");
                    if (sc.nextLine().equalsIgnoreCase("Y")) {
                        service.readFromFile();
                        System.out.println("Đã đọc danh bạ từ file.");
                    }
                    break;
                case "7":
                    System.out.print("Lưu file sẽ ghi đè dữ liệu cũ. Bạn có chắc không? (Y/N): ");
                    if (sc.nextLine().equalsIgnoreCase("Y")) {
                        service.saveToFile();
                        System.out.println("Đã lưu danh bạ vào file.");
                    }
                    break;
                case "8":
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }
}
