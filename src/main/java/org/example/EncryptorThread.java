package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;

import static org.example.GUIForm.password;

public class EncryptorThread extends Thread{
    private GUIForm form;
    private File file;
    private  ZipParameters parameters = new ZipParameters();


    public EncryptorThread (GUIForm form){
        this.form = form;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void onFinish(){
        form.setButtonsEnabled(true);
        form.showFinished();
    }
    public void onStart(){
        form.setButtonsEnabled(false);
    }

    @Override
    public void run() {
        onStart();
        encrypt();
        onFinish();
    }
    private String getArchiveName() {

        for (int i = 1; ; i++) {
            String number = i > 1 ? Integer.toString(i) : "";
            String archiveName = "";
            if (file.getAbsolutePath().matches("[a-zA-Z0-9_\\\\:]+\\.[a-z]+")){
                String[] parts = file.getAbsolutePath().split("\\.");
                archiveName = parts[0] + number + ".enc";
            } else {
            archiveName = file.getAbsolutePath() + number + ".enc";
            }
            if (!new File(archiveName).exists()) {
                return archiveName;
            }
        }
    }
    private void encrypt(){
        String archiveName = getArchiveName();
        ZipFile zipFile = new ZipFile(archiveName, password);
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        parameters.setCompressionLevel(CompressionLevel.ULTRA);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        try {
            if (file.isDirectory()) {
                zipFile.addFolder(file, parameters);
            } else {
                zipFile.addFile(file, parameters);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
