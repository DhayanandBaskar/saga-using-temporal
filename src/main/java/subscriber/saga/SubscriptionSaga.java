package subscriber.saga;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import subscriber.event.SubscriptionEvent;
import subscriber.workflow.SubscriptionWorkflow;
import subscriber.workflow.SubscriptionWorkflowImpl;
import subscriber.workflow.activities.registry.PersistenceActivityRegistry;
import subscriber.workflow.activities.registry.SubscriptionActivityRegistry;

import java.io.PrintStream;

public class SubscriptionSaga {
    public static final String TASK_QUEUE = "Subscription";
    public static final String WORKFLOW_PREFIX = "SubscriptionWorkflow";

    public void startWorkflow(SubscriptionEvent event, PrintStream out) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);

        out.println("Starting workflow");
        PersistenceActivityRegistry.register(out);
        SubscriptionActivityRegistry.register(out);
        worker.registerWorkflowImplementationTypes(SubscriptionWorkflowImpl.class);
        factory.start();

        WorkflowOptions options =
                WorkflowOptions.newBuilder()
                        .setTaskQueue(TASK_QUEUE)
                        .setWorkflowId(WORKFLOW_PREFIX+event.subscription.ownerInternalId)
                        .build();

        SubscriptionWorkflow subscriptionWorkflow = client.newWorkflowStub(SubscriptionWorkflow.class, options);
        subscriptionWorkflow.evaluateSubscriptionStatus(event);
    }
}
