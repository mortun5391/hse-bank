// InputValidator.java
package hse.hsebank.utils;

import hse.hsebank.domains.enums.CategoryType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.UUID;

/**
 * Input data validator
 */
@Component
public class InputValidator {
    private final InputProvider inputProvider;

    public InputValidator(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return inputProvider.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter an integer.");
            } finally {
                inputProvider.nextLine();
            }
        }
    }

    public BigDecimal getBigDecimalInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = inputProvider.nextDouble();
                if (value > 0) {
                    return BigDecimal.valueOf(value);
                } else {
                    System.out.println("Error: amount must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter a number.");
            } finally {
                inputProvider.nextLine();
            }
        }
    }

    public String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = inputProvider.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Error: input cannot be empty.");
            }
        }
    }

    public UUID getUUIDInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = inputProvider.nextLine().trim();
                return UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: invalid UUID format.");
            }
        }
    }

    public boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = inputProvider.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: please enter 'y' or 'n'.");
            }
        }
    }

    public CategoryType getCategoryTypeInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (INCOME/OUTCOME): ");
            String input = inputProvider.nextLine().trim().toUpperCase();
            try {
                return CategoryType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: please enter 'INCOME' or 'OUTCOME'.");
            }
        }
    }
}