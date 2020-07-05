package subscriber.event;

import com.google.gson.annotations.SerializedName;
import subscriber.event.models.Event;
import subscriber.event.models.Subscription;

public class SubscriptionEvent {
    public String generated;

    public Event event;

    @SerializedName("security_token")
    public String securityToken;

    public Subscription subscription;
}