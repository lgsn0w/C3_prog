package InsercaoSequencial;
import javax.swing.JOptionPane;
import java.io.*;
public class Principal {
    static StringBuffer memoria = new StringBuffer();
    static String arquivo = EscolherArquivo.caminho();
    public static void main(String[] args) {
        do {
            int menu = Integer.parseInt(JOptionPane.showInputDialog("""
                    1- Cadastrar um personagem
                    2- Consulta
                    3- Alterar dados
                    4- Excluir dados
                    5- Sair"""));
            switch (menu) {
                case 1 -> cadastro();
                case 2 -> consulta();
                case 3 -> alterarDados();
                case 4 -> excluirDados();
                case 5 -> System.exit(0);
            }
        } while (true);
    }

    static void cadastro() {
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo, true));///tells BFW to write to a FILE(usando caminho)
            String nome = JOptionPane.showInputDialog("Digite o nome: ");
            nome = nome.toUpperCase();
            String nomeJogo = JOptionPane.showInputDialog("Digite o nome do jogo de origem: ");
            nomeJogo = nomeJogo.toUpperCase();
            String ano = JOptionPane.showInputDialog("Digite o ano de criação: ");
            DB passagem = new DB(nome, nomeJogo, ano);///passagem de dados pro DB
            saida.write(nome + "\t");
            saida.write(nomeJogo + "\t");
            saida.write(ano + "\t\n");///writes the data
            saida.flush();///limpa os dados no BFW
            saida.close();///Fecha o BFW
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de gravaaco");
        }
    }
    static void consulta() {
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(arquivo));
            String nome = JOptionPane.showInputDialog("Nome do personagem: ");
            nome = nome.toUpperCase();
            String linha;
            String nomeJogo ="";
            String ano= "";
            while ((linha = entrada.readLine()) != null){///enquanto a linha tiver dados, pula
                memoria.append(linha+ "\r\n");
            }
            int inicio = memoria.indexOf(nome);
            if (inicio != -1) {
                int ultimo = memoria.indexOf("\t", inicio);
                nome = ler(inicio, ultimo);
                int primeiro = ultimo + 1;
                ultimo = memoria.indexOf("\t", primeiro);
                nomeJogo = ler(primeiro, ultimo);
                primeiro = ultimo + 1;
                int fim = memoria.indexOf("\n", primeiro);
                ano = ler(primeiro, fim);
                DB dados = new DB(nome, nomeJogo, ano);
                JOptionPane.showMessageDialog(null, dados.mostraNomeJogo() + "\n" + dados.mostraAno());
            }else {
                JOptionPane.showMessageDialog(null,"Não cadastrado");
            }
            entrada.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro leitura");
        }
    }
    static void alterarDados(){
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(arquivo));
            String nome = JOptionPane.showInputDialog("Nome:").toUpperCase();
            String linha;
            String nomeJogo="";
            String ano="";
            while ((linha = entrada.readLine()) != null){
                memoria.append(linha + "\r\n");
            }
            int inicio = memoria.indexOf(nome);
            if(inicio != -1){
                int ultimo = memoria.indexOf("\t", inicio);
                nome = ler(inicio,ultimo);
                int primeiro = ultimo+1;
                ultimo = memoria.indexOf("\t",primeiro);
                nomeJogo = ler(primeiro,ultimo);
                primeiro = ultimo+1;
                int fim = memoria.indexOf("\n", primeiro);
                ano = ler(primeiro,ultimo);
                DB dados = new DB(nome,nomeJogo,ano);
                JOptionPane.showMessageDialog(null, dados.mostraNomeJogo()+"\n"+dados.mostraAno());
                nomeJogo= JOptionPane.showInputDialog("Novo nome do jogo: ").toUpperCase();
                dados.alteraNomeJogo(nomeJogo);
                ano = JOptionPane.showInputDialog("Novo ano: ").toUpperCase();
                dados.alterarAno(ano);
                memoria.replace(inicio, fim+1, dados.mostraNome()+"\t"+dados.mostraNomeJogo()+ "\t"+dados.mostraAno()
                +"\r\n");
                gravar();
                JOptionPane.showMessageDialog(null,"Cadastro alterado");
            }else {
                JOptionPane.showMessageDialog(null, "Nao cadastrado");
            }
            entrada.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"erro");
        }
    }
    public static String ler(int primeiro, int ultimo) {
        String dados;
        dados = memoria.substring(primeiro, ultimo);
        return dados;

    }
    static  void gravar(){
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo));
            saida.write(memoria.toString());
            saida.flush();
            saida.close();
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Erro de gravação");
        }
    }
    public static void excluirDados(){
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(arquivo));
            String nome = JOptionPane.showInputDialog("Nome: ").toUpperCase();
            String linha;
            String nomeJogo;
            String ano;
            while ((linha = entrada.readLine()) != null){
                memoria.append(linha+"\r\n");
            }
            int inicio = memoria.indexOf(nome);
            if (inicio != -1){
                int ultimo = memoria.indexOf("\t",inicio);
                nome = ler(inicio,ultimo);
                int primeiro = ultimo +1;
                ultimo = memoria.indexOf("\t", primeiro);
                nomeJogo = ler(inicio,ultimo);
                primeiro = ultimo+1;
                int fim = memoria.indexOf("\n",primeiro);
                ano = ler(primeiro,ultimo);
                DB dados = new DB(nome,nomeJogo,ano);
                int resposta = JOptionPane.showConfirmDialog(null,"Deseja excluir?"+"\n"+dados.mostraNome()
                +"\n"+dados.mostraNomeJogo()+"\n"+dados.mostraAno());
                if (resposta == 0){
                    memoria.delete(inicio,fim+1);
                    gravar();
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Nao cadastrado");
            }
            entrada.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro");
        }
    }
}


