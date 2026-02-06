package service;

import exception.InvalidDataException;
import model.Contact;
import util.FileUtil;
import validation.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    private Contact findByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone))
                return c;
        }
        return null;
    }

    // Hiển thị 5 danh bạ / lần
    public void display() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống.");
            return;
        }

        System.out.println("SĐT          | NHÓM       | HỌ TÊN              | GT     | ĐỊA CHỈ        | NGÀY SINH  | EMAIL");
        Scanner sc = new Scanner(System.in);
        int count = 0;
        int size = contacts.size();

        for (Contact c : contacts) {
            System.out.println(c.display());
            count++;

            if (count % 5 == 0 && count < size) {
                System.out.print("Nhấn Enter để xem tiếp...");
                sc.nextLine();
            }
        }
    }

    // Thêm mới
    public void add(Scanner sc) {
        try {
            System.out.print("Nhập số điện thoại: ");
            String phone = InputValidator.validatePhone(sc.nextLine());
            if (findByPhone(phone) != null) {
                System.out.println("Số điện thoại đã tồn tại!");
                return;
            }

            System.out.print("Nhập nhóm danh bạ: ");
            String group = InputValidator.required(sc.nextLine(), "Nhóm");

            System.out.print("Nhập họ tên: ");
            String name = InputValidator.required(sc.nextLine(), "Họ tên");

            System.out.print("Nhập giới tính: ");
            String gender = InputValidator.required(sc.nextLine(), "Giới tính");

            System.out.print("Nhập địa chỉ: ");
            String address = InputValidator.required(sc.nextLine(), "Địa chỉ");

            System.out.print("Nhập ngày sinh: ");
            String birthday = InputValidator.required(sc.nextLine(), "Ngày sinh");

            System.out.print("Nhập email: ");
            String email = InputValidator.validateEmail(sc.nextLine());

            contacts.add(new Contact(
                    phone, group, name, gender,
                    address, birthday, email
            ));
            System.out.println("Thêm danh bạ thành công!");

        } catch (InvalidDataException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Cập nhật
    public void update(Scanner sc) {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phone = sc.nextLine();
        if (phone.isEmpty()) return;

        Contact c = findByPhone(phone);
        if (c == null) {
            System.out.println("Không tìm thấy danh bạ với số điện thoại này.");
            return;
        }

        try {
            System.out.print("Nhập nhóm mới: ");
            c.setGroup(InputValidator.validateGroup(sc.nextLine()));

            System.out.print("Nhập họ tên mới: ");
            c.setName(InputValidator.validateName(sc.nextLine()));

            System.out.print("Nhập giới tính mới: ");
            c.setGender(InputValidator.required(sc.nextLine(), "Giới tính"));

            System.out.print("Nhập địa chỉ mới: ");
            c.setAddress(InputValidator.validateAddress(sc.nextLine()));

            System.out.print("Nhập ngày sinh mới: ");
            c.setBirthday(InputValidator.validateBirthday(sc.nextLine()));

            System.out.print("Nhập email mới: ");
            c.setEmail(InputValidator.validateEmail(sc.nextLine()));

            System.out.println("Cập nhật danh bạ thành công!");
        } catch (InvalidDataException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Xóa
    public void delete(Scanner sc) {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phone = sc.nextLine();
        if (phone.isEmpty()) return;

        Contact c = findByPhone(phone);
        if (c == null) {
            System.out.println("Không tìm thấy danh bạ.");
            return;
        }

        System.out.print("Bạn có chắc chắn muốn xóa? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            contacts.remove(c);
            System.out.println("Xóa danh bạ thành công!");
        } else {
            System.out.println("Đã hủy thao tác xóa.");
        }
    }

    // Tìm kiếm gần đúng
    public void search(String key) {
        key = key.toLowerCase();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.getPhone().contains(key) ||
                    c.getName().toLowerCase().contains(key)) {
                System.out.println(c.display());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy danh bạ phù hợp.");
        }
    }

    // Đọc file
    public void readFromFile() {
        contacts.clear();
        contacts.addAll(FileUtil.read());
    }

    // Lưu file
    public void saveToFile() {
        FileUtil.write(contacts);
    }
}
