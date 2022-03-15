import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten = new ArrayList<>();

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    public void studentToevoegen(Scanner scanner) {
        Student student = new Student();
        System.out.println("Voer studenten naam in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        int studentnummer = vraagOmStudentnummer(scanner);
        student.setStudentennummer(studentnummer);
        studenten.add(student);
    }

    public void studentVerwijderen(Scanner scanner) {
        int nummer = vraagOmStudentnummer(scanner);
        studenten.removeIf(st -> st.getStudentennummer() == nummer);
    }

    public void studentLijst() {
        Util.printArrayList(studenten, "student", "studenten");
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


    public void studentWelkeExamensGehaald(Scanner scanner) {
        int studentnummer = vraagOmStudentnummer(scanner);
        for (Student st : studenten) {
            if (st.getStudentennummer() == (studentnummer)) {
                Util.printArrayList(st.getGehaald(), "examen gehaald", "examens gehaald");
            }
        }
    }

    private int vraagOmStudentnummer(Scanner scanner) {
        System.out.println("Voer studentnummer in:");
        return Util.leesInt(scanner, 0, Integer.MAX_VALUE);
    }
}
