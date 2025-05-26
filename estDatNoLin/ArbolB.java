package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolB<T>{
    private T raiz;
    private ArbolB<T> izq, der;

    public ArbolB(){
        izq = der = null;
        raiz = null;
    }

    public boolean esVacia(){
        boolean res;
        res = (raiz == null);
        return res;
    }

    public boolean insertar(Lista<T> ruta, T dato){
        return insertar(ruta, dato, 0, ruta.getSize());
    }

    private boolean insertar(Lista<T> ruta, T dato, int pos, int longitud){
        boolean res;
        if(pos >= longitud){
            res = true;
            if(esVacia()){
                //Puedo insertar
                raiz = dato;
                izq = new ArbolB<T>();
                der = new ArbolB<T>();
            }else{
                //recorrer
                T raizCopia = raiz;
                ArbolB<T> izqCopia = izq, derCopia = der;
                raiz = dato;
                izq = new ArbolB<T>();
                der = new ArbolB<T>();
                izq.raiz = raizCopia;
                izq.izq = izqCopia;
                izq.der = derCopia;
            }
        }else{
            T cabezaRuta = ruta.acceder(pos);
            if(!esVacia()){
                if(cabezaRuta.equals(raiz)){
                    res = izq.insertar(ruta, dato, pos+1, longitud);
                    if(!res){
                        res = der.insertar(ruta, dato, pos+1, longitud);
                    }
                }else{
                    res = false;
                }
            }else{
                res = false;
            }
        }
        return res;
    }
    public boolean esHoja(){
        return izq.esVacia() && der.esVacia();
    }
    
    public boolean esEstrictamenteBinario(){
        boolean res;
        if(esVacia()){
            res = true;
        }else{
            if(esHoja()){
                res = true;
            }else{
                if(!izq.esVacia() && !der.esVacia()){
                    res = izq.esEstrictamenteBinario() && der.esEstrictamenteBinario();
                }else{
                    res = false;
                }
            }
        }
        return res;
    }
    
    public void podarPorNivel(int n){
        podarPorNivel(1, n);
    }
    
    private void podarPorNivel(int actual, int n){
        if(!esVacia()){
            if(actual == n){
                izq = new ArbolB<T>();
                der = new ArbolB<T>();
            }else{
                izq.podarPorNivel(actual+1, n);
                der.podarPorNivel(actual+1, n);
            }
        }
    }
}
