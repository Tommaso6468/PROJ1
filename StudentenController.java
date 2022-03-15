import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten = new ArrayList<>();

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    /**
     * Haalt de student up met een bepaald nummer
     * @param nummer Het studentennummer
     * @return De student met dat nummer, of null als die er niet is.
     */
    public Student getStudentMetNummer(int nummer) {
        for (Student student : studenten) {
            if (student.getStudentennummer() == nummer) {
                return student;
            }
        }
        return null;
    }

    public void studentToevoegen(Scanner scanner) {
        Student student = new Student();
        System.out.println("Voer studenten naam in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        int studentnummer;
        while (true) {
            studentnummer = vraagOmStudentnummer(scanner);
            if (getStudentMetNummer(studentnummer) == null) {
                break;
            }
            System.out.println("Er is al een student met dit nummer; probeer opnieuw.");
        }
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
        Student student = getStudentMetNummer(studentnummer);
        if (student == null) {
            System.out.println("Er is geen student met dat nummer.");
            return;
        }
        Util.printArrayList(student.getGehaald(), "examen gehaald", "examens gehaald");
    }

    private int vraagOmStudentnummer(Scanner scanner) {
        System.out.println("Voer studentnummer in:");
        return Util.leesInt(scanner, 0, Integer.MAX_VALUE);
    }
}
