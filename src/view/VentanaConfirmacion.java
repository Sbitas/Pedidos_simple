package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaConfirmacion {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public VentanaConfirmacion(String pregunta) {
		initialize(pregunta);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pregunta) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JLabel confirma = new JLabel(pregunta);

		// Agregar botones de confirmación para que cierren la ventana y realicen la
		// operación
		// Podria usar esta misma ventana para errores / warnings pasandole una
		// propiedad y asi en vez de un si/no poner un ok o algo así

	}

}
