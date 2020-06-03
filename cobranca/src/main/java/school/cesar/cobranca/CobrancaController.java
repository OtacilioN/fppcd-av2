package school.cesar.cobranca;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CobrancaController {
    CobrancaModel javaBigBook;
    CobrancaModel javaSmallBook;
    CobrancaModel pythonBook;

    public CobrancaController() {
        this.javaBigBook = new CobrancaModel("JAVA-BOOK-300-PAGES", 100.80);
        this.javaSmallBook = new CobrancaModel("JAVA-BOOK-100-PAGES", 42.42);
        this.pythonBook = new CobrancaModel("PYTHON-BOOK-400-PAGES", 300.00);
    }

    @PostMapping("/")
    public ResponseEntity<HashMap<String, String>> cobranca(@RequestBody List<String> skuList) {
        HashMap<String, String> response = new HashMap<>();
        String flag = "true";
        double totalPrice = 0;

        for (String sku : skuList) {
            if (sku.equals(javaBigBook.getSku())) {
                response.put(javaBigBook.getSku(), "bought");
                totalPrice = totalPrice + javaBigBook.getPrice();
            }
            if (sku.equals(javaSmallBook.getSku())) {
                response.put(javaSmallBook.getSku(), "bought");
                totalPrice = totalPrice + javaSmallBook.getPrice();
            }
            if (sku.equals(pythonBook.getSku())) {
                response.put(pythonBook.getSku(), "bought");
                totalPrice = totalPrice + pythonBook.getPrice();
            }
        }
        response.put("success", flag);
        response.put("total", String.valueOf(totalPrice));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}