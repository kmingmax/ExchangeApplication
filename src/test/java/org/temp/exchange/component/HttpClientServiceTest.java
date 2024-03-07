package org.temp.exchange.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HttpClientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpClientService httpClientService;

    @Test
    public void testGet_Success() {
        // Setup
        String url = "http://example.com/api/data";
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Success");

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(), eq(responseType)))
                .thenReturn(expectedResponse);

        // Execute
        ResponseEntity<String> actualResponse = httpClientService.get(url, responseType);

        // Verify
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testGet_RestClientException() {
        // Setup
        String url = "http://example.com/api/error";
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {};

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(), eq(responseType)))
                .thenThrow(new RestClientException("Error"));

        // Execute
        ResponseEntity<String> actualResponse = httpClientService.get(url, responseType);

        // Verify
        assertThat(actualResponse.getStatusCode()).isEqualTo(ResponseEntity.badRequest().build().getStatusCode());
    }
}
