import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten;

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    public StudentenController() {studenten = new ArrayList<>();}

    private StudentenController(ArrayList<Student> studenten) {
        this.studenten = studenten;
    }

    /**
     * Leest de info over alle examens uit een JSON-array.
     * @param array De array met de info over alle examens.
     * @return De ExamensController geconstrueerd met de info uit de array.
     * @throws InvalidJsonFormatException De examens zijn niet goed geformatteerd.
     */
    public static StudentenController leesVanJson(JsonArray array) {
        ArrayList<Student> studenten = new ArrayList<>(array.size());
        for (Object examenInput : array) {
            if (!(examenInput instanceof JsonObject examenObject)) {
                return null;
            }
            studenten.add(Student.leesVanJson(examenObject));
        }
        return new StudentenController(studenten);
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
