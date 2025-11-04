package hse.hsebank.factories;

import hse.hsebank.domains.BankAccount;
import hse.hsebank.domains.Category;
import hse.hsebank.domains.Operation;
import hse.hsebank.domains.enums.CategoryType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DomainFactoryImpl implements DomainFactory {

    @Override
    public BankAccount createBankAccount(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be empty");
        }
        return new BankAccount(name.trim(), UUID.randomUUID());
    }

    @Override
    public Category createCategory(CategoryType type, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Category type cannot be null");
        }
        return new Category(UUID.randomUUID(), type, name.trim());
    }

    @Override
    public Operation createOperation(UUID bankAccountId, UUID categoryId, CategoryType type,
                                     BigDecimal amount, String description) {
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
            throw new IllegalArgumentException("Operation amount must be positive");
        }

        return new Operation(UUID.randomUUID(), bankAccountId, categoryId,
                type, amount, description != null ? description.trim() : "");
    }
}