package school.cesar.estoque;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EstoqueController {
    EstoqueModel javaBigBook;
    EstoqueModel javaSmallBook;
    EstoqueModel pythonBook;

    public EstoqueController() {
        this.javaBigBook = new EstoqueModel("JAVA-BOOK-300-PAGES", 10);
        this.javaSmallBook = new EstoqueModel("JAVA-BOOK-100-PAGES", 0);
        this.pythonBook = new EstoqueModel("PYTHON-BOOK-400-PAGES", 4);
    }

    @PostMapping("/")
    public ResponseEntity<HashMap<String, String>> estoque(@RequestBody List<String> skuList) {
        HashMap<String, String> response = new HashMap<>();
        String flag = "true";
        for (String sku : skuList) {
            switch (sku) {
                case "JAVA-BOOK-300-PAGES":
                    if (javaBigBook.getQuantity() > 0) {
                        response.put("JAVA-BOOK-300-PAGES", "available at stock");
                    } else {
                        response.put("JAVA-BOOK-300-PAGES", "out of stock");
                        flag = "false";
                    }
                    break;
                case "JAVA-BOOK-100-PAGES":
                    if (javaSmallBook.getQuantity() > 0) {
                        response.put("JAVA-BOOK-100-PAGES", "available at stock");
                    } else {
                        response.put("JAVA-BOOK-100-PAGES", "out of stock");
                        flag = "false";
                    }
                    break;
                case "PYTHON-BOOK-400-PAGES":
                    if (pythonBook.getQuantity() > 0) {
                        response.put("PYTHON-BOOK-400-PAGES", "available at stock");
                    } else {
                        response.put("PYTHON-BOOK-400-PAGES", "out of stock");
                        flag = "false";
                    }
                    break;
                default:
                    response.put("success", "false");
                    response.put("invalid", sku);
                    response.put("reason", "You ordered an invalid sku");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        response.put("success", flag);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
