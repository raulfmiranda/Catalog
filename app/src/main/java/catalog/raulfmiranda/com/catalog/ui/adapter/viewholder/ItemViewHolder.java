package catalog.raulfmiranda.com.catalog.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.ui.adapter.ItemRecyclerAdapter;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final ItemRecyclerAdapter adapter;
    TextView nomeTextView;

    public ItemViewHolder(final View itemView, final ItemRecyclerAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        nomeTextView = (TextView) itemView.findViewById(R.id.item_nome);
    }

    public TextView getNomeTextView() { return nomeTextView; }
}
