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
			requestEstoque();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void requestEstoque() throws Exception {

		HttpPost post = new HttpPost("http://localhost:8080");
		post.addHeader("content-type", "application/json");

		// add request parameter, form parameters
		// List<NameValuePair> urlParameters = new ArrayList<>();
		// urlParameters.addAll(["JAVA-BOOK-300-PAGES"]);
		// urlParameters.add(new BasicNameValuePair("username", "abc"));
		// urlParameters.add(new BasicNameValuePair("password", "123"));
		// urlParameters.add(new BasicNameValuePair("custom", "secret"));
		StringBuilder json = new StringBuilder();
		json.append("[\"JAVA-BOOK-300-PAGES\"]");

		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {

			System.out.println(EntityUtils.toString(response.getEntity()));
		}
	}
}
