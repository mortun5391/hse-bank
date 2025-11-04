// Category.java
package hse.hsebank.domains;

import hse.hsebank.domains.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Operation category
 */
@ToString
public class Category {
    @Getter
    private final UUID id;

    @Getter @Setter
    private CategoryType type;

    @Getter @Setter
    private String name;

    public Category(UUID id, CategoryType type, String name) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Category type cannot be null");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        this.id = id;
        this.type = type;
        this.name = name.trim();
    }

    /**
     * Check if category is for income operations
     */
    public boolean isIncome() {
        return type == CategoryType.INCOME;
    }

    /**
     * Check if category is for expense operations
     */
    public boolean isExpense() {
        return type == CategoryType.OUTCOME;
    }
}