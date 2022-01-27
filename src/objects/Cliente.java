package objects;

import annotation.Tabla;

@Tabla(nombreTabla = "clientes", numeroCampos = 4, nombreFrame = "CLIENTES")
public class Cliente extends ObjetoBase{
	
	private String nombre;
	
	private String direccion;
	
	private String contacto;

	public Cliente() {
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
}
