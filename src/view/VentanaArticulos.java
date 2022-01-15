package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objects.Articulo;
import service.ConnectionService;
import service.impl.ArticuloServiceImpl;

public class VentanaArticulos {

	private JFrame frmSimplewear;

	/**
	 * Tabla en la que pinto el listado de articulos
	 */
	private JTable tablaArticulos;

	/**
	 * Datos de la tabla de art
	 */
	private Object[][] tablaArt;

	/**
	 * Lista de artículos seleccionados
	 */
	private ArrayList<Integer> idsArtSeleccionados = new ArrayList<Integer>();

	/**
	 * Hace referencia a si ya se ha pulsado el botón de seleccionar todos
	 */
	private boolean allSelected;

	public Object[][] getTablaArt() {
		return tablaArt;
	}

	public void setTablaArt(Object[][] tablaArt) {
		this.tablaArt = tablaArt;
	}

	public boolean isAllSelected() {
		return allSelected;
	}

	public void setAllSelected(boolean allSelected) {
		this.allSelected = allSelected;
	}

	public JFrame getFrame() {
		return frmSimplewear;
	}

	public void setFrame(JFrame frame) {
		this.frmSimplewear = frame;
	}

	public JTable getTablaArticulos() {
		return tablaArticulos;
	}

	public void setTablaArticulos(JTable tablaArticulos) {
		this.tablaArticulos = tablaArticulos;
	}

	public ArrayList<Integer> getIdsArtSeleccionados() {
		return idsArtSeleccionados;
	}

	public void setIdsArtSeleccionados(ArrayList<Integer> idsArtSeleccionados) {
		this.idsArtSeleccionados = idsArtSeleccionados;
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public VentanaArticulos() throws SQLException, IOException {
		ConnectionService conn = new ConnectionService();
		initialize(conn.obtenerListaArticulos(), conn);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize(List<Articulo> articulos, ConnectionService conn) throws IOException {
		frmSimplewear = new JFrame();
		frmSimplewear.setTitle("SIMPLE_WEAR");
		frmSimplewear.setBounds(100, 100, 582, 300);
		frmSimplewear.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.LIGHT_GRAY);
		frmSimplewear.setJMenuBar(menuBar);

		menuBar.add(this.buttonSelectAll());

		menuBar.add(this.buttonAddArticulo());

		menuBar.add(this.buttonEditArticulo());

		menuBar.add(this.buttonRemoveArticulo(conn));

		JScrollPane panelPrincipal = new JScrollPane();
		panelPrincipal.setBounds(100, 100, 582, 300);
		frmSimplewear.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		tablaArt = new Object[articulos.size()][5];
		for (int i = 0; i < tablaArt.length; i++) {
			tablaArt[i][0] = false;
			tablaArt[i][1] = articulos.get(i).getId();
			tablaArt[i][2] = articulos.get(i).getTipo().getNombre();
			tablaArt[i][3] = articulos.get(i).getColor().toString();
			tablaArt[i][4] = articulos.get(i).getPrecioBase();
		}
		DefaultTableModel defaultTableModel = new DefaultTableModel(tablaArt,
				new String[] { "", "ID", "Tipo", "Color", "Precio" });

		tablaArticulos = new JTable(defaultTableModel) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
				case 2:
				case 3:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}

		};

		// Doble click
		tablaArticulos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {
					JTable table = (JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);
					try {
						ArticuloServiceImpl service = new ArticuloServiceImpl();
						Articulo articuloVista = service.dameArticuloFromDobleClickEnColumna(
								(int) tablaArticulos.getValueAt(row, 1), articulos);
						if (articuloVista != null) {
							VentanaDetallaEdicionArticulo ventana = new VentanaDetallaEdicionArticulo(articuloVista);
							ventana.getFrame().setVisible(true);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Anchos columnas

		tablaArticulos.getColumnModel().getColumn(0).setMinWidth(25);
		tablaArticulos.getColumnModel().getColumn(0).setWidth(25);
		tablaArticulos.getColumnModel().getColumn(1).setWidth(25);
		tablaArticulos.getColumnModel().getColumn(1).setMinWidth(25);
		tablaArticulos.getColumnModel().getColumn(2).setWidth(150);
		tablaArticulos.getColumnModel().getColumn(2).setMinWidth(150);
		tablaArticulos.getColumnModel().getColumn(3).setWidth(100);
		tablaArticulos.getColumnModel().getColumn(3).setMinWidth(100);
		tablaArticulos.getColumnModel().getColumn(4).setWidth(150);
		tablaArticulos.getColumnModel().getColumn(4).setMinWidth(150);

		tablaArticulos.setRowSelectionAllowed(false);
		tablaArticulos.setBounds(10, 11, 464, 214);
		panelPrincipal.setViewportView(tablaArticulos);

	}

	/**
	 * Botón para agregar artículo
	 * 
	 * @return
	 * @throws IOException
	 */
	private JButton buttonAddArticulo() throws IOException {
		JButton btnAddArticulo = new JButton();
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource("/resources/crear.png"));
		btnAddArticulo.setIcon(new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		btnAddArticulo.setMargin(new Insets(0, 0, 0, 0));
		btnAddArticulo.setOpaque(false);
		btnAddArticulo.setContentAreaFilled(false);
		return btnAddArticulo;
	}

	/**
	 * Botón para editar un artículo seleccionado
	 * 
	 * @return
	 * @throws IOException
	 */
	private JButton buttonEditArticulo() throws IOException {
		JButton btnEditArt = new JButton("");
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource("/resources/edit.png"));
		btnEditArt.setIcon(new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		btnEditArt.setMargin(new Insets(0, 0, 0, 0));
		btnEditArt.setOpaque(false);
		btnEditArt.setContentAreaFilled(false);
		return btnEditArt;
	}

	private JButton buttonRemoveArticulo(ConnectionService conn) throws IOException {
		JButton btnDeleteArticulo = new JButton("");
		btnDeleteArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < tablaArticulos.getRowCount(); i++) {

					if ((boolean) tablaArticulos.getValueAt(i, 0)) {
						if (!yaSeleccionado((Integer) tablaArt[i][1])) {
							idsArtSeleccionados.add((Integer) tablaArt[i][1]);
						}
					} else {
						idsArtSeleccionados.remove((Integer) tablaArt[i][1]);
					}
				}
				conn.removeArticulosSeleccionados(idsArtSeleccionados);
			}
		});
		Image icono = null;
		icono = ImageIO.read(this.getClass().getResource("/resources/remove.png"));
		btnDeleteArticulo.setIcon(new ImageIcon(icono.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		btnDeleteArticulo.setMargin(new Insets(0, 0, 0, 0));
		btnDeleteArticulo.setOpaque(false);
		btnDeleteArticulo.setContentAreaFilled(false);
		return btnDeleteArticulo;
	}

	private boolean yaSeleccionado(int id) {
		boolean retorno = false;
		for (int ids : idsArtSeleccionados) {
			if (ids == id) {
				retorno = true;
			}
		}
		return retorno;
	}

	private JCheckBox buttonSelectAll() throws IOException {
		JCheckBox checkbox = new JCheckBox();
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				allSelected = !allSelected;

				for (int i = 0; i < tablaArt.length; i++) {
					tablaArticulos.setValueAt(allSelected, i, 0);
					if (allSelected) {
						idsArtSeleccionados.add((Integer) tablaArt[i][1]);
					} else {
						idsArtSeleccionados.remove((Integer) tablaArt[i][1]);
					}
				}
			}
		});
		return checkbox;
	}
}
