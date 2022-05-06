package com.letscode;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    private static Object[][] produtos = new Object[5][9];
    private static int indice = 0;

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        String id; //arroz123
        String marca; //tiojoao
        TipoProduto tipo; //ALM
        String nome; //Arroz branco Tio Joao
        Double custo; //2 pila
        Double preco; //custo*markup
        Integer estoque; //estoque = estoque + quantidade
        Integer quantidade; //200
        LocalDateTime dataCompra; //datetime now

        Object produto[] = new Object[9];
        produto[6] = (Integer)0;

        //Object[][] produtos = new Object[20][9];

        System.out.println("Bem vindo ao mercadinho!");
        String opcao;
        do{
            System.out.println("Selecione a opção desejada:");
            System.out.println("1 - Cadastrar/Comprar produtos \n2 - Imprimir estoque \n3 - Listar os produto pelo Tipo \n0 - Sair");
            opcao = ler.nextLine();

            switch  (opcao) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Digite o identificador do produto:");
                    produto[0] = ler.nextLine();
                    System.out.println("Digite a marca do produto:");
                    produto[1] = ler.nextLine();
                    System.out.println("Digite o tipo do produto (ALM, BEB, HIG):");
                    produto[2] = TipoProduto.valueOf(ler.nextLine());
                    System.out.println("Digite o nome do produto:");
                    produto[3] = ler.nextLine();
                    System.out.println("Digite o custo do produto:");
                    produto[4] = Double.parseDouble(ler.nextLine());
                    System.out.println("Digite a quantidade do produto:");
                    produto[7] = Integer.parseInt(ler.nextLine());

                    tipo = (TipoProduto)produto[2];
                    produto[5] = (Double)produto[4] * tipo.getMarkup();
                    produto[6] = (Integer)produto[7] + (Integer)produto[6];
                    produto[8] = LocalDateTime.now();

                    int verif = verificarProduto(produto);
                    if (verif == -1){
                        cadastrarProduto(produto);
                    }else{
                        comprarProduto(produto, verif);
                    }
                    break;
                case "2":
                    System.out.println("Estoque atual:");
                    System.out.println("IDENTIFICADOR  --  MARCA  --  ESTOQUE");
                    for (int i = 0; i < produtos.length; i++) {
                    if (produtos[i][0]  != null){
                        System.out.printf((String)produtos[i][0]+"  --  "
                                        +(String)produtos[i][1]+"  --  "
                                        +"%d \n"
                                ,(Integer)produtos[i][6]);
                    }
                }
                    break;
                case "3":
                    System.out.println("Produtos por tipo:");
                    for (TipoProduto tipo2: TipoProduto.values()) {
                        System.out.println(tipo2.getDescricao()+":");
                        for (int i = 0; i < produtos.length; i++) {
                            if (produtos[i][0] != null) {
                                if ((TipoProduto) produtos[i][2] == tipo2) {
                                    System.out.printf((String) produtos[i][0] + "  --  "
                                                    + (String) produtos[i][1] + "  --  "
                                                    + "%d \n"
                                            , (Integer) produtos[i][6]);
                                }
                            }
                        }
                    }

                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente!");
            }
        }while (!opcao.equals(""));


    }


    public static void cadastrarProduto(Object[] produto){
        if (indice == produtos.length){
            redimensionar();
        }
        for (int i = 0; i < produto.length ; i++){
            produtos[indice][i] = produto[i];
        }
        indice++;
        System.out.println("Novo produto cadastrado!");
    }

    public static void comprarProduto(Object[] produto, int posicao){
        for (int i = 2; i < produto.length ; i++){
            if (i==6){
                produtos[posicao][i] = (Integer)produto[i] + (Integer)produtos[posicao][i];
            }else{
                produtos[posicao][i] = produto[i];
            }
        }
        System.out.println("Produto comprado!");
    }

    public static int verificarProduto(Object[] produto){
        for (int i = 0; i < produtos.length ; i++){
            if (produtos[i][0] == null){
                continue;
            }
            if ((produtos[i][0]+produtos[i][1].toString()).equals(produto[0].toString()+produto[1].toString())){
                return i;
            }
        }
        return -1;
    }

    public static void redimensionar(){
        Object[][] newMatrix = new Object[produtos.length*2][9];
        for (int i =0; i< produtos.length; i++){
            newMatrix[i] = produtos[i];
        }
        produtos = newMatrix;
    }

    public static int procurarProduto(String identificador, Object[][] produtos){
        for (int i = 0; i < produtos.length; i++){
            if ((produtos[i][0].toString()).equals(identificador)){
                return i;
            }
        }
        return -1;
    }
}
