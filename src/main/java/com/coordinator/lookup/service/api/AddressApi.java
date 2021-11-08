package com.coordinator.lookup.service.api;

import com.coordinator.lookup.dto.AddressResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressApi {
    @Value("${address.url}")
    private String url;

    public AddressResponseDto requestAddress(String lat, String lon) {
        HttpGet request = new HttpGet(url + lat + "&lon=" + lon + "&accept-language=en");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String result = EntityUtils.toString(httpEntity);
                Map<String, Object> addressMap = objectMapper.readValue(result, Map.class);
                Map<String, String> addressResponseMap
                        = (Map<String, String>) addressMap.get("address");
                AddressResponseDto addressResponseDto = new AddressResponseDto();
                addressResponseDto.setAddress(addressResponseMap);
                return addressResponseDto;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't get address by coordinates. Latitude: " + lat
                    + ", longitude: " + lon, e);
        }
        return null;
    }
}
