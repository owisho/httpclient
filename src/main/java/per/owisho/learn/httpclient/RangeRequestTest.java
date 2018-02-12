package per.owisho.learn.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

/**
 * 请求时范围请求
 * http 1.1开始支持
 * @author owisho
 *
 */
public class RangeRequestTest {

	public static void main(String[] args) throws Exception{
		requestWithRange();
	}
	
	public static void requestWithRange() throws ClientProtocolException, IOException {
		HttpClientBuilder builder = HttpClientBuilder.create();
		long start = 0;
		long end = 10000;
		FileOutputStream oStream = new FileOutputStream(new File("C:\\Users\\owisho\\Desktop\\2.jpg"));
		while(true) {
			BasicHeader rangeHeader = new BasicHeader("Range", "bytes="+start+"-"+end);
			List<BasicHeader> headers = new ArrayList<BasicHeader>();
			headers.add(rangeHeader);
			builder.setDefaultHeaders(headers);
			HttpClient client = builder.build();
			HttpResponse resp = client.execute(new HttpHost("localhost",8080),new HttpGet("/rangerequest"));
			System.out.println(resp);
			InputStream respin = resp.getEntity().getContent();
			int read = -1;
			int index = 1;
			while((read=respin.read())!=-1) {
 				oStream.write(read);
				index++;
			}
			if(index<10000)
				break;
			start = end+1;
			end = end+10000;
		}
		oStream.close();
		
	}
}
