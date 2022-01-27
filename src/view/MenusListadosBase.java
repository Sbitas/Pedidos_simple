package view;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;

import constants.EnumAcciones;

public class MenusListadosBase<T> {
	public JMenuBar bar;

	public MenusListadosBase() throws IOException {
		this.bar = new JMenuBar();
		bar.add(this.botonSelectAll());
		bar.add(this.botonAdd());
		bar.add(this.botonEditSelected());
		bar.add(this.botonDelete());
		
	}

	/**
	 * Crea el bot�n de seleccionar todos los elementos del listado en el men�
	 * 
	 * @return bot�n selectAll
	 * @throws IOException
	 */
	public JCheckBox botonSelectAll() throws IOException {
		JCheckBox checkBox = new JCheckBox();

		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO accion de seleccionar todos
			}
		});

		return checkBox;
	}

	/**
	 * Crea el bot�n de a�adir en el men�
	 * 
	 * @return bot�n a�adir
	 * @throws IOException
	 */
	public JButton botonAdd() throws IOException {
		JButton boton = new JButton("");
		boton.setIcon(this.dameIconoPorAccion(EnumAcciones.CREATE));
		boton.setMargin(new Insets(0, 0, 0, 0));
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO accion de agregar elemento al listado
			}
		});
		return boton;
	}
	
	/**
	 * Crea el bot�n de editar en el men�
	 * 
	 * @return bot�n editar
	 * @throws IOException
	 */
	public JButton botonEditSelected() throws IOException {
		JButton boton = new JButton("");
		boton.setIcon(this.dameIconoPorAccion(EnumAcciones.UPDATE));
		boton.setMargin(new Insets(0, 0, 0, 0));
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO accion de editar elemento del listado
			}
		});
		return boton;
	}

	/**
	 * Crea el bot�n de eliminar en el men�
	 * 
	 * @return bot�n delete
	 * @throws IOException
	 */
	public JButton botonDelete() throws IOException {
		JButton boton = new JButton("");
		boton.setIcon(this.dameIconoPorAccion(EnumAcciones.DELETE));
		boton.setMargin(new Insets(0, 0, 0, 0));
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO accion de elminar selecccionados
			}
		});
		
		return boton;
	}

	/**
	 * M�todo que obtiene el icono correspondiente al boton de cada una de las
	 * acciones de la barra de listados
	 * 
	 * @param accion
	 * @return icono
	 * @throws IOException
	 */
	public ImageIcon dameIconoPorAccion(EnumAcciones accion) throws IOException {
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource("/resources/botonesListado/" + accion.getValorTraducido() + ".png"));
		return new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}
}
