package org.grizz.keeper.client.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HttpAdapter {
    private final BasicCookieStore cookieStore = new BasicCookieStore();
    private final CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    private final String baseUrl;

    public HttpAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String get(String url) {
        HttpGet httpGet = new HttpGet(baseUrl + url);
        return execute(httpGet);
    }

    private String execute(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);

            if (isErrorResponse(response)) {
                handleError(response);
            }

            return extractContent(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractContent(HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        return IOUtils.toString(content, StandardCharsets.UTF_8.name());
    }

    private void handleError(HttpResponse response) {
        //TODO
    }

    private boolean isErrorResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode() >= 300;
    }
}
