import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final Map<String, String> settings = new HashMap<>();

    private AppConfig() {}

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public void set(String key, String value) {
        settings.put(key, value);
    }

    public String get(String key) {
        return settings.get(key);
    }

    public Map<String, String> snapshot() {
        return Collections.unmodifiableMap(settings);
    }
}
