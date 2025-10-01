public class StripeSDK {
    public boolean sendCharge(int paise) {
        System.out.println("[Stripe] Charging " + (paise/100.0) + " INR");
        return true;
    }
}
