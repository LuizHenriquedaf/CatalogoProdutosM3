
import java.util.ArrayList;
import java.util.List;

public class GerenciadorWorkflow {
    
    private List<Command> logAuditoria = new ArrayList<>();

    public void dispararAcao(Command comando) {
        comando.executar();
        logAuditoria.add(comando); 
    }

    public int getQuantidadeAcoesExecutadas() {
        return logAuditoria.size();
    }
}