package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class VentanaDetallaEdicionArticulo {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDetallaEdicionArticulo window = new VentanaDetallaEdicionArticulo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public VentanaDetallaEdicionArticulo() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblTipoArticulo = new JLabel("Tipo de art\u00EDculo\r\n");
		lblTipoArticulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoArticulo.setBounds(25, 25, 100, 25);
		desktopPane.add(lblTipoArticulo);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColor.setBounds(25, 100, 100, 25);
		desktopPane.add(lblColor);
		
		JComboBox comboTipoArticulo = new JComboBox();
		comboTipoArticulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTipoArticulo.setBounds(25, 60, 100, 25);
		desktopPane.add(comboTipoArticulo);
		
		JComboBox comboColor = new JComboBox();
		comboColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboColor.setBounds(25, 135, 100, 25);
		desktopPane.add(comboColor);
		
		JLabel lblPreciobase = new JLabel("PrecioBase");
		lblPreciobase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreciobase.setBounds(25, 175, 100, 25);
		desktopPane.add(lblPreciobase);
		
		textField = new JTextField();
		textField.setBounds(25, 210, 100, 25);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(160, 25, 250, 285);
		desktopPane.add(panel);
		
		JLabel lblImage = new JLabel("");
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource("/resources/colors/violeta.jpg"));
		lblImage.setIcon(new ImageIcon(icono.getScaledInstance(250, 285, java.awt.Image.SCALE_SMOOTH)));
		
		panel.add(lblImage);
	}
}
