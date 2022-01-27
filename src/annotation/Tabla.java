package annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Tabla {
	
	/**
	 * Nombre de la tabla
	 * @return nombre
	 */
	public String nombreTabla() default "s";
	
	/**
	 * Número de columnas que tiene la entidad
	 * @return num
	 */
	public int numeroCampos() default 0;
	
	/**
	 * Titulo del frame
	 * @return titulo
	 */
	public String nombreFrame() default "s";
}
