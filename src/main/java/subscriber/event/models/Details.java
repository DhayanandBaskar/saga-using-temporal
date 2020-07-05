package subscriber.event.models;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("new_status")
    public Integer newStatus;

    @SerializedName("new_status_verbose")
    public String newStatusVerbose;

    public String message;
}