package view.detalle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import annotation.Tabla;
import objects.ObjetoBase;
import service.BaseService;
import view.VentanaBase;

public class VentanaDetalleBase<T extends ObjetoBase, ISERVICE extends BaseService<T>>
		extends VentanaBase<T, ISERVICE> {

	private JDesktopPane desktopPane;
	private JFrame frame;

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
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public VentanaDetalleBase() throws IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		initialize(null);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public void initialize(T objeto) throws IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (objeto != null) {
			// -1 porque quitamos el Id que eso no lo mostramos
			int numCampos = objeto.getClass().getAnnotation(Tabla.class).numeroCampos();

			// El alto del frame es dinámico
			int alto = (numCampos * 50) + 120;

			frame = new JFrame();
			frame.setBounds(100, 100, 550, alto);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			desktopPane = this.panelCampos(objeto);
			desktopPane.setBackground(Color.GRAY);
			frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

		}
	}

	public JDesktopPane panelCampos(T objeto) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		desktopPane = new JDesktopPane();

		// Todos los elementos están seteados a una distancia fija
		int distancia = 35;

		// Usamos esta variable para guardar la última posición de un elemento
		int lastY = 0;
		for (Field f : objeto.getClass().getDeclaredFields()) {

			String nombrePropiedad = capitalizaColumna(this.capitalizaColumna(f.getName()));
			Method getterPropiedad = this.dameGetterPorNombrePropiedad(nombrePropiedad, objeto.dameListaGetters());

			// Label de la propiedad
			JLabel label = this.creaLabelPropiedad(nombrePropiedad);
			lastY = lastY + distancia;
			label.setBounds(25, lastY, 150, 25);
			desktopPane.add(label);

			// Input para la propiedad
			if (getterPropiedad.getName().contains("Id")) {
				// TODO Agregar input code
			} else {
				if (getterPropiedad.getReturnType().isEnum()) {
					lastY = lastY + distancia;
					// Si es un enum tenemos que generar un combo
					JComboBox<String> combo = this.creaComboPropiedad(
							(Enum[]) getterPropiedad.getReturnType().getMethod("values").invoke(null),
							(Enum) getterPropiedad.invoke(objeto));
					combo.setBounds(25, lastY, 150, 25);
					desktopPane.add(combo);

				} else {

					lastY = lastY + distancia;
					// Si no, es un un txt por ahora
					JTextField textField = this.creaTxtPropiedad(getterPropiedad.invoke(objeto));
					textField.setBounds(25, lastY, 150, 25);
					desktopPane.add(textField);
				}
			}
		}
		return desktopPane;
	}

	/**
	 * Para cada propiedad tenemos que generar su label
	 * 
	 * @param nombrePropiedad
	 * @return label
	 */
	public JLabel creaLabelPropiedad(String nombrePropiedad) {
		JLabel label = new JLabel();
		label.setText(nombrePropiedad != null ? nombrePropiedad : "");
		return label;
	}

	/**
	 * Para algunas propiedades necesitamos un text field para introducir su valor
	 * Ej.: Precios
	 * 
	 * @param valorDefecto
	 * @return field
	 */
	public JTextField creaTxtPropiedad(Object valorDefecto) {
		JTextField field = new JTextField(String.valueOf(valorDefecto != null ? valorDefecto : ""));
		return field;
	}

	/**
	 * Para las propiedades de tipo (color, tipo producto) usamos combos
	 * 
	 * @param valoresCombo
	 * @param valorDefecto
	 * @return
	 */
	public JComboBox<String> creaComboPropiedad(Enum[] valoresCombo, Enum valorDefecto) {
		JComboBox<String> box;
		ArrayList<String> listaString = new ArrayList<String>();
		int defaultSelected = 0;
		int contador = 0;
		for (Enum e : valoresCombo) {
			try {
				if (e.equals(valorDefecto)) {
					defaultSelected = contador;
				}
				contador++;
				listaString.add(String.valueOf(e.getClass().getMethod("getNombre").invoke(e)));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		box = new JComboBox<String>(listaString.toArray(new String[listaString.size()]));
		box.setSelectedIndex(defaultSelected);
		return box;
	}
}
