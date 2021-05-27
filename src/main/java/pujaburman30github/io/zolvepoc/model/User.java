package pujaburman30github.io.zolvepoc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

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

    @OneToMany(mappedBy = "payee" ,targetEntity = Transactions.class)
    private Collection<Transactions> creditTransactions;

    @OneToMany(mappedBy = "payer" ,targetEntity = Transactions.class)
    private Collection<Transactions> debitTransactions;

    private double balance;
}
