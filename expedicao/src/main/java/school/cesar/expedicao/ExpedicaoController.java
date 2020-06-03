package school.cesar.expedicao;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ExpedicaoController {
    @PostMapping("/")
    public ResponseEntity<HashMap<String, String>> expedicao(@RequestBody String body) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("Delivered order to: " + body);
        response.put("delivered", "true");
        response.put("success", "true");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}