package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/resources/simple.png")));
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 200, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane panelPrincipal = new JDesktopPane();
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		
		JButton btnArticulos = new JButton("Articulos");
		btnArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaArticulos window = new VentanaArticulos();
					window.getFrame().setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnArticulos.setBounds(45, 30, 89, 23);
		panelPrincipal.add(btnArticulos);
		
		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.setBounds(45, 90, 89, 23);
		panelPrincipal.add(btnPedidos);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(45, 150, 89, 23);
		panelPrincipal.add(btnClientes);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(45, 210, 89, 23);
		panelPrincipal.add(btnNewButton);
	}
}
