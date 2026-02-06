package util;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static final String FILE = "data/contacts.csv";

    public static List<Contact> read() {
        List<Contact> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new Contact(
                        p[0], p[1], p[2],
                        p[3], p[4], p[5], p[6]
                ));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file danh bạ.");
        }
        return list;
    }

    public static void write(List<Contact> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            bw.write("Phone,Group,Name,Gender,Address,Birthday,Email");
            bw.newLine();
            for (Contact c : list) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi file danh bạ.");
        }
    }
}
