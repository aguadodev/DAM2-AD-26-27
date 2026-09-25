package ud1.ejemplos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EjemploSerializacionObjetosCompuestos {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("DATOS/rectangulos.dat"));
        salida.writeObject(new Rectangulo(3, 4, 5, 8));
        salida.writeObject(new Rectangulo(6, 1, 5, 8));
        salida.writeObject(new Rectangulo(2, 4, 5, 8));
        salida.close();


        System.out.println("leyendo el fichero");
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("DATOS/rectangulos.dat"));
        try {
            while (true) {
                Rectangulo Rectentrada = (Rectangulo) entrada.readObject();
                System.out.println(Rectentrada);
            }
        } // Si llega al final se produce una excepción IOException
        catch (IOException e) {
            System.out.println("FIN de Fichero");
        }
        entrada.close();
    }        

}


    class Punto implements Serializable {
        private static final long serialVersionUID = 1L;

        int x, y;

        Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Punto [x=" + x + ", y=" + y + "]";
        }
        
    }

    class Rectangulo implements Serializable {
        private static final long serialVersionUID = 1L;
        private int ancho;
        private int alto;
        private Punto origen;

        public Rectangulo(int x, int y, int ancho, int alto) {
            origen = new Punto(x, y);
            this.ancho = ancho;
            this.alto = alto;
        }

        public int getX() {
            return origen.getX();
        }

        public int getY() {
            return origen.getY();
        }

        public int getAncho() {
            return ancho;
        }

        public int getAlto() {
            return alto;
        }

        @Override
        public String toString() {
            return "Rectangulo [ancho=" + ancho + ", alto=" + alto + ", origen=" + origen + "]";
        }
        
    }
