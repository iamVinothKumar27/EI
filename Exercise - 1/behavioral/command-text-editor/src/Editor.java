public class Editor {
    private final StringBuilder text = new StringBuilder();

    public void insert(String s) {
        text.append(s);
    }

    public String delete(int n) {
        int len = text.length();
        int start = Math.max(0, len - n);
        String removed = text.substring(start);
        text.delete(start, len);
        return removed;
    }

    public String getText() {
        return text.toString();
    }
}
