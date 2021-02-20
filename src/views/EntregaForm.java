package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ProcessaEntrega;
import controllers.ProcessaEntregador;
import models.Entrega;
import models.Entregador;

public class EntregaForm extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lbCabecalho, lbSubtotal;
	private JTextField tfId, tfData, tfHora, tfEndereco, tfkmRodado, tfsubTotal;
	private JComboBox<String> cbEntregador;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel tableModel;
	private JButton jbAdd, jbDel, jbCancelar, jbSalvar;
	private Entrega entrega;
	private Entregador entregador;
	private int id;
	
	
	EntregaForm(){
		id = ProcessaEntregador.getAutoId();
		
		// Definições da Janela
		setTitle("Cadastro de Entrega");
		setBounds(250,160,597,410);
		panel = new JPanel();
		setContentPane(panel);
		setLayout(null);
					
		// Criando o label
		lbCabecalho = new JLabel("ID       Entregador	                      Data           Hora        Endereço                                      KmRodado");
		lbCabecalho.setBounds(10,10,580,20);
		panel.add(lbCabecalho);
		
		// Criando TextField
		tfId = new JTextField();
		tfId.setBounds(10,30,30,25);
		tfId.setText(String.format("%d", id));
		panel.add(tfId);
		
		
		tfData = new JTextField();
		tfData.setBounds(170,30,60,25);
		panel.add(tfData);
		
		tfHora = new JTextField();
		tfHora.setBounds(230,30,50,25);
		panel.add(tfHora);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(280,30,170,25);
		panel.add(tfEndereco);
		
		tfkmRodado = new JTextField();
		tfkmRodado.setBounds(450,30,50,25);
		panel.add(tfkmRodado);
		
		// Adicionar as carteiras no combo box
		
		cbEntregador = new JComboBox<String>();
		cbEntregador.setBounds(40,30,130,25);
		panel.add(cbEntregador);
		
		for(Entregador e: ProcessaEntregador.getArray()) {
			cbEntregador.addItem(e.getId()+" - "+e.getNome());
		}
		
		
		
		// Criando Tabela
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Entregador");
		tableModel.addColumn("Data");
		tableModel.addColumn("Hora");
		tableModel.addColumn("Endereco");
		tableModel.addColumn("kmRodado");
		
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		scroll.setBounds(10,55,559,275);
		panel.add(scroll);
		
		// Sub total e textfield
		
		lbSubtotal = new JLabel("Subtotal");
		lbSubtotal.setBounds(10,330,60,20);
		panel.add(lbSubtotal);
		
		tfsubTotal = new JTextField();
		tfsubTotal.setBounds(64,330,50,25);
		System.out.println(ProcessaEntrega.getSubtotal());
		tfsubTotal.setText(String.format("%.2f", ProcessaEntrega.getSubtotal()));
		panel.add(tfsubTotal);
		
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
				if(!ProcessaEntrega.getArray().isEmpty()) {// Verifica se o BD não está vazio
					for(Entrega e : ProcessaEntrega.getArray() ) {
						tableModel.addRow(e.toVetor());
					}
				}
				
			}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbAdd) { // Adicionar informações na tabela
			System.out.print ("Adicionar");
			if( !tfData.getText().isEmpty() 
					&& !tfHora.getText().isEmpty()
					&& cbEntregador.getSelectedIndex()>=0
					&& !tfEndereco.getText().isEmpty()
					&& !tfkmRodado.getText().isEmpty()) {// Avaliar se todos os campos foram inseridos
					
				// Utiliza a Classe Entrega DAO para inserir os dados na tabela
				entrega = new Entrega();
				entrega.setId(id);
				//entrega.setEntregador(tfEntregador.getText());
				entrega.setData(tfData.getText());
				entrega.setHora(tfHora.getText());
				entrega.setEndereco(tfEndereco.getText());
				entrega.setKmRodado(Integer.parseInt(tfkmRodado.getText()));
				
				entregador = ProcessaEntregador.getArray().get(cbEntregador.getSelectedIndex());
				entrega.setEntregador(entregador);
				
				tableModel.addRow(entrega.toVetor());
				
				//Limpar os campos text field
				id++;
				tfId.setText(String.format("%d", id));
				cbEntregador.setSelectedIndex(-1);
				tfData.setText("");
				tfHora.setText("");
				tfEndereco.setText("");
				tfkmRodado.setText("");
				
				
				
				tfsubTotal.setText(String.format("%f", ProcessaEntrega.getSubtotal()));
			}
			
			
		} else if (e.getSource() == jbDel) { // Ao clicar no botão deletar
			if(table.getSelectedRow()>=0) {
				tableModel.removeRow(table.getSelectedRow());
			}else {
				JOptionPane.showInternalMessageDialog(null, "Selecione uma linha para remover.");
			}
						
		} else if (e.getSource() == jbCancelar) { // Ao clicar no botão Cancelar
			dispose();			
		} else if (e.getSource() == jbSalvar) { // Ao clicar no botão salvar
			ArrayList<Entrega> entregas = new ArrayList<>();
			
			// Salvar
			for (int i=0;i<tableModel.getRowCount();i++) {
				entrega = new Entrega();
				entrega.setId(Integer.parseInt((String)tableModel.getValueAt(i,0)));
				
				Entregador entregador = new Entregador ();
				entregador.setId(Integer.parseInt((String)tableModel.getValueAt(i,1)));
				entrega.setEntregador(entregador);
				
				entrega.setData((String)tableModel.getValueAt(i, 2));
				entrega.setHora((String)tableModel.getValueAt(i, 3));
				entrega.setEndereco((String)tableModel.getValueAt(i, 4));
				entrega.setKmRodado(Integer.parseInt((String)tableModel.getValueAt(i,5)));
				entregas.add(entrega);
			}
			
			
			ProcessaEntrega.setArray(entregas);
			JOptionPane.showMessageDialog(null,"Entregas salvo com sucesso!");
			dispose();
			
			
		}
	}
}