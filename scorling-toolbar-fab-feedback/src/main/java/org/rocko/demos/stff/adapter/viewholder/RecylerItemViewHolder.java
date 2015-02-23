package org.rocko.demos.stff.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.rocko.demos.stff.R;

/**
 * Created by Rocko on 2015/2/23 23:13
 */
public class RecylerItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mItemTextView;

    public RecylerItemViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static RecylerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.tv_message);
        return new RecylerItemViewHolder(parent, itemTextView);
    }

    public void setmItemText(CharSequence text) {
        mItemTextView.setText(text);

    }
}
