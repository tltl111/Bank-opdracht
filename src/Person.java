import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private String birthDate;
    private String eyeColor;
    private String relationshipStatus;

    public Person(String name, String birthDate, String eyeColor, String relationshipStatus) {
        this.name = name;
        this.birthDate = birthDate;
        this.eyeColor = eyeColor;
        this.relationshipStatus = relationshipStatus;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getEyeColore() {
        return this.eyeColor;
    }

    public String getRelationshipStatus() {
        return this.relationshipStatus;
    }

    // public void setName() {
    // this.name = name;
    // }
    // public void setBirthDate() {
    // this.birthDate = birthDate;
    // }
    // public void setEyeColor() {
    // this.eyeColor = eyeColor;
    // }
    // public void setRelationshipStatus() {
    // this.relationshipStatus = relationshipStatus;
    // }

    public int getAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birDate = LocalDate.parse(dob, formatter);
        LocalDate curDate = LocalDate.now();

        if ((dob != null) && (curDate != null)) {
            return Period.between(birDate, curDate).getYears();
        } else {
            return 0;
        }
    }
}
