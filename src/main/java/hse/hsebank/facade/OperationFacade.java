package hse.hsebank.facade;

import hse.hsebank.domains.Operation;
import hse.hsebank.domains.enums.CategoryType;
import hse.hsebank.factories.DomainFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class OperationFacade {
    private final Map<UUID, Operation> operations = new HashMap<>();
    private final DomainFactory domainFactory;
    private final BankAccountFacade bankAccountFacade;
    private final CategoryFacade categoryFacade;

    public OperationFacade(DomainFactory domainFactory,
                           BankAccountFacade bankAccountFacade,
                           CategoryFacade categoryFacade) {
        this.domainFactory = domainFactory;
        this.bankAccountFacade = bankAccountFacade;
        this.categoryFacade = categoryFacade;
    }

    public Operation createOperation(UUID bankAccountId, UUID categoryId,
                                     CategoryType type, BigDecimal amount, String description) {
        if (bankAccountFacade.getAccount(bankAccountId).isEmpty()) {
            throw new IllegalArgumentException("Bank account not found");
        }
        if (categoryFacade.getCategory(categoryId).isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }

        Operation operation = domainFactory.createOperation(bankAccountId, categoryId, type, amount, description);
        operations.put(operation.getId(), operation);

        bankAccountFacade.getAccount(bankAccountId).ifPresent(
                account -> account.processOperation(operation)
        );

        return operation;
    }

    public Optional<Operation> getOperation(UUID id) {
        return Optional.ofNullable(operations.get(id));
    }

    public List<Operation> getAllOperations() {
        return new ArrayList<>(operations.values());
    }

    public List<Operation> getOperationsByAccount(UUID accountId) {
        return operations.values().stream()
                .filter(op -> op.getBankAccountId().equals(accountId))
                .toList();
    }

    public List<Operation> getOperationsByCategory(UUID categoryId) {
        return operations.values().stream()
                .filter(op -> op.getCategoryId().equals(categoryId))
                .toList();
    }

    public List<Operation> getOperationsByDateRange(LocalDateTime start, LocalDateTime end) {
        return operations.values().stream()
                .filter(op -> !op.getDate().isBefore(start) && !op.getDate().isAfter(end))
                .toList();
    }

    public boolean deleteOperation(UUID id) {
        return operations.remove(id) != null;
    }
}