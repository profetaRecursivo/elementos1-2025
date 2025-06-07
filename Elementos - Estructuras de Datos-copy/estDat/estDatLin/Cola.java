package estDat.estDatLin;
public class Cola<T>{
    protected NodoDE<T> ini;
    protected NodoDE<T> fin;
    
    public Cola(){
        ini = null;
        fin = null;
    }
    
    public boolean estaVacia(){
        boolean res;
        res = (ini == null);
        return res;
    }
    
    public void encolar(T nDato){
        if(estaVacia()){
            NodoDE<T> p = new NodoDE<T>();
            p.setDato(nDato);
            ini = p;
            fin = p;
        }else{
            NodoDE<T> p = new NodoDE<T>();
            p.setDato(nDato);
            fin.setSig(p);
            p.setAnt(fin);
            fin = p;
        }
    }
    
    public T decolar(){
        T res;
        if(!estaVacia()){
            res = ini.getDato();
            ini = ini.getSig();
            if(ini == null){
                fin = null;
            }else{
                ini.setAnt(null);
            }
        }else{
            res = null;
        }
        return res;
    }
    
    public T top(){
        T res;
        if(!estaVacia()){
            res = ini.getDato();
        }else{
            res = null;
        }
        return res;
    }
}