public class Insumos extends Produto {
    //atributos
    private String validade;
    private String tipo;

    //construtor
    public Insumos(double preço, int codigo, int quantidade, String nome, String validade) {
        super(preço, codigo, quantidade, nome);
        this.validade = validade;
        this.tipo =(this.getClass().toString().replaceAll("class ",""));
    }

    //getter e setter
    public String getValidade() {return validade;}
    public void setValidade(String validade) {this.validade = validade;}

    //toString
    @Override
    public String toString() {
        return (this.codigo+"-"+this.tipo +"-"+this.nome+"-"+this.quantidade+String.format("-R$%.2f",this.preço)+"-"+this.validade);
    }


}
