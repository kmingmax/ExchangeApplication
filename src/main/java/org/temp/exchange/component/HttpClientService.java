package org.temp.exchange.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * HttpClientService
 */
@Service
public class HttpClientService {
    private final RestTemplate restTemplate;

    @Autowired
    public HttpClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Http Get
     * @param url baseurl
     * @param responseType 回傳型別
     * @return ResponseEntity
     * @param <T> 回傳型別
     */
    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType) {
        try {
            // 嘗試發送GET請求並接收響應
            return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        } catch (RestClientException e) {
            // 處理HTTP錯誤
            return ResponseEntity.badRequest().build();
        }
    }

}
