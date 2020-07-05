package subscriber;

import com.google.gson.Gson;
import subscriber.event.SubscriptionEvent;
import subscriber.event.SubscriptionEventMock;
import subscriber.saga.SubscriptionSaga;
import subscriber.sqs.AmazonSQSClient;

public class Driver {
    public static void main(String[] args) {
        final SubscriptionEventMock eventMock = new SubscriptionEventMock();
        final AmazonSQSClient sqsClient = new AmazonSQSClient();
        final Gson gson = new Gson();
        final SubscriptionSaga saga = new SubscriptionSaga();

        sqsClient.sendMessage(eventMock.fetch());

        String payload = sqsClient.receiveMessage();
        System.out.println(payload);
        SubscriptionEvent event = gson.fromJson(payload, SubscriptionEvent.class);
        saga.startWorkflow(event, System.out);
    }
}
