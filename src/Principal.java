import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal{
/*
        ARQUIVO MODELO
CODIGO-TIPO-NOME-QUANTIDADE-PRECO-INFOS
    INFOS ORDEM:
        BONSAI:-ESPECIE-IDADE
        VASO:-COR-ALTURA-LARGURA-FABRICANTE
        INSUMOS:VALIDADE
*/
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        File f = new File("estoque.txt");
        Estoque e = new Estoque(contLinhas(f));
        e.criaEstoque(contLinhas(f),f);

        boolean tela = true;

            while (tela) {
                try {
                    //Impressão da tela
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|----------------------|Bonsai Shop|----------------------|");
                    System.out.println("|Digite o que deseja fazer:-------------------------------|");
                    System.out.println("|1-Conferir estoque---------------------------------------|");
                    System.out.println("|2-Adicionar produtos ao estoque--------------------------|");
                    System.out.println("|3-Remover produtos do estoque----------------------------|");
                    System.out.println("|4-Atualizar produtos do estoque--------------------------|");
                    System.out.println("|5-Pesquisar produtos por nome----------------------------|");
                    System.out.println("|Para sair, digite qualquer outro valor-------------------|");
                    System.out.println("+---------------------------------------------------------+");
                    int opcao = sc.nextInt();

                    switch (opcao) {//opcoes
                        case 1:
                            System.out.println("+---------------------------------------------------------+");
                            System.out.println("|Ordenar por:---------------------------------------------|");
                            System.out.println("|1-Codigo(padrão)-----------------------------------------|");
                            System.out.println("|2-Nome---------------------------------------------------|");
                            System.out.println("|3-Preço--------------------------------------------------|");
                            System.out.println("+---------------------------------------------------------+");
                            int subOpcao = sc.nextInt();
                            switch (subOpcao) {
                                case 1:
                                    e.ordenaCodigo(); //ordenaçao
                                    e.imprimeProdutos();//impressao
                                    break;
                                case 2:
                                    e.ordenaNome(e.getEstoque(), e.getEstoque().length);
                                    e.imprimeProdutos();
                                    break;
                                case 3:
                                    e.ordenaPreco();
                                    e.imprimeProdutos();
                                    break;
                                default:
                                    System.out.println("+Opcão invalida, voltando para o menu---------------------+");
                                    break;

                            }
                            break;
                        case 2:
                            e.add(f, e.criaProduto());//cria e adiicona o produto
                            e.criaEstoque(contLinhas(f), f);//atualiza o estoque
                            break;
                        case 3:
                            System.out.println("|Digite o codigo do produto que deseja remover:-----------|");
                            e.removeProdCodigo(f, sc.nextInt()); //remove o produto
                            e.criaEstoque(contLinhas(f), f);//atualiza o estoque
                            break;
                        case 4:
                            System.out.println("|Digite o codigo do produto que deseja substituir---------|");
                            e.atualizaProduto(f, sc.nextInt());//atualiza o produto desejado
                            break;

                        case 5:
                            System.out.println("|Digite a palavra chave para pesquisar-------------------+");
                            sc.nextLine();
                            String aaa = sc.nextLine();
                            e.pesquisaProduto(aaa);
                            break;
                        default:
                            System.out.println("+Saindo do programa, obrigado por usar--------------------+");
                            break;
                }
            }catch (InputMismatchException a){
                    System.out.println("+Saindo do programa, obrigado por usar--------------------+");
                    tela = false;
                }
        }

    }

    //conta a quantidade de linhas do arquivo
    public static int contLinhas(File f){
        int cont = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line = br.readLine();
            while(line != null){
                cont++;
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }
}
