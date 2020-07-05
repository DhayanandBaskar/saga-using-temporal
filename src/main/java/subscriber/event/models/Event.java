package subscriber.event.models;

import com.google.gson.annotations.SerializedName;

public class Event {
    public String walltime;

    public Integer code;

    public Integer id;

    public String created;

    @SerializedName("subscription_id")
    public Integer subscriptionId;

    public String subscription;

    public Details details;

    public Integer severity;
}