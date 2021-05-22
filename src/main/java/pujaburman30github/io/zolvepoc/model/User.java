package pujaburman30github.io.zolvepoc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Z_USER")
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @SequenceGenerator(name = "Z_SEQ", sequenceName = "Z_SEQ", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Z_SEQ")
    private Long id;

    private String firstName;

    private String lastName;

    private long contact;

    private double balance;
}
