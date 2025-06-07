import estDat.estDatLin.*;
public class Prueba{
    private ListaSE<Nota> lista;
    
    public Prueba(){
        lista = new ListaSE<Nota>();
    }
    
    public void mostrarNotas(){
        lista.insertar(new Nota(70, 60));
        lista.insertar(new Nota(87, 34));
        for(int i = 0; i < lista.getSize(); i++){
            System.out.println(lista.acceder(i).getNotaPP());
        }
    }
}