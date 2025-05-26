package estDat.estDatLin;
public class ListaCSE<T> implements Lista<T>{
    private int size;
    private NodoSE<T> ini;

    public ListaCSE(){
        size = 0;
        ini = null;
    }

    public boolean estaVacia(){
        return (ini == null);
    }

    public int getSize(){
        return size;
    }

    public T insertar(T nDato){
        T res;
        NodoSE<T> p = new NodoSE<T>();
        p.setDato(nDato);
        if(estaVacia()){
            ini = p;
            p.setSig(ini);
            size++;
            res = p.getDato();
        }else{
            res = insertar(nDato, ini);
        }
        return res;
    }

    private T insertar(T nDato, NodoSE<T> actual){
        T res;
        NodoSE<T> p = new NodoSE<T>();
        p.setDato(nDato);
        if(actual.getSig().equals(ini)){
            actual.setSig(p);
            p.setSig(ini);
            size++;
            res = p.getDato();
        }else{
            res = insertar(nDato, actual.getSig());
        }
        return res;
    }

    public T acceder(int pos){
        T res;
        if(!estaVacia() && pos < size){
            if(pos == 0){
                res = ini.getDato();
            }else{
                res = acceder(pos-1, ini.getSig());
            }
        }else{
            res = null;
        }
        return res;
    }

    private T acceder(int pos, NodoSE<T> actual){
        T res;
        if(pos == 0){
            res = actual.getDato();
        }else{
            res = acceder(pos-1, actual.getSig());
        }
        return res;
    }

    public boolean vaciar(){
        boolean res;
        ini = null;
        size = 0;
        res = (ini == null);
        return res;
    }

    public T eliminar(T nDato){
        T res;
        if(size == 1){
            res = ini.getDato();
            ini = null;
            size--;
        }else{
            res = eliminar(nDato, ini);
        }
        return res;
    }
    
    private T eliminar(T nDato, NodoSE<T> actual){
        T res;
        if(!estaVacia()){
            if(nDato.equals(actual.getDato())){
                res = actual.getDato();
                actual.setDato(actual.getSig().getDato());
                actual.setSig(actual.getSig().getSig());
                size--;
            }else{
                res = eliminar(nDato, actual.getSig());
            }
        }else{
            res = null;
        }
        return res;
    }

    private NodoSE<T> buscar(NodoSE<T> inicio, T nDato){
        NodoSE<T> res;
        if(inicio.getDato().equals(nDato)){
            res = inicio;
        }else{
            if(inicio.getSig().equals(ini)){
                res = null;
            }else{
                res = buscar(inicio.getSig(), nDato);
            }
        }
        return res;
    }

    private NodoSE<T> buscar(NodoSE<T> inicio){
        NodoSE<T> res;
        if(inicio.getSig() == ini){
            res = inicio;
        }else{
            res = buscar(inicio.getSig());
        }
        return res;
    }
}