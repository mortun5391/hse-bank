package hse.hsebank.domains;

import hse.hsebank.domains.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Financial operation (income or expense)
 */
public class Operation {
    @Getter
    private final UUID id;

    @Getter @Setter
    private UUID bankAccountId;

    @Getter @Setter
    private UUID categoryId;

    @Getter
    private CategoryType type;

    @Getter @Setter
    private BigDecimal amount;

    @Getter
    private final LocalDateTime date;

    @Getter @Setter
    private String description;

    public Operation(UUID id, UUID bankAccountId, UUID categoryId, CategoryType type,
                     BigDecimal amount, String description) {
        validateInput(id, bankAccountId, categoryId, type, amount);

        this.id = id;
        this.bankAccountId = bankAccountId;
        this.categoryId = categoryId;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.description = description != null ? description.trim() : "";
    }

    private void validateInput(UUID id, UUID bankAccountId, UUID categoryId,
                               CategoryType type, BigDecimal amount) {
        if (id == null) {
            throw new IllegalArgumentException("Operation ID cannot be null");
        }
        if (bankAccountId == null) {
            throw new IllegalArgumentException("Bank account ID cannot be null");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Operation type cannot be null");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    /**
     * Set operation type with validation
     */
    public void setType(CategoryType type) {
        if (type == null) {
            throw new IllegalArgumentException("Operation type cannot be null");
        }
        this.type = type;
    }

    /**
     * Check if operation is income
     */
    public boolean isIncome() {
        return type == CategoryType.INCOME;
    }

    /**
     * Check if operation is expense
     */
    public boolean isExpense() {
        return type == CategoryType.OUTCOME;
    }

    /**
     * Update operation date (for import/export scenarios)
     */
    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Operation date cannot be null");
        }
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Operation date cannot be in the future");
        }
    }

    /**
     * Get formatted date string
     */
    public String getFormattedDate() {
        return date.toString();
    }

    @Override
    public String toString() {
        return String.format("Operation{id=%s, accountId=%s, categoryId=%s, type=%s, amount=%.2f, date=%s, description='%s'}",
                id, bankAccountId, categoryId, type, amount, date, description);
    }
}