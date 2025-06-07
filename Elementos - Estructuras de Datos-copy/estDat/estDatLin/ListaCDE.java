package estDat.estDatLin;
public class ListaCDE<T> implements Lista<T>{
    private int size;
    private NodoDE<T> ini;
    
    public boolean estaVacia(){
        return (ini == null);
    }
    
    public int getSize(){
        return size;
    }
    
    public T insertar(T nDato){
        T res;
        NodoDE<T> p = new NodoDE<T>();
        p.setDato(nDato);
        NodoDE<T> fin;
        if(estaVacia()){
            ini = p;
            p.setSig(ini);
            p.setAnt(ini);
            size++;
            res = p.getDato();
        }else{
            fin = ini.getAnt();
            fin.setSig(p);
            p.setAnt(fin);
            p.setSig(ini);
            ini.setAnt(p);
            size++;
            res = p.getDato();
        }
        return res;
    }
    
    public T acceder(int pos){
        T res;
        res = acceder(pos, ini);
        return res;
    }
    
    public T acceder(int pos, NodoDE<T> actual){
        T res;
        if(!estaVacia() && pos < size){
            if(pos == 0){
                res = actual.getDato();
            }else{
                res = acceder(pos-1, actual.getSig());
            }
        }else{
            res = null;
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
        NodoDE<T> q, p, r;
        if(!estaVacia()){
            q = buscar(ini, nDato);
            p = q.getAnt();
            r = q.getSig();
            p.setSig(r);
            r.setAnt(p);
            if(q == ini){
                ini = r;
            }
            if(q == r){
                ini = null;
            }
            size--;
            res = q.getDato();
        }else{
            res = null;
        }
        return res;
    }
    
    private NodoDE<T> buscar(NodoDE<T> inicio, T nDato){
        NodoDE<T> res;
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
}