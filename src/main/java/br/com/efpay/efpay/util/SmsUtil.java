package br.com.efpay.efpay.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class SmsUtil {
    private final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGg6ODA4MC9hcGkvdjEvdXNlcnMvYXBpL2tleS9nZW5lcmF0ZSIsImlhdCI6MTY3Mzk3OTM0MCwibmJmIjoxNjczOTc5MzQwLCJqdGkiOiJ5b3FEV1A3VXRqTFllWktZIiwic3ViIjo0MDgxMDcsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.GW7yEyAwcuka4hzNWjHBB4c_8FWihXgwe2_2gn_F4Do";

    public void sendSms(String message, String to, String senderId, String callbackUrl) throws IOException {
    	OkHttpClient client = new OkHttpClient();
    	MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = String.format("{\n" +
                "    \"message\": \"%s\",\n" +
                "    \"to\": \"%s\",\n" +
                "    \"bypass_optout\": true,\n" +
                "    \"sender_id\": \"%s\",\n" +
                "    \"callback_url\": \"%s\"\n" +
                "}", message, to, senderId, callbackUrl);

        RequestBody body = RequestBody.create(jsonBody, mediaType);

        Request request = new Request.Builder()
                .url("https://api.sms.to/sms/send")
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println(response.body().string());
        }
    }
}
