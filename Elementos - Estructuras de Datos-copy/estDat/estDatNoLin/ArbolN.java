package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolN<T>
{
    private T raiz;
    private Lista<ArbolN<T>> hijos;
    private int grado;
    public ArbolN(int grado){
        this.grado = grado;
        raiz = null;
        hijos = new ListaDE<ArbolN<T>>();
    }
    
    public boolean esVacia(){
        return raiz == null;
    }
    
    public void insertarAmp(T dato){
        Cola<ArbolN<T>> cola = new Cola<ArbolN<T>>();
        cola.encolar(this);
        insertarAmp(dato, cola);
    }
    
    private void insertarAmp(T dato, Cola<ArbolN<T>> cola){
        ArbolN<T> arb = cola.decolar();
        if(arb.esVacia()){
            arb.raiz = dato;
            for(int i = 0; i<grado; i++){
                arb.hijos.insertar(new ArbolN<T>(grado));
            }
        }else{
            for(int i = 0; i<grado; i++){
                cola.encolar(arb.hijos.acceder(i));
            }
            insertarAmp(dato, cola);
        }
    }
}
