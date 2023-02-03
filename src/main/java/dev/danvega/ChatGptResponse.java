package dev.danvega;

public record ChatGptResponse(
        String id,
        String object,
        int created,
        String model,
        ChatGptResponseChoice[] choices,
        ChatGptResponseUsage usage) {
}
