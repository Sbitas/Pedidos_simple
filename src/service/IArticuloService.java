package service;

import java.util.List;

import objects.Articulo;

/**
 * Interfaz para las acciones sobre los art�culos
 * @author Sbita
 *
 */
public interface IArticuloService {
	
	public Articulo dameArticuloFromDobleClickEnColumna(int idArticuloColumna, List<Articulo> listaArticulos);
}
