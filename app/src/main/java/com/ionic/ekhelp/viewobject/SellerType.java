package com.ionic.ekhelp.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class SellerType {
    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("seller_type")
    public final String sellerType;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_user_id")
    public final String addedUserId;

    @SerializedName("updated_date")
    public final String updatedDate;

    @SerializedName("updated_user_id")
    public final String updatedUserId;

    @SerializedName("updated_flag")
    public final String updatedFlag;

    public SellerType(@NonNull String id, String sellerType, String status, String addedDate, String addedUserId, String updatedDate, String updatedUserId, String updatedFlag) {
        this.id = id;
        this.sellerType = sellerType;
        this.status = status;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
    }
}
