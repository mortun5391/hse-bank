package hse.hsebank.template;

import hse.hsebank.domains.BankAccount;
import hse.hsebank.domains.Category;
import hse.hsebank.domains.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Template method for data import
 */
@Component
public abstract class DataImporter {

    public final void importData(String filePath) {
        String content = readFile(filePath);
        List<BankAccount> accounts = parseAccounts(content);
        List<Category> categories = parseCategories(content);
        List<Operation> operations = parseOperations(content);
        saveData(accounts, categories, operations);
        postImport();
    }

    protected String readFile(String filePath) {
        System.out.println("Reading file: " + filePath);
        return "file content";
    }

    protected abstract List<BankAccount> parseAccounts(String content);
    protected abstract List<Category> parseCategories(String content);
    protected abstract List<Operation> parseOperations(String content);

    protected void saveData(List<BankAccount> accounts, List<Category> categories, List<Operation> operations) {
        System.out.println("Saving imported data...");
    }

    protected void postImport() {
        System.out.println("Import completed successfully");
    }
}