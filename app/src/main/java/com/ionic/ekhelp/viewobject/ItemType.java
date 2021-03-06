package com.ionic.ekhelp.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemType {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public final String name;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    public ItemType(@NonNull String id, String name, String status, String addedDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.addedDate = addedDate;
    }
}
