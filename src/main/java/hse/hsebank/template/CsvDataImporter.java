// CsvDataImporter.java
package hse.hsebank.template;

import hse.hsebank.domains.BankAccount;
import hse.hsebank.domains.Category;
import hse.hsebank.domains.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvDataImporter extends DataImporter {

    @Override
    protected List<BankAccount> parseAccounts(String content) {
        System.out.println("Parsing CSV accounts...");
        return List.of();
    }

    @Override
    protected List<Category> parseCategories(String content) {
        System.out.println("Parsing CSV categories...");
        return List.of();
    }

    @Override
    protected List<Operation> parseOperations(String content) {
        System.out.println("Parsing CSV operations...");
        return List.of();
    }
}