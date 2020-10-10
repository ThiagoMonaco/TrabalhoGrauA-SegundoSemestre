public class Vasos extends Produto {

    //atributos
    private double altura;
    private double largura;
    private String cor;
    private String fabricante;

    //construtor
    public Vasos(double preço, int codigo, int quantidade, String nome, double altura, double largura, String cor, String fabricante) {
        super(preço, codigo, quantidade, nome);
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
        this.fabricante = fabricante;
        this.tipo =(this.getClass().toString().replaceAll("class ",""));
    }

    //getters e setters
    public double getAltura() {return altura;}
    public void setAltura(double altura) {this.altura = altura;}
    public double getLargura() {return largura;}
    public void setLargura(double largura) {this.largura = largura;}
    public String getCor() {return cor;}
    public void setCor(String cor) {this.cor = cor;}
    public String getFabricante() {return fabricante;}
    public void setFabricante(String fabricante) {this.fabricante = fabricante;}

    //toString
    @Override
    public String toString() {
        return (this.codigo+"-"+this.tipo +"-"+this.nome+"-"+this.quantidade+String.format("-R$%.2f",this.preço)+"-"+
                this.cor+"-"+this.altura+"-"+this.largura+"-"+this.fabricante);
    }


}
