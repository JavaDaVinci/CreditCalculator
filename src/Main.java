import java.util.Scanner;
public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        // ----------------------- Prompting user for input ----------------------------
        System.out.println("Loan amount: ");
        int loanAmount = scanner.nextInt();
        do{

            if(loanAmount <= 99){
                System.out.println("Loan amount must be greater than $99\n");
            }
        }while(loanAmount <= 99);


        System.out.println("Loan term in years: ");
        int termInYears = scanner.nextInt();

        do{
            if(termInYears<= 0){
                System.out.println("Loan term must be at least one year\n");
            }
            else if(termInYears > 50){
                System.out.println("Loan term cannot exceed 50 years\n");
            }
        }while(termInYears <=0 || termInYears> 50);


        System.out.println("Interest rate per year: ");
        double annualRate = scanner.nextDouble();
        do{
            if(annualRate < 0){
                System.out.println("Interest rate cannot be negative\n");
            }
        }while(annualRate < 0);


        System.out.println("Downpayment: ");
        double downPay = scanner.nextDouble();
        do{
            if(downPay < 0){
                System.out.println("Downpayment cannot be negative, but can be $0\n");
            }
            else if(downPay>=loanAmount){
                System.out.println("Downpayment cannot equal or exceed your loan amount\n");
            }
        }while(downPay < 0 || downPay >= loanAmount);
        System.out.println();
        // ------------------------ End Prompting User ---------------------------------

        // Call functions
        double monthlyPayment = calculateMonthlyPayment(loanAmount, termInYears, annualRate, downPay);
        double totalInterestAccrued = calculateInterestAccrued(monthlyPayment, loanAmount, downPay, termInYears);

        // Print Results
        System.out.println("Monthly Payments: ");
        System.out.print("$");
        System.out.printf("%.2f", monthlyPayment);
        System.out.println();
        System.out.println("Total Accrued Interest: ");
        System.out.println("$" + (int)Math.round(totalInterestAccrued));
    }

    public static double calculateMonthlyPayment(double loanAmount, int termInYears, double annualRate, double downPay )
    {
        double monthlyRate = (annualRate/100.0) / 12;
        int termsInMonths = termInYears * 12;
        loanAmount -= downPay;
        double monthlyPayment = (monthlyRate * loanAmount)/(1-Math.pow((1+monthlyRate), -termsInMonths));
        return monthlyPayment;
    }


    public static double calculateInterestAccrued(double monthlyPayment, double loanAmount, double downPay, int termInYears)
    {
        int termsInMonths = termInYears * 12;
        loanAmount -= downPay;
        double totalCost = monthlyPayment * termsInMonths;
        double totalInterestAccrued = totalCost - loanAmount;
        return totalInterestAccrued;
    }

}
