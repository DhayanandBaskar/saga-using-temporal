package subscriber.workflow.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PersistenceActivity {
    @ActivityMethod
    void saveSubscription(String subscription);
}