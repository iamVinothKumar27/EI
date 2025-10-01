import java.util.Scanner;

public class PrototypeDocumentDemo {
    public static void runDemo(Scanner in) {
        Resume resumeTemplate = new Resume("Resume Template", "TemplateAuthor");
        Report reportTemplate = new Report("Report Template", "TemplateAuthor");

        System.out.println("Choose template: 1) Resume  2) Report");
        System.out.print("> ");
        String s = in.nextLine().trim();
        Document doc = s.equals("2") ? reportTemplate.clone() : resumeTemplate.clone();

        System.out.print("Enter custom title: ");
        doc.setTitle(in.nextLine().trim());

        System.out.print("Enter author: ");
        doc.setAuthor(in.nextLine().trim());

        System.out.println("\n=== Document ===");
        doc.print();
    }
}
