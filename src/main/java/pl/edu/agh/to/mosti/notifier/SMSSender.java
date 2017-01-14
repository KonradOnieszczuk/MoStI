package pl.edu.agh.to.mosti.notifier;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SMSSender implements NotificationSender {

    public void sendNotification(PageChange pageChange, String phoneNumber) {

        try {
            sendPostRequest("https://api.gsmservice.pl/v5/send.php",
                    "mateuszw", "to2bramka_sms", phoneNumber,
                    getMessage(pageChange), "", "1",
                    "utf-8", "0", "0"); //sandbox=1 -> not real sms
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void sendPostRequest(String url, String login, String pass, String recipient, String message, String sender, String msg_type, String encoding, String unicode, String sandbox) throws IOException{

        URL objUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) objUrl.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Accept-Language", "pl-PL,en;q=0.5");

        String parameters = String.format("login=%s&pass=%s&recipient=%s&message=%s&sender=%s&msg_type=%s&encoding=%s&unicode=%s&sandbox=%s",
                login, pass, recipient, URLEncoder.encode(message, "UTF-8"), sender, msg_type, encoding, unicode, sandbox);

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuilder response = new StringBuilder();
        while((line = reader.readLine()) != null){
            response.append(line);
        }

        reader.close();

        System.out.println("Wysłano żądanie pod adres: " + url);
        System.out.println("Parametry: " + parameters);
        System.out.println("Kod odpowiedzi: " + responseCode);
        System.out.println("Odpowiedź:");
        System.out.println(response.toString());
    }

    /** Formats message to be sent out */
    private String getMessage (PageChange pageChange) {
        StringBuilder message = new StringBuilder("MoStI notification\n");
        message.append("Page: " + makeTextShorter(pageChange.getTitle(), 20) + "\n");
        message.append("Old val: " + makeTextShorter(pageChange.getOldValue(), 37) + "\n");
        message.append("New val: " + makeTextShorter(pageChange.getNewValue(), 37) + "\n");
        return message.toString();//46
    }

    private String makeTextShorter(String text, int maxLength) {
        if(text.length() > maxLength)
            return text.substring(0, maxLength - 3) + "...";
        else
            return text;
    }
}
