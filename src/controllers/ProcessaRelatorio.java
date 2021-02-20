package controllers;

import javax.swing.JOptionPane;

import models.dao.RelatorioDAO;

public class ProcessaRelatorio {

	private static RelatorioDAO relatorioDAO = new RelatorioDAO();


	public static boolean salvarRelatorio(String dados, String endArquivo) {
		if(ProcessaRelatorio.relatorioDAO.salvarRelatorio(dados, endArquivo)) {
			JOptionPane.showMessageDialog(null,"Relatório Salvo com Sucesso!");
			return true;
		}else { // Erro ao salvar
			JOptionPane.showMessageDialog(null,"Erro ao Salvar o Relatório!");
			return false;
		}
	}
}
