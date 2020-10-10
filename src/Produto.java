public abstract class Produto {

    //atributos
    protected double preço;
    protected int codigo;
    protected int quantidade;
    protected String nome;
    protected String tipo;

    //construtor
    public Produto(double preço, int codigo, int quantidade, String nome) {
        this.preço = preço;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipo = this.getClass().toString();
    }

    //getters e setters
    public double getPreço(){return preço;}
    public void setPreço(double preço) {this.preço = preço;}
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}





}
