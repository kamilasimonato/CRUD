package models.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Entregador;

public class EntregadorDAO {

	BufferedWriter bw;
	BufferedReader br;
	String arquivo = ".\\bd\\entregador.csv";

	public boolean salvar(ArrayList<Entregador> entregadores) {

		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));

			for(Entregador entregador: entregadores) {
				bw.write(entregador.toCSV());
			}

			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar:"+e);
		}

		return retorno;
	}

	public ArrayList<Entregador> retornar(){
		ArrayList<Entregador> entregadores = new ArrayList<Entregador>();
		String[] campos;
		Entregador entregador;

		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha;

				linha = br.readLine();

			while(linha!=null) {
				campos = linha.split(";");
				entregador = new Entregador();
				entregador.setId(Integer.parseInt(campos[0]));
				entregador.setNome(campos[1]);
				entregador.setHabilitacao(campos[2]);
				entregador.setTelefone(campos[3]);
				entregador.setVeiculo(campos[4]);
				entregador.setValorKM(Double.parseDouble(campos[5]));
				
				entregadores.add(entregador);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao retornar, arquivo não encontrado:"+e);
		} catch (IOException e) {
			System.out.println("Erro ao retornar, erro na leitura da linha:"+e);
		} 

		return entregadores;

	}
	
	public static void main(String[] args) {
		ArrayList<Entregador> entregadores = new ArrayList<Entregador>();
		Entregador entregadoraMaria = new Entregador();
		entregadoraMaria.setId(1);
		entregadoraMaria.setNome("Entregadora Maria");
		entregadoraMaria.setTelefone("1999999");
		entregadoraMaria.setHabilitacao("ap777");
		entregadoraMaria.setVeiculo("Corsa");
		entregadoraMaria.setValorKM(50);
	
		entregadores.add(entregadoraMaria);
		
		EntregadorDAO entregadorDAO = new EntregadorDAO();
		if(!entregadorDAO.salvar(entregadores))
			System.out.printf("Erro ao salvar");
	}
}