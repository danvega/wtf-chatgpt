# WTF ChatGPT

I was excited to explore the OpenAI API, so I created a basic command-line interface (CLI) in Java that leverages the GPT-3 API to generate responses. By the end of this tutorial, you will have the ability to generate a native image of the application and execute it directly from your command line. This could be useful when you need to quickly execute a task but don’t know the exact command to do so.

## Prerequisites

You need to sign up for an OpenAI account and create an API key. You can do so here: https://beta.openai.com/account/api-keys

## Test Drive 

If you want to test out the OpenAI API before writing any code you can take a look at `gtp.http` which contains a sample `POST` request to the API. You will need to replace `YOUR_API_KEY_HERE` with your API key.

```http request
POST https://api.openai.com/v1/completions
Content-Type: application/json
Authorization: Bearer YOUR_API_KEY_HERE

{
  "model": "text-davinci-001",
  "prompt": "What is the command to list all files in a directory on macOS?",
  "temperature": 1,
  "max_tokens": 100
}
```

## GraalVM

You can collect metadata about your application by enabling the Tracing Agent and defining the output directory.

```bash
-agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image/
```

## Inspiration 

I was inspired by a video from Les Jackson where he built a Chat GPT CLI in .NET. 

https://www.youtube.com/watch?v=25i-Dh6U6Cw&t=1299s