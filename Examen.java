public class Examen {

    private final String naam;
    private final Vraag[] vragen;
    private final int minimum;
    private int aantalGoed;

    public Examen(String naam, Vraag[] vragen, int minimum) {
        if (naam == null || vragen == null) throw new IllegalArgumentException();
        this.naam = naam;
        this.vragen = vragen;
        this.minimum = minimum;
    }

    public void controleerVoldoende() {

    }

    public String getNaam() {
        return naam;
    }

    /**
     * Neemt het examen af; stelt alle vragen in het examen en checkt of het minimum aantal goed is gehaald.
     * @return True als het minimum aantal goed is gehaald; anders false.
     */
    public boolean neemAf() {
        int aantalGoed = 0;
        for (int i = 0; i < vragen.length; i++) {
            System.out.println();
            System.out.printf("Vraag %d:%n", i+1);
            boolean isGoed = vragen[i].stelVraag();
            if (isGoed) {
                aantalGoed++;
            }
        }
        System.out.println();
        System.out.printf("Het examen is voltooid. U heeft %d vragen goed.%n", aantalGoed);
        return aantalGoed >= minimum;
    }
}
