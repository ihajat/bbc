package com.example.owner.myfruitapplication.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.owner.myfruitapplication.api.FruitApi;
import com.example.owner.myfruitapplication.dao.FruitDao;
import com.example.owner.myfruitapplication.dto.ApiResponse;
import com.example.owner.myfruitapplication.dto.FruitDto;
import com.example.owner.myfruitapplication.dto.FruitResponse;
import com.example.owner.myfruitapplication.dto.Resource;
import com.example.owner.myfruitapplication.network.Auditor;
import com.example.owner.myfruitapplication.network.NetworkBoundResource;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * This the Single source of truth!
 * This one will use the NetworkBoundResource
 */
public class
FruitRepository {
    private static final String TAG = "FruitRepository";
    final private FruitApi api;
    final private FruitDao dao;
    final private Executor executor;
    final private Auditor auditor;

    @Inject
    public FruitRepository(FruitApi api, FruitDao dao, Executor executor, Auditor auditor) {
        this.api = api;
        this.dao = dao;
        this.executor = executor;
        this.auditor = auditor;
    }

    public LiveData<Resource<List<FruitDto>>> browseRepo(final int page, final int limit) {
        final int offset = (page - 1) * limit;
        LiveData<Resource<List<FruitDto>>> liveData = new NetworkBoundResource<List<FruitDto>, FruitResponse>(auditor) {
            @Override
            protected void saveCallResult(@NonNull FruitResponse fruitResponse) {
                FruitDto[] arr = new FruitDto[fruitResponse.getList().size()];
                fruitResponse.getList().toArray(arr);
                dao.insertAll(arr);


            }

            @Override
            protected boolean shouldFetch(@Nullable List<FruitDto> data) {
                return data == null || data.isEmpty();//let's always refresh to be up to date. data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<FruitDto>> loadFromDb() {
                return dao.get(offset, limit);
            }


            @NonNull
            @Override
            protected LiveData<ApiResponse<FruitResponse>> createCall() {
                LiveData<ApiResponse<FruitResponse>> response = api.browseRepoLiveData(page, limit);
                return response;
            }
        }.getAsLiveData();

        return liveData;
    }

    public void clearCache() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }


}
