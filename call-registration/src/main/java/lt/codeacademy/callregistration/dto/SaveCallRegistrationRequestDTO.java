package lt.codeacademy.callregistration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SaveCallRegistrationRequestDTO {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    @Override
    public String toString() {
        return "SaveCallRegistrationRequestDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
