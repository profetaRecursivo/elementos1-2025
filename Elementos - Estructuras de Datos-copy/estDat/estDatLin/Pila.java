package estDat.estDatLin;
public class Pila<T>{
    private T tope;
    private Pila<T> base;
    
    public Pila(){
        tope = null;
        base = null;
    }
    
    private Pila(T tope, Pila<T> base){
        this.tope = tope;
        this.base = base;
    }
    
    public boolean estaVacia(){
        boolean res;
        res = (tope == null);
        return res;
    }
    
    public void push(T nDato){
        if(estaVacia()){
            tope = nDato;
            base = new Pila<T>();
        }else{
            base = new Pila<T>(tope, base);
            tope = nDato;
        }
    }
    
    public T pop(){
        T res;
        if(!estaVacia()){
            T aux = tope;
            tope = base.tope;
            base = base.base;
            res = aux;
        }else{
            res = null;
        }
        return res;
    }
    
    public T top(){
        return tope;
    }
}