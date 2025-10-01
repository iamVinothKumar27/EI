public class EncryptDecorator extends NotifierDecorator {
    public EncryptDecorator(Notifier inner) { super(inner); }
    @Override
    public void send(String message) {
        // toy "encryption": reverse string (for demo)
        String enc = new StringBuilder(message).reverse().toString();
        inner.send("[ENC]" + enc);
    }
}
