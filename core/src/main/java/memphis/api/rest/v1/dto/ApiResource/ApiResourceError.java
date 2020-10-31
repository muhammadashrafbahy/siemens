package memphis.api.rest.v1.dto.ApiResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import memphis.api.rest.exception.exceptionHandleController;
import memphis.exception.ApiError;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResourceError extends ResourceSupport {
    @JsonProperty("apiError")
    public ApiError apiError ;

     public  ApiResourceError(ApiError apiError){
         this.apiError=apiError;

         String errorNumber =this.apiError.getErrorNumber();

         this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(exceptionHandleController.class , errorNumber).getError(errorNumber)).withSelfRel());

     }


}
