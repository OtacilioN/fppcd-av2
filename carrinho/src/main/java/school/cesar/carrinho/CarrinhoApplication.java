package school.cesar.carrinho;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.http.util.EntityUtils;

@SpringBootApplication
public class CarrinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrinhoApplication.class, args);
		try {
			// Example of failing by out of stock book
			if (requestEstoque("[\"JAVA-BOOK-100-PAGES\"]")) {
				System.out.println("Could get JAVA-BOOK-100-PAGES from stock");
			} else {
				System.out.println("Fail to get JAVA-BOOK-100-PAGES from stock");
			}

			// Example of available book in stock
			if (requestEstoque("[\"JAVA-BOOK-300-PAGES\"]")) {
				System.out.println("Could get JAVA-BOOK-300-PAGES from stock");
			} else {
				System.out.println("Fail to get JAVA-BOOK-300-PAGES from stock");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean requestEstoque(String book) throws Exception {
		String result = "";
		HttpPost post = new HttpPost("http://localhost:8080");
		post.addHeader("content-type", "application/json");
		StringBuilder json = new StringBuilder();
		json.append(book);

		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		}
		return result.indexOf("\"success\":\"true\"") != -1 ? true : false;
	}
}
