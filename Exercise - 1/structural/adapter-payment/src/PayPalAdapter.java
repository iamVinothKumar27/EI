public class PayPalAdapter implements PaymentGateway {
    private final PayPalSDK sdk = new PayPalSDK();
    @Override
    public boolean pay(int amountInPaise) {
        return sdk.makePayment(amountInPaise / 100.0);
    }
}
