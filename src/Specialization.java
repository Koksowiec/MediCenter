public enum Specialization {
    KARDIOLOG("Kardiolog"),
    ORTOPEDA("Ortopeda"),
    PEDIATRA("Pediatra"),
    DERMATOLOG("Dermatolog"),
    NEUROLOG("Neurolog"),
    PSYCHIATRA("Psychiatra"),
    CHIRURG("Chirurg"),
    LARYNGOLOG("Laryngolog"),
    OKULISTA("Okulista"),
    GINEKOLOG("Ginekolog");

    private final String displayName;

    Specialization(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
