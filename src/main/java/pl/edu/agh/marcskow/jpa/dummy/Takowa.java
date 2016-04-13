package pl.edu.agh.marcskow.jpa.dummy;

/**
 * Created by intenso on 13.04.16.
 */
public class Takowa {

    public int a = 5;
    {
        System.out.println("WYWOŁUJĘ BLOK BAZOWEJ");
        a = 10;
    }


    public Takowa(){
        System.out.println("WYWOŁUJĘ BAZOWĄ");
        a = 20;
    }


    public static void main(String[] args) {
      //  Takowa t = new Takowa();
       // System.out.println(t.a);
        Takowa d = new Druga();
        System.out.println(d.a);
    }
}
