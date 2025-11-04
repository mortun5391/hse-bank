package hse.hsebank;

import hse.hsebank.commands.*;
import hse.hsebank.console.ConsoleMenu;
import hse.hsebank.facade.AnalyticsFacade;
import hse.hsebank.facade.BankAccountFacade;
import hse.hsebank.facade.CategoryFacade;
import hse.hsebank.facade.OperationFacade;
import hse.hsebank.services.BalanceRecalculationService;
import hse.hsebank.template.CsvDataImporter;
import hse.hsebank.utils.InputValidator;
import hse.hsebank.visitor.JsonExportVisitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Main console application with DI
 */
@Component
public class ConsoleApplication implements CommandLineRunner {
    private final ConsoleMenu consoleMenu;
    private final InputValidator inputValidator;
    private final CreateAccountCommand createAccountCommand;
    private final CreateCategoryCommand createCategoryCommand;
    private final CreateOperationCommand createOperationCommand;
    private final ShowAnalyticsCommand showAnalyticsCommand;
    private final AdvancedAnalyticsCommand advancedAnalyticsCommand; // ← Новая команда
    private final ListAccountsCommand listAccountsCommand;
    private final ListCategoriesCommand listCategoriesCommand;
    private final ListOperationsCommand listOperationsCommand;
    private final BalanceRecalculationService balanceRecalculationService;
    private final CsvDataImporter csvDataImporter;
    private final JsonExportVisitor jsonExportVisitor;

    public ConsoleApplication(ConsoleMenu consoleMenu,
                              InputValidator inputValidator,
                              CreateAccountCommand createAccountCommand,
                              CreateCategoryCommand createCategoryCommand,
                              CreateOperationCommand createOperationCommand,
                              ShowAnalyticsCommand showAnalyticsCommand,
                              AdvancedAnalyticsCommand advancedAnalyticsCommand, // ← Новый параметр
                              ListAccountsCommand listAccountsCommand,
                              ListCategoriesCommand listCategoriesCommand,
                              ListOperationsCommand listOperationsCommand,
                              BalanceRecalculationService balanceRecalculationService,
                              CsvDataImporter csvDataImporter,
                              JsonExportVisitor jsonExportVisitor) {
        this.consoleMenu = consoleMenu;
        this.inputValidator = inputValidator;
        this.createAccountCommand = createAccountCommand;
        this.createCategoryCommand = createCategoryCommand;
        this.createOperationCommand = createOperationCommand;
        this.showAnalyticsCommand = showAnalyticsCommand;
        this.advancedAnalyticsCommand = advancedAnalyticsCommand;
        this.listAccountsCommand = listAccountsCommand;
        this.listCategoriesCommand = listCategoriesCommand;
        this.listOperationsCommand = listOperationsCommand;
        this.balanceRecalculationService = balanceRecalculationService;
        this.csvDataImporter = csvDataImporter;
        this.jsonExportVisitor = jsonExportVisitor;

        initializeMenuCommands();
    }

    private void initializeMenuCommands() {
        consoleMenu.registerCommand(1, createAccountCommand);
        consoleMenu.registerCommand(2, createCategoryCommand);
        consoleMenu.registerCommand(3, createOperationCommand);
        consoleMenu.registerCommand(4, showAnalyticsCommand);
        consoleMenu.registerCommand(5, advancedAnalyticsCommand); // ← Новая команда
        consoleMenu.registerCommand(6, this::importData);
        consoleMenu.registerCommand(7, this::exportData);
        consoleMenu.registerCommand(8, this::recalculateBalances);
        consoleMenu.registerCommand(9, listAccountsCommand);
        consoleMenu.registerCommand(10, listCategoriesCommand);
        consoleMenu.registerCommand(11, listOperationsCommand);
    }

    @Override
    public void run(String... args) {
        consoleMenu.run();
    }

    private void importData() {
        String filePath = inputValidator.getStringInput("Enter file path for import: ");
        csvDataImporter.importData(filePath);
    }

    private void exportData() {
        jsonExportVisitor.exportAll(null, null, null);
        System.out.println("Data exported successfully");
    }

    private void recalculateBalances() {
        balanceRecalculationService.recalculateAllBalances();
        System.out.println("All balances recalculated successfully");
    }
}