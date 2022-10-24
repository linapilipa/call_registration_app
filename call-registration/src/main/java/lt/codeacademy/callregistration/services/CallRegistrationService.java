package lt.codeacademy.callregistration.services;

import lt.codeacademy.callregistration.dto.CallRegistrationDTO;
import lt.codeacademy.callregistration.entities.CallRegistration;
import lt.codeacademy.callregistration.repositories.CallRegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static lt.codeacademy.callregistration.converter.CallRegistrationConverter.convertCallRegistrationToCallRegistrationDto;

@Service
public class CallRegistrationService {

    private CallRegistrationRepository callRegistrationRepository;

    public CallRegistrationService(CallRegistrationRepository callRegistrationRepository) {
        this.callRegistrationRepository = callRegistrationRepository;
    }

    public List<CallRegistration> getAllCalls(){
        return this.callRegistrationRepository.findAll();
    }

    public CallRegistration getCallById(Long id){

        Optional<CallRegistration> call = this.callRegistrationRepository.findById(id);
        if (call.isPresent()){
            return call.get();
        } else {
            return null;
        }
    }

    public void deleteCallById(Long id){
        this.callRegistrationRepository.deleteById(id);
    }


    public void saveCall(CallRegistration callRegistration){
        this.callRegistrationRepository.save(callRegistration);
    }

}
