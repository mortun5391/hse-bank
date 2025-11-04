package hse.hsebank.facade;

import hse.hsebank.domains.Category;
import hse.hsebank.domains.enums.CategoryType;
import hse.hsebank.factories.DomainFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryFacade {
    private final Map<UUID, Category> categories = new HashMap<>();
    private final DomainFactory domainFactory;

    public CategoryFacade(DomainFactory domainFactory) {
        this.domainFactory = domainFactory;
    }

    public Category createCategory(CategoryType type, String name) {
        Category category = domainFactory.createCategory(type, name);
        categories.put(category.getId(), category);
        return category;
    }

    public Optional<Category> getCategory(UUID id) {
        return Optional.ofNullable(categories.get(id));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public List<Category> getCategoriesByType(CategoryType type) {
        return categories.values().stream()
                .filter(category -> category.getType() == type)
                .toList();
    }

    public boolean updateCategory(UUID id, String newName, CategoryType newType) {
        Category category = categories.get(id);
        if (category != null) {
            category.setName(newName);
            category.setType(newType);
            return true;
        }
        return false;
    }

    public boolean deleteCategory(UUID id) {
        return categories.remove(id) != null;
    }
}