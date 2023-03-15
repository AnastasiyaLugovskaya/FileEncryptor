package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class GUIForm {
    private JPanel rootPanel;
    private JTextField filePath;
    private JButton button;
    private File selectedFile;

    public GUIForm(){
        button.addActionListener(new Action() {
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
                filePath.setText(selectedFile.getAbsolutePath());
            }
        });
    }
     public JPanel getRootPanel(){
         return rootPanel;
     }
}
