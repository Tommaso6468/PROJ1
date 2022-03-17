import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static ExamensController examensController;
    private static StudentenController studentenController;

    public static void main(String[] args) {
        System.out.println("Welkom bij (naam programma)!");

        String error = leesExamens();
        if (error != null) {
            System.out.println("Het programma kan helaas niet worden opgestart, omdat de info over de examens niet kan worden ingelezen:");
            System.out.println(error);
            return;
        }

        // TODO
        studentenController = new StudentenController();

        startMenu();
    }

    /**
     * Creëert de ExamensController door de info van de JSON te lezen.
     * @return Als er een fout is, de beschrijving van de fout; anders null.
     */
    private static String leesExamens() {
        FileReader reader;
        try {
            // TODO
            reader = new FileReader("./examens.json");
        }
        catch (FileNotFoundException exception) {
            return "Het bestand \"examens.json\" mist.";
        }

        Object rootObj;
        try {
            rootObj = Jsoner.deserialize(reader);
            if (!(rootObj instanceof JsonArray array)) {
                return "De JSON moet een array met examens bevatten.";
            }
            examensController = ExamensController.leesVanJson(array);
        }
        catch (JsonException exception) {
            //exception.printStackTrace();
            return "Er zit een syntaxisfout in de JSON.";
        }
        catch (InvalidJsonFormatException exception) {
            return "De JSON is niet goed geformatteerd. " + exception.getMessage();
        }
        return null;
    }

    public static void startMenu() {
        if (examensController == null || studentenController == null) throw new IllegalStateException();

        Scanner scanner = new Scanner(System.in);
        boolean isGeslaagdExamen = false;

        int keuze;
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

            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    System.out.println("Examens:");
                    examensController.printExamens();
                    break;
                case 2:
                    System.out.println("Studenten:");
                    studentenController.studentLijst();
                    break;
                case 3:
                    studentenController.studentToevoegen();
                    break;
                case 4:
                    studentenController.studentVerwijderen();
                    break;
                case 5:
                    //check of student in lijst staat
                    System.out.println("Voer je leerlingennummer in:");
                    int studentenNummer = scanner.nextInt();

                    //loopt door studentenLijst om te zoeken naar het leerlingnummer
                    for (int i = 0; i < aantalStudenten; i++) {
                        if (studenten.get(i).getStudentennummer() == studentenNummer) {
                            isGeslaagdExamen = false;
                            Examen examen = examensController.kiesExamen(scanner);
                            isGeslaagdExamen = examen.neemAf();
                        } else if (i == aantalStudenten - 1) {
                            studentenController.studentToevoegen();
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
                    studentenController.studentWelkeExamensGehaald();
                    break;
                case 8:
                    System.out.println("Deze Student heeft de meeste examens gehaald:");
                    Student student = studentenController.studentMeesteExamensGehaald();
                    System.out.println(student.getStudentennummer() + " " + student.getNaam());
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Dit is geen menu optie!");
                    break;
            }
        }
    }
}


