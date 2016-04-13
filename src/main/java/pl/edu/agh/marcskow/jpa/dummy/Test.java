package pl.edu.agh.marcskow.jpa.dummy;

import pl.edu.agh.marcskow.jpa.dummy.Enumum;

/**
 * Created by intenso on 12.04.16.
 */
public class Test {
    public static void main(String[] args) {
        Enumum taki = Enumum.ZIELONY;
        System.out.println(taki.next());
        System.out.println(Enumum.CZERWONY.next());
        System.out.println(Enumum.CZERWONY.getId());
        Enumum id = Enumum.CZERWONY;
        System.out.println(Enumum.CZERWONY);
        System.out.println(Enumum.CZERWONY.getId());


    }
}
