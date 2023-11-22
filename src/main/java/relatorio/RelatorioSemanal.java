package relatorio;

public class RelatorioSemanal implements Relatorio {
	String dataRef;
	String texto;
	
	public RelatorioSemanal(String dataRef) {
		this.dataRef = dataRef;
	}


	public String gerarRelatorio(String dataRef) {
		String[] dataFinal = dataRef.split("/");
		RelatorioDiario tl = new RelatorioDiario(dataRef);
		Float valorTotal = (float) 0;
		texto = "Relatorio Semana: " + dataRef + "\n";
		
		for(int i = 0;i<7;i++) {
			texto += tl.gerarRelatorio(dataFinal[0] + "/" + dataFinal[1] + "/" + dataFinal[2]);
			String[] textoAux = texto.split("\n");
			String[] ultEle = textoAux[textoAux.length-1].split(" ");
			Float valorUlt = Float.parseFloat(ultEle[ultEle.length-1]);
			valorTotal += valorUlt;
			dataFinal = confereData(dataFinal);
		}
		
		texto += "Valor Final da Semana: " + String.valueOf(valorTotal);
		return texto;
	}
	
	public String[] confereData(String[] dataRef) {
		int dia = Integer.parseInt(dataRef[0]);
		int mes = Integer.parseInt(dataRef[1]);
		int ano = Integer.parseInt(dataRef[2]);
		if(mes <= 7 && mes % 2 != 0) {
			//31 dias
			dia++;
			if(dia > 31) {
				dia = 1;
				mes++;
			}
		}else if(mes<=12 && mes % 2 == 0) {
			//31 dias
			dia++;
			if(dia > 31) {
				dia = 1;
				mes++;
				if(mes > 12) {
					mes = 1;
					ano++;
				}
			}
		}else if (mes == 2){
			//28 dias
			dia++;
			if(dia > 28) {
				dia = 1;
				mes = 3;
			}
		}else {
			//30
			dia++;
			if(dia > 30) {
				dia = 1;
				mes++;
			}
		}
		String formattedDate = String.format("%02d/%02d/%04d", dia, mes, ano);
		return formattedDate.split("/");
	}

}
