package subscriber.workflow.activities.registry;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import subscriber.workflow.activities.PersistenceActivityImpl;
import subscriber.workflow.activities.SubscriptionActivityImpl;

import java.io.PrintStream;

import static subscriber.saga.SubscriptionSaga.TASK_QUEUE;

public class PersistenceActivityRegistry {
    private void registerActivity(PrintStream out) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerActivitiesImplementations(new PersistenceActivityImpl(out));

        factory.start();
    }

    public static void register(PrintStream out) {
        PersistenceActivityRegistry registry = new PersistenceActivityRegistry();
        registry.registerActivity(out);
    }
}
