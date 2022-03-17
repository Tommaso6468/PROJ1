public class InvalidJsonFormatException extends RuntimeException {

    private final String key;

    /**
     * Construeert een foutmelding voor ongeldig geformatteerde JSON.
     * @param key De naam van de JSON-eigenschap waar de fout is.
     * @param fout Een zinsdeel dat de fout beschrijft, zoals "is te groot".
     */
    public InvalidJsonFormatException(String key, String fout) {
        super(String.format("De eigenschap \"%s\" %s.", key, fout));
        this.key = key;
    }

    /**
     * Verkrijgt de naam van de JSON-eigenschap waar de fout is.
     * @return De naam van de JSON-eigenschap waar de fout is.
     */
    public String getKey() {
        return key;
    }
}
