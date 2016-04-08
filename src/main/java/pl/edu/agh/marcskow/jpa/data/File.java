package pl.edu.agh.marcskow.jpa.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="files")
@Getter @Setter
public class File {
    @Id
    @SequenceGenerator(name = "fileGenerator", sequenceName = "file_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileGenerator")
    private int id;

    @Column(name="filename", nullable = false, length = 100)
    private String filename;
    @Column(name="owner_id", nullable = false)
    private int owner_id;
    @Column(name="group_id", nullable = false)
    private int group_id;

    //There are columns on the same name as files
    @Column(nullable = false)
    private byte user_read = 0;
    @Column(nullable = false)
    private byte user_write = 0;
    @Column(nullable = false)
    private byte group_read = 0;
    @Column(nullable = false)
    private byte group_write = 0;
}
