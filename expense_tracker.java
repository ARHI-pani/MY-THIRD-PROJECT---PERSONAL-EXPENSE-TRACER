package myprojects;
import java.util.Scanner;
public class expense_tracker {
    static Scanner sc = new Scanner(System.in);
    static String NAME;
    static double budget;
    static int n;

    static String[] expense_name;
    static double[] expense_amount;

    static double total_expense = 0;
    static double total_savings = 0;
    static int expense_count = 0;

    public static void main(String[] args) {
        inputUserDetails();
        int choice;
        do {
            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expenses");
            System.out.println("2. View All Expenses");
            System.out.println("3. Show Total Expenses");
            System.out.println("4. Show Highest Expense");
            System.out.println("5. Show Remaining Budget");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addExpenses();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    System.out.println("Total Expense : " + calculateTotalExpense());
                    break;
                case 4:
                    calculateHighestExpense();
                    break;
                case 5:
                    System.out.println("Remaining Budget : " + calculateRemainingBudget());
                    break;
                case 6:
                    displayReport();
                    System.out.println("Thank you for using Personal Expense Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    public static void inputUserDetails() {
        System.out.println("===== EXPENSE DETAILS =====");

        System.out.print("Enter the name of the user: ");
        NAME = sc.nextLine();

        System.out.print("Enter your monthly budget: ");
        budget = sc.nextDouble();

        System.out.print("How many expenses do you want to enter : ");
        n = sc.nextInt();
        sc.nextLine();

        expense_name = new String[n];
        expense_amount = new double[n];
    }

    public static void addExpenses() {
        if (expense_count == n) {
            System.out.println("You have already entered all expenses.");
            return;
        }

        for (int i = expense_count; i < n; i++) {
            System.out.print("Enter expense name: ");
            expense_name[i] = sc.nextLine();

            System.out.print("Enter expense amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            if (amount < 0) {
                System.out.println("Amount cannot be negative. Please enter again.");
                i--;
            } else {
                expense_amount[i] = amount;
                expense_count++;
            }
        }

        System.out.println("Expenses added successfully.");
    }

    // This method displays all expenses
    public static void viewExpenses() {
        if (expense_count == 0) {
            System.out.println("No expenses added yet.");
            return;
        }

        System.out.println("\nExpense Name            Amount");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < expense_count; i++) {
            System.out.printf("%-23s ₹%.2f\n", expense_name[i], expense_amount[i]);
        }
    }

    public static double calculateTotalExpense() {
        total_expense = 0;

        for (int i = 0; i < expense_count; i++) {
            total_expense = total_expense + expense_amount[i];
        }
        return total_expense;
    }

    public static void calculateHighestExpense() {
        if (expense_count == 0) {
            System.out.println("No expenses added yet.");
            return;
        }
        int highestIndex = 0;

        for (int i = 1; i < expense_count; i++) {
            if (expense_amount[i] > expense_amount[highestIndex]) {
                highestIndex = i;
            }
        }

        System.out.println("Highest Expense : " + expense_name[highestIndex]);
        System.out.println("Highest Amount : ₹" + expense_amount[highestIndex]);
    }

    public static double calculateRemainingBudget() {
        total_savings = budget - calculateTotalExpense();
        return total_savings;
    }

    public static void displayReport() {
        total_expense = calculateTotalExpense();
        total_savings = calculateRemainingBudget();

        int highestIndex = 0;

        if (expense_count > 0) {
            for (int i = 1; i < expense_count; i++) {
                if (expense_amount[i] > expense_amount[highestIndex]) {
                    highestIndex = i;
                }
            }
        }

        System.out.println("\n=========================================");
        System.out.println("PERSONAL EXPENSE REPORT");
        System.out.println("=========================================");

        System.out.println("\nUser Name : " + NAME);
        System.out.println("\nMonthly Budget : ₹" + budget);

        System.out.println("\n-----------------------------------------");
        System.out.println("\nExpense Name            Amount\n");

        for (int i = 0; i < expense_count; i++) {
            System.out.printf("%-23s ₹%.2f\n", expense_name[i], expense_amount[i]);
        }

        System.out.println("\n-----------------------------------------");

        System.out.println("\nTotal Expense : ₹" + total_expense);

        if (expense_count > 0) {
            System.out.println("\nHighest Expense : " + expense_name[highestIndex]);
            System.out.println("\nHighest Amount : ₹" + expense_amount[highestIndex]);
        } else {
            System.out.println("\nHighest Expense : No expenses added");
            System.out.println("\nHighest Amount : ₹0");
        }

        System.out.println("\nRemaining Budget : ₹" + total_savings);

        if (total_expense > budget) {
            System.out.println("\nBudget Status : Warning: Budget Exceeded!");
        } else {
            System.out.println("\nBudget Status : Within Budget");
        }

        System.out.println("\n=========================================");
    }
}

