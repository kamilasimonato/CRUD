package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

import controllers.ProcessaEntrega;
import controllers.ProcessaRelatorio;
import models.Entrega;

public class RelatorioForm extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextArea areaDeTexto;
	private JScrollPane scroll; 
	private JButton jbSalvar;
	private String relatorio;

	RelatorioForm(){
		setTitle("Relatório de Entrega");
		setBounds(250,149,700,450);
		panel = new JPanel();
		setContentPane(panel);
		setLayout(null);

		
		
		//Fazer o relatorio
		relatorio = "\n\t\t\tRelatório de Entrega\n";
		relatorio += "\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		relatorio += "\t ID \t Entregador \t Data \t Hora \t Endereco \t KmRodado \n";
		relatorio += "\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		
		for(Entrega e: ProcessaEntrega.getArray()) {
			relatorio += "\t" + e.getId() +"\t" + e.getEntregador().getId()  +"\t" + e.getData()+"\t" 
		    + e.getHora() +"\t" + e.getEndereco() + "\t" + e.getKmRodado()+"\n";
			
		}
		
		relatorio += "\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		relatorio += "\t\t\t\t\tSubtotal = " + String.format("R$ %.2f", ProcessaEntrega.getSubtotal()) + "\n";
		
		// Declaração dos componentes do relatório
				areaDeTexto = new JTextArea(relatorio);
				scroll = new JScrollPane(areaDeTexto);
				scroll.setBounds(10,10,665,360);
				panel.add(scroll);

				jbSalvar = new JButton("Salvar");
				jbSalvar.setBounds(570,372,100,30);
				panel.add(jbSalvar);
				jbSalvar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jbSalvar) {
			
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione apenas TXT", "txt");
			fc.setFileFilter(filter);
			
			if(fc.showSaveDialog(this) != 1) {
				File arquivo = fc.getSelectedFile();
				
				if(arquivo.getPath().endsWith(".txt"))
					ProcessaRelatorio.salvarRelatorio(relatorio, arquivo.getPath());
				else
					ProcessaRelatorio.salvarRelatorio(relatorio, arquivo.getPath() + ".txt");
				
				dispose();
			}

		}
	}

}
