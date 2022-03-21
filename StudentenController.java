import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten = new ArrayList<>();

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    /**
     * Haalt de student up met een bepaald nummer
     *
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
        studenten.add(student);
        return student;
    }

    public void studentVerwijderen(Scanner scanner) {
        int nummer = vraagOmStudentnummer(scanner);
        studenten.removeIf(st -> st.getStudentennummer() == nummer);
    }

    public void studentLijst() {
        Util.printArrayList(studenten, "student", "studenten");
    }

    public ArrayList<Student> studentMeesteExamensGehaald() {
        ArrayList<Student> studentenMeesteExamens = new ArrayList<>();

        for (Student st : studenten) {

            if (studentenMeesteExamens.isEmpty()) {
                studentenMeesteExamens.add(st);
            } else if (st.getGehaald().size() > studentenMeesteExamens.get(0).getGehaald().size()) {
                studentenMeesteExamens.clear();
                studentenMeesteExamens.add(st);
            } else if (st.getGehaald().size() == studentenMeesteExamens.get(0).getGehaald().size()) {
                studentenMeesteExamens.add(st);
            }
        }
        return studentenMeesteExamens;
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
