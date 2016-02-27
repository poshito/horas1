package co.horas.labadm.services;

/**
 *
 * @author Zacehiro
 */
public class ServicesFacadeException extends Exception{
    public static final String PROBLEMA_BASE_DATOS="Ha ocurrido un error al leer en la base de datos";
    public static final String PROBLEMA_GUARDAR_SOLICITUD="Recuerde que si la solicitud se aprueba, debe agregar una fecha de realización y dejar la justificación en blanco y viceversa en caso contrario.";
    public static final String PROBLEMA_SOLICITUD_NO_SELECCIONADA="Seleccione una solicitud para darle respuesta.";
    public static final String NO_CRITERIA_OR_EMPTY="No existen datos para los criterios dados o la base de datos esta vacia";
    public static final String WRONG_LINK_TYPED="Los links ingresados a la solicitud son incorrectos";
    public ServicesFacadeException() {
    }

    public ServicesFacadeException(String message) {
        super(message);
    }
    public ServicesFacadeException(String message, Throwable cause){
        super(message, cause);
    }
}
