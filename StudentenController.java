import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten = new ArrayList<>();

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    public void studentToevoegen() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Voer studenten naam in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        System.out.println("Voer studentennummer in:");
        int studentnummer = scanner.nextInt();
        student.setStudentennummer(studentnummer);
        studenten.add(student);
    }

    public void studentVerwijderen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer studentnummer in:");
        int nummer = scanner.nextInt();
        studenten.removeIf(st -> st.getStudentennummer() == nummer);
    }

    public void studentLijst() {
        for (Student student : studenten) {
            System.out.println(student.getNaam());
            System.out.print(", " + student.getStudentennummer());
        }
    }

    public Student studentMeesteExamensGehaald() {
        Student maxex = new Student();
        for (Student st : studenten) {
            if (st.getGehaald().size() > maxex.getGehaald().size()) {
                maxex = st;
            }
        }
        return maxex;
    }


    public void studentWelkeExamensGehaald() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer studentnummer in:");
        int studentnummer = scanner.nextInt();
        for (Student st : studenten) {
            if (st.getStudentennummer() == (studentnummer)) {
                for (int i = 0; i < st.getGehaald().size(); i++) {
                    System.out.println(st.getGehaald().get(i));
                }
            }
        }
    }

}
