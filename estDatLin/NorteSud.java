package estDat.estDatLin;

public class NorteSud<T>
{
    private NodoDE<T> norIni, norFin;
    private NodoDE<T> sudIni, sufFin;
    private int tamNorte, tamSud;

    public NorteSud(){
        norIni = norFin = sudIni = sufFin = null;
        tamNorte = tamSud = 0;
    }

    public boolean esVacia(){
        return norIni == null && sudIni == null;
    }

    public void insertar(T dato){
        NodoDE<T> nuevo = new NodoDE<T>();
        nuevo.setDato(dato);
        if(tamNorte <= tamSud){
            //insertamos en norte
            if(norIni == null){
                norIni = norFin = nuevo;
            }else{
                norFin.setSig(nuevo);
                nuevo.setSig(norFin);
                norFin = nuevo;
            }
            tamNorte++;
            //insercion como si fuera cola, es decir, hacia el final
        }else{
            //Insertamos en sud
            if(sudIni == null){
                sudIni = sufFin = nuevo;
            }else{
                sudIni.setAnt(nuevo);
                nuevo.setSig(sudIni);
                sudIni = nuevo;
            }
            tamSud++;
            //insercion como si fuera pila, es decir, en el principio
        }
    }
    
    public T accederNorth(){
        
    }
    
    public T accederSouth(){
        
    }
    
    public boolean eliminarNorth(){
        
    }
    
    public boolean eliminarSouth(){
        
    }
}
