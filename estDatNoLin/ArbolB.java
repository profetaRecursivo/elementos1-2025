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

    public boolean sempeso(ArbolB<Integer> a, ArbolB<Integer> b, int n){
        Par<Boolean, Par<Integer, Integer>> parsito;
            //<SonIsomorfos, <suma a, suma b>>
            parsito = recorrerEstructura(a, b);
        boolean res = parsito.first && (Math.abs(parsito.second.first - parsito.second.second) <= n);
        return res;
    }
    
    private Par<Boolean, Par<Integer, Integer>> recorrerEstructura(ArbolB<Integer> a, ArbolB<Integer> b){
        Par<Boolean, Par<Integer, Integer>> res;
        if(a.esVacia() == b.esVacia()){
            if(a.esVacia()){
                //
                res = new Par<Boolean, Par<Integer, Integer>>(true, new Par<Integer, Integer>(0, 0));
            }else{
                Par<Boolean, Par<Integer, Integer>> izquierdos = recorrerEstructura(a.izq, b.izq);
                //la suma a.izq, b.izq
                Par<Boolean, Par<Integer, Integer>> derechos = recorrerEstructura(a.der, b.der);
                //la suma a.der, b.der
                int sumaTotalA = izquierdos.second.first + derechos.second.first + a.raiz;
                int sumaTotalB = izquierdos.second.second + derechos.second.second + b.raiz;
                boolean isomorfos = izquierdos.first && derechos.first;
                res = new Par<Boolean, Par<Integer, Integer>>
                (isomorfos, new Par<Integer, Integer>(sumaTotalA, sumaTotalB));
            }
        }else{
            res = new Par<Boolean, Par<Integer, Integer>>(false, new Par<Integer, Integer>(-1, -1));
        }
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
