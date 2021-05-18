package pujaburman30github.io.zolvepoc.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Receipt {

    private long payee;

    private long benificiary;

    private double amount;

}
