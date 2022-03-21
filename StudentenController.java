import java.util.Scanner;

public class StudentenController extends Controller<Student> {

    public StudentenController() {
        super();
    }

    /**
     * Haalt de student up met een bepaald nummer
     * @param nummer Het studentennummer
     * @return De student met dat nummer, of null als die er niet is.
     */
    public Student getStudentMetNummer(int nummer) {
        for (Student student : getLijst()) {
            if (student.getStudentennummer() == nummer) {
                return student;
            }
        }
        return null;
    }

    public void studentToevoegen(Scanner scanner) {
        int studentnummer;
        while (true) {
            studentnummer = vraagOmStudentnummer(scanner);
            if (getStudentMetNummer(studentnummer) == null) {
                break;
            }
            System.out.println("Er is al een student met dit nummer; probeer opnieuw.");
        }
        studentToevoegen(scanner, studentnummer);
    }

    public Student studentToevoegen(Scanner scanner, int studentnummer) {
        Student student = new Student();
        student.setStudentennummer(studentnummer);
        System.out.println("Voer de naam van de student in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        voegToe(student);
        return student;
    }

    public void studentVerwijderen(Scanner scanner) {
        int nummer = vraagOmStudentnummer(scanner);
        getLijst().removeIf(st -> st.getStudentennummer() == nummer);
    }

    public void studentLijst() {
        Util.printArrayList(getLijst(), "student", "studenten");
    }

    public Student studentMeesteExamensGehaald() {
        Student maxex = new Student();
        for (Student st : getLijst()) {
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
