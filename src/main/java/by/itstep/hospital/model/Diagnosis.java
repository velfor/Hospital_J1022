package by.itstep.hospital.model;

public enum Diagnosis {
    PNEUMONIA("пневмония"),
    ATYPICAL_PNEUMONIA("атипичная пневмония"),
    TONSILLITIS("ангина");

    private String russianValue;

    Diagnosis(String russianValue) {
        this.russianValue = russianValue;
    }

    public String getRussianName() {
        return russianValue;
    }
}
