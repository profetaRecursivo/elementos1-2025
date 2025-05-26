package estDat.estDatLin;


/**
 * Write a description of class Par here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Par<T, V>
{
    private T first;
    private V second;
    
    public Par(T f, V s){
        first = f;
        second = s;
    }
    
    public T first(){
        return first;
    }
    
    public V second(){
        return second;
    }
    
    public void setFirst(T f){
        first = f;
    }
    
    public void setSecond(V s){
        second = s;
    }
    
    
}
