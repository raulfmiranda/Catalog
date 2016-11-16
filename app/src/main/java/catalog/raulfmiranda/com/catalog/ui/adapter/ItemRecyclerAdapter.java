package catalog.raulfmiranda.com.catalog.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.model.Item;
import catalog.raulfmiranda.com.catalog.ui.adapter.viewholder.ItemViewHolder;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Item> items = new ArrayList<>();
    private final Activity activity;

    public ItemRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        return new ItemViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = (Item) getItem(position);
        ((ItemViewHolder)holder).getNomeTextView().setText(item.getTitulo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public void setItems(List<Item> novaLista) {
        items.clear();
        items.addAll(novaLista);

        notifyDataSetChanged();
    }
}
