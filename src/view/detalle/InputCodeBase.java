package view.detalle;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import objects.ObjetoBase;

public class InputCodeBase<T extends ObjetoBase> {
	public JTextField text;
	public ImageIcon iconoBusqueda;
	public JButton botonBusqueda;
	
	public InputCodeBase() {
		this.text = creaJText();
		this.iconoBusqueda = imagenDefecto();
		this.botonBusqueda = creaBotonBusqueda();
	}
	
	/**
	 * Crea el texto vacio por defecto
	 * @return
	 */
	public JTextField creaJText() {
		return new JTextField();
	}
	
	/**
	 * Crea la imagen de busqueda con un icono de lupa por defecto
	 * @return
	 */
	public ImageIcon imagenDefecto() {
		Image icono = null;
		try {
			icono = ImageIO.read(this.getClass().getResource("/resources/botonesDetalle/lupa.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}
	
	public JButton creaBotonBusqueda() {
		JButton boton = new JButton();
		boton.setIcon(iconoBusqueda);
		return boton;
	}
	
	/**
	 * Cambia el texto por el puesto
	 * @param texto
	 */
	public void cambiaTexto(String texto) {
		this.text.setText(texto);
	}
	
	/**
	 * Cambia la imagen de defecto por la de la ruta nueva
	 * @param rutaNuevaImagen
	 */
	public void cambiaImagen(String rutaNuevaImagen) {
		Image icono = null;
		try {
			icono = ImageIO.read(this.getClass().getResource(rutaNuevaImagen));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iconoBusqueda =  new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}
}
