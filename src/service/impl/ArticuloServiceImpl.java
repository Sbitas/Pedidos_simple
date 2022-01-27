package service.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import constants.EnumColoresSudaderaConCapuchaBasica;
import constants.EnumTipoArticulo;
import objects.Articulo;
import service.IArticuloService;

public class ArticuloServiceImpl implements IArticuloService {
	
	String SELECT_ARTICULOS = "SELECT * FROM articulos";
	
	@Override
	public Articulo dameArticuloFromDobleClickEnColumna(int idArticuloColumna, List<Articulo> listaArticulos) {
		Articulo retorno = null;
		for (Articulo a : listaArticulos) {
			if (a.getId() == idArticuloColumna)
				retorno = a;
		}

		return (retorno != null ? retorno : null);
	}

	public ArrayList<Articulo> obtenerListaArticulos() throws SQLException {
		ArrayList<Articulo> articulos = new ArrayList<>();

		DbConnection dbConnection = new DbConnection();

		ResultSet result = dbConnection.getStatement().executeQuery(SELECT_ARTICULOS);
		ResultSetMetaData resultSetMetaData = result.getMetaData();

		while (result.next()) {
			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i = i + 4) {

				Articulo articulo = new Articulo();
				articulo.setId(result.getInt(i));
				articulo.setTipo(EnumTipoArticulo.convert(result.getInt(i + 1)));
				articulo.setColor(this.dameValorEnumEnFuncionTipoArticulo(articulo.getTipo(), result.getInt(i + 2)));
				articulo.setPrecioBase(result.getBigDecimal(i + 3));

				articulos.add(articulo);
			}
		}
		return articulos;
	}

	/**
	 * En función del tipo de artículo que sea obtiene el color de un enum u otro
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Enum dameValorEnumEnFuncionTipoArticulo(EnumTipoArticulo tipoArticulo, Integer codigoColor) {
		Enum color = null;

		switch (tipoArticulo) {
		case SUDADERA_BASICA:
			return EnumColoresSudaderaConCapuchaBasica.convert(codigoColor);
		case SUDADERA_BICOLOR:
		case CAMISETA:
		case GORRO:
		case FORRO_POLAR:
		default:
			break;
		}
		return color;
	}
	
	public void guardaCambiosArticulo(Articulo articulo) {

	}
}
