package estDat.estDatLin;

public class NorteSud<T>
{
    private NodoDE<T> norteIni;
    private NodoDE<T> norteFin;
    private NodoDE<T> sudIni;
    private NodoDE<T> sudFin;
    private int tamNorte;
    private int tamSur;

    public NorteSud(){
        norteIni = norteFin = sudIni = sudFin = null;
        tamNorte = 0;
        tamSur = 0;
    }

    public boolean vacia(){
        boolean res = norteIni == null && sudIni == null;
        return res;
    }

    public void insertar(T dato){
        NodoDE<T> nuevo = new NodoDE<T>();
        if(tamNorte <= tamSur){
            if(norteFin == null){//El norte esta vacio
                norteIni = nuevo;
                norteFin = nuevo;
            }else{
                norteFin.setSig(nuevo);
                nuevo.setAnt(norteFin);
                norteFin = nuevo;
            }
            tamNorte++;
        }else{
            if(sudIni == null){
                sudIni = nuevo;
                sudFin = nuevo;
            }else{
                sudIni.setAnt(nuevo);
                nuevo.setSig(sudIni);
                sudIni = nuevo;
            }
            tamSur++;
        }
    }
    
    public int longitud(){
        return tamNorte + tamSur;
    }
    
    public void eliminarNorte(){
        
    }

}
