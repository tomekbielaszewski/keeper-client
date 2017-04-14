package org.grizz.keeper.client.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class HttpAdapter {
    private final String charset = "UTF-8";
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

    public String post(String url, String content, NameValuePair... params) {
        HttpUriRequest postRequest = buildPostRequest(url, content, params);
        return execute(postRequest);
    }

    public String delete(String url) {
        HttpDelete httpDelete = new HttpDelete(baseUrl + url);
        return execute(httpDelete);
    }

    private HttpUriRequest buildPostRequest(String url, String content, NameValuePair[] params) {
        RequestBuilder postRequestBuilder = RequestBuilder.post(baseUrl + url);
        Arrays.stream(params).forEach(param -> postRequestBuilder.addParameter(param));
        Optional.ofNullable(content).ifPresent(c -> postRequestBuilder.setEntity(createHttpEntity(c)));
        return postRequestBuilder.build();
    }

    private HttpEntity createHttpEntity(String content) {
        return new StringEntity(content, charset);
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
