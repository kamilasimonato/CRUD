package models.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class RelatorioDAO {

	private BufferedWriter bw;


	public  boolean salvarRelatorio(String dados, String endArquivo) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(endArquivo,false));
			bw.write(dados);
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar:"+e);
		}
		return retorno;
	}
}
