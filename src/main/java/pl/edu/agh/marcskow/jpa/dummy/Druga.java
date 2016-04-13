package pl.edu.agh.marcskow.jpa.dummy;

/**
 * Created by intenso on 13.04.16.
 */
public class Druga extends Takowa {
    public int a = 30;

    {
        System.out.println("WYWOŁUJĘ BLOK POCHODNEJ");
        a = 40;
        super.a = 50;
    }

    public Druga(){
        super();
        a = 60;
        System.out.println("WYWOŁUJĘ KONSTRUKTOR POCHODNEJ");
    }
}
