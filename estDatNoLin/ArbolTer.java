package estDat.estDatNoLin;
import estDat.estDatLin.*;

/**
 * Write a description of class ArbolTer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArbolTer<T>
{
    private T raiz;
    private ArbolTer<T> izq;
    private ArbolTer<T> centro;
    private ArbolTer<T> der;
    
    public ArbolTer(){
        raiz = null;
        izq = centro = der = null;
    }
    
    public boolean esVacio(){
        return raiz == null;
    }
    
    public void insertar(T dato){
        Cola<ArbolTer<T>> cola = new Cola<ArbolTer<T>>();
        cola.encolar(this);
        boolean inserte = false;
        while(!inserte){
            ArbolTer<T> arb = cola.decolar();
            if(arb.esVacio()){
                arb.raiz = dato;
                arb.izq = new ArbolTer<T>();
                arb.centro = new ArbolTer<T>();
                arb.der = new ArbolTer<T>();
                inserte = true;
            }else{
                cola.encolar(arb.izq);
                cola.encolar(arb.centro);
                cola.encolar(arb.der);
            }
        }
    }
}
