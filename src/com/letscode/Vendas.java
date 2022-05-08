package com.letscode;

import java.util.Scanner;


public class Vendas {
    public static Object [][] matrizTeste = {{1, "Marca", "tipo", "nome", 1.5, 4.5, 0, 100, "data"},
            {2, "Marca2", "tipo2", "nome2", 10.5, 20.5, 2, 200, "data2"}}; //matriz só para testar esta classe

    public static Object[][] matrizVendas = new Object[1][4];
    public static int indice =0;
    public static String [] matrizCpf = new  String[1];
    public static int indiceCpf =0;

    public static void main(String[] args) {

        vender(matrizTeste); //ignorar- era para teste
    }


    public static void vender(Object [][] produtos){
        Scanner ler = new Scanner(System.in);
        String cpf = null;
        TipoCliente tipoCliente = null;
        final String CPF_PADRAO = "000 000 001 91";

        System.out.println("Deseja inserir o CPF/CNPJ do cliente? \n1 - Sim \n2 - Nao");
        String opcao = ler.nextLine();
        switch  (opcao) {
            case "1":
                do {
                    System.out.println("Digite o CPF/CNPJ do cliente (apenas os algarismos):");
                    cpf = ler.nextLine();
                }  while((cpf.length()!=11)&&(cpf.length()!=14));

                String opcaoCliente = "";

                if (cpf.length()==11){
                    do {
                        System.out.println("Digite o tipo de cliente (1 - PF, 2- VIP):");
                        opcaoCliente = ler.nextLine();
                    } while (!opcaoCliente.equals("1") && !opcaoCliente.equals("2"));
                    switch (opcaoCliente){
                        case "1":
                           tipoCliente = TipoCliente.PF;
                           break;
                        case "2":
                            tipoCliente = TipoCliente.VIP;
                            break;
                     }
                }
                else if (cpf.length()==14){
                   do{
                       System.out.println("Digite o tipo de cliente (1 - PJ, 2- VIP):");
                       opcaoCliente = ler.nextLine();
                   } while (!opcaoCliente.equals("1") && !opcaoCliente.equals("2"));
                       switch (opcaoCliente){
                        case "1":
                            tipoCliente = TipoCliente.PJ;
                            break;
                        case "2":
                            tipoCliente = TipoCliente.VIP;
                            break;
                    }
                }
                else {
                    System.out.println("Opcao invalida, tente novamente!");
                }

                break;

            case "2":
                cpf = CPF_PADRAO;
                tipoCliente = TipoCliente.PF;
                break;
        }

        boolean comprando = true;
        String notaFiscal = "Codigo | Nome | Quantidade | Preco | ValorPagar";
        double precoTotal = 0.0;
        int quantidadeTotal = 0;

        while (comprando){
            System.out.println("Digite o identificador do produto ou FIM para finalizar a compra");
            String opcao2 = ler.next();
            if (opcao2.equalsIgnoreCase("fim")){
                comprando = false;
            } else {
            String identificador = opcao2;
            System.out.println("Digite a quantidade do produto:");
            int quantidade = ler.nextInt();
            //vasculha matriz de estoque e encontra o nome, preco e atualiza estoque
            int i = Main.procurarProduto(identificador, Main.produtos);
            String nome = (String)produtos[i][3];
            double precoUnit = (Double)produtos[i][5];
            int estoqueAtual = (Integer)produtos[i][6];
            if(quantidade>estoqueAtual){
                System.out.println("Estoque insuficiente. Estoque atual:" + estoqueAtual);
            }
            else {
                //            System.out.println("i" +i +"\n nome" +nome +"\n preco" +precoUnit +"\n estoque"+ estoqueAtual);
                double preco = quantidade * precoUnit;
                precoTotal += preco;
                quantidadeTotal += quantidade;
                Main.produtos[i][6] = (Integer)Main.produtos[i][6]-quantidade;

                String notaFiscalNova = notaFiscal + "\n" + identificador + " | " + nome + " | " + quantidade + " | "
                        + String.format("%.2f",precoUnit) + " | " + String.format("%.2f", preco);
                System.out.println(notaFiscalNova);
                notaFiscal = notaFiscalNova;
            }
            }
        }

        precoTotal = precoTotal*(1- tipoCliente.getDesconto());

        System.out.printf("Quantidade de produtos "+quantidadeTotal+"\n"+"Valor total pago %.2f", precoTotal);
        System.out.println("\nCPF/CNPJ Cliente: "+cpf+"\n"+tipoCliente.getDescricao()+"\nDesconto de: "+tipoCliente.getDesconto()*100+"% aplicado");
        adicionarVendas(cpf, tipoCliente, quantidadeTotal, precoTotal);




    }

    public static void adicionarVendas(String cpf, TipoCliente tipoCliente, Integer quantidadeTotal, Double precoTotal) {
        if (indice == matrizVendas.length){
            redimensionar();
        }
        if(precoTotal>0) {
            matrizVendas[indice][0] = cpf;
            matrizVendas[indice][1] = tipoCliente;
            matrizVendas[indice][2] = quantidadeTotal;
            matrizVendas[indice][3] = precoTotal;
            indice++;
            cadastroCpf(cpf);
        }
    }

    public static void redimensionar(){
        Object[][] newMatrix = new Object[matrizVendas.length*2][4];
        for (int i =0; i< matrizVendas.length; i++){
            newMatrix[i] = matrizVendas[i];
        }
        matrizVendas = newMatrix;
    }

    public static void redimensionarCpf(){
        String[] newMatrix = new String[matrizCpf.length*2];
        for (int i =0; i< matrizCpf.length; i++){
            newMatrix[i] = matrizCpf[i];
        }
        matrizCpf = newMatrix;
    }

    public static void cadastroCpf(String cpf){
        if (indiceCpf == matrizCpf.length){
            redimensionarCpf();
        }
        boolean cpfExiste = false;

        for(int i =0; i<matrizCpf.length; i++){
            if (matrizCpf[i]!= null){
                if(matrizCpf[i]==cpf){
                    cpfExiste = true;
                }
            }
        }
        if (!cpfExiste){
            matrizCpf[indiceCpf]=cpf;
            indiceCpf++;
        }

    }


}
