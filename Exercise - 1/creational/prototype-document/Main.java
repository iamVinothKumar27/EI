
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String SRC_DIR = "src";
    private static final String BIN_DIR = "bin";

    public static void main(String[] args) throws Exception {
        ensureCompiled();
        List<String> demoClassNames = findDemoClassNames();
        if (demoClassNames.isEmpty()) {
            System.out.println("No *Demo classes found in src/. Ensure files end with 'Demo.java'.");
            return;
        }

        String chosen;
        if (demoClassNames.size() == 1) {
            chosen = demoClassNames.get(0);
            System.out.println("Auto-running: " + chosen);
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("=== Design Pattern Demos ===");
            for (int i = 0; i < demoClassNames.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, demoClassNames.get(i));
            }
            System.out.print("Choose a demo to run (1-" + demoClassNames.size() + "): ");
            int choice = readInt(in, 1, demoClassNames.size());
            chosen = demoClassNames.get(choice - 1);
        }

        try (URLClassLoader cl = new URLClassLoader(new URL[]{new File(BIN_DIR).toURI().toURL()})) {
            Class<?> demo = Class.forName(chosen, true, cl);
            // Prefer runDemo(Scanner), else main(String[])
            try {
                Method m = demo.getMethod("runDemo", Scanner.class);
                m.invoke(null, new Scanner(System.in));
            } catch (NoSuchMethodException e) {
                try {
                    Method m = demo.getMethod("main", String[].class);
                    m.invoke(null, (Object) new String[]{});
                } catch (NoSuchMethodException ex) {
                    System.out.println("No runDemo(Scanner) or main(String[]) found on " + chosen);
                }
            }
        }
    }

    private static void ensureCompiled() throws IOException {
        File src = new File(SRC_DIR);
        if (!src.exists()) return;
        File bin = new File(BIN_DIR);
        if (!bin.exists()) bin.mkdirs();

        List<String> files = Files.walk(src.toPath())
                .filter(p -> p.toString().endsWith(".java"))
                .map(Path::toString)
                .collect(Collectors.toList());
        if (files.isEmpty()) return;

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.out.println("No system Java compiler found. Use a JDK (not a JRE).");
            System.exit(1);
        }
        List<String> args = new ArrayList<>();
        args.add("-d");
        args.add(BIN_DIR);
        args.addAll(files);
        int result = compiler.run(null, null, null, args.toArray(new String[0]));
        if (result != 0) {
            System.out.println("Compilation failed. Check errors above.");
            System.exit(result);
        }
    }

    private static List<String> findDemoClassNames() throws IOException {
        List<String> names = new ArrayList<>();
        Path src = Paths.get(SRC_DIR);
        if (!Files.exists(src)) return names;
        Files.walk(src)
            .filter(p -> p.toString().endsWith("Demo.java"))
            .forEach(p -> {
                String fileName = p.getFileName().toString();
                String className = fileName.substring(0, fileName.length() - ".java".length());
                names.add(className);
            });
        Collections.sort(names);
        return names;
    }

    private static int readInt(Scanner in, int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.print("Enter a number between " + min + " and " + max + ": ");
        }
    }
}
