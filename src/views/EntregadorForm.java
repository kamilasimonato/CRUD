package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ProcessaEntregador;
import models.Entregador;

public class EntregadorForm extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private  JLabel lbCabecalho;
	private  JTextField tfId, tfNome, tfTelefone, tfHabilitacao, tfVeiculo, tfValorKM;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel tableModel;
	private JButton jbAdd, jbDel, jbCancelar, jbSalvar;
	private Entregador entregador;
	private int id;
	
	
	EntregadorForm(){
		
		id = ProcessaEntregador.getAutoId();
		
		// Definições da Janela
		setTitle("Cadastro do Entregador");
		setBounds(250,160,597,410);
		panel = new JPanel();
		setContentPane(panel);
		setLayout(null);
					
		// Criando o label
		lbCabecalho = new JLabel("ID           Nome                         Telefone              Habilitacao          Veiculo               ValorKM");
		lbCabecalho.setBounds(10,10,580,20);
		panel.add(lbCabecalho);
		
		// Criando TextField
		tfId = new JTextField();
		tfId.setBounds(10,30,40,25);
		tfId.setText(String.format("%d", id));
		panel.add(tfId);
		
		tfNome = new JTextField();
		tfNome.setBounds(50,30,110,25);
		panel.add(tfNome);
		
		tfTelefone = new JTextField();
		tfTelefone.setBounds(160,30,90,25);
		panel.add(tfTelefone);
		
		tfHabilitacao = new JTextField();
		tfHabilitacao.setBounds(250,30,90,25);
		panel.add(tfHabilitacao);
		
		tfVeiculo = new JTextField();
		tfVeiculo.setBounds(340,30,90,25);
		panel.add(tfVeiculo);
		
		tfValorKM = new JTextField();
		tfValorKM.setBounds(430,30,70,25);
		panel.add(tfValorKM);
		
		// Criando Tabela
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Telefone");
		tableModel.addColumn("Habilitacao");
		tableModel.addColumn("Veiculo");
		tableModel.addColumn("ValorKM");
		
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(10,55,559,275);
		panel.add(scroll);
		
		// Criar os botões
		jbAdd = new JButton("Add");
		jbAdd.setBounds(500,30,68,25);
		jbAdd.addActionListener(this);
		panel.add(jbAdd);
		
		
		jbDel = new JButton("Del");
		jbDel.setBounds(278,330,59,30);
		jbDel.addActionListener(this);
		panel.add(jbDel);
		
		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(328,330,120,30);
		jbCancelar.addActionListener(this);
		panel.add(jbCancelar);
		
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(448,330,120,30);
		jbSalvar.addActionListener(this);
		panel.add(jbSalvar);
		
		// Adicionar as informações do BD na view do usuário
		if(!ProcessaEntregador.getArray().isEmpty()) {// Verifica se o BD não está vazio
			for(Entregador e : ProcessaEntregador.getArray() ) {
				tableModel.addRow(e.toVetor());
			}
		}
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbAdd) {
			if(!tfNome.getText().isEmpty() 
					&& !tfTelefone.getText().isEmpty() 
					&& !tfHabilitacao.getText().isEmpty() 
					&& !tfVeiculo.getText().isEmpty() 
					&& !tfValorKM.getText().isEmpty()) {// Avaliar se todos os campos foram inseridos
					
				// Utiliza a Classe Carteira DAO para inserir os dados na tabela
				entregador = new Entregador();
				
				entregador.setId(id);
				entregador.setNome(tfNome.getText());
				entregador.setTelefone(tfTelefone.getText());
				entregador.setHabilitacao(tfHabilitacao.getText());
				entregador.setVeiculo(tfVeiculo.getText());
				entregador.setValorKM(Double.parseDouble(tfValorKM.getText()));
				tableModel.addRow(entregador.toVetor());
				
				//Limpar os campos text field
				id++;
				tfId.setText(String.format("%d", id));
				tfNome.setText("");
				tfTelefone.setText("");
				tfHabilitacao.setText("");
				tfVeiculo.setText("");
				tfValorKM.setText("");
			}
			
		} else if (e.getSource() == jbDel) {
			if(table.getSelectedRow()>=0) {
				tableModel.removeRow(table.getSelectedRow());
			}else {
				JOptionPane.showInternalMessageDialog(null, "Selecione uma linha para remover.");
			}
						
		} else if (e.getSource() == jbCancelar) { // Ao clicar no botão Cancelar
			dispose();			
		} else if (e.getSource() == jbSalvar) { // Ao clicar no botão salvar
			ArrayList<Entregador> entregadores = new ArrayList<>();
			
			
			for (int i=0;i<tableModel.getRowCount();i++) {
				entregador = new Entregador();
				entregador.setId(Integer.parseInt((String)tableModel.getValueAt(i,0)));
				entregador.setNome((String)tableModel.getValueAt(i, 1));
				entregador.setTelefone((String)tableModel.getValueAt(i, 2));
				entregador.setHabilitacao((String)tableModel.getValueAt(i, 3));
				entregador.setVeiculo((String)tableModel.getValueAt(i, 4));
				entregador.setValorKM(Double.parseDouble((String)tableModel.getValueAt(i, 5)));
				
				entregadores.add(entregador);
				
			}
			
			ProcessaEntregador.setArray(entregadores);
			JOptionPane.showMessageDialog(null,"Entregas salvo com sucesso!");
			dispose();
		}
	}

}

