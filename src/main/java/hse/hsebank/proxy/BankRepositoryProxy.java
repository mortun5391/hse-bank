// BankRepositoryProxy.java
package hse.hsebank.proxy;

import hse.hsebank.domains.BankAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Proxy with in-memory cache
 */
@Component
public class BankRepositoryProxy implements BankRepository {
    private final BankRepository realRepository;
    private final ConcurrentMap<UUID, BankAccount> cache = new ConcurrentHashMap<>();

    public BankRepositoryProxy(BankRepositoryImpl realRepository) {
        this.realRepository = realRepository;
    }

    @Override
    public void save(BankAccount account) {
        realRepository.save(account);
        cache.put(account.getId(), account);
    }

    @Override
    public Optional<BankAccount> findById(UUID id) {
        // Check cache first
        BankAccount cached = cache.get(id);
        if (cached != null) {
            System.out.println("Returning from cache: " + id);
            return Optional.of(cached);
        }

        // If not in cache, get from real repository and cache it
        Optional<BankAccount> account = realRepository.findById(id);
        account.ifPresent(acc -> cache.put(id, acc));
        return account;
    }

    @Override
    public List<BankAccount> findAll() {
        // For simplicity, we'll use real repository for findAll
        // In real scenario, we might cache the entire list or use more sophisticated caching
        return realRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        realRepository.delete(id);
        cache.remove(id);
    }

    public void clearCache() {
        cache.clear();
    }
}