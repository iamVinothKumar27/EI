public class StripeAdapter implements PaymentGateway {
    private final StripeSDK sdk = new StripeSDK();
    @Override
    public boolean pay(int amountInPaise) {
        return sdk.sendCharge(amountInPaise);
    }
}
