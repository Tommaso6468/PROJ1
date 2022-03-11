import java.util.*;

public class Vraag {

    private final String vraag;
    private final String[] opties;
    private final String antwoord;
    private String eindUserAntwoord;
    private boolean goedOfFout = false;

    public Vraag(String vraag, String[] opties, String antwoord){
        this.vraag = vraag;
        this.opties = opties;
        this.antwoord = antwoord;
    }

    public boolean stelVraag() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(vraag);

        if (opties != null) {
            //Meerdere opties

            //Preparen voor printen opties
            List<String> optiesList = Arrays.asList(opties);
            Collections.shuffle(optiesList);
            ArrayList<Character> letters = new ArrayList<>();
            char a;
            for (a = 'a'; a <= 'z'; ++a) {
                letters.add(a);
            }

            //Printen opties
            for (int i = 0; i < opties.length; i++) {
                System.out.println(letters.get(i) + ") " + opties[i]);
            }

            System.out.println("\nTyp uw antwoord in en druk Enter");

            //Inlezen antwoord
            int indexAntwoord;
            while (true) {
                String userInput = scanner.next();
                Character userInputChar = userInput.charAt(0);

                //Controle antwoord
                indexAntwoord = userInputChar - 'a';
                if (indexAntwoord > 0 && indexAntwoord < opties.length) {
                    break;
                } else {
                    System.out.println("Voer een geldige optie in.");
                }
            }
            eindUserAntwoord = opties[indexAntwoord];
            goedOfFout = opties[indexAntwoord].equals(antwoord);
        }

        // Open vraag
        System.out.println("\nTyp uw antwoord in en druk Enter");
        String userInput = scanner.next();
        eindUserAntwoord = userInput;
        goedOfFout = userInput.equalsIgnoreCase(antwoord);

        return goedOfFout;
    }

    public void printVraagEnAntwoordEnGoedOfFout() {
        System.out.println("\nVraag: " + vraag + "\nJuist antwoord: " + antwoord + "\nJouw antwoord: " + eindUserAntwoord + "\nResultaat: " + goedOfFout);
    }

}
