
public class AprovarComplianceCommand implements Command {
    private ItemCatalogo item;

    public AprovarComplianceCommand(ItemCatalogo item) {
        this.item = item;
    }

    @Override
    public void executar() {
        this.item.aprovar();
    }
}