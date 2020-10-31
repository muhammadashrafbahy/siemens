package memphis.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ApiError {

    @JsonProperty("errorNumber")
    private final String errorNumber;

    @JsonProperty("errorMessage")
    private final String errorMessage;

    @JsonProperty("detailedErrors")
    private final Map<String, String> detailedErrors;


    public ApiError(String errorMessage, String errorNumber, Map<String, String> detailedErrors) {
        this.errorMessage = errorMessage;
        this.errorNumber = errorNumber;
        this.detailedErrors = detailedErrors;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public Map<String, String> getDetailedErrors() {
        return detailedErrors;
    }
}



