import java.util.Scanner;

public class AdapterPaymentDemo {
    public static void runDemo(Scanner in) {
        System.out.println("Choose gateway: 1) Stripe  2) PayPal");
        System.out.print("> ");
        String g = in.nextLine().trim();
        PaymentGateway gw = g.equals("2") ? new PayPalAdapter() : new StripeAdapter();

        System.out.print("Enter amount (INR rupees): ");
        int rupees;
        try { rupees = Integer.parseInt(in.nextLine().trim()); }
        catch (Exception e) { rupees = 100; }
        int paise = rupees * 100;

        boolean ok = gw.pay(paise);
        System.out.println("Payment status: " + (ok ? "SUCCESS" : "FAILED"));
    }
}
