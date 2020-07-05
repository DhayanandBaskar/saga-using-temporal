package subscriber.models;

public class Subscription {
    public String fhShipmentId;
    public String subscriptionKey;
    public String type;
    public String carrierId;
    public String subscriptionId;
    public boolean isLegit;

    public Subscription(String fhShipmentId, String subscriptionKey, String type, String carrierId, String subscriptionId, boolean isLegit) {
        this.fhShipmentId = fhShipmentId;
        this.subscriptionKey = subscriptionKey;
        this.type = type;
        this.carrierId = carrierId;
        this.subscriptionId = subscriptionId;
        this.isLegit = isLegit;
    }

    public static Subscription initEmpty() {
        return new Subscription("", "", "", "", "", false);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "fhShipmentId='" + fhShipmentId + '\'' +
                ", subscriptionKey='" + subscriptionKey + '\'' +
                ", type='" + type + '\'' +
                ", carrierId='" + carrierId + '\'' +
                ", subscriptionId='" + subscriptionId + '\'' +
                ", isLegit=" + isLegit +
                '}';
    }
}
