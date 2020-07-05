package subscriber.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;

public class AmazonSQSClient {
    private static final String QUEUE_URL = "http://localhost:4566/000000000000/subscription-events";
    private final AmazonSQS sqs;

    public AmazonSQSClient() {
        this.sqs = AmazonSQSClientBuilder.defaultClient();
    }

    public void sendMessage(String eventPayload) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(QUEUE_URL)
                .withMessageBody(eventPayload)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }

    public String receiveMessage() {
        List<Message> messages = sqs.receiveMessage(QUEUE_URL).getMessages();
        Message message = messages.get(0);
        String result = message.getBody();
        deleteMessage(messages);
        return result;
    }

    public void deleteMessage(List<Message> messages) {
        for (Message m: messages) {
            sqs.deleteMessage(QUEUE_URL, m.getReceiptHandle());
        }
    }
}