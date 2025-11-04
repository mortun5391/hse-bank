package hse.hsebank.facade;

import hse.hsebank.domains.BankAccount;
import hse.hsebank.factories.DomainFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BankAccountFacade {
    private final Map<UUID, BankAccount> accounts = new HashMap<>();
    private final DomainFactory domainFactory;

    public BankAccountFacade(DomainFactory domainFactory) {
        this.domainFactory = domainFactory;
    }

    public BankAccount createAccount(String name) {
        BankAccount account = domainFactory.createBankAccount(name);
        accounts.put(account.getId(), account);
        return account;
    }

    public Optional<BankAccount> getAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public boolean updateAccount(UUID id, String newName) {
        BankAccount account = accounts.get(id);
        if (account != null) {
            account.setName(newName);
            return true;
        }
        return false;
    }

    public boolean deleteAccount(UUID id) {
        return accounts.remove(id) != null;
    }

    public void processOperation(BankAccount account, hse.hsebank.domains.Operation operation) {
        account.processOperation(operation);
    }
}