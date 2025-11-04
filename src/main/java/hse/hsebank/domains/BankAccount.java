package hse.hsebank.domains;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    @Getter @Setter private UUID id;
    @Getter @Setter private String name;
    @Getter @Setter private BigDecimal balance;

    public BankAccount(String name, UUID id) {
        this.name = name;
        this.id = id;
        this.balance = new BigDecimal(0);
    }
}
