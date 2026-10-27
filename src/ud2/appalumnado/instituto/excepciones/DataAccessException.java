package ud2.appalumnado.instituto.excepciones;

/**
 * DataAccessException
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
