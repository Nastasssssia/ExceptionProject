import java.text.SimpleDateFormat;
import java.util.Date;

class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private long phoneNumber;
    private char gender;

    public UserData(String lastName, String firstName, String middleName, Date birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "<" + lastName +">" + "<"+firstName +">"+ "<"+ middleName +">"+ "<"+dateFormat.format(birthDate) + " >" + "<"+phoneNumber +">"+ "<"+gender+">";
    }
}






