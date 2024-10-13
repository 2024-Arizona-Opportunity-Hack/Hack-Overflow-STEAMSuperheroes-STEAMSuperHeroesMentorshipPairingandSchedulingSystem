package testMMMatching;
package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DistanceCalculator {

    private static final String API_KEY = "da36e8d870da4d0aa7710f22921463cd"; // Replace with your OpenCage API key

    // Method to fetch lat/long for two cities and states and calculate the distance
    public double getDistance(String city1, String state1, String city2, String state2) throws Exception {
        // Fetch lat/long for the first city
        double[] coordinates1 = getLatLongFromCityState(city1, state1);
        double lat1 = coordinates1[0];
        double lon1 = coordinates1[1];

        // Fetch lat/long for the second city
        double[] coordinates2 = getLatLongFromCityState(city2, state2);
        double lat2 = coordinates2[0];
        double lon2 = coordinates2[1];

        // Calculate and return the distance
        return haversine(lat1, lon1, lat2, lon2);
    }

    // Method to call OpenCage API and get lat/long for a given city and state
    private double[] getLatLongFromCityState(String city, String state) throws Exception {
        String uri = String.format(
                "https://api.opencagedata.com/geocode/v1/json?q=%s,%s&key=%s",
                city, state, API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the lat/long from the response
        return parseLatLong(response.body());
    }

    // Method to parse the latitude and longitude from the API response
    private double[] parseLatLong(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode resultsNode = rootNode.path("results").get(0);
        JsonNode geometryNode = resultsNode.path("geometry");

        double lat = geometryNode.get("lat").asDouble();
        double lng = geometryNode.get("lng").asDouble();

        return new double[] { lat, lng };
    }

    // Haversine formula to calculate the distance between two lat/long points in miles
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 3958.8; // Earth radius in miles

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in miles
    }
}
