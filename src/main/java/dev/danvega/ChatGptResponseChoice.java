package dev.danvega;

public record ChatGptResponseChoice(
        String text,
        int index,
        Object logprobs,
        String finish_reason
) {
}
