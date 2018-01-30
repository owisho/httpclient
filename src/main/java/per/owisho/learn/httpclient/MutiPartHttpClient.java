package per.owisho.learn.httpclient;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class MutiPartHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		detect();
	}
	
	public static void detect() throws ClientProtocolException, IOException {

		String url = "http://10.199.2.18:8061/detect";
		HttpPost post = new HttpPost(url);
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("image", new File("C:\\Users\\owisho\\Desktop\\工作任务\\20180122_20180126\\file\\24658483_1376969062009.jpg"));
		post.setEntity(builder.build());
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		CloseableHttpResponse resp = client.execute(post);
		System.out.println(resp);
		
	}

}
