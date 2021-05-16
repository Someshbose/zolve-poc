package pujaburman30github.io.zolvepoc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Z_TRANSACTION")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class Transactions {

    @Id
    @Generated
    private long id;

    private long payer;

    private long payee;

    private double amount;

    private TransactionType type;
}
