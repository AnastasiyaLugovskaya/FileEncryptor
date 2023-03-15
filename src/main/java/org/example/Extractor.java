package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;

import static org.example.Main.*;

public class Extractor {
    private static String archiveName;
    private static String originName;
    public static void zip(char [] password, String archiveName, String originName) {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        parameters.setCompressionLevel(CompressionLevel.ULTRA);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        try {
            ZipFile zipFile = new ZipFile(path + archiveName, password);
            zipFile.addFolder(new File(path + originName), parameters);
            for (FileHeader fileH : zipFile.getFileHeaders()) {
                fileNames.add(fileH.getFileName());
            }
            fileName = zipFile.getFile().getName();
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }



    public char[] getPassword() {
        return password;
    }

    public Extractor(String archiveName, String originName ) {
        this.archiveName = archiveName;
        this.originName = originName;

    }
}
