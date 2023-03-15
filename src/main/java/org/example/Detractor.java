package org.example;

import net.lingala.zip4j.ZipFile;

import static org.example.Main.fileName;
import static org.example.Main.password;

public class Detractor {

    public static void unZip(String path, String fileName){
        ZipFile zipFile = new ZipFile(path + fileName);
        try {
            if (zipFile.isEncrypted()){
                zipFile.setPassword(password);
            }
            zipFile.extractAll(path + "extracted\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
