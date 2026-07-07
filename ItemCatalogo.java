public class ItemCatalogo {
    private String id;
    private String nome;
    private String ncm;
    private String status;

    public ItemCatalogo(String id, String nome, String ncm) {
        this.id = id;
        this.nome = nome;
        this.ncm = ncm;
        this.status = "Rascunho Comercial";
    }

    public void alterarParaRevisao() {
        this.status = "Em Revisão de Compliance";
        System.out.println("-> [PRODUTO " + id + "] Status alterado para: " + status);
    }

    public void aprovar() {
        if (this.status.equals("Em Revisão de Compliance")) {
            this.status = "Aprovado - Pronto para Envio Pucomex";
            System.out.println("-> [PRODUTO " + id + "] Status alterado para: " + status);
        } else {
            System.out.println("-> [ERRO] O item " + id + " precisa estar 'Em Revisão' antes de ser aprovado.");
        }
    }

    public void rejeitar(String motivo) {
        this.status = "Rejeitado - Necessita Ajustes";
        System.out.println("-> [PRODUTO " + id + "] Status alterado para: " + status + " (Motivo: " + motivo + ")");
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getNcm() { return ncm; }
    public String getStatus() { return status; }
}
