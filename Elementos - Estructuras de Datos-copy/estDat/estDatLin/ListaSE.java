package estDat.estDatLin;
public class ListaSE<T> implements Lista<T>{
    private int size;
    private T dato;
    private ListaSE<T> sig;

    public ListaSE(){
        size = 0;
        dato = null;
        sig = null;
    }

    public boolean estaVacia(){
        return (dato == null) && (sig == null);
    }

    public int getSize(){
        return size;
    }

    public T insertar(T nDato){
        T res;
        if(estaVacia()){
            dato = nDato;
            sig = new ListaSE<T>();
            size++;
            res = dato;
        }else{
            res = insertar(nDato, sig);
        }
        return res;
    }

    private T insertar(T nDato, ListaSE<T> actual){
        T res;
        if(actual.dato == null){
            actual.dato = nDato;
            actual.sig = new ListaSE<T>();
            size++;
            res = actual.dato;
        }else{
            res = insertar(nDato, actual.sig);
        }
        return res;
    }

    public T acceder(int pos){
        T res;
        if(!estaVacia() && pos < size){
            if(pos == 0){
                res = dato;
            }else{
                res = acceder(pos-1, sig);
            } 
        }else{
            res = null;
        }
        return res;
    }

    private T acceder(int pos, ListaSE<T> actual){
        T res;
        if(pos == 0){
            res = actual.dato;
        }else{
            res = acceder(pos-1, actual.sig);
        }
        return res;
    }
    
    public boolean vaciar(){
        boolean res;
        size = 0;
        dato = null;
        sig = null;
        res = true;
        return res;
    }
    
    public T eliminar(T nDato){
        T res;
        res = eliminar(nDato, this);
        return res;
    }
    
    public T eliminar(T nDato, ListaSE<T> actual){
        T res;
        if(!estaVacia()){
            if(nDato.equals(actual.dato)){
                res = actual.dato;
                actual.dato = actual.sig.dato;
                actual.sig = actual.sig.sig;
                size--;
            }else{
                res = eliminar(nDato, actual.sig);
            }
        }else{
            res = null;
        }
        return res;
    }
    
    public int calculadorPos(int pos){
        int res;
        res = calculadorPos2(pos);
        return res;
    }
    
    private int calculadorPos2(int n){
        int res;
        if(n == 0){
            res = 0;
        }else{
            res = calculadorPos2(n-1) + n;
        }
        return res;
    }
    
    public ListaSE<T> saltoNatural(int pos){
        ListaSE<T> lista = new ListaSE<T>();
        saltoNatural(lista, 1, pos);
        return lista;
    }
    
    private void saltoNatural(ListaSE<T> lista, int i, int pos){
        if(i == pos){
            int p = calculadorPos(i);
            lista.insertar(this.acceder(p));
        }else{
            int p = calculadorPos(i);
            lista.insertar(this.acceder(p));
            saltoNatural(lista, i+1, pos);
        }
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
    
    public T accederUltimo(){
        T res;
        if(!estaVacia()){
            if(sig.dato == null){
                res = this.dato;
            }else{
                res = accederUltimo(sig);
            }
        }else{
            res = null;
        }
        return res;
    }
    
    private T accederUltimo(ListaSE<T> actual){
        T res;
        if(actual.sig.dato == null){
            res = actual.dato;
        }else{
            res = accederUltimo(actual.sig);
        }
        return res;
    }
    
    public T insertarPos(int pos, T nDato){
        T res;
        ListaSE<T> copia = new ListaSE<T>(); 
        if(pos < size && pos >= 0 ){
            if(pos == 0){
                copia.insertar(dato);
                copia.sig = sig;
                dato = nDato;
                sig = copia;
                size++;
                res = dato;
            }else{
                res = sig.insertarPos(pos-1, nDato);
            }
        }else if(size == 0 && pos == 0){
            insertar(nDato);
            res = dato;
        }else{
            res = null;
        }
        return res;
    }
    
    public T accederAnteriorPos(int pos){
        T res;
        if(pos == 0 || pos >= size){
            res = null;
        }else{
            res = acceder(pos-1);
        }
        return res;
    }
    
    public T antecesor(T dato){
        T res;
        ListaSE<T> antecesor = new ListaSE<T>();
        ListaSE<T> actual = this;
        boolean encontre = false;
        while(!actual.estaVacia() && !encontre){
            if(actual.dato.equals(dato)){
                encontre = true;
            }else{
                antecesor = actual;
                actual = actual.sig;
            }
        }
        if(encontre){
            res = antecesor.dato;
        }else{
            res = null;
        }
        return res;
    }
    
    public boolean insertarSiNoEsta(T nDato){
        boolean res;
        if(estaVacia()){
            dato = nDato;
            sig = new ListaSE<T>();
            res = true;
        }else{
            if(dato.equals(nDato)){
                res = false;
            }else{
                res = sig.insertarSiNoEsta(nDato);
            }
        }
        return res;
    }
    
    public ListaSE<T> distancia(T nDato, int n){
        ListaSE<T> res = new ListaSE<T>();
        if(!estaVacia() && n > 0){
            ListaSE<T> listaAux = buscarEnLista(nDato);
            if(listaAux != null && listaAux.sig != null){
                listaAux.sig.llenarDatos(res, n);
            }
        }
        return res;
    }

    public void llenarDatos(ListaSE<T> res, int n){
        if(!estaVacia() && n > 0) {
            res.insertar(dato);
            sig.llenarDatos(res, n - 1);
        }
    }
    
     public ListaSE<T> buscarEnLista(T nDato){
        ListaSE<T> res = null;
        if(!estaVacia()){
            if(dato.equals(nDato)){
                res = this;
            }else{
                res = sig.buscarEnLista(nDato);
            }
        }
        return res;
    }
    
    public boolean buscar(T nDato){
        boolean res;
        T comparable;
        if(estaVacia()){ 
            res = false;
        }else{
            comparable = dato;
            if(dato.equals(comparable)){
                res = true;
            }else{
                res = sig.buscar(nDato);
            }
        }
        return res;
    }
    
    public T eliminarPos(int pos){
        T res;
        if(estaVacia()){
            res = null;
        }else{
            if(pos == 0){
                res = dato;
                dato = sig.dato;
                sig = sig.sig;
                size--;
            }else{
                res = sig.eliminarPos(pos-1);
            }
        }
        return res;
    }
}