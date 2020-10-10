import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Estoque {

    private Produto[] estoque;
    private int numDeProd;

    public Estoque(int numDeProd){this.numDeProd = numDeProd;}

    //cria e atualiza o estoque
    public void criaEstoque(int tam, File f){

        this.estoque = new Produto[tam];
        int cont = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while(line != null){
                String[] array = line.split("-");
                //remove os caracteres especiais para ler o preco como double
                array [4] = array[4].replace(',','.');
                array [4] = array[4].replace('$','a');
                array [4] = array[4].replaceAll("Ra","");
                //le o arquivo e constroi um produto de acordo com sua leitura
                switch (array[1]){
                    case "Bonsai":
                        this.estoque[cont] = new Bonsai(Double.parseDouble(array[4]),Integer.parseInt(array[0]),
                                Integer.parseInt(array[3]),array[2], array[5],Integer.parseInt(array[6]));
                                break;
                    case"Insumos":
                        this.estoque[cont] = new Insumos(Double.parseDouble(array[4]),Integer.parseInt(array[0]),
                                Integer.parseInt(array[3]),array[2],array[5]);
                                break;
                    case"Vasos":
                        this.estoque[cont] = new Vasos(Double.parseDouble(array[4]),Integer.parseInt(array[0]),
                                Integer.parseInt(array[3]),array[2],Double.parseDouble(array[6]),Double.parseDouble(array[7]),
                                array[5],array[8]);
                        break;
                }
                line = br.readLine();
                cont++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //cria o produto
    public Produto criaProduto() {
        try{
        System.out.println("Qual produto você quer adicionar?\n" +
                "\t1-Bonsai\n\t2-Insumos\n\t3-Vasos");
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        while (opcao < 1 || opcao > 3) {
            String nome;
            double preco;
            int quant;
            opcao = sc.nextInt();
            //constroi o produto de acordo com a opcao selecionada
            switch (opcao) {
                case 1:
                    System.out.printf("Nome do produto:");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.printf("Preço:");
                    preco = sc.nextDouble();
                    System.out.printf("Quantidade:");
                    quant = sc.nextInt();
                    System.out.printf("Especie:");
                    sc.nextLine();
                    String especie = sc.nextLine();
                    System.out.printf("Idade:");
                    int idade = sc.nextInt();

                    return new Bonsai(preco, this.numDeProd++, quant, nome, especie, idade);

                case 2:
                    System.out.printf("Nome do produto:");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.printf("Preço:");
                    preco = sc.nextDouble();
                    System.out.printf("Quantidade:");
                    quant = sc.nextInt();
                    System.out.printf("Validade:");
                    sc.nextLine();
                    String validade = sc.nextLine();

                    return new Insumos(preco, this.numDeProd++, quant, nome, validade);

                case 3:
                    System.out.printf("Nome do produto:");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.printf("Preço:");
                    preco = sc.nextDouble();
                    System.out.printf("Quantidade:");
                    quant = sc.nextInt();
                    System.out.printf("Altura:");
                    double altura = sc.nextDouble();
                    System.out.printf("Largura:");
                    double largura = sc.nextDouble();
                    System.out.printf("Cor:");
                    sc.nextLine();
                    String cor = sc.nextLine();
                    System.out.printf("Fabricante:");
                    String fabricante = sc.nextLine();

                    return new Vasos(preco, this.numDeProd++, quant, nome, altura, largura, cor, fabricante);

                default://caso seja digitado um valor invalido, ele faz o usuario digitar novamente
                    System.out.printf("\nDigite numeros de 1 a 3:");
                    opcao = sc.nextInt();
                    break;
            }
        }
    }catch (InputMismatchException e){
            System.out.println("|Erro de digitação, reescreva o produto que você deseja adicionar");
            return this.criaProduto();
        }
        return this.criaProduto();
    }

    //adiciona um produto no arquivo txt e escreve ele
    public void add(File f, Produto p){
        try {
            try (FileWriter fw = new FileWriter(f,true)) {
                fw.write(p.toString()+"\r"); //escrita do arquivo
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //remove um produto de acordo com seu codigo
    public void removeProdCodigo(File f,int codigo){
        int posDoProd = -1;
        this.ordenaCodigo();//ordena o codigo antes de fazer a pesquisa binaria
        //pesquisa binaria
        int inf = 0;   int sup = this.estoque.length - 1;
        while (inf <= sup) {
            int med = (inf + sup) / 2;
            if (codigo == this.estoque[med].getCodigo()) {
                posDoProd = med;
                break;
            }else if (codigo < this.estoque[med].getCodigo())
                sup = med - 1;
            else
                inf = med + 1;
        }
        //caso nao ache ele usa a excecao criada
        if(posDoProd == -1){
            ImpossibleCodeException me = new ImpossibleCodeException("Codigo não encontrado");
            System.out.println(me);
        }else {
            this.estoque[posDoProd] = null;
            this.AtualizaArquivo(f);
        }
    }

    //atualiza um produto
    public void atualizaProduto(File f,int codigo){
        int posDoProd = -1;
        this.ordenaCodigo(); //ordena antes da pesquisa binaria0
        //pesquisa binaria
        int inf = 0;   int sup = this.estoque.length - 1;
        while (inf <= sup) {
            int med = (inf + sup) / 2;
            if (codigo == this.estoque[med].getCodigo()) {
                posDoProd = med;
                break;
            }else if (codigo < this.estoque[med].getCodigo())
                sup = med - 1;
            else
                inf = med + 1;
        }

        //se nao achar ele chama a excecao criada
        if(posDoProd == -1){
            ImpossibleCodeException e = new ImpossibleCodeException("Codigo não encontrado");
            System.out.println(e);
        }else {
            System.out.println("Crie um novo produto para atualizar o antigo");
            Produto p = this.criaProduto(); //cria o produto que vai ser atualizado
            this.estoque[posDoProd] = p;
            this.AtualizaArquivo(f);
        }
    }

    //Metodo auxiliar para atualizar o arquivo quando mudancas forem feitas nele
    public void AtualizaArquivo(File f){
        try {
            Writer out = null;
            out = new FileWriter(f);
            //limpa o arquivo
            out.write("");
            out.flush();
            out.close();
            //ordena o estoque pelo codigo
            this.ordenaCodigo();
            //imprime o array no arquivo em ordem de codigo
            for(int i=0;i<this.estoque.length;i++){
                if(this.estoque[i]!= null) {
                    this.add(f, this.estoque[i]);
                }
            }

        } catch (IOException e) {
                e.printStackTrace();
                }
    }

    //Imprime os produtos (metodo publico recursivo)
    public void imprimeProdutos(){
        System.out.println("+---------------------------------------------------------+");
        if(this.estoque.length == 0){System.out.println("|Estoque vazio"); // caso o estoque esteja vazio
        }else{
            this.imprimeProdutos(0);
            System.out.println("+---------------------------------------------------------+");
        }
    }

    //imprime os produtos(metodo recursivo privado)
    private void imprimeProdutos(int i){
        if(i < this.estoque.length) {
            System.out.println("|" + this.estoque[i].toString());
            imprimeProdutos(i+1);
        }
    }

    //ordena o array do estoque pelo codigo dos objetos(selecao direta)
    public void ordenaCodigo(){
        int min = 0;

        for (int i = 0; i < this.estoque.length - 1; i++) {
            //selecao direta
            min = i;
            for (int j = i + 1; j < this.estoque.length; j++)
                if (this.estoque[j] != null && this.estoque[min] != null)
                    if (this.estoque[j].getCodigo() < this.estoque[min].getCodigo())
                        min = j;
            Produto T = this.estoque[i];
            this.estoque[i] = this.estoque[min];
            this.estoque[min] = T;
        }

    }

    //ordena o array do estoque pelo preco(selecao direta)
    public void ordenaPreco() {
        //selecao direta
        int min = 0;
        for (int i = 0; i < this.estoque.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < this.estoque.length; j++)
                if (this.estoque[j] != null && this.estoque[min] != null)
                    if (this.estoque[j].getPreço() < this.estoque[min].getPreço())
                        min = j;
            Produto T = this.estoque[i];
            this.estoque[i] = this.estoque[min];
            this.estoque[min] = T;
        }
    }

    //ordena o array do estoque pelo nome(insercao direta)
    public void ordenaNome(Produto[] Array, int n) {
        for (int i = 1; i < n; ++i) {
            Produto proxNome = Array[i];
            int k = i;
            while ((k > 0) &&(Array[k-1].getNome().compareTo(proxNome.getNome()) > 0)) {
                Array[k] = Array[k-1];
                k--;
            }
            Array[k] = proxNome;
        }
    }

    //pesquisa o produto pela palavra chave digitada
    public void pesquisaProduto(String nome){
        System.out.println("|Produtos com a palavra "+ nome);
        this.ordenaNome(this.estoque,this.estoque.length);
        for(int i = 0;i <this.estoque.length;i++){
            if(this.estoque[i].getNome().contains(nome)){ //se conter a palavra ele imprime
                System.out.println(this.estoque[i]);
            }
        }
    }

    public Produto[] getEstoque() {return estoque;}
    public void setEstoque(Produto[] estoque) {this.estoque = estoque;}
}
