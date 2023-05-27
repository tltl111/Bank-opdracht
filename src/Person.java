import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


class Person {
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
        return name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getEyeColor() {
        return eyeColor;
    }
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public int getAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate curDate = LocalDate.now();
        if ((dob != null) && (curDate != null)) {
            return Period.between(birthDate, curDate).getYears();
        } else {
            return 0;
        }
    }
}
