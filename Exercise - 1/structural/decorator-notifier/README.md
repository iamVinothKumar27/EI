# Structural • Decorator — Notifier

**Pattern goal:** Dynamically add responsibilities (e.g., timestamp, encryption, compression) to a notifier at runtime.

**Run**
```bash
javac Main.java
java Main
# (auto-runs DecoratorNotifierDemo)
```

**User inputs**
- Message text
- Choose base channel: Email / SMS / Push
- Choose decorators: Timestamp / Encrypt / Compress (any combination)

**Classes**
- `Notifier` (Component)
- `BaseEmailNotifier`, `BaseSMSNotifier`, `BasePushNotifier` (Concrete components)
- `NotifierDecorator` (Base decorator)
- `TimestampDecorator`, `EncryptDecorator`, `CompressDecorator` (Concrete decorators)
- `DecoratorNotifierDemo` (Demo harness)
