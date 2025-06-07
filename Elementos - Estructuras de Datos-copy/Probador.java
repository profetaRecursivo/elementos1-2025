import estDat.estDatLin.*;
public class Probador
{
    public void probar1(){
        Lista<Integer> secuencia = new ListaDE<Integer>();
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(4);
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(4);
        secuencia.insertar(4);
        secuencia.insertar(8);
        secuencia.insertar(16);
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(2);
        Secuencia2 solver = new Secuencia2();
        Lista<Integer> respuesta =solver.reducir(secuencia);
        for(int i = 0; i<respuesta.getSize(); i++){
            System.out.print(respuesta.acceder(i) + " ");
        }
    }
}
