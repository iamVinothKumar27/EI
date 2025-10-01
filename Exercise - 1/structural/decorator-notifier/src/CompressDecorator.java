public class CompressDecorator extends NotifierDecorator {
    public CompressDecorator(Notifier inner) { super(inner); }
    @Override
    public void send(String message) {
        // toy "compression": collapse spaces
        String cmp = message.replaceAll("\\s+", " ");
        inner.send("[ZIP]" + cmp);
    }
}
