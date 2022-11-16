package C3;

import javax.swing.*;
import java.io.File;

public class EscolherArquivo {
    public static String caminho(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        File arquivo = fileChooser.getSelectedFile();
        if(arquivo == null || arquivo.getName().equals("")) {
            JOptionPane.showMessageDialog(null, "Nome do arquivo invalido");
        }
        else
            return arquivo.getPath();
        return null;
    }
}
