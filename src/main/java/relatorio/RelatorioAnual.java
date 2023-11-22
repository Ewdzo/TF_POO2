package relatorio;

public class RelatorioAnual implements Relatorio {
	private String anoRef;
	private String texto;

	public RelatorioAnual(String anoRef) {
		this.anoRef = anoRef;
	}

	public String gerarRelatorio(String anoRef) {
		RelatorioMensal rl = new RelatorioMensal();
		texto = "Ano: " + anoRef + "\n";
		Float valorTotal = (float) 0;
		for(int i = 1; i<=12;i++) {
			texto += rl.gerarRelatorio("0" + String.valueOf(i) + "/" + anoRef);
			String[] textoAux = texto.split("\n");
			String[] ultEle = textoAux[textoAux.length-1].split(" ");
			Float valorUlt = Float.parseFloat(ultEle[ultEle.length-1]);
			valorTotal += valorUlt;
		}		
		texto += "Valor Final do Ano: " + String.valueOf(valorTotal)+"\n";
		return texto;
	}
}
