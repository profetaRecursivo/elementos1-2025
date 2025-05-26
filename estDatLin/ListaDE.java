package estDat.estDatLin;
public class ListaDE<T> implements Lista<T>{
    private int size;
    private NodoDE<T> ini;

    public ListaDE(){
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
        NodoDE<T> p = new NodoDE<T>();
        p.setDato(nDato);
        if(estaVacia()){
            ini = p;
            size++;
            res = p.getDato();
        }else{
            res = insertar(nDato, ini);
        }
        return res;
    }

    private T insertar(T nDato, NodoDE<T> actual){
        T res;
        NodoDE<T> p = new NodoDE<T>();
        p.setDato(nDato);
        if(actual.getSig() == null){
            actual.setSig(p);
            p.setAnt(actual);
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

    private T acceder(int pos, NodoDE<T> actual){
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
        NodoDE<T> q, p, r;
        if(!estaVacia()){
            q = buscar(ini, nDato);
            p = q.getAnt();
            r = q.getSig();
            if(q.equals(ini)){
                ini = r;
                if(ini != null){
                    ini.setAnt(null); 
                }
            }else{
                if(r != null){
                    p.setSig(r);
                    r.setAnt(p);
                }else{
                    p.setSig(r);
                }
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
            res = buscar(inicio.getSig(), nDato);
        }
        return res;
    }

    public String split(int n){
        String mensaje = "";
        ListaDE<ListaDE<T>> res = new ListaDE<ListaDE<T>>();
        if(n <= this.getSize()){
            while(!estaVacia()){
                ListaDE<T> actual = new ListaDE<T>();
                for(int i = 0; i < n; i++){
                    actual.insertar(this.acceder(0));
                    this.eliminar(this.acceder(0));
                }
                res.insertar(actual);
            }
            for(int i = 0; i < res.getSize(); i++){
                mensaje = mensaje + "" + res.acceder(i).toStringDE();
            }
        }else{
            mensaje = "";
        }
        return mensaje;
    }

    public String toStringDE(){
        String res;
        if(size == 0){
            res = "[]";
        }else{
            res = "[" + toStringDE(0);
        }
        return res;
    }

    private String toStringDE(int i){
        String res;
        if(i == size-1){
            res = "" + this.acceder(i) + "]";
        }else{
            res = "" + this.acceder(i) + ", " + toStringDE(i+1);
        }
        return res;
    }
    
    public void insertar(int pos, T dato){
        //Insertar un dato a partir de una posicion gaaaaaç
        if(pos >= 0 && pos < getSize()){
            NodoDE<T> ac = ini;
            NodoDE<T> aux = new NodoDE<T>();
            aux.setDato(dato);
            while(pos > 0){
                ac = ac.getSig();
                pos--;
            }
            if(ac == ini){
                //estamos comparandop direcciones de memoria x si acaso
            }else{
                ac.getAnt().setSig(aux);
                aux.setAnt(ac.getAnt());
                aux.setSig(ac);
                ac.setAnt(aux);
            }
        }
    }
}