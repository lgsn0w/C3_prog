package C3;
import javax.swing.JOptionPane;
import java.io.*;
public class Exemplo91 {
    public static void main(String[] args) {
        try{
            String arquivo = EscolherArquivo.caminho();
            BufferedWriter saida;
            saida = new BufferedWriter(new FileWriter(arquivo,true));
            String nome = JOptionPane.showInputDialog("Digite o nome: ");
            nome = nome.toUpperCase();
            String end = JOptionPane.showInputDialog("Digite o endereço: ");
            end = end.toUpperCase();
            String tel = JOptionPane.showInputDialog("Digite o telefone: ");
            RegAgenda regAg1 = new RegAgenda(nome,end,tel);
            saida.write(regAg1.mostraNome()+"\t");
            saida.write(regAg1.mostraEnd()+"\t");
            saida.write(regAg1.mostraTel()+"\t");
            saida.flush();
            saida.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro de gravaaco");
        }
    }
}
