/* La clase Token permite crear objetos que representen cada unidad lexica 
del programa fuente. Para cada Token se debe almacenar el nombre y el tipo  */

/* - NOMBRE
 * - TIPO DE TOKEN 
 */
public class Token {
    private TipoToken tipo;
    private String nombre;

    public Token(TipoToken tipo, String nombre){
        this.tipo = tipo;
        this.nombre = nombre;

    }

    public TipoToken getTipo(){
        return tipo;
    }

    public String getNombre(){
        return nombre;
    }

    public String toString(){
        return String.format("<%s, \"%s\">", tipo.getNombre(), nombre);
    }
}