import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;

import javax.management.MBeanRegistration;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentenController {

    private final ArrayList<Student> studenten;

    public ArrayList<Student> getStudentLijst() {
        return this.studenten;
    }

    public StudentenController() {
        studenten = new ArrayList<>();
    }

    private StudentenController(ArrayList<Student> studenten) {
        this.studenten = studenten;
    }

    /**
     * Leest de info over alle examens uit een JSON-array.
     *
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

    public JsonArray studentToevoegenAanJson(JsonArray array, int studentennummer, String naam) {

        try {

            FileWriter fw = new FileWriter("./studenten.json");

            JsonObject obj = new JsonObject();
            obj.put("studentennummer", studentennummer);
            obj.put("naam", naam);

            array.add(obj);

            fw.write(array.toJson());
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
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

    public void studentToevoegen(Scanner scanner, JsonArray array) {
        int studentnummer;
        while (true) {
            studentnummer = vraagOmStudentnummer(scanner);
            if (getStudentMetNummer(studentnummer) == null) {
                break;
            }
            System.out.println("Er is al een student met dit nummer; probeer opnieuw.");
        }
        studentToevoegen(scanner, studentnummer, array);
    }

    public Student studentToevoegen(Scanner scanner, int studentnummer, JsonArray array) {
        Student student = new Student();
        student.setStudentennummer(studentnummer);
        System.out.println("Voer de naam van de student in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        studenten.add(student);
        Menu.studentenArray = studentToevoegenAanJson(array, studentnummer, naam);
        return student;
    }

    public void studentVerwijderen(Scanner scanner, JsonArray array) {
        int nummer = vraagOmStudentnummer(scanner);
        studenten.removeIf(st -> st.getStudentennummer() == nummer);
        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = (JsonObject) array.get(i);
            if (obj.get("studentennummer").equals(nummer)) {
                array.remove(i);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("./studenten.json");
            fw.write(array.toJson());
            fw.flush();
            fw.close();
            Menu.studentenArray = array;
        } catch (IOException e) {
            e.printStackTrace();
        }


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
