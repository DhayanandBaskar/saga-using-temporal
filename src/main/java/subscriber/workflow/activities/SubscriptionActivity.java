package subscriber.workflow.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SubscriptionActivity {
    @ActivityMethod
    void subscribe(String fhShipmentId);
}