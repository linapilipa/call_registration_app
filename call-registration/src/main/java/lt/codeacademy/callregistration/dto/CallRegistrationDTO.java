package lt.codeacademy.callregistration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.ShiftRight;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CallRegistrationDTO {

    private String name;
    private String last_name;
    private String eMail;
    private LocalDate dateOfBirth;
}
