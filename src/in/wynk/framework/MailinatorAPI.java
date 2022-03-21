package in.wynk.framework;

import com.manybrain.mailinator.client.MailinatorClient;
import com.manybrain.mailinator.client.message.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MailinatorAPI {
    String mailID ;

    public String readEmail(String email)
    {

        MailinatorClient mailinatorClient = new MailinatorClient("2f29f755e95444f5be4b41e107ba1c37");
        Inbox inbox = mailinatorClient.request(new GetInboxRequest("team807464.testinator.com"));
        List<Message> messages = inbox.getMsgs();
        mailID =  messages.get(0).getId();

        Message m = mailinatorClient.request(
                new GetMessageRequest("team807464.testinator.com", email, mailID));

        List<Part> parts = m.getParts();
        return parts.get(0).getBody().split("href")[1].split("'")[1].split("'")[0];

    }

    public String generateRandomUserId() {
        new String();
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 10) {
            int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        String saltStr = salt.toString();
        return saltStr ;
    }



}
