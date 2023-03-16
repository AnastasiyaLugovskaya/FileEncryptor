package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class GUIForm {
    private JPanel rootPanel;
    private JTextField filePath;
    private JButton selectButton;
    private JButton actionButton;
    private File selectedFile;
    private boolean encryptedFileSelected = false;
    private String decryptAction = "Расшифровать";
    private String encryptAction = "Зашифровать";
    private ZipParameters parameters;
    static char[] password ;

    public GUIForm() {
//        parameters = new ZipParameters();
//        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
//        parameters.setCompressionLevel(CompressionLevel.ULTRA);
//        parameters.setEncryptFiles(true);
//        parameters.setEncryptionMethod(EncryptionMethod.AES);
//        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        selectButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showOpenDialog(rootPanel);
                selectedFile = chooser.getSelectedFile();
                if (selectedFile == null) {
                    filePath.setText("");
                    actionButton.setVisible(false);
                    return;
                }
                filePath.setText(selectedFile.getAbsolutePath());
                try {
                    ZipFile zipFile = new ZipFile(selectedFile);
                    encryptedFileSelected = zipFile.isValidZipFile() && zipFile.isEncrypted();
                    actionButton.setText(encryptedFileSelected ?
                            decryptAction : encryptAction);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                actionButton.setVisible(true);
            }
        });
        actionButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile == null) {
                    return;
                }

                password =  JOptionPane.showInputDialog("Введите пароль: ").toCharArray();
                if (encryptedFileSelected) {
                    decryptFile(password);
                } else {
                    encryptFile(password);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setButtonsEnabled(boolean enabled) {
        selectButton.setEnabled(enabled);
        actionButton.setEnabled(enabled);
    }

    private void encryptFile(char[] password) {
        EncryptorThread encryptorThread = new EncryptorThread(this);
        encryptorThread.setFile(selectedFile);
        encryptorThread.start();
    }

    private void decryptFile(char[] password) {
        DecryptorThread decryptorThread = new DecryptorThread(this);
        decryptorThread.setFile(selectedFile);
        decryptorThread.start();
    }


}
