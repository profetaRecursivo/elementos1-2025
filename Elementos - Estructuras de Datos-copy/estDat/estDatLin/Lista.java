package estDat.estDatLin;
public interface Lista<T>{
    public boolean estaVacia();
    public int getSize();
    public T insertar(T nDato);
    public T acceder(int pos);
    public boolean vaciar();
    public T eliminar(T nDato);
}