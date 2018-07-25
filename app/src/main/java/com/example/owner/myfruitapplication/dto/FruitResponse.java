package com.example.owner.myfruitapplication.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FruitResponse {
    final private int page;
    final private int limit;

    @SerializedName("fruit")
    @Expose
    final private List<FruitDto> list;

    public FruitResponse(int page, int limit, List<FruitDto> list) {
        this.page = page;
        this.limit = limit;
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public List<FruitDto> getList() {
        return list;
    }
}
