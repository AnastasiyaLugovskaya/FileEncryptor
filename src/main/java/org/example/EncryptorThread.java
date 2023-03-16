package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;

import static org.example.GUIForm.password;

public class EncryptorThread extends Thread{
    private GUIForm form;
    private File file;
    public EncryptorThread (GUIForm form){
        this.form = form;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void onFinish(){
        form.setButtonsEnabled(true);
    }
    public void onStart(){
        form.setButtonsEnabled(false);
    }

    @Override
    public void run() {
        onStart();
        String archiveName = getArchiveName();
        try {
            ZipFile zipFile = new ZipFile(archiveName, password);
            if (file.isDirectory()) {
                zipFile.addFolder(file, ParametersContainer.getParameters());
            } else {
                zipFile.addFile(file, ParametersContainer.getParameters());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        onFinish();
    }
    private String getArchiveName() {

        for (int i = 1; ; i++) {
            String number = i > 1 ? Integer.toString(i) : "";
            String archiveName = file.getAbsolutePath() + number + ".enc";
            if (!new File(archiveName).exists()) {
                return archiveName;
            }
        }
    }


}
