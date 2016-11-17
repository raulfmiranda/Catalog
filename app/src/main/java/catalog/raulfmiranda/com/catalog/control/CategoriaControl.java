package catalog.raulfmiranda.com.catalog.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catalog.raulfmiranda.com.catalog.model.Categoria;
import catalog.raulfmiranda.com.catalog.model.Item;

public final class CategoriaControl {
    private final String CATEGORIA_DATA_KEY = "CATEGORIA_DATA_KEY";

    private final List<Categoria> list = new ArrayList<>();
    private final Map<Long, Categoria> categoriaMap = new HashMap<>();

    private final SharedPreferences sharedPreferences;
    private static CategoriaControl INSTANCE;

    private CategoriaControl(Context context) {
        if(INSTANCE != null) {
            throw new IllegalStateException("CategoriaControl já foi instanciada.");
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized CategoriaControl getInstance() {
        if(INSTANCE == null) throw new IllegalStateException("Call getInstance(Context) first!");
        return INSTANCE;
    }

    public static synchronized CategoriaControl getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new CategoriaControl(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public void addCategoria(Categoria categoria) {
        list.add(categoria);
        saveData();
    }

    public void removerCategoria(Categoria categoria) {
        list.remove(categoria);
        saveData();
    }

    public List<Categoria> retrieveCategorias() {
        if(list.isEmpty()) {
            List<Categoria> listaCarregada = carregarCategorias();
            if(listaCarregada != null) {
                list.addAll(listaCarregada);
                syncMap();
            }
        }
        return new ArrayList<>(list);
    }

    private List<Categoria> carregarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String savedData = sharedPreferences.getString(CATEGORIA_DATA_KEY, null);
        if (savedData != null) {
            Type listType = new TypeToken<List<Categoria>>() {}.getType();
            List<Categoria> savedList = new Gson().fromJson(savedData, listType);
            if (savedList != null) {
                lista.addAll(savedList);
            }
        }
        return lista;
    }

    private void saveData() {
        String jsonData = new Gson().toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CATEGORIA_DATA_KEY, jsonData);
        editor.apply();

        syncMap();
    }

    private void syncMap() {
        categoriaMap.clear();
        for (Categoria categoria : list) {
            categoriaMap.put(categoria.getCodigo(), categoria);
        }
    }
}
