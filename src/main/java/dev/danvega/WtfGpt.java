package dev.danvega;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WtfGpt {

    public static void main(String[] args) throws IOException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String prompt;
        if(args.length > 0) {
            prompt = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string to search for: ");
            prompt = scanner.nextLine();
        }
        ObjectMapper mapper = new ObjectMapper();

        ChatGptRequest chatGptRequest = new ChatGptRequest("text-davinci-001", prompt, 1, 100);
        String input = mapper.writeValueAsString(chatGptRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer " + dotenv.get("OPEN_AI_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200) {
            ChatGptResponse chatGptResponse = mapper.readValue(response.body(), ChatGptResponse.class);
            String answer = chatGptResponse.choices()[chatGptResponse.choices().length-1].text();
            if(!answer.isEmpty()) {
                System.out.println(answer.replace("\n","").trim());
            }
        } else {
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
    }

}
