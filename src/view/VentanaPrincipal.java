package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import objects.Pedido;
import view.listados.VentanaListadoClientes;
import view.listados.VentanaListadoPedidos;

public class VentanaPrincipal {

	public JFrame frame;

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
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaListadoPedidos window = new VentanaListadoPedidos(new Pedido());
					window.getFrame().setVisible(true);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelPrincipal.add(btnPedidos);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(45, 150, 89, 23);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaListadoClientes window = new VentanaListadoClientes();
					window.getFrame().setVisible(true);
				} catch ( IllegalArgumentException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelPrincipal.add(btnClientes);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(45, 210, 89, 23);
		panelPrincipal.add(btnNewButton);
	}
}
