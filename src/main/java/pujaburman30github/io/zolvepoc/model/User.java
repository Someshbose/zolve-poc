package pujaburman30github.io.zolvepoc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Z_USER")
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Generated
    private Long id;

    private String firstName;

    private String lastName;

    private long contact;

    @Setter
    private double balance;
}
