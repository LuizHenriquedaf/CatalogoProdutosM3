

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA INTERNO DE COMPLIANCE - PORTAL ÚNICO ===");

        
        ItemCatalogo produto = new ItemCatalogo("PROD-8517", "Roteador Wireless Industrial", "8517.62.77");
        System.out.println("Item criado: " + produto.getNome() + " | Status Atual: " + produto.getStatus());

        
        GerenciadorWorkflow motorWorkflow = new GerenciadorWorkflow();

        Command acao1_submeter = new SubmeterRevisaoCommand(produto);
        Command acao2_aprovar = new AprovarComplianceCommand(produto);

        System.out.println("\n--- Iniciando Trâmite de Aprovação ---");
        
        System.out.println("[Setor Compras]: Enviando para validação fiscal...");
        motorWorkflow.dispararAcao(acao1_submeter);

        System.out.println("[Setor Comex]: Validando NCM e Atributos aduaneiros...");
        motorWorkflow.dispararAcao(acao2_aprovar);

        System.out.println("\n--- Resumo de Auditoria Interna ---");
        System.out.println("Status Final do Item: " + produto.getStatus());
        System.out.println("Total de ações registradas no Log: " + motorWorkflow.getQuantidadeAcoesExecutadas());
        System.out.println("====================================================");
    }
}