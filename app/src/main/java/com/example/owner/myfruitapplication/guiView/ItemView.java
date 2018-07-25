package com.example.owner.myfruitapplication.guiView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner.myfruitapplication.R;
import com.example.owner.myfruitapplication.dto.FruitDto;
import com.example.owner.myfruitapplication.databinding.TemItemBinding;


public class ItemView implements GuiView {
    private TemItemBinding binding;
    private View rootView;


    public ItemView(LayoutInflater inflater, ViewGroup vg, Listener listener){
        binding = DataBindingUtil.inflate(inflater, R.layout.tem_item, vg, false);
        rootView = binding.getRoot();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    public void bind(FruitDto fruitDto) {
        binding.setFruitDto(fruitDto);
    }


    public interface Listener{

    }
}

