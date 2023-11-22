package relatorio;

public class FabricaRelatorio {
	public Relatorio criarRelatorio(String tipo, String dataRef) {
        if (tipo.equals("semanal")) {
            return new RelatorioSemanal(dataRef);
        } else if (tipo.equals("mensal")) {
            return new RelatorioMensal(dataRef);
        } else if (tipo.equals("anual")) {
            return new RelatorioAnual(dataRef);
        }else if(tipo.equals("diario")) {
        	return new RelatorioDiario(dataRef);
        }
        throw new IllegalArgumentException("Tipo de relatório inválido: " + tipo);
    }
}
