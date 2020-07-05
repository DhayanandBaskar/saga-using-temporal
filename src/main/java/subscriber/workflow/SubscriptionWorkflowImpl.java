package subscriber.workflow;

import com.google.gson.Gson;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import subscriber.event.SubscriptionEvent;
import subscriber.models.Subscription;
import subscriber.workflow.activities.PersistenceActivity;
import subscriber.workflow.activities.SubscriptionActivity;

import java.time.Duration;

public class SubscriptionWorkflowImpl implements SubscriptionWorkflow {

    private final PersistenceActivity persistenceActivity =
            Workflow.newActivityStub(
                    PersistenceActivity.class,
                    ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofSeconds(2)).build());

    private final SubscriptionActivity subscriptionActivity =
            Workflow.newActivityStub(
                    SubscriptionActivity.class,
                    ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofSeconds(2)).build());

    Subscription subscription = Subscription.initEmpty();

    @Override
    public void evaluateSubscriptionStatus(SubscriptionEvent event) {
        subscription = mapEventToSubscription(event);

        if (this.isInvalidSubscription(event) || this.isCompletedOrArchivedRightAfterCreation(event) || this.isCancelledSubscription(event)) {
            System.out.println("Subscription invalid, resubscribing");
            subscriptionActivity.subscribe(subscription.fhShipmentId);
        }

        if (this.isActiveSubscription(event)) {
            System.out.println("Subscription is active");
            if (!subscription.isLegit) {
                subscription.isLegit = true;
                persistenceActivity.saveSubscription(new Gson().toJson(subscription));
            }
        }
    }

    @Override
    public boolean isSubscriptionLegit() {
        return subscription.isLegit;
    }

    private Subscription mapEventToSubscription(SubscriptionEvent event) {
        return new Subscription(
                event.subscription.ownerInternalId,
                event.subscription.requestKey,
                event.subscription.requestType,
                event.subscription.requestCarrierName,
                event.subscription.id,
                false
        );
    }

    private boolean isInvalidSubscription (SubscriptionEvent event) {
        return event.subscription.status == 4;
    }

    private boolean isCancelledSubscription (SubscriptionEvent event) {
        return event.subscription.status == 3;
    }

    private boolean isActiveSubscription (SubscriptionEvent event) {
        return event.subscription.status == 1;
    }

    private boolean isCompletedOrArchivedRightAfterCreation (SubscriptionEvent event) {
        return event.subscription.status == 2 || event.subscription.status == 5;
    }
}
