# Structural • Adapter — Payment Gateways

**Pattern goal:** Convert one interface (`PaymentGateway`) so multiple providers can be used uniformly.

**Run**
```bash
javac Main.java
java Main
# choose: AdapterPaymentDemo
```

**User inputs**
- Choose gateway (Stripe, PayPal) and amount

**Classes**
- `PaymentGateway` (Target)
- `StripeSDK`, `PayPalSDK` (Adaptees; simulated)
- `StripeAdapter`, `PayPalAdapter` (Adapters)
- `AdapterPaymentDemo` (Client)
