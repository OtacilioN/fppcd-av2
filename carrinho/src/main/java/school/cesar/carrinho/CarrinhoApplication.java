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
		String outOfStockBookSkuTest = "[\"JAVA-BOOK-100-PAGES\"]";
		String availableBooksInStokTest = "[\"JAVA-BOOK-300-PAGES\", \"PYTHON-BOOK-400-PAGES\"]";
		// First test with out of stock book
		buyBooks(outOfStockBookSkuTest);

		// Test with available books
		buyBooks(availableBooksInStokTest);
	}

	private static void buyBooks(String skuList) {
		String cep = "53080042";
		String email = "contato@otaciliomaia.com";
		try {
			if (requestEstoque(skuList)) {
				System.out.println("Got item from stock");
				if (requestCobranca(skuList)) {
					System.out.println("Paid books!");
					if (requestExpedicao(skuList, cep, email)) {
						System.out.println("Books delivered!");
					} else {
						System.out.println("Fail to deliver");
					}
				} else {
					System.out.println("Fail to pay");
				}
			} else {
				System.out.println("Fail to get from stock");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean requestExpedicao(String skuList, String cep, String email) throws Exception {
		String result = "";
		HttpPost post = new HttpPost("http://localhost:8084");
		post.addHeader("content-type", "application/json");
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("skuList: " + skuList + ", ");
		json.append("cep: " + cep + ", ");
		json.append("email: " + email);
		json.append("}");

		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		}
		return result.indexOf("\"success\":\"true\"") != -1 ? true : false;
	}

	private static boolean requestCobranca(String skuList) throws Exception {
		String result = "";
		HttpPost post = new HttpPost("http://localhost:8083");
		post.addHeader("content-type", "application/json");
		StringBuilder json = new StringBuilder();
		json.append(skuList);

		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		}
		return result.indexOf("\"success\":\"true\"") != -1 ? true : false;
	}

	private static boolean requestEstoque(String skuList) throws Exception {
		String result = "";
		HttpPost post = new HttpPost("http://localhost:8082");
		post.addHeader("content-type", "application/json");
		StringBuilder json = new StringBuilder();
		json.append(skuList);

		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		}
		return result.indexOf("\"success\":\"true\"") != -1 ? true : false;
	}
}
