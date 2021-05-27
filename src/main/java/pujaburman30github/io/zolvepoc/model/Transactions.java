package pujaburman30github.io.zolvepoc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Z_TRANSACTION")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transactions {

    @Id
    @SequenceGenerator(name = "Z_SEQ", sequenceName = "Z_SEQ", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Z_SEQ")
    private long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "payer",insertable = true,updatable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee" ,insertable = true,updatable = false)
    private User payee;

    private TransactionType type;
}
