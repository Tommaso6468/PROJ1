import com.github.cliftonlabs.json_simple.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static ExamensController examensController;
    public static StudentenController studentenController;
    public static JsonArray studentenArray;
    public static JsonArray examensArray;

    public static void main(String[] args) {
        System.out.println("Welkom bij UwU Exams!");

        String errorStudenten = leesStudenten();
        String errorExamens = leesExamens();
        if (errorExamens != null || errorStudenten != null) {
            System.out.println("Het programma kan helaas niet worden opgestart, omdat de info over de examens/studenten niet kan worden ingelezen:");
            System.out.println(errorExamens);
            System.out.println(errorStudenten);
            return;
        }

        startMenu();
    }

    /**
     * Creëert de ExamensController door de info van de JSON te lezen.
     *
     * @return Als er een fout is, de beschrijving van de fout; anders null.
     */
    private static String leesExamens() {
        FileReader reader;
        try {
            // TODO
            reader = new FileReader("./examens.json");
        } catch (FileNotFoundException exception) {
            return "Het bestand \"examens.json\" mist.";
        }

        Object rootObj;
        try {
            rootObj = Jsoner.deserialize(reader);
            if (!(rootObj instanceof JsonArray array)) {
                return "De JSON moet een array met examens bevatten.";
            }
            examensController = ExamensController.leesVanJson(array);
            examensArray = array;
        } catch (JsonException exception) {
            //exception.printStackTrace();
            return "Er zit een syntaxisfout in de JSON.";
        } catch (InvalidJsonFormatException exception) {
            return "De JSON is niet goed geformatteerd. " + exception.getMessage();
        }
        return null;
    }

    private static String leesStudenten() {
        FileReader reader;
        try {
            reader = new FileReader("./studenten.json");
        } catch (FileNotFoundException exception) {
            return "Het bestand \"studenten.json\" mist";
        }

        Object rootObj;
        try {
            rootObj = Jsoner.deserialize(reader);
            if (!(rootObj instanceof JsonArray array)) {
                return "De JSON moet een array met studenten bevatten.";
            }
            studentenController = StudentenController.leesVanJson(array);
            studentenArray = array;
        } catch (JsonException exception) {
            //exception.printStackTrace();
            return "Er zit een syntaxisfout in de JSON.";
        } catch (InvalidJsonFormatException exception) {
            return "De JSON is niet goed geformatteerd. " + exception.getMessage();
        }

        return null;
    }

    public static void startMenu() {
        if (examensController == null || studentenController == null) throw new IllegalStateException();

        Scanner scanner = new Scanner(System.in);
        boolean isGeslaagdExamen = false;

        while (true) {
            System.out.println("1) Lijst met examens");
            System.out.println("2) Lijst met studenten");
            System.out.println("3) Nieuwe student inschrijven");
            System.out.println("4) Student verwijderen");
            System.out.println("5) Examen afnemen");
            System.out.println("6) Is student geslaagd voor test?");
            System.out.println("7) Welke examens heeft student gehaald?");
            System.out.println("8) Welke student heeft de meeste examens gehaald?");
            System.out.println("0) Exit");

            ArrayList<Student> studenten = studentenController.getStudentLijst();
            int aantalStudenten = studenten.size();

            int keuze = Util.leesInt(scanner, 0, 8);

            switch (keuze) {
                case 1:
                    examensController.lijstExamens();
                    break;
                case 2:
                    studentenController.studentLijst();
                    break;
                case 3:
                    studentenController.studentToevoegen(scanner);
                    break;
                case 4:
                    studentenController.studentVerwijderen(scanner);
                    break;
                case 5:
                    //check of student in lijst staat
                    System.out.println("Voer je leerlingennummer in:");
                    int studentenNummer = Util.leesInt(scanner, 0, Integer.MAX_VALUE);
                    Student student = studentenController.getStudentMetNummer(studentenNummer);
                    if (student == null) {
                        student = studentenController.studentToevoegen(scanner, studentenNummer);
                    }
                    Examen examen = examensController.kiesExamen(scanner);
                    isGeslaagdExamen = examen.neemAf(scanner);
                    if (isGeslaagdExamen) {
                        student.setGehaald(examen);

                        JsonObject gekozenExamenJsonObj = (JsonObject) examensArray.get(ExamensController.gekozenExamenIndex);

                        JsonArray listGehaald = (JsonArray) gekozenExamenJsonObj.get("gehaald");
                        listGehaald.add(studentenNummer);
                        gekozenExamenJsonObj.putChain("gehaald",listGehaald);
                        examensArray.remove(ExamensController.gekozenExamenIndex);
                        examensArray.add(gekozenExamenJsonObj);

                        try {

                            FileWriter fw = new FileWriter("./examens.json");
                            fw.write(examensArray.toJson());
                            fw.flush();
                            fw.close();

                        } catch (IOException e) {
                            System.out.println("Examens.json niet gevonden");
                        }


                    }
                    break;
                case 6:
                    if (isGeslaagdExamen) {
                        System.out.println("Student is geslaagd voor het examen!");
                    } else {
                        System.out.println("Student is niet geslaagd voor het examen");
                    }
                    break;
                case 7:
                    studentenController.studentWelkeExamensGehaald(scanner);
                    break;
                case 8:
                    System.out.println("Deze student heeft de meeste examens gehaald:");
                    ArrayList<Student> studentMeesteExamens = studentenController.studentMeesteExamensGehaald();

                    if (studentMeesteExamens.isEmpty()) {
                        System.out.println("Niemand heeft een examen gehaald.");
                    } else {
                        for (Student st : studentMeesteExamens) {
                            System.out.println(st.getNaam());
                        }
                    }

                    break;
                case 0:
                    return;
            }

            System.out.println("Druk op enter om terug te gaan naar het menu.");
            scanner.nextLine();
        }
    }
}

