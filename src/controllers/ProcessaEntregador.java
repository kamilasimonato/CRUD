package controllers;

import java.util.ArrayList;

import models.Entregador;
import models.dao.EntregadorDAO;

public class ProcessaEntregador {
	private static EntregadorDAO entregadorDAO = new EntregadorDAO();
	private static ArrayList<Entregador> entregadores = new ArrayList<Entregador>();


	public static void setArray(ArrayList<Entregador> entregadores) {
		ProcessaEntregador.entregadores = entregadores;
		ProcessaEntregador.entregadorDAO.salvar(ProcessaEntregador.entregadores);
	}

	public static ArrayList<Entregador> getArray(){
		ProcessaEntregador.entregadores = ProcessaEntregador.entregadorDAO.retornar();
		return ProcessaEntregador.entregadores;
	}

	public static int getAutoId() {
		ProcessaEntregador.entregadores = ProcessaEntregador.entregadorDAO.retornar();
		if(ProcessaEntregador.entregadores.isEmpty())
			return 1;
		else
			return ProcessaEntregador.entregadores.get(ProcessaEntregador.entregadores.size()-1).getId() + 1;
	}
	
	
		
	
}
		
		
