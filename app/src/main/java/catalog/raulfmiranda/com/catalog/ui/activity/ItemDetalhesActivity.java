package catalog.raulfmiranda.com.catalog.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.control.ItemControl;
import catalog.raulfmiranda.com.catalog.model.Item;

public class ItemDetalhesActivity extends BasicActivity {
    private Long id;
    private TextView itemDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        id = getIntent().getLongExtra("id", -1);
        Item item = ItemControl.getInstance().getItemById(id);
        String detalhes;

        String titulo = item.getTitulo() != null ? item.getTitulo().toString() : "";
        String autor = item.getAutor() != null ? item.getAutor().toString() : "";
        String descricao = item.getDescricao() != null ? item.getDescricao().toString() : "";

        itemDetalhes = (TextView) findViewById(R.id.item_detalhes);
        detalhes = "Título: " + titulo +
                "\nAutor: " + autor +
                "\n\nDescrição: " + descricao +
                "\n\nQuantidade: " + item.getQuantidade() + "    Ano: " + item.getAno();
        itemDetalhes.setText(detalhes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_detalhes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_deletar).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if(item.getItemId() == R.id.action_deletar) {
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Deletar Item").setMessage("Deseja deletar este item?");
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"Nada feito.", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Item itemToRemove = ItemControl.getInstance().getItemById(id);
                    ItemControl.getInstance().removerItem(itemToRemove);
                    Toast.makeText(getApplicationContext(),"Item deletado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


            alerta = builder.create();
            alerta.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_item_detalhes;
    }
}
