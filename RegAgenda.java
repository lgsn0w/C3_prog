package C3;

public class RegAgenda {
    private String nome;
    private String end;
    private String tel;

///Constructor
    public RegAgenda(String nome, String end, String tel) {
        this.nome = nome;
        this.end = end;
        this.tel = tel;
    }
///Functions
    public String mostraNome(){
        return nome;
    }
    public String mostraEnd(){
        return end;
    }
    public String mostraTel(){
        return tel;
    }
}
