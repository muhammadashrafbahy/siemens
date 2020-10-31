package memphis.api.rest.exception;

import memphis.api.rest.v1.dto.ApiResource.ApiResourceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;

import memphis.exception.*;

@ControllerAdvice(annotations = RestController.class)
public class exceptionHandleController extends ResponseEntityExceptionHandler {


        private Logger  logger =LoggerFactory.getLogger(exceptionHandleController.class);


        @RequestMapping("/errors-directory/{errorReference}")
        public ResponseEntity getError(@PathVariable("errorReference") String errorReference){
            logger.error(errorReference);
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(equipmentAlreadyExist.class)
        public ResponseEntity<ApiResourceError> equipmentAlreadyexist(final equipmentAlreadyExist equipmentAlreadyExist){

        // print log error message
           logger.error(String.format("Error : %s , %s",equipmentAlreadyExist.getErrorNumber() , equipmentAlreadyExist.getMessage()));

        //Make ApiResource for error response
            HashMap<String ,String> errors = new HashMap<>();
            errors.put(equipmentAlreadyExist.getEquipment_id() , equipmentAlreadyExist.getErrorNumber());
            ApiError apiError = new ApiError(equipmentAlreadyExist.getMessage(),equipmentAlreadyExist.getErrorNumber(),errors);
            return  new ResponseEntity<>(new ApiResourceError(apiError),HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(equipmentNotFound.class)
        public ResponseEntity<ApiResourceError> equipmentNotFound(final equipmentNotFound equipmentNotFound){

            // print log error message
            logger.error(String.format("Error : %s , %s",equipmentNotFound.getErrorNumber() , equipmentNotFound.getMessage()));

            //Make ApiResource for error response
            HashMap<String ,String> errors = new HashMap<>();
            errors.put(equipmentNotFound.getEquipment_id() , equipmentNotFound.getErrorNumber());
            ApiError apiError = new ApiError(equipmentNotFound.getMessage(),equipmentNotFound.getErrorNumber(),errors);
            return  new ResponseEntity<>(new ApiResourceError(apiError),HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResourceError> handleAuthenticationException(final AuthenticationException e) {
        logger.error(String.format("Error: %s / %s", "APP1-EQUIPMENT-ERROR-004", e.getMessage()));

        ApiError apiError = new ApiError("APP1-EQUIPMENT-ERROR-004", e.getMessage(), Collections.emptyMap());
        return new ResponseEntity<>(new ApiResourceError(apiError), HttpStatus.UNAUTHORIZED);
    }


}
