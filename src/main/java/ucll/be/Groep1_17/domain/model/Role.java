package ucll.be.Groep1_17.domain.model;

public enum Role {
    ADMIN("admin"), COORDINATOR("coordinator"), TRAINER("trainer");

    private String stringValue;

    private Role(String stringValue){
        this.stringValue=stringValue;
    }

    public String getStringValue() {return stringValue;}
}
