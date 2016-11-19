package catalog.raulfmiranda.com.catalog.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.control.CategoriaControl;
import catalog.raulfmiranda.com.catalog.control.ItemControl;
import catalog.raulfmiranda.com.catalog.model.Item;
import catalog.raulfmiranda.com.catalog.ui.adapter.ItemRecyclerAdapter;

public class MainActivity extends BasicActivity {
    private ItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        adapter = new ItemRecyclerAdapter(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button botaoCadastrarItem = (Button) findViewById(R.id.novoCadastro);
        botaoCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemCadastrarActivity.class);
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
                CategoriaControl.getInstance(getApplicationContext()).retrieveCategorias();
                List<Item> items = ItemControl.getInstance(getApplicationContext()).retrieveItems();

                return items;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                //progressView.setVisibility(View.GONE);
                adapter.setItems(items);

                supportInvalidateOptionsMenu();
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_categorias).set...
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_categorias) {
            startActivity(new Intent(this, CategoriaListaActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
