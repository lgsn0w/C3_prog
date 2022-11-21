package InsercaoSequencial;

public class DB {
    private String nome;
    private String nome_jogo;
    private String ano;

///Constructor
    public DB(String nome, String nome_jogo, String ano) {
        this.nome = nome;
        this.nome_jogo = nome_jogo;
        this.ano = ano;
    }
///Functions
    public String mostraNome(){
        return nome;
    }
    public String mostraNomeJogo(){
        return nome_jogo;
    }
    public String mostraAno(){
        return ano;
    }

    public void alteraNomeJogo(String nomeJogo){
        this.nome_jogo = nomeJogo;
    }
    public void alterarAno(String ano){
        this.ano = ano;
    }
    public void alterarNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "RegAgenda{" +
                "nome='" + nome + '\'' +
                ", end='" + nome_jogo + '\'' +
                ", tel='" + ano + '\'' +
                '}';
    }
}
