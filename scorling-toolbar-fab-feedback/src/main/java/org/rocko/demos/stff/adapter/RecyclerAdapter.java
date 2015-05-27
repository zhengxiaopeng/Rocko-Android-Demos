package org.rocko.demos.stff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.rocko.demos.stff.R;
import org.rocko.demos.stff.adapter.viewholder.RecylerHeaderViewHolder;
import org.rocko.demos.stff.adapter.viewholder.RecylerItemViewHolder;

import java.util.List;

/**
 * Created by Rocko on 2015/2/23 23:03
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
            return RecylerItemViewHolder.newInstance(view);
        } else if (viewType == TYPE_HEADER) {
            View header = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
            return new RecylerHeaderViewHolder(header);
        }

        throw new RuntimeException("There is no type that match the type " + viewType + "...");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }
        RecylerItemViewHolder holder = (RecylerItemViewHolder) viewHolder;
        String itemText = mItemList.get(position - 1);
        holder.setmItemText(itemText);
    }

    @Override
    public int getItemCount() {
        return getBaseCount() + 1;
    }

    private int getBaseCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }
}
