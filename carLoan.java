import java.util.Scanner;

public class carLoan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the car loan amount: ");
        int carLoan = scanner.nextInt();
        
        System.out.print("Enter the loan length in years: ");
        int loanLength = scanner.nextInt();
        
        System.out.print("Enter the annual interest rate (as a percentage): ");
        double annualInterestRate = scanner.nextDouble();
        
        System.out.print("Enter the down payment: ");
        int downPayment = scanner.nextInt();
        
        if (loanLength <= 0 || annualInterestRate <= 0) {
            System.out.println("Error! You must take out a valid car loan");
        } else if (downPayment >= carLoan) {
            System.out.println("The car can be paid in full.");
        } else {
            int remainingBalance = carLoan - downPayment;
            int months = loanLength * 12;
            
            // Convert annual interest rate to monthly and decimal
            double monthlyInterestRate = (annualInterestRate / 100) / 12;
            
            // Calculate monthly payment using the formula for an installment loan
            double monthlyPayment = (remainingBalance * monthlyInterestRate) /
                                    (1 - Math.pow(1 + monthlyInterestRate, -months));
            
            // Format the output
            System.out.printf("Monthly payment: $%.2f%n", monthlyPayment);
        }
        
        scanner.close();
    }
}
