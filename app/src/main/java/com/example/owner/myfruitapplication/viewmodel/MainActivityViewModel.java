package com.example.owner.myfruitapplication.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.owner.myfruitapplication.dto.FruitDto;
import com.example.owner.myfruitapplication.dto.FruitResponse;
import com.example.owner.myfruitapplication.dto.Resource;
import com.example.owner.myfruitapplication.repo.FruitRepository;

import java.util.List;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {
    private FruitRepository repository;

    final private MutableLiveData<Request> request = new MutableLiveData();
    final private LiveData<Resource<FruitResponse>> result = Transformations.switchMap(request, new Function<Request, LiveData<Resource<FruitResponse>>>() {
        @Override
        public LiveData<Resource<FruitResponse>> apply(final Request input) {
            LiveData<Resource<List<FruitDto>>> resourceLiveData = repository.browseRepo(input.page, input.limit);
            final MediatorLiveData<Resource<FruitResponse>> mediator = new MediatorLiveData<>();
            mediator.addSource(resourceLiveData, new Observer<Resource<List<FruitDto>>>() {

                @Override
                public void onChanged(@Nullable Resource<List<FruitDto>> FruitDtos) {
                    FruitResponse resp = new FruitResponse(input.page, input.limit, FruitDtos.getData());
                    Resource<FruitResponse> response = null;
                    switch (FruitDtos.getStatus()) {
                        case LOADING:
                            response = Resource.<FruitResponse>loading(resp);
                            break;
                        case SUCCESS:
                            response = Resource.<FruitResponse>success(resp);
                            break;
                        case ERROR:
                            response = Resource.<FruitResponse>error(FruitDtos.getException(), null);
                            break;

                    }
                    mediator.setValue(response);
                }
            });
            return mediator;
        }
    });

    public static MainActivityViewModel create(FragmentActivity activity) {
        MainActivityViewModel viewModel = ViewModelProviders.of(activity).get(MainActivityViewModel.class);
        return viewModel;
    }

    public void load(int page, int limit) {
        request.setValue(new Request(page, limit));
    }

    @Inject
    public void setRepository(FruitRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<FruitResponse>> getResult() {
        return result;
    }

    public void clearCache() {
        repository.clearCache();
    }

    public static class Request {
        final private int page, limit;

        public Request(int page, int limit) {
            this.page = page;
            this.limit = limit;
        }

        public int getLimit() {
            return limit;
        }
    }

}
