package catalog.raulfmiranda.com.catalog.ui.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.model.Item;
import catalog.raulfmiranda.com.catalog.ui.activity.ItemDetalhesActivity;
import catalog.raulfmiranda.com.catalog.ui.adapter.ItemRecyclerAdapter;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final ItemRecyclerAdapter adapter;
    TextView nomeTextView;

    public ItemViewHolder(final View itemView, final ItemRecyclerAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        nomeTextView = (TextView) itemView.findViewById(R.id.item_nome);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Context context = itemView.getContext();
                Intent intent = new Intent(context, ItemDetalhesActivity.class);
                Item item = (Item) adapter.getItem(position);
                intent.putExtra("id", item.getId());
                context.startActivity(intent);
            }
        });
    }

    public TextView getNomeTextView() { return nomeTextView; }
}
