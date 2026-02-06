package validation;

import exception.InvalidDataException;

public class InputValidator {

    public static String required(String value, String field)
            throws InvalidDataException {
        if (value == null || value.trim().isEmpty())
            throw new InvalidDataException(field + " is required");
        return value.trim();
    }
    public static String validateGroup(String group)
            throws InvalidDataException {
        group = required(group, "Nhóm");
        if (group.length() >= 10)
            throw new InvalidDataException("Nhóm phải nhỏ hơn 10 ký tự");
        return group;
    }

    public static String validateName(String name)
            throws InvalidDataException {
        name = required(name, "Họ tên");
        if (name.length() >= 30)
            throw new InvalidDataException("Họ tên phải nhỏ hơn 30 ký tự");
        return name;
    }

    public static String validateAddress(String address)
            throws InvalidDataException {
        address = required(address, "Địa chỉ");
        if (address.length() >= 40)
            throw new InvalidDataException("Địa chỉ phải nhỏ hơn 40 ký tự");
        return address;
    }

    public static String validateBirthday(String birthday)
            throws InvalidDataException {
        birthday = required(birthday, "Ngày sinh");
        if (!birthday.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new InvalidDataException("Ngày sinh phải theo dạng yyyy-mm-dd");
        return birthday;
    }

    public static String validatePhone(String phone)
            throws InvalidDataException {
        if (!phone.matches("0\\d{9}"))
            throw new InvalidDataException("Phone must be 10 digits (start with 0)");
        return phone;
    }

    public static String validateEmail(String email)
            throws InvalidDataException {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new InvalidDataException("Invalid email format");
        return email;
    }

}
