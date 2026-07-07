
public class SubmeterRevisaoCommand implements Command {
    private ItemCatalogo item;

    public SubmeterRevisaoCommand(ItemCatalogo item) {
        this.item = item;
    }

    @Override
    public void executar() {
        this.item.alterarParaRevisao();
    }
}