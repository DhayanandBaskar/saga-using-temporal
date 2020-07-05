package subscriber.workflow;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import subscriber.event.SubscriptionEvent;

@WorkflowInterface
public interface SubscriptionWorkflow {
    @WorkflowMethod
    void evaluateSubscriptionStatus(SubscriptionEvent subscriptionEvent);

    @QueryMethod
    boolean isSubscriptionLegit();
}
