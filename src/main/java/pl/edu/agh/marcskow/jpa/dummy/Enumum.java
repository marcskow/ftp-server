package pl.edu.agh.marcskow.jpa.dummy;

import lombok.Getter;

/**
 * Created by intenso on 12.04.16.
 */
@Getter
public enum Enumum {
    ZIELONY(500), CZERWONY(100), NIEBIESKI(2);


    private final int id;
    Enumum(int id){
        this.id = id;
        this.a = 5;
    }

    private int a;



    public static void main(){

    }

    public Enumum next(){
        return CZERWONY;
    }
}
