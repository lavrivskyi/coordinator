package com.coordinator.lookup.service.api;

import com.coordinator.lookup.entity.CoordinatesEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
public class CoordinatesApi {
    private static final String URL_PARAMETERS = "?format=json&addressdetails=1"
            + "&limit=3&accept-language=en";
    @Value("${coordinates.url}")
    private String url;

    public List<CoordinatesEntity> getCoordinatesByAddress(String address) {
        HttpGet request = new HttpGet(url + address + URL_PARAMETERS);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String result = EntityUtils.toString(httpEntity);
                List<LinkedHashMap<String, Object>> list = objectMapper
                        .readValue(result, List.class);
                List<CoordinatesEntity> coordinatesEntities = new ArrayList<>();
                for (Map<String, Object> map : list) {
                    CoordinatesEntity entity = new CoordinatesEntity();
                    entity.setLatitude((String) map.get("lat"));
                    entity.setLongitude((String) map.get("lon"));
                    coordinatesEntities.add(entity);
                }
                return coordinatesEntities;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't get coordinates by address:" + address, e);
        }
        return null;
    }
}
