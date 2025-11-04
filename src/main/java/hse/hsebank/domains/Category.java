package hse.hsebank.domains;

import hse.hsebank.domains.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Category {
    @Getter
    @Setter
    private UUID id;
    @Getter
    @Setter
    private CategoryType type;
    @Getter
    @Setter
    private String name;

        public Category(UUID id, CategoryType type, String name) {
            this.id = id;
        this.type = type;
        this.name = name;
    }
}
