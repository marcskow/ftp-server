package pl.edu.agh.marcskow.jpa.dummy;

/**
 * Created by intenso on 13.04.16.
 */
public interface Probny {
    void proba();

    default void probaDomyslna(){
        System.out.println("DOMYSLNY");
    }


    static void cosik(){
        System.out.println("nic");
    }
}
