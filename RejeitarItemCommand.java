
public class RejeitarItemCommand implements Command {
    private ItemCatalogo item;
    private String motivo;

    public RejeitarItemCommand(ItemCatalogo item, String motivo) {
        this.item = item;
        this.motivo = motivo;
    }

    @Override
    public void executar() {
        this.item.rejeitar(motivo);
    }
}