package view.listados;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import annotation.Tabla;
import objects.ObjetoBase;
import service.BaseService;
import view.VentanaBase;
import view.detalle.VentanaDetalleBase;
import view.detalle.VentanaDetallePedido;
import view.menu.MenuListadoSuperior;
import view.menu.MenusListadosBase;

public abstract class VentanaListadoBase<T extends ObjetoBase, ISERVICE extends BaseService<T>, VENTANA_DETALLE extends VentanaDetalleBase<T, ISERVICE>>
		extends VentanaBase<T, ISERVICE> {

	// Propiedades de los listados

	/**
	 * Labels de las columnas
	 */
	String[] titulosColumnas;

	/**
	 * Objetos con los que rellenamos las tablas
	 */
	private Object[][] objetosTabla;

	/**
	 * Tabla que se pinta
	 */
	private JTable tabla;

	/**
	 * Frame sobre el que pintamos
	 */
	private JFrame frame;

	/**
	 * Guardamos la lista de ids seleccionados
	 */
	private ArrayList<Integer> idsSeleccionados;

	/**
	 * Propiedad que identifica si todos los elementos están seleccionados
	 */
	private boolean allSelected;

	// Getters y setters

	public Object[][] getObjetosTabla() {
		return objetosTabla;
	}

	public void setObjetosTabla(Object[][] objetosTabla) {
		this.objetosTabla = objetosTabla;
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public ArrayList<Integer> getIdsSeleccionados() {
		return idsSeleccionados;
	}

	public void setIdsSeleccionados(ArrayList<Integer> idsSeleccionados) {
		this.idsSeleccionados = idsSeleccionados;
	}

	public boolean isAllSelected() {
		return allSelected;
	}

	public void setAllSelected(boolean allSelected) {
		this.allSelected = allSelected;
	}

	public VentanaListadoBase(T entidad) {
		this.idsSeleccionados = new ArrayList<Integer>();
		this.allSelected = false;
		initialize(entidad);
	}

	public void initialize(T entidad) {

		try {
			this.frameBase(entidad);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| NoSuchMethodException | SecurityException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga el frame de la entidad
	 * 
	 * @param entidad
	 * @throws IOException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public void frameBase(T entidad) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, SQLException, NoSuchMethodException, SecurityException {
		frame = new JFrame();
		frame.setTitle(entidad.getClass().getAnnotation(Tabla.class).nombreFrame());
		frame.setBounds(100, 100, 580, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		MenuListadoSuperior<T, ISERVICE, VENTANA_DETALLE> menu = new MenuListadoSuperior<T, ISERVICE, VENTANA_DETALLE>(
				this.obtieneClaseObjeto(), this.obtieneClaseService(), this.obtieneVentanaDetalle());

		this.actionListenersMenu(menu);

		frame.setJMenuBar(menu.bar);
		JScrollPane panelPrincipal = new JScrollPane();
		panelPrincipal.setBounds(100, 100, 582, 300);
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		this.objetosTablaEntidad();
		this.tablaEntidad();
		panelPrincipal.setViewportView(tabla);

	}

	/**
	 * Rellena los objetos de la tabla de la entidad
	 * 
	 * @param entidad
	 * @throws SQLException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public void objetosTablaEntidad() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, SecurityException {

		List<T> listaElementos = this.cargaListadoObjetos();

		if (listaElementos.size() > 0) {
			int numCols = listaElementos.get(0).getClass().getAnnotation(Tabla.class).numeroCampos();
			objetosTabla = new Object[listaElementos.size()][numCols + 1];
			titulosColumnas = new String[numCols + 1];

			for (int i = 0; i < objetosTabla.length; i++) {
				objetosTabla[i][0] = false;
				titulosColumnas[0] = "";

				List<Method> getters = this.filtraGetters(this.dameListaPropiedades(listaElementos.get(i)),
						this.dameGettersT(listaElementos.get(i)));

				objetosTabla[i][1] = this.dameGetterPorNombrePropiedad("Id", getters).invoke(listaElementos.get(i));
				titulosColumnas[1] = "ID";

				for (int j = 0; j < numCols; j++) {
					if (!getters.get(j).getName().equals("getId")) {
						objetosTabla[i][j + 2] = getters.get(j).invoke(listaElementos.get(i));
						titulosColumnas[j + 2] = getters.get(j).getName().substring(3);
					}
				}

			}
		}
	}

	/**
	 * Construye el JTable
	 */
	public void tablaEntidad() {

		DefaultTableModel defaultTableModel = new DefaultTableModel(objetosTabla, titulosColumnas);
		tabla = new JTable(defaultTableModel) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};

		tabla.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {
					JTable table = (JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);

					try {

						Class<VENTANA_DETALLE> vDetalleClass = obtieneVentanaDetalle();
						VENTANA_DETALLE v = vDetalleClass.newInstance();
						v.initialize(dameEntidadPorIdFila((int) tabla.getValueAt(row, 1)));
						v.getFrame().setVisible(true);

					} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		tabla.setRowSelectionAllowed(false);
	}

	// AXUILIARES

	/**
	 * Obtiene todos los getters de la entidad
	 * 
	 * @param entidad
	 * @return getters
	 */
	public List<Method> dameGettersT(T entidad) {
		return entidad.dameListaGetters();
	}

	/**
	 * Obtiene todas las propiedades de la entidad
	 * 
	 * @param entidad
	 * @return propiedades
	 */
	public List<String> dameListaPropiedades(T entidad) {
		return entidad.dameListaPropiedades();
	}

	/**
	 * Filtra los getters para eliminar el de getClass
	 * 
	 * @param propiedades
	 * @param getters
	 * @return getters
	 */
	public List<Method> filtraGetters(List<String> propiedades, List<Method> getters) {
		List<Method> gettersFiltrados = new ArrayList<Method>();
		for (Method m : getters) {
			if (m.getName().equals("getId")) {
				gettersFiltrados.add(m);
			} else {
				for (String s : propiedades) {
					if (m.getName().equalsIgnoreCase("get" + s)) {
						gettersFiltrados.add(m);

					}

				}
			}
		}

		return gettersFiltrados;
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
	 * Obtiene la clase de la vista de la entana de detalle
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class<T> obtieneClaseObjeto() throws InstantiationException, IllegalAccessException {
		return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	/**
	 * Obtiene la clase de la vista de la entana de detalle
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class<ISERVICE> obtieneClaseService() throws InstantiationException, IllegalAccessException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	/**
	 * Método que actualiza el menu para settear las acciones de los botones que no
	 * podemos hacer desde el propio menú
	 * 
	 * @param base
	 */
	public void actionListenersMenu(MenuListadoSuperior<T, ISERVICE, VENTANA_DETALLE> base) {
		
		// Primero el de seleccionar todos
		base.selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				allSelected = !allSelected;

				for (int i = 0; i < objetosTabla.length; i++) {
					tabla.setValueAt(allSelected, i, 0);

					if (allSelected) {
						idsSeleccionados.add((Integer) objetosTabla[i][1]);
					} else {
						idsSeleccionados.remove((Integer) objetosTabla[i][1]);
					}
				}
			}
		});
		
		// Ahora el de editar, que tiene que comprobar que solo se puede editar uno, por ahora
		base.edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// Ahora el de eliminar, que si hay más de uno muestra un mensaje por pantalla
		base.delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
