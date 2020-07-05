package subscriber.event.models;

import com.google.gson.annotations.SerializedName;

public class Subscription {
    @SerializedName("request_type")
    public String requestType;

    @SerializedName("status_verbose")
    public String statusVerbose;


    public String modified;

    public String url;

    public String id;

    public String created;

    @SerializedName("request_carrier_name")
    public String requestCarrierName;

    @SerializedName("last_actuals_update")
    public String lastActualsUpdate;

    @SerializedName("last_carrier_update")
    public String lastCarrierUpdate;

    @SerializedName("owner_internal_id")
    public String ownerInternalId;

    @SerializedName("suspicious_key")
    public Boolean suspiciousKey;

    public Integer status;

    @SerializedName("request_key")
    public String requestKey;

    @SerializedName("on_hold")
    public Boolean onHold;
}
