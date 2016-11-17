package catalog.raulfmiranda.com.catalog.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import catalog.raulfmiranda.com.catalog.R;
import catalog.raulfmiranda.com.catalog.control.ItemControl;
import catalog.raulfmiranda.com.catalog.model.Item;

public class CadastrarItemActivity extends BasicActivity {
    private EditText nomeItem;
    private Button concluidoBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nomeItem = (EditText) findViewById(R.id.nomeItemEditText);
        concluidoBotao = (Button) findViewById(R.id.cadastroConcluido);

        concluidoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeItemString = nomeItem.getText().toString();
                if((nomeItemString != null) && (!nomeItemString.isEmpty())) {
                    Item.Builder builder = new Item.Builder(System.nanoTime(), nomeItemString)
                            .setAno(2000).setAutor("Raul").setQuantidade(1);
                    ItemControl.getInstance().addItem(builder.build());
                    finish();
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cadastrar_item;
    }
}
