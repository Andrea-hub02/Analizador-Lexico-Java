import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PseudoLexer {
    private ArrayList<TipoToken> tipos = new ArrayList<>();
    private ArrayList<Token> tokens = new ArrayList<>();

    public PseudoLexer(){
        tipos.add(new TipoToken("ABSTRACT", "abstract"));
        tipos.add(new TipoToken("SYSTEM", "system"));
        tipos.add(new TipoToken("JAVA", "java"));
        tipos.add(new TipoToken("UTIL", "util"));
        tipos.add(new TipoToken("OUT", "out"));
        tipos.add(new TipoToken("PRINTLN", "println"));
        tipos.add(new TipoToken("IMPORT", "import"));
        tipos.add(new TipoToken("STRING", "string"));
        tipos.add(new TipoToken("ASSERT", "assert"));
        tipos.add(new TipoToken("BOOLEAN", "boolean"));
        tipos.add(new TipoToken("BREAK", "break"));
        tipos.add(new TipoToken("BYTE", "byte"));
        tipos.add(new TipoToken("CASE", "case"));
        tipos.add(new TipoToken("CATCH", "catch"));
        tipos.add(new TipoToken("CHAR", "char"));
        tipos.add(new TipoToken("DOUBLE", "double"));
        tipos.add(new TipoToken("CLASS", "class"));
        tipos.add(new TipoToken("CONST", "const")); // No se usa, pero está reservado
        tipos.add(new TipoToken("CONTINUE", "continue"));
        tipos.add(new TipoToken("DEFAULT", "default"));
        tipos.add(new TipoToken("DO", "do"));
        tipos.add(new TipoToken("ELSE", "else"));
        tipos.add(new TipoToken("ENUM", "enum"));
        tipos.add(new TipoToken("EXTENDS", "extends"));
        tipos.add(new TipoToken("FINAL", "final"));
        tipos.add(new TipoToken("FINALLY", "finally"));
        tipos.add(new TipoToken("FLOAT", "float"));
        tipos.add(new TipoToken("FOR", "for"));
        tipos.add(new TipoToken("GOTO", "goto")); // No se usa, pero está reservado
        tipos.add(new TipoToken("IF", "if"));
        tipos.add(new TipoToken("IMPLEMENTS", "implements"));
        tipos.add(new TipoToken("INSTANCEOF", "instanceof"));
        tipos.add(new TipoToken("INT", "int"));
        tipos.add(new TipoToken("INTERFACE", "interface"));
        tipos.add(new TipoToken("LONG", "long"));
        tipos.add(new TipoToken("NATIVE", "native"));
        tipos.add(new TipoToken("NEW", "new"));
        tipos.add(new TipoToken("NULL", "null"));
        tipos.add(new TipoToken("PACKAGE", "package"));
        tipos.add(new TipoToken("PRIVATE", "private"));
        tipos.add(new TipoToken("PROTECTED", "protected"));
        tipos.add(new TipoToken("PUBLIC", "public"));
        tipos.add(new TipoToken("RETURN", "return"));
        tipos.add(new TipoToken("SHORT", "short"));
        tipos.add(new TipoToken("STATIC", "static"));
        tipos.add(new TipoToken("STRICTFP", "strictfp"));
        tipos.add(new TipoToken("SUPER", "super"));
        tipos.add(new TipoToken("SWITCH", "switch"));
        tipos.add(new TipoToken("SYNCHRONIZED", "synchronized"));
        tipos.add(new TipoToken("THIS", "this"));
        tipos.add(new TipoToken("THROW", "throw"));
        tipos.add(new TipoToken("THROWS", "throws"));
        tipos.add(new TipoToken("TRANSIENT", "transient"));
        tipos.add(new TipoToken("TRY", "try"));
        tipos.add(new TipoToken("VOID", "void"));
        tipos.add(new TipoToken("VOLATILE", "volatile"));
        tipos.add(new TipoToken("WHILE", "while"));

        tipos.add(new TipoToken("NUMERO", "-?[0-9]+(\\.[0-9]+)?"));
        tipos.add(new TipoToken("CADENA", "\"(\\\\.|[^\"\\\\])*\""));
        tipos.add(new TipoToken("CARACTER", "\'\\\\?.\'"));
        tipos.add(new TipoToken("IDENTIFICADOR", "[a-zA-Z_][a-zA-Z0-9_]*"));
        tipos.add(new TipoToken("OPARITMETICO", "[*/+-]"));
        tipos.add(new TipoToken("OPRELACIONAL", "==|!=|<=|>=|<|>"));
        tipos.add(new TipoToken("ASIGNACION", "="));
        tipos.add(new TipoToken("PUNTOYCOMA", ";"));
        tipos.add(new TipoToken("COMA", ","));
        tipos.add(new TipoToken("PUNTO", "\\."));
        tipos.add(new TipoToken("LLAVEIZQ", "\\{"));
        tipos.add(new TipoToken("LLAVEDER", "\\}"));
        tipos.add(new TipoToken("PARENTESISIZQ", "\\("));
        tipos.add(new TipoToken("PARENTESISDER", "\\)"));
        tipos.add(new TipoToken("CORCHETEIZQ", "\\["));
        tipos.add(new TipoToken("CORCHETEDER", "\\]"));
        tipos.add(new TipoToken("ESPACIO", "[ \\t\\f\\r\\n]+"));
        tipos.add(new TipoToken("UNICODE", "[\\p{L}\\p{M}]+"));  // \p{L} para letras, \p{M} para marcas
        tipos.add(new TipoToken("ERROR", "."));
        
    }

    public ArrayList<Token> getTokens(){
        return tokens;
    }

    /* EL PROGRAMA QUE TIENE QUE ANALIZAR  */
    public void analizar(String entrada) throws LexicalException {
        StringBuffer er= new StringBuffer();

        /* CONCATENAR LOS TOKENS */
        for (TipoToken tt : tipos)
            er.append(String.format("|(?<%s>%s)", tt.getNombre(), tt.getPatron()));


        Pattern p = Pattern.compile(new String(er.substring(1)));

        Matcher m = p.matcher(entrada);

        while(m.find()){
            for (TipoToken tt: tipos){
                if(m.group("ESPACIO") != null)
                    continue;
                else if (m.group(tt.getNombre()) != null){
                    if (tt.getNombre().equals("ERROR"))
                        throw new LexicalException(m.group(tt.getNombre()));

                    String nombre = m.group(tt.getNombre());

                    if (tt.getNombre().equals("CADENA")){
                        nombre = nombre.substring(1, nombre.length()-1);
                    }

                    tokens.add(new Token(tt, nombre));
                    break;
                }
            }
        }
    }

    
    
}

