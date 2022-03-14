public class Examen {

    private final String naam;
    private final Vraag[] vragen;
    private final int minimum;

    /**
     * Construeert een Examen.
     * @param naam De naam van het examen.
     * @param vragen De lijst met vragen in dit examen.
     * @param minimum Het minimum aantal vragen dat goed moeten zijn om dit examen te halen.
     */
    public Examen(String naam, Vraag[] vragen, int minimum) {
        if (naam == null || vragen == null) throw new IllegalArgumentException();
        this.naam = naam;
        this.vragen = vragen;
        this.minimum = minimum;
    }

    /**
     * Controleert of een aantal vragen goed voldoende is om dit examen te slagen.
     * @param aantalGoed Het aantal vragen dat goed is.
     * @return True als het voldoende is, anders false.
     */
    public boolean isVoldoende(int aantalGoed) {
        return aantalGoed >= minimum;
    }

    /**
     * Krijgt de naam van dit examen.
     * @return De naam van dit examen.
     */
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
        return isVoldoende(aantalGoed);
    }
}
