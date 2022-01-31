package view.menu;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;

import constants.EnumAcciones;
import objects.ObjetoBase;
import service.BaseService;
import view.detalle.VentanaDetalleBase;

public abstract class MenusListadosBase<T extends ObjetoBase, ISERVICE extends BaseService<T>, VENTANA_DETALLE extends VentanaDetalleBase<T, ISERVICE>> {
	public JMenuBar bar;
	public T objeto;
	public VENTANA_DETALLE vDet;

	public MenusListadosBase() throws IOException {
		this.bar = new JMenuBar();

		bar.add(this.botonSelectAll());
		bar.add(this.botonAdd());
		bar.add(this.botonEditSelected());
		bar.add(this.botonDelete());

	}

	public MenusListadosBase(Class<T> claseObjeto, Class<ISERVICE> claseService, Class<VENTANA_DETALLE> claseDetalle)
			throws IOException {
		this.bar = new JMenuBar();
		try {
			this.objeto = claseObjeto.newInstance();
			this.vDet = claseDetalle.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bar.add(this.botonSelectAll());
		bar.add(this.botonAdd());
		bar.add(this.botonEditSelected());
		bar.add(this.botonDelete());

	}

	/**
	 * Crea el botón de seleccionar todos los elementos del listado en el menú
	 * 
	 * @return botón selectAll
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
	 * Crea el botón de añadir en el menú
	 * 
	 * @return botón añadir
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

				try {
					vDet.initialize(objeto);
					vDet.getFrame().setVisible(true);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO accion de agregar elemento al listado
			}
		});
		return boton;
	}

	/**
	 * Crea el botón de editar en el menú
	 * 
	 * @return botón editar
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
	 * Crea el botón de eliminar en el menú
	 * 
	 * @return botón delete
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
	 * Método que obtiene el icono correspondiente al boton de cada una de las
	 * acciones de la barra de listados
	 * 
	 * @param accion
	 * @return icono
	 * @throws IOException
	 */
	public ImageIcon dameIconoPorAccion(EnumAcciones accion) throws IOException {
		Image icono = null;
		icono = ImageIO
				.read(this.getClass().getResource("/resources/botonesListado/" + accion.getValorTraducido() + ".png"));
		return new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}

	/**
	 * Obtiene la clase de la vista de la entana de detalle
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class<VENTANA_DETALLE> obtieneVentanaDetalle() throws InstantiationException, IllegalAccessException {
		return ((Class<VENTANA_DETALLE>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[2]);
	}

	/**
	 * Obtiene la clase del objeto con el que se instancia
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class<T> obtieneClaseObjetoBase() throws InstantiationException, IllegalAccessException {
		return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
}
