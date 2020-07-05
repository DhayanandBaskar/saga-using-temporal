package subscriber.workflow.activities;

import subscriber.repo.SubscriptionRepo;

import java.io.PrintStream;

public class PersistenceActivityImpl implements PersistenceActivity {
    private final PrintStream out;
    private SubscriptionRepo repo;

    public PersistenceActivityImpl(PrintStream out) {
        this.out = out;
        repo = new SubscriptionRepo();
    }

    @Override
    public void saveSubscription(String subscription) {
        out.println("save subscription");
        repo.save(subscription);
    }
}