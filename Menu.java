import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        //constructors
        ExamensController examensController = new ExamensController();
        StudentenController studentenController = new StudentenController();


        int keuze;
        while(true) {
            System.out.println("1) Lijst met examens");
            System.out.println("2) Lijst met studenten");
            System.out.println("3) Nieuwe student inschrijven");
            System.out.println("4) Student verwijderen");
            System.out.println("5) Examen afnemen");
            System.out.println("6) Is student geslaagd voor test?");
            System.out.println("7) Welke examens heeft student gehaald?");
            System.out.println("8) Welke student heeft de meeste examens gehaald?");
            System.out.println("0) Exit");

            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    //check of student in lijst staat
                    ArrayList<Student> studenten = StudentenController.studentLijst();
                    int aantal = studenten.size();

                    System.out.println("Voer je leerlingennummer in:");
                    int studentenNummer = scanner.nextInt();

                    for (int i = 0; i < aantal; i++) {
                        if (studenten.get(i).getStudentennummer() == studentenNummer) {
                            examensController.kiesEnStartExamen(scanner);
                        } else if (i == aantal) {
                            studentenController.studentToevoegen();
                        }
                    }
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 0:

                    break;
                default:
                    System.out.println("Dit is geen menu optie!");
                    break;
            }
        }
    }
}


