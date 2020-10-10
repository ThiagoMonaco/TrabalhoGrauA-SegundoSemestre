
public class Bonsai extends Produto {
    //atributos
    private String especie;
    private int idade;

    //construtor
    public Bonsai(double preço, int codigo, int quantidade, String nome, String especie, int idade) {
        super(preço, codigo, quantidade, nome);
        this.especie = especie;
        this.idade = idade;
        this.tipo = (this.getClass().toString().replaceAll("class ",""));
    }

    //getters e setters
    public String getEspecie() {return especie;}
    public void setEspecie(String especie) {this.especie = especie;}
    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    //toString
    @Override
    public String toString() {
        return (this.codigo+"-"+this.tipo +"-"+this.nome+"-"+this.quantidade+String.format("-R$%.2f",this.preço)+"-"+
                this.especie+"-"+this.idade);
    }
}
