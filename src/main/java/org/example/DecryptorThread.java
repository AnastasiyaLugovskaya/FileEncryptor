package org.example;
import net.lingala.zip4j.ZipFile;
import javax.swing.*;
import java.io.File;
import static org.example.GUIForm.password;


public class DecryptorThread extends Thread {
    private GUIForm form;
    private File file;


    public DecryptorThread(GUIForm form) {
        this.form = form;
    }

    @Override
    public void run() {
        String outPath = getOutputPath();
        try {
            ZipFile zipFile = new ZipFile(file, password);
            zipFile.extractAll(outPath);
        } catch (Exception ex) {
            if (ex.getMessage().contains("Wrong Password")) {
                setWrongPasswordWarning();
            } else if (ex.getMessage().contains("empty or null password provided")) {
                setEmptyPasswordWarning();
            } else {
                ex.printStackTrace();
            }
        }
    }

    public void setWrongPasswordWarning() {
        JOptionPane.showMessageDialog(form.getRootPanel(), "Пароль указан неверно!",
                "Ошибка", JOptionPane.WARNING_MESSAGE);
    }

    public void setEmptyPasswordWarning() {
        JOptionPane.showMessageDialog(form.getRootPanel(), "Пароль не указан!",
                "Ошибка", JOptionPane.WARNING_MESSAGE);
    }

    private String getOutputPath() {
        String path = file.getAbsolutePath().replaceAll("\\.enc$", "");
        for (int i = 1; ; i++) {
            String number = i > 1 ? Integer.toString(i) : "";
            String outPath = path + number;
            if (!new File(outPath).exists()) {
                return outPath;
            }
        }
    }

    public void setFile(File file) {
        this.file = file;
    }
}
