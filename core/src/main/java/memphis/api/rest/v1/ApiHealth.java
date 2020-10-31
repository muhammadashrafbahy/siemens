package memphis.api.rest.v1;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/equipment")
@Api(description = "show API Health status")
public class ApiHealth {

    @GetMapping(path = "/health" , produces = APPLICATION_JSON_VALUE)
    public HashMap<Object , Object> health (){

        HashMap<Object ,Object> result = new HashMap<>();
        String out =  String.format("%s %d", System.getenv("REVISION"), System.currentTimeMillis());
        result.put("health" , out);
        return result;
    }


}
