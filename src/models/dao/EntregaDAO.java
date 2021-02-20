package models.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Entrega;
import models.Entregador;

public class EntregaDAO {

	BufferedWriter bw;
	BufferedReader br;
	String arquivo = ".\\bd\\entrega.csv";

	public boolean salvar(ArrayList<Entrega> entregas) {

		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));

			for(Entrega entrega: entregas) {
				bw.write(entrega.toCSV());
			}

			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar:"+e);
		}

		return retorno;
	}

	public ArrayList<Entrega> retornar(){
		ArrayList<Entrega> entregas = new ArrayList<Entrega>();
		String[] campos;
		Entrega entrega;
		Entregador entregador;

		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha;

			linha = br.readLine();

			while(linha!=null) {
				campos = linha.split(";");
				entrega = new Entrega();
				entrega.setId(Integer.parseInt(campos[0]));
				
				entregador = new Entregador();
				entregador.setId(Integer.parseInt(campos[1]));
				entrega.setEntregador(entregador);
				
				entrega.setData(campos[2]);
				entrega.setHora(campos[3]);
				entrega.setEndereco(campos[4]);
				entrega.setKmRodado(Integer.parseInt(campos[5]));

	

				entregas.add(entrega);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao retornar, arquivo não encontrado:"+e);
		} catch (IOException e) {
			System.out.println("Erro ao retornar, erro na leitura da linha:"+e);
		} 

		return entregas;

	}

	public static void main(String[] args) {
		ArrayList<Entrega> entregas = new ArrayList<Entrega>();
		
		Entrega entrega1 = new Entrega();
		entrega1.setId(1);
		entrega1.setData("16/11/2020");
		entrega1.setHora("19 30");
		entrega1.setEndereco("rua armelinda, 22");
		entrega1.setKmRodado(0);
		
		Entregador Marcos = new Entregador ();
		Marcos.setId(1);
		entrega1.setEntregador(Marcos);
		
		entregas.add(entrega1);
		
		EntregaDAO entregaDAO = new EntregaDAO();
		if(!entregaDAO.salvar(entregas))
			System.out.printf("Erro ao salvar");
	}
}

