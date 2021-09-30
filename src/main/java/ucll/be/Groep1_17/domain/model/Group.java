package ucll.be.Groep1_17.domain.model;

public enum Group {
    RECREATION("recreation"), YOUTH("youth"), ELITE("elite");

    private String stringValue;

    private Group(String stringValue){
        this.stringValue=stringValue;
    }

    public String getStringValue() {return stringValue;}
}
