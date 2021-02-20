package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainForm extends JFrame implements ActionListener  { // Herda todos os atributos e métodos da classe JFrame
	private static final long serialVersionUID = 1L; // Necessário para JFrame trabalhar com byte
	private JPanel panel;
	private JMenuBar barraMenu;
	private JMenu menuArquivo, menuSistema;
	private JMenuItem itemEntregador, itemEntrega, itemRelatorios, itemSair;
	private JLabel lbFundo;
	private ImageIcon fundo;
	
	MainForm(){ // Método construtor
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Define o que acontece quando clica no botão fechar
		setTitle("Gestao de Entregas"); // Define o título da janela
		setBounds(300, 80, 900, 700); // Dimensões da janela x, y, largura e altura
		
		panel = new JPanel(); // Panel para adicionar os elementos na janela
		setContentPane(panel); // Setando o panel na janela
		
		// Definindo menu na janela
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		setLayout(null); //Define layout, no primeiro momento nulo
		
		// Parte do Menu
		menuArquivo = new JMenu("Arquivo");
		menuSistema = new JMenu("Sistema");
		barraMenu.add(menuArquivo);
		barraMenu.add(menuSistema);
		
		itemEntregador = new JMenuItem("Entregador");
		itemEntrega = new JMenuItem("Entrega");
		itemRelatorios = new JMenuItem("Relatórios");
		itemSair = new JMenuItem("Sair");
		
		menuArquivo.add(itemEntregador);
		menuArquivo.add(itemEntrega);
		
		menuSistema.add(itemRelatorios);
		menuSistema.add(itemSair);
		
		// Adicionando Eventos nos itens do menu
		itemEntregador.addActionListener(this);
		itemEntrega.addActionListener(this);
		itemRelatorios.addActionListener(this);
		itemSair.addActionListener(this);
		
		
		// Parte da Imagem
		fundo = new ImageIcon(".\\img\\boneco.png");
		lbFundo = new JLabel();
		lbFundo.setIcon(fundo);
		lbFundo.setBounds(20,15,850,650); // x,y, largura, altura
		panel.add(lbFundo);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == itemEntregador) { // entregador
			EntregadorForm ef = new EntregadorForm();
			ef.setVisible(true);
		} else if (e.getSource() == itemEntrega) { // Entrega
			EntregaForm enf = new EntregaForm();
			enf.setVisible(true);
			
		} else if (e.getSource() == itemRelatorios) { // Relatórios
			RelatorioForm rf = new RelatorioForm ();
			rf.setVisible(true);
			
			System.out.println("Relatórios");			
		} else if (e.getSource() == itemSair) { // Sair
			dispose();		
		}
		
	}
	
	public static void main(String[] args) {
		MainForm mainform = new MainForm();
		mainform.setVisible(true);
	}
} 