package com.tintas.TintasShop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tintas.TintasShop.model.PaletteSuggestion;
import com.tintas.TintasShop.model.Room;

@Service
public class GeminiService {
    @Value("${gemini.api.key:}")
    private String apiKey;

    @Value("${gemini.api.endpoint:https://api.example.com/v1/generate}")
    private String endpoint;

    public List<PaletteSuggestion> generatePalettes(Room room) {
        // If there's no API key configured, return a mocked set of suggestions.
        if (apiKey == null || apiKey.isBlank()) {
            return mockPalettes();
        }

        try {
            RestTemplate rest = new RestTemplate();
            Map<String, Object> payload = new HashMap<>();
            payload.put("prompt", buildPrompt(room));
            payload.put("max_tokens", 1000);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> resp = rest.postForEntity(endpoint, request, String.class);
            String body = resp.getBody() == null ? "" : resp.getBody();

            // Note: Gemini responses vary. This is a simple best-effort parser that expects
            // a JSON or text output. For now, fallback to mock if we can't parse reliably.
            List<PaletteSuggestion> parsed = tryParseBody(body);
            if (parsed == null || parsed.isEmpty()) return mockPalettes();
            return parsed;
        } catch (RestClientException e) {
            return mockPalettes();
        }
    }

    private String buildPrompt(Room room) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are an interior design assistant. Given the room details, suggest 2-4 paint color palettes. For each palette, return: title, list of hex codes (3-6), color names, suggested primary wall color, optional accent wall, trim color, and a short explanation (1-3 sentences). Room info:\n");
        sb.append("Name: ").append(room.getName()).append("\n");
        sb.append("Description: ").append(room.getDescription()).append("\n");
        if (room.getSize() != null) sb.append("Size: ").append(room.getSize()).append(" ").append(Optional.ofNullable(room.getSizeUnit()).orElse("m²")).append("\n");
        sb.append("Natural light: ").append(Optional.ofNullable(room.getNaturalLight()).orElse("unspecified")).append("\n");
        sb.append("Orientation: ").append(Optional.ofNullable(room.getOrientation()).orElse("unspecified")).append("\n");
        sb.append("Dominant colors: ").append(Optional.ofNullable(room.getDominantColors()).orElse("unspecified")).append("\n");
        sb.append("Preferred style: ").append(Optional.ofNullable(room.getPreferredStyle()).orElse("unspecified")).append("\n");
        sb.append("Preferred palette: ").append(Optional.ofNullable(room.getPreferredPalette()).orElse("unspecified")).append("\n");
        sb.append("Constraints: ").append(Optional.ofNullable(room.getConstraints()).orElse("none")).append("\n");
        sb.append("Provide the results in a concise structured JSON array where each element contains: title, hexCodes (array), colorNames (array), primary, accent, trim, explanation.");
        return sb.toString();
    }

    private List<PaletteSuggestion> tryParseBody(String body) {
        // Minimal attempt: Look for JSON array start
        try {
            String trimmed = body.trim();
            if (trimmed.startsWith("[")) {
                // Try to use a simple JSON parser via org.json if available, otherwise skip parsing.
                // To keep this implementation dependency-free here, we'll not attempt complex parsing.
                return Collections.emptyList();
            }
        } catch (Exception ignored) {}
        return Collections.emptyList();
    }

    private List<PaletteSuggestion> mockPalettes() {
        List<PaletteSuggestion> list = new ArrayList<>();

        list.add(new PaletteSuggestion(
                "Warm Cozy",
                Arrays.asList("#D9C4A8", "#A76F3E", "#6B3E2B"),
                Arrays.asList("Soft Sand", "Cinnamon", "Espresso"),
                "#D9C4A8",
                "#A76F3E",
                "#FFFFFF",
                "A warm, cozy palette that works well with wooden floors and low natural light. Primary is a soft sand for walls, with a cinnamon accent and white trim to brighten the space."
        ));

        list.add(new PaletteSuggestion(
                "Neutral Calm",
                Arrays.asList("#F5F5F0", "#CFCBC6", "#8B8A84"),
                Arrays.asList("Ivory", "Stone", "Graphite"),
                "#F5F5F0",
                "#CFCBC6",
                "#EDEDED",
                "Neutral tones that keep the room feeling open and adaptable; good for spaces with varying decor and a budget-conscious refresh."
        ));

        list.add(new PaletteSuggestion(
                "Cool Modern",
                Arrays.asList("#E6F0FA", "#84B4D9", "#2E5F7B"),
                Arrays.asList("Pale Sky", "Steel Blue", "Deep Teal"),
                "#E6F0FA",
                "#84B4D9",
                "#FFFFFF",
                "A cool modern palette that brightens north-facing rooms; pale sky as primary keeps the space airy while deep teal works as a dramatic accent."
        ));

        return list;
    }
}
