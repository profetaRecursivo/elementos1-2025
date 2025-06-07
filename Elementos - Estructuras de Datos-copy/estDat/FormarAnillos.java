package estDat;
import estDat.estDatLin.*;

public class FormarAnillos{
    ListaSE<Pares> lista;

    public FormarAnillos(){
        lista = new ListaSE<Pares>();
    }

    public void getAnillos(){
        lista.insertar(new Pares(1, 2));
        lista.insertar(new Pares(2, 3));
        lista.insertar(new Pares(3, 1));
        lista.insertar(new Pares(4, 3));
        lista.insertar(new Pares(3, 5));
        lista.insertar(new Pares(5, 1));
        lista.insertar(new Pares(1, 4));
    }

    public void getAnillos2(){
        lista.insertar(new Pares(1, 2));
        lista.insertar(new Pares(1, 3));
        lista.insertar(new Pares(3, 1));
        lista.insertar(new Pares(4, 3));
        lista.insertar(new Pares(3, 5));
        lista.insertar(new Pares(5, 1));
        lista.insertar(new Pares(2, 4));
    }

    public void getAnillos3(){
        lista.insertar(new Pares(1, 2));
        lista.insertar(new Pares(1, 3));
        lista.insertar(new Pares(3, 1));
        lista.insertar(new Pares(4, 3));
        lista.insertar(new Pares(3, 5));
        lista.insertar(new Pares(5, 1));
    }

    public void getAnillos4(){
        lista.insertar(new Pares(1, 2));
        lista.insertar(new Pares(1, 3));
        lista.insertar(new Pares(1, 4));
        lista.insertar(new Pares(1, 5));
        lista.insertar(new Pares(5, 2));
    }

    // public boolean encontrarAnillos(){
    // getAnillos2();
    // boolean res;
    // res = encontrarAnillos(0, lista.getSize());
    // return res;
    // }

    // private boolean encontrarAnillos(int i, int n){
    // boolean res;
    // if(i == n-2){
    // if(lista.acceder(i).getP2() == lista.acceder(i+1).getP1()){
    // res = true;
    // }else{
    // res = false;
    // }
    // }else{
    // if(lista.acceder(i).getP2() == lista.acceder(i+1).getP1()){
    // res = encontrarAnillos(i+1, n);
    // }else{
    // if(buscarIdeal(lista.acceder(i).getP2())){
    // res = encontrarAnillos(i+1, n);
    // }else{
    // res = false;
    // }
    // }
    // }
    // return res;
    // }

    public boolean anillos(){
        boolean res;
        ListaSE<Pares> acumular = new ListaSE<Pares>(); 
        acumular.insertar(lista.acceder(0));
        lista.eliminar(lista.acceder(0));
        if(!lista.estaVacia()){
            anillos(0, lista.getSize(), acumular);
        }
        if(acumular.acceder(0).getP1() == acumular.acceder(acumular.getSize()-1).getP2()){
            res = true;
        }else{
            res = false;
        }
        return res;
    }

    private void anillos(int i, int n, ListaSE<Pares> acumular){
        if(lista.getSize() != 1){
            if(acumular.accederUltimo().getP2() == lista.acceder(i).getP1()){
                if(i == 0){
                    acumular.insertar(lista.acceder(i));
                    lista.eliminar(lista.acceder(i));
                    anillos(i, lista.getSize(), acumular);
                }else{
                    acumular.insertar(lista.acceder(i));
                    lista.eliminar(lista.acceder(i));
                    anillos(0, lista.getSize(), acumular);
                }
            }else if(i < lista.getSize()-1){
                anillos(i+1, n, acumular);
            }else{
                acumular.eliminar(acumular.acceder(acumular.getSize()-1));
                acumular.insertar(lista.acceder(0));
                lista.eliminar(lista.acceder(0));
                anillos(0, lista.getSize(), acumular);
            }
        }else{
            acumular.insertar(lista.acceder(0));
        }
    }

    // public boolean buscarIdeal(){
    // boolean res;
    // int aux;
    // aux = buscarIdeal(0, 0, lista.getSize());
    // if(aux != 0){
    // res = true;
    // }else{
    // res = false;
    // }
    // return res;
    // }

    // private int buscarIdeal(int i, int j, int n){
    // int res;
    // if(i == n-1){
    // if(j == n-1){
    // if(lista.acceder(i).getP2() != lista.acceder(i).getP1()){
    // res = 0;
    // }else{
    // res = 1;
    // }
    // }else{
    // if(lista.acceder(i).getP2() != lista.acceder(j).getP1()){
    // res = buscarIdeal(i, j+1, n);
    // }else{
    // res = 1 + buscarIdeal(i, j+1, n);
    // }
    // }
    // }else{
    // if(j == n-1){
    // if(lista.acceder(i).getP2() != lista.acceder(j).getP1()){
    // res = buscarIdeal(i+1, 0, n);
    // }else{
    // res = 1 + buscarIdeal(i+1, 0, n);
    // }
    // }else{
    // if(lista.acceder(i).getP2() != lista.acceder(j).getP1()){
    // res = buscarIdeal(i, j+1, n);
    // }else{
    // res = 1 + buscarIdeal(i, j+1, n);
    // }
    // }
    // }
    // return res;
    // }
}