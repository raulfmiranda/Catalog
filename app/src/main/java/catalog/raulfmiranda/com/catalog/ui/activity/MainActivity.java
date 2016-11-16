package catalog.raulfmiranda.com.catalog.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.control.CategoriaControl;
import catalog.raulfmiranda.com.catalog.control.ItemControl;
import catalog.raulfmiranda.com.catalog.model.Item;
import catalog.raulfmiranda.com.catalog.ui.adapter.ItemRecyclerAdapter;

public class MainActivity extends BasicActivity {
    private final ItemRecyclerAdapter adapter = new ItemRecyclerAdapter(this);
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button botaoCadastrarItem = (Button) findViewById(R.id.cadastrar_botao);
        botaoCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastrarItemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public List<Item> doInBackground(Void... voids) {
                CategoriaControl.getInstance().retrieveCategorias();
                List<Item> items = ItemControl.getInstance().retrieveItems();

                return items;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                MainActivity.this.items = items;
                //progressView.setVisibility(View.GONE);
                adapter.setItems(items);

                supportInvalidateOptionsMenu();
            }
        }.execute();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
