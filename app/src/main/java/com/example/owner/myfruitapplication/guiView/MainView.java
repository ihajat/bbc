package com.example.owner.myfruitapplication.guiView;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.owner.myfruitapplication.R;
import com.example.owner.myfruitapplication.databinding.ActivityMainBinding;
import com.example.owner.myfruitapplication.dto.FruitDto;
import com.example.owner.myfruitapplication.network.Auditor;
import com.example.owner.myfruitapplication.ui.DetailedActivity;
import com.example.owner.myfruitapplication.ui.listeners.ExtendableList;
import com.example.owner.myfruitapplication.ui.listeners.RecyclerViewLoadMoreScrollListener;

import java.util.ArrayList;
import java.util.List;

public class MainView implements GuiView {
    private View rootView;
    private ActivityMainBinding binding;
    private RecyclerView listView;
    private EventHandler handler;

    public MainView(LayoutInflater inflater, ViewGroup vg, final Listener listener) {
        this.handler = new EventHandler(listener);
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, vg, false);
        binding.setHandler(handler);
        rootView = binding.getRoot();
        listView = (RecyclerView) rootView.findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(new RepoAdapter(inflater));

        RecyclerViewLoadMoreScrollListener loadMoreScrollListener = new RecyclerViewLoadMoreScrollListener(new ExtendableList() {
            @Override
            public void loadMore() {
                if (listener != null) {
                    listener.loadMore();
                }
            }
        }, 100, 3);
        listView.addOnScrollListener(loadMoreScrollListener);
        loadMoreScrollListener.loadMoreIfneeded(listView);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    public void loading(boolean loading) {
        binding.setShowLoadMoreSpinner(loading);

    }

    public void refreshing(boolean refreshing) {
        binding.setRefreshing(refreshing);

    }

    public void bind(List<FruitDto> list, int page, int limit) {
        if (list == null) return;

        ((RepoAdapter) listView.getAdapter()).getFruitDtoList().addAll(list);

        int position = page * limit;
        ((RepoAdapter) listView.getAdapter()).notifyItemRangeChanged(position, limit);
        if (((RepoAdapter) listView.getAdapter()).getFruitDtoList().size() != ((RepoAdapter) listView.getAdapter()).getItemCount()) {
            ((RepoAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }

    public interface Listener {

        void loadMore();

        void clearCache();
    }

    public class EventHandler {
        final private Listener listener;

        public EventHandler(Listener listener) {
            this.listener = listener;
        }

        public void onClearCacheClicked(View v) {
            if (listener != null) listener.clearCache();
        }
    }

    private class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        final private LayoutInflater inflater;
        private List<FruitDto> fruitDtoList = new ArrayList<>();

        public List<FruitDto> getFruitDtoList() {
            return fruitDtoList;
        }

        public void setFruitDtoList(List<FruitDto> fruitDtoList) {
            this.fruitDtoList = fruitDtoList;
        }

        private RepoAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemView view = new ItemView(inflater, parent, new ItemView.Listener() {
            });
            ItemHolder holder = new ItemHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            FruitDto fruitDto = fruitDtoList.get(position);
            ((ItemHolder) holder).bind(fruitDto);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = ((TextView) listView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.txtType)).getText().toString();
                    String weight = ((TextView) listView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.txtWeight)).getText().toString();
                    String price = ((TextView) listView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.txtPrice)).getText().toString();
                    Intent intent = new Intent(rootView.getContext(), DetailedActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("weight", weight);
                    intent.putExtra("price", price);
                    Auditor auditor = new Auditor();
                    auditor.startTimer();
                    rootView.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return fruitDtoList.size();
        }

        public class ItemHolder extends RecyclerView.ViewHolder {
            ItemView holder;

            public ItemHolder(ItemView holder) {
                super(holder.getRootView());
                this.holder = holder;
            }

            public void bind(FruitDto fruitDto) {
                holder.bind(fruitDto);
            }
        }
    }


}
