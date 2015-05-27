package org.rocko.touchlistener.subclasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.rocko.touchlistener.subclasses.R;

/**
 * Created by Administrator on 2015/4/25.
 */
public class SimpleRecylerViewAdapter extends RecyclerView.Adapter<SimpleRecylerViewAdapter.SimpleViewHolder> {
    protected String[] strs;
    protected Context context;

    public SimpleRecylerViewAdapter(Context context, String[] strs) {
        this.context = context;
        this.strs = strs;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(strs[position]);
        if (0 == position % 2)
            holder.imageView.setImageResource(R.drawable.ic_launcher);
        else if (0 == position % 3)
            holder.imageView.setImageResource(R.drawable.mid);
        else
            holder.imageView.setImageResource(R.drawable.big);
    }

    @Override
    public int getItemCount() {
        return strs.length;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
