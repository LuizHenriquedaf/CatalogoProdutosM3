
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorWorkflow motorWorkflow = new GerenciadorWorkflow();

        System.out.println("====================================================");
        System.out.println("   SISTEMA DE COMPLIANCE ADUANEIRA - PORTAL ÚNICO   ");
        System.out.println("====================================================");

        System.out.println("[SETOR DE COMPRAS] Insira os dados brutos da mercadoria:");
        System.out.print("Digite o ID do produto (ex: PROD-1020): ");
        String id = scanner.nextLine();

        System.out.print("Digite o Nome/Descrição do produto: ");
        String nome = scanner.nextLine();


        String ncm = "";
        boolean ncmValida = false;

        while (!ncmValida) {
            System.out.print("Digite a NCM sugerida (apenas 8 números, ex: 85176277 ou 8517.62.77): ");
            ncm = scanner.nextLine();

            if (validarEstruturaNcm(ncm)) {
                ncmValida = true;
            } else {
                System.out.println("\n[ALERTA DE COMPLIANCE] NCM inválida! A NCM precisa conter exatamente 8 dígitos numéricos.");
                System.out.println("Por favor, verifique a tabela TEC/TIPI e tente novamente.\n");
            }
        }


        ItemCatalogo produto = new ItemCatalogo(id, nome, ncm);
        
        System.out.println("\n[SUCESSO] Produto salvo internamente como 'Rascunho Comercial'.");

        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n====================================================");
            System.out.println("      PAINEL DE VALIDAÇÃO DO ANALISTA DE COMEX      ");
            System.out.println("====================================================");
            System.out.println(" > ID do Item:        " + produto.getId());
            System.out.println(" > Descrição/Nome:    " + produto.getNome());
            System.out.println(" > NCM para Análise:  " + produto.getNcm());
            System.out.println(" > STATUS ATUAL:      [" + produto.getStatus() + "]");
            System.out.println("====================================================");
            
            System.out.println("Escolha a ação aduaneira para o fluxo:");
            System.out.println("1 - Submeter para Revisão de Compliance");
            System.out.println("2 - Aprovar Classificação Fiscal (NCM Correta)");
            System.out.println("3 - Rejeitar Cadastro (Necessita Ajustes de Compras)");
            System.out.println("4 - Finalizar Processo e Emitir Log");
            System.out.print("Escolha a ação desejada (1-4): ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n-> [ERRO] Por favor, digite um número de 1 a 4.");
                continue;
            }

            System.out.println("\n----------------------------------------------------");
            switch (opcao) {
                case 1:
                    Command submeter = new SubmeterRevisaoCommand(produto);
                    System.out.println("[Executando] Disparando SubmeterRevisaoCommand...");
                    motorWorkflow.dispararAcao(submeter);
                    break;

                case 2:
                    Command aprovar = new AprovarComplianceCommand(produto);
                    System.out.println("[Executando] Disparando AprovarComplianceCommand...");
                    motorWorkflow.dispararAcao(aprovar);
                    break;

                case 3:
                    System.out.print("Digite a divergência fiscal encontrada (Motivo): ");
                    String motivo = scanner.nextLine();
                    
                    Command rejeitar = new RejeitarItemCommand(produto, motivo);
                    System.out.println("[Executando] Disparando RejeitarItemCommand...");
                    motorWorkflow.dispararAcao(rejeitar);
                    break;

                case 4:
                    System.out.println("Encerrando painel de controle...");
                    break;

                default:
                    System.out.println("-> [ERRO] Opção inválida!");
                    break;
            }
            System.out.println("----------------------------------------------------");
        }


        System.out.println("\n====================================================");
        System.out.println("          RELATÓRIO DE AUDITORIA FINAL              ");
        System.out.println("====================================================");
        System.out.println("Produto:           " + produto.getId() + " - " + produto.getNome());
        System.out.println("NCM Final:         " + produto.getNcm());
        System.out.println("Status Adquirido:  " + produto.getStatus());
        System.out.println("Ações Rastreadas:  " + motorWorkflow.getQuantidadeAcoesExecutadas() + " operação(ões) registrada(s).");
        System.out.println("====================================================");
        
        scanner.close();
    }

    
    private static boolean validarEstruturaNcm(String ncmEntrada) {
        if (ncmEntrada == null) return false;
        
        
        String ncmApenasNumeros = ncmEntrada.replace(".", "").trim();

        return ncmApenasNumeros.matches("\\d{8}");
    }
}
