public class Analizar {
    
    public static void main(String[] args) {
        int numeroDeElementos;
        double promedio = 0;
        double elemento;

        
        if (args.length < 1) {
            System.out.println("Por favor, ingrese los elementos como argumentos de línea de comandos.");
            return;
        }

        numeroDeElementos = args.length;
        
        
        for (int i = 0; i < numeroDeElementos; i++) {
            try {
                elemento = Double.parseDouble(args[i]);
                promedio = promedio + elemento;
            } catch (NumberFormatException e) {
                System.out.println("El argumento " + args[i] + " no es un número válido.");
                return;
            }
        }
        
        if (numeroDeElementos > 0) {
            promedio = promedio / numeroDeElementos;
        }
        
        System.out.println("El promedio es: " + promedio);
    }
}

