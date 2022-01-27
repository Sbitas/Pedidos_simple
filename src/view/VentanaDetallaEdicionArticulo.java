package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.EnumColoresSudaderaConCapuchaBasica;
import constants.EnumTipoArticulo;
import objects.Articulo;
import javax.swing.JButton;

public class VentanaDetallaEdicionArticulo {

	private JDesktopPane desktopPane;
	private JFrame frame;
	private JTextField txtPrecio;
	private JComboBox comboColor;
	private int selectedIndexColor;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public VentanaDetallaEdicionArticulo(Articulo articulo) throws IOException {
		initialize(articulo);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(Articulo articulo) throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

		comboColor = new JComboBox();
		if (articulo.getTipo() != null) {
			comboColor = new JComboBox(dameListaColoresPosiblesPorTipo(articulo.getTipo()));
		}
		try {
			selectedIndexColor = 
					(int) articulo.getColor().getClass().getMethod("getCodigoNumerico").invoke(articulo.getColor());
			comboColor.setSelectedIndex(selectedIndexColor);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}

		JComboBox comboTipoArticulo = this.jComboTipoArticulo(EnumTipoArticulo.comboSeleccion(), articulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(225, 25, 250, 285);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		;
		desktopPane.add(panel);

		JLabel lblImage = new JLabel("");
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource(this.dameRutaImagen(articulo.getTipo(), articulo.getColor())));
		lblImage.setIcon(new ImageIcon(icono.getScaledInstance(240, 270, java.awt.Image.SCALE_SMOOTH)));

		panel.add(lblImage);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColor.setBounds(25, 100, 100, 25);
		desktopPane.add(lblColor);

		comboColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EnumTipoArticulo tipo = EnumTipoArticulo.convert(comboTipoArticulo.getSelectedIndex());
					Class color = dameEnumColoresPorTipo(tipo);
					
					try {
						Image iconoAux = ImageIO.read(this.getClass().getResource(dameRutaImagen(tipo, (Enum) color
								.getMethod("convert", int.class).invoke(color, comboColor.getSelectedIndex()))));
						if (iconoAux != null) {
							lblImage.setIcon(
									new ImageIcon(iconoAux.getScaledInstance(240, 270, java.awt.Image.SCALE_SMOOTH)));
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e1) {
						e1.printStackTrace();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		comboColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboColor.setBounds(25, 135, 150, 25);

		desktopPane.add(comboColor);

		JLabel lblTipoArticulo = new JLabel("Tipo de art\u00EDculo\r\n");
		lblTipoArticulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoArticulo.setBounds(25, 25, 100, 25);
		desktopPane.add(lblTipoArticulo);

		comboTipoArticulo.setSelectedIndex(articulo.getTipo().getCodigoNumerico());
		desktopPane.add(comboTipoArticulo);

		JLabel lblPreciobase = new JLabel("PrecioBase");
		lblPreciobase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreciobase.setBounds(25, 175, 100, 25);
		desktopPane.add(lblPreciobase);

		txtPrecio = new JTextField(String.valueOf(articulo.getPrecioBase()));
		txtPrecio.setBounds(25, 210, 150, 25);
		desktopPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnGuardarArticulo = new JButton("Guardar cambios");
		btnGuardarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardarArticulo.setBounds(25, 287, 150, 23);
		desktopPane.add(btnGuardarArticulo);

	}

	/**
	 * Devulve la ruta hasta la imagen que se pone en el panel
	 * 
	 * @param tipo
	 * @param color
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String dameRutaImagen(EnumTipoArticulo tipo, Enum color) {

		StringBuilder ruta = new StringBuilder("");
		if (tipo != null && tipo != EnumTipoArticulo.UNDEFINED && color != null
				&& color != EnumColoresSudaderaConCapuchaBasica.UNDEFINED) {

			ruta.append("/resources/colors/");
			ruta.append(tipo.getRutaColores());
			ruta.append("/");
			try {
				ruta.append(color.getClass().getMethod("getRutaColor").invoke(color));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			ruta.append(".jpg");
		}
		return ruta.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox jComboTipoArticulo(String[] objetos, Articulo articulo) {
		JComboBox comboTipoArticulo = new JComboBox(EnumTipoArticulo.comboSeleccion());
		comboTipoArticulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboTipoArticulo.setBounds(25, 60, 150, 25);
		comboTipoArticulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					comboColor.removeAllItems();
				} catch (Exception exp) {

				}
				comboColor.removeAllItems();
				for (String s : dameListaColoresPosiblesPorTipo(
						EnumTipoArticulo.convert(comboTipoArticulo.getSelectedIndex()))) {
					comboColor.addItem(s);
				}
				if(articulo.getTipo().getCodigoNumerico() == comboTipoArticulo.getSelectedIndex()) {
					comboColor.setSelectedIndex(selectedIndexColor);
				}
				comboColor.repaint();
			}
		});
		return comboTipoArticulo;
	}

	/**
	 * Devuelve la lista de posibles colores en función del tipo de artículo
	 * 
	 * @param tipo
	 * @return
	 */
	public String[] dameListaColoresPosiblesPorTipo(EnumTipoArticulo tipo) {
		String[] retorno = null;

		switch (tipo) {
		case SUDADERA_BASICA:
			retorno = EnumColoresSudaderaConCapuchaBasica.comboSeleccion();
			break;
		default:
			break;
		}
		return retorno;
	}

	@SuppressWarnings("rawtypes")
	public Class dameEnumColoresPorTipo(EnumTipoArticulo tipo) {
		Class retorno = null;
		switch (tipo) {
		case SUDADERA_BASICA:
			retorno = EnumColoresSudaderaConCapuchaBasica.class;
		}
		return retorno;
	}
}
