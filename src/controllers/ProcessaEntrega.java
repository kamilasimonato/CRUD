package controllers;

import java.util.ArrayList;

import models.Entrega;
import models.Entregador;
import models.dao.EntregaDAO;


public class ProcessaEntrega {
	private static EntregaDAO entregaDAO = new EntregaDAO();
	private static ArrayList<Entrega> entregas= new ArrayList<Entrega>();


	public static void setArray(ArrayList<Entrega> entregas) {
		ProcessaEntrega.entregas = entregas;
		ProcessaEntrega.entregaDAO.salvar(ProcessaEntrega.entregas);
	}

	public static ArrayList<Entrega> getArray(){
		ProcessaEntrega.entregas = ProcessaEntrega.entregaDAO.retornar();
		return ProcessaEntrega.entregas;
	}

	public static int getAutoId() {
		ProcessaEntrega.entregas =  ProcessaEntrega.entregaDAO.retornar();
		if(ProcessaEntrega.entregas.isEmpty())
			return 1;
		else
			return ProcessaEntrega.entregas.get(ProcessaEntrega.entregas.size()-1).getId() + 1;
	}

	public static double getSubtotal() {
		ProcessaEntrega.entregas = ProcessaEntrega.entregaDAO.retornar();
		double subtotal = 0.0;
		for(Entrega entrega: ProcessaEntrega.entregas) {
			subtotal += entrega.getEntregador().getValorKM() * entrega.getKmRodado();
		//	System.out.println(entrega.getEntregador().getValorKM() + "-" + entrega.getKmRodado());
		}
		
		
		return subtotal;
	}
}