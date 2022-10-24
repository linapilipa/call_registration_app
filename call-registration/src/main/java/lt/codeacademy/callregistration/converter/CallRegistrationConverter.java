package lt.codeacademy.callregistration.converter;

import lt.codeacademy.callregistration.dto.CallRegistrationDTO;
import lt.codeacademy.callregistration.dto.SaveCallRegistrationRequestDTO;
import lt.codeacademy.callregistration.entities.CallRegistration;
import org.aspectj.weaver.ast.Call;

public class CallRegistrationConverter {

    public static CallRegistrationDTO convertCallRegistrationToCallRegistrationDto(CallRegistration callRegistration){
        CallRegistrationDTO callRegistrationDTO = null;
        if (callRegistration != null){
            callRegistrationDTO = new CallRegistrationDTO();
            callRegistrationDTO.setName(callRegistration.getName());
            callRegistrationDTO.setLast_name(callRegistration.getLastName());
            callRegistrationDTO.setEMail(callRegistration.getEmail());
            callRegistrationDTO.setDateOfBirth(callRegistration.getDateOfBirth());
        }
        return callRegistrationDTO;
    }

    public static CallRegistration convertSaveCallRegistrationRequestDtoToCallRegistration(SaveCallRegistrationRequestDTO requestDTO){
        CallRegistration callRegistration = null;
        if (requestDTO != null){
            callRegistration = new CallRegistration();
            callRegistration.setName(requestDTO.getName());
            callRegistration.setLastName(requestDTO.getLastName());
            callRegistration.setEmail(requestDTO.getEmail());
            callRegistration.setDateOfBirth(requestDTO.getDateOfBirth());
        }
        return callRegistration;

    }

    public static CallRegistration patchCallRegistrationFromSaveCallRegistrationRequestDto (CallRegistration callRegistration,
                                                                                            SaveCallRegistrationRequestDTO requestDTO){
        if (requestDTO.getName() != null &&
                !requestDTO.getName().isEmpty() &&
                !requestDTO.getName().equals(callRegistration.getName())){
            callRegistration.setName(requestDTO.getName());
        }

        if (requestDTO.getLastName() != null &&
                !requestDTO.getLastName().isEmpty() &&
                !requestDTO.getLastName().equals(callRegistration.getLastName())){
            callRegistration.setLastName(requestDTO.getLastName());
        }

        if (requestDTO.getEmail() != null &&
                !requestDTO.getEmail().isEmpty() &&
                !requestDTO.getEmail().equals(callRegistration.getEmail())){
            callRegistration.setEmail(requestDTO.getEmail());
        }

        if (requestDTO.getDateOfBirth() != null &&
                !requestDTO.getDateOfBirth().equals("") &&
                !requestDTO.getDateOfBirth().equals(callRegistration.getDateOfBirth())){
            callRegistration.setDateOfBirth(requestDTO.getDateOfBirth());
        }

        return callRegistration;
    }

}
