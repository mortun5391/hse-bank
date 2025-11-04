// JsonExportVisitor.java
package hse.hsebank.visitor;

import hse.hsebank.domains.BankAccount;
import hse.hsebank.domains.Category;
import hse.hsebank.domains.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonExportVisitor implements DataExportVisitor {

    @Override
    public String exportAccounts(List<BankAccount> accounts) {
        System.out.println("Exporting accounts to JSON...");
        return "{}"; // Simplified
    }

    @Override
    public String exportCategories(List<Category> categories) {
        System.out.println("Exporting categories to JSON...");
        return "{}"; // Simplified
    }

    @Override
    public String exportOperations(List<Operation> operations) {
        System.out.println("Exporting operations to JSON...");
        return "{}"; // Simplified
    }

    @Override
    public String exportAll(List<BankAccount> accounts, List<Category> categories, List<Operation> operations) {
        System.out.println("Exporting all data to JSON...");
        return "{}"; // Simplified
    }
}