package com.morningneeds.base.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public  class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericViewHolder> {

    private final Context mContext;

    private List<T> mItems;
    private String adapterTag;

    @Override
    public int getItemViewType(int position) {
        return genericRecyclerViewAdapterBridge.getLayoutId(position, adapterTag);
    }


    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(viewType, viewGroup, false);
        return genericRecyclerViewAdapterBridge.createViewHolder(view, adapterTag,viewType,this);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder viewHolder, int position) {
              viewHolder.onBind(position);
    }

    public GenericRecyclerViewAdapter(String adapterTag, Context context, List<T> items, GenericRecyclerViewAdapterBridge adapterInterface) {
        if (items == null) {
            items = new ArrayList<T>();
        }
        mItems = items;
        mContext = context;
        genericRecyclerViewAdapterBridge =adapterInterface;
        this.adapterTag = adapterTag;
    }

    public void add(T item, int position) {
        if (item == null) {
            return;
        }
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void add(List<T> items, int position) {
        if (items == null || items.isEmpty()) {
            return;
        }
        mItems.addAll(position, items);
        notifyItemRangeInserted(position, items.size());
    }

    public void add(T item) {
        int position = mItems.size();
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void add(List<T> items) {
        if (items.isEmpty()) {
            return;
        }
        int position = mItems.isEmpty() ? 0 : mItems.size();
        mItems.addAll(position, items);
        notifyItemRangeInserted(position, items.size());
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    public List<T> getItems() {
        return mItems;
    }

    public int getItemCount() {
        return mItems.size();
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * Requires equals() and hashcode() to be implemented in T class.
     */
    public void remove(T item) {
        int position = mItems.indexOf(item);
        if (position == -1) {
            return;
        }
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, int itemCount) {
        for (int i = position; i < itemCount; i++) {
            mItems.remove(i);
        }
        notifyItemRangeRemoved(position, itemCount);
    }

    public void replaceList(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    private GenericRecyclerViewAdapterBridge genericRecyclerViewAdapterBridge;
    public interface GenericRecyclerViewAdapterBridge {
        GenericViewHolder createViewHolder(View view, String tag, int layoutId, GenericRecyclerViewAdapter genericRecyclerViewAdapter);
        int getLayoutId(int position,String tag);
        void onViewHolderClick(String tag, int action, int position, GenericViewHolder viewHolder);
    }

    public GenericRecyclerViewAdapterBridge getGenericRecyclerViewAdapterBridge() {
        return genericRecyclerViewAdapterBridge;
    }

    public String getAdapterTag() {
        return adapterTag;
    }
}