package pujaburman30github.io.zolvepoc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Z_TRANSACTION_MAPPING")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionTypeMapping {

    @Id
    @SequenceGenerator(name = "Z_TRANS_SEQ", sequenceName = "Z_TRANS_SEQ", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Z_TRANS_SEQ")
    private long id;

    @ManyToOne
    @JoinColumn(name = "transaction_type",insertable = true,updatable = false)
    private Transactions transaction_type;

    @ManyToOne
    @JoinColumn(name = "user",insertable = true,updatable = false)
    private User useId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
