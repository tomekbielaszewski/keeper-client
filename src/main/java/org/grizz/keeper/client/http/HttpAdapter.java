package org.grizz.keeper.client.http;

import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.grizz.keeper.client.http.exceptions.KeeperApiException;
import org.grizz.keeper.client.model.KeeperEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@Log
public class HttpAdapter {
    private final String charset = "UTF-8";
    private final KeeperApiErrorHandler errorHandler = new KeeperApiErrorHandler();
    private final BasicCookieStore cookieStore = new BasicCookieStore();
    private final CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    private final String baseUrl;

    public HttpAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String get(String url) {
        HttpGet httpGet = new HttpGet(baseUrl + url);
        log.info("Executing GET request to " + baseUrl + url);
        return execute(httpGet);
    }

    public String post(String url, String content, NameValuePair... params) {
        HttpUriRequest postRequest = buildPostRequest(url, content, params);
        log.info("Executing POST request to " + baseUrl + url);
        return execute(postRequest);
    }

    public String put(String url, String content, NameValuePair... params) {
        HttpUriRequest putRequest = buildPutRequest(url, content, params);
        log.info("Executing PUT request to " + baseUrl + url);
        return execute(putRequest);
    }

    public String delete(String url) {
        HttpDelete httpDelete = new HttpDelete(baseUrl + url);
        log.info("Executing DELETE request to " + baseUrl + url);
        return execute(httpDelete);
    }

    private HttpUriRequest buildPostRequest(String url, String content, NameValuePair[] params) {
        return buildRequest(content, params, RequestBuilder.post(baseUrl + url));
    }

    private HttpUriRequest buildPutRequest(String url, String content, NameValuePair[] params) {
        return buildRequest(content, params, RequestBuilder.put(baseUrl + url));
    }

    private HttpUriRequest buildRequest(String content, NameValuePair[] params, RequestBuilder postRequestBuilder) {
        Arrays.stream(params).forEach(param -> postRequestBuilder.addParameter(param));
        Optional.ofNullable(content).ifPresent(c -> {
            postRequestBuilder.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
            postRequestBuilder.setEntity(createHttpEntity(c));
        });
        return postRequestBuilder.build();
    }

    private HttpEntity createHttpEntity(String content) {
        return new StringEntity(content, charset);
    }

    private String execute(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);

            if (isErrorResponse(response)) {
                handleError(extractContent(response), response.getStatusLine().getStatusCode());
            }

            return extractContent(response);
        } catch (IOException e) {
            throw new KeeperApiException(e);
        }
    }

    private String extractContent(HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        return IOUtils.toString(content, StandardCharsets.UTF_8.name());
    }

    private void handleError(String content, int statusCode) {
        errorHandler.handle(content, statusCode);
    }

    private boolean isErrorResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode() >= 300;
    }
}
