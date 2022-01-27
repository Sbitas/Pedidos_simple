package view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import annotation.Tabla;
import objects.ObjetoBase;
import service.BaseService;
import service.ConnectionService;

public abstract class ventanaListadoBase<T extends ObjetoBase, ISERVICE extends BaseService<T>> {

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

	public ventanaListadoBase(T entidad) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, SQLException {
		initialize(entidad);
	}

	public void initialize(T entidad) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, SQLException {
		ConnectionService conn = new ConnectionService();

		this.frameBase(entidad);
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
	 */
	public void frameBase(T entidad) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, SQLException {
		frame = new JFrame();
		frame.setTitle(entidad.getClass().getAnnotation(Tabla.class).nombreFrame());
		frame.setBounds(100, 100, 580, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(new MenusListadosBase<T>().bar);

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
	 */
	public void objetosTablaEntidad() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, InstantiationException {

		List<T> listaElementos = this.cargaListadoObjetos();

		if (listaElementos.size() > 0) {
			int numCols = listaElementos.get(0).getClass().getAnnotation(Tabla.class).numeroCampos();
			objetosTabla = new Object[listaElementos.size()][numCols+1];
			titulosColumnas = new String[numCols+1] ;
			
			for (int i = 0; i < objetosTabla.length; i++) {
				objetosTabla[i][0] = false;
				titulosColumnas[0] = "";

				List<Method> getters = this.filtraGetters(this.dameListaPropiedades(listaElementos.get(i)),
						this.dameGettersT(listaElementos.get(i)));

				objetosTabla[i][1] = this.dameSetterPorNombrePropiedad("Id", getters).invoke(listaElementos.get(i));
				titulosColumnas[1] = "ID";

				for (int j = 0; j < numCols; j++) {
					if (!getters.get(j).getName().equals("getId")) {
						objetosTabla[i][j+2] = getters.get(j).invoke(listaElementos.get(i));
						titulosColumnas[j+2] = getters.get(j).getName().substring(3);
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

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		
		tabla.setRowSelectionAllowed(false);
	}

	// AXUILIARES

	@SuppressWarnings("deprecation")
	public List<T> cargaListadoObjetos() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, InstantiationException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1])
				.newInstance().consultaListado();
	}

	public Method dameSetterPorNombrePropiedad(String nombrePropiedad, List<Method> metodos)
			throws InstantiationException, IllegalAccessException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1])
				.newInstance().dameGetterPorNombreColumna(nombrePropiedad, metodos);
	}

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

}
