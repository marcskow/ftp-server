package pl.edu.agh.marcskow.jpa.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="groups")
@Getter @Setter
public class Group {
    @Id
    @SequenceGenerator(name = "groupGenerator", sequenceName = "group_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupGenerator")
    private int id;

    @Column(name = "group_name", nullable = false)
    private String name;
}
