package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.Detractor.unZip;
import static org.example.Extractor.zip;


public class Main {
    static String path = "C:\\Users\\nasta\\Desktop\\";
    static String fileName;
    static char[] password = "1234".toCharArray();
    static List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("File Encryptor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        GUIForm form = new GUIForm();
        frame.add(form.getRootPanel());
        frame.setVisible(true);


//        zip(password, "archive.zip", "Folder");
//        unZip(path, "archive.zip");
    }

}
