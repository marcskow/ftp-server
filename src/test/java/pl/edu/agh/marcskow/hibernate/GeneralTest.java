package pl.edu.agh.marcskow.hibernate;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;


public class GeneralTest {
    BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );
/*
    doInHibernate( this::sessionFactory, session -> {
        Product product = new Product( );
        product.setId( 1 );
        product.setBitSet( bitSet );
        session.persist( product );
    } );

    doInHibernate( this::sessionFactory, session -> {
        Product product = session.get( Product.class, 1 );
        assertEquals(bitSet, product.getBitSet());
    } );

    @Entity(name = "Product")
    public static class Product {

        @Id
        private Integer id;

        @Type( type = "bitset" )
        private BitSet bitSet;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public BitSet getBitSet() {
            return bitSet;
        }

        public void setBitSet(BitSet bitSet) {
            this.bitSet = bitSet;
        }
    }*/
}
