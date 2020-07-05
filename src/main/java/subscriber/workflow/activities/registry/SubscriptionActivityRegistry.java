package subscriber.workflow.activities.registry;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import subscriber.workflow.activities.SubscriptionActivityImpl;

import java.io.PrintStream;

import static subscriber.saga.SubscriptionSaga.TASK_QUEUE;

public class SubscriptionActivityRegistry {
    private void registerActivity(PrintStream out) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerActivitiesImplementations(new SubscriptionActivityImpl(out));

        factory.start();
    }

    public static void register(PrintStream out) {
        SubscriptionActivityRegistry registry = new SubscriptionActivityRegistry();
        registry.registerActivity(out);
    }
}
