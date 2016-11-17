package catalog.raulfmiranda.com.catalog.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.control.ItemControl;
import catalog.raulfmiranda.com.catalog.model.Item;

public class ItemDetalhesActivity extends BasicActivity {
    private TextView itemDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Long id = getIntent().getLongExtra("id", -1);
        Item item = ItemControl.getInstance().getItemById(id);
        String detalhes;

        itemDetalhes = (TextView) findViewById(R.id.item_detalhes);
        detalhes = "Título: " + item.getTitulo().toString() +
                "\nAutor: " + item.getAutor().toString() +
                "\nQuantidade: " + item.getQuantidade() + "    Ano: " + item.getAno();
        itemDetalhes.setText(detalhes);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_item_detalhes;
    }
}
