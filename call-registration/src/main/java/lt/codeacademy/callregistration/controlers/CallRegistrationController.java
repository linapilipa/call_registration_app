package lt.codeacademy.callregistration.controlers;

import lt.codeacademy.callregistration.dto.CallRegistrationDTO;
import lt.codeacademy.callregistration.dto.SaveCallRegistrationRequestDTO;
import lt.codeacademy.callregistration.entities.CallRegistration;
import lt.codeacademy.callregistration.services.CallRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lt.codeacademy.callregistration.converter.CallRegistrationConverter.*;

@CrossOrigin
@RestController
@RequestMapping("/call-registration")
public class CallRegistrationController {

    @Autowired
    private CallRegistrationService callRegistrationService;

    @GetMapping
    public List<CallRegistration> getAllCalls(){
        return callRegistrationService.getAllCalls();
    }

    @PostMapping
    public void createCall(@RequestBody SaveCallRegistrationRequestDTO saveCallRegistrationRequestDTO){
        CallRegistration callRegistration = convertSaveCallRegistrationRequestDtoToCallRegistration(saveCallRegistrationRequestDTO);
        this.callRegistrationService.saveCall(callRegistration);
        System.out.println(saveCallRegistrationRequestDTO.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putCallById(@PathVariable(name = "id") Long id,
                                            @RequestBody SaveCallRegistrationRequestDTO requestDTO){
        CallRegistration call = this.callRegistrationService.getCallById(id);
        if (call == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CallRegistration newCall = convertSaveCallRegistrationRequestDtoToCallRegistration(requestDTO);
        newCall.setId(call.getId());
        this.callRegistrationService.saveCall(newCall);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchCallById(@PathVariable(name = "id") Long id,
                                            @RequestBody SaveCallRegistrationRequestDTO requestDTO){
        CallRegistration call = this.callRegistrationService.getCallById(id);
        if (call == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        patchCallRegistrationFromSaveCallRegistrationRequestDto(call, requestDTO);
        this.callRegistrationService.saveCall(call);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCallById(@PathVariable(name = "id") Long id){
        this.callRegistrationService.deleteCallById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallRegistrationDTO> getCallById(@PathVariable(name = "id") Long id){
        CallRegistration call = this.callRegistrationService.getCallById(id);

        if (call == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(convertCallRegistrationToCallRegistrationDto(call));
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getCallNameById(@PathVariable(name = "id") Long id){
        CallRegistration call = this.callRegistrationService.getCallById(id);

        if (call == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(convertCallRegistrationToCallRegistrationDto(call).getName());
    }

}

