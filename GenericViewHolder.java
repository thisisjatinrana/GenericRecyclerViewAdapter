package com.morningneeds.base.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GenericViewHolder extends RecyclerView.ViewHolder {

    private static final int ACTION_ITEM_VIEW_CLICK=0;
    protected GenericRecyclerViewAdapter genericRecyclerViewAdapter;
    public GenericViewHolder(View view, GenericRecyclerViewAdapter genericRecyclerViewAdapter) {
        super(view);
        this.genericRecyclerViewAdapter = genericRecyclerViewAdapter;
        findViews(view);
        itemView.setOnClickListener(v -> genericRecyclerViewAdapter.getGenericRecyclerViewAdapterBridge().onViewHolderClick(genericRecyclerViewAdapter.getAdapterTag(),ACTION_ITEM_VIEW_CLICK,getAdapterPosition(),this));
    }

    public GenericRecyclerViewAdapter getGenericRecyclerViewAdapter() {
        return genericRecyclerViewAdapter;
    }

    public abstract void findViews(View rootView);

    public abstract void onBind(int position);

    public abstract void onDetached();

    public abstract void onViewRecycled();

}

