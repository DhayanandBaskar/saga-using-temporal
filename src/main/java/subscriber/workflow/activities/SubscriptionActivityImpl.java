package subscriber.workflow.activities;

import java.io.PrintStream;

public class SubscriptionActivityImpl implements SubscriptionActivity {
    private final PrintStream out;

    public SubscriptionActivityImpl(PrintStream out) {
        this.out = out;
    }

    @Override
    public void subscribe(String subscription) {
        out.println("Subscription Requested");
    }
}