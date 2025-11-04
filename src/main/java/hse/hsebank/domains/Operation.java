// Operation.java (доработанный)
package hse.hsebank.domains;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Operation {
    @Getter @Setter private UUID id;
    @Getter @Setter private UUID bankAccountId;
    @Getter @Setter private UUID categoryId;
    @Getter @Setter private BigDecimal amount;
    @Getter @Setter private LocalDateTime date;
    @Getter @Setter private String description;

    public Operation(UUID id, UUID bankAccountId, UUID categoryId,
                     BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.description = description;
    }
}