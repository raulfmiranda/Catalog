package catalog.raulfmiranda.com.catalog.control;

import android.app.Activity;
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

public final class ItemControl {
    private final String ITEMS_DATA_KEY = "ITEMS_DATA_KEY";

    private final List<Item> list = new ArrayList<>();
    private final Map<Long, Item> itemMap = new HashMap<>();

    private static ItemControl INSTANCE;
    private final SharedPreferences sharedPreferences;

    private ItemControl(Context context) {
        if (INSTANCE != null) {
            throw new IllegalStateException("ItemControl já foi instanciado.");
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized ItemControl getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ItemControl(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public static synchronized ItemControl getInstance() {
        if (INSTANCE == null) throw new IllegalStateException("Call getInstance(Context) first!");
        return INSTANCE;
    }

    public Item getItemById(Long id) {
        return itemMap.get(id);
    }

    public void addItem(Item item) {
        list.add(item);

        saveData();
    }

    public void removerItem(Item item) {
        list.remove(item);
        saveData();
    }

    private void saveData() {
        String jsonData = new Gson().toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ITEMS_DATA_KEY, jsonData);
        editor.apply();

        syncMap();
    }

    public List<Item> retrieveItems() {
        if (list.isEmpty()) {
            List<Item> listaCarregada = carregarItems();
            if (listaCarregada != null) {
                list.addAll(listaCarregada);
                syncMap();
            }
        }
        return new ArrayList<>(list);
    }

    private void syncMap() {
        itemMap.clear();
        for (Item item : list) {
            itemMap.put(item.getId(), item);
        }
    }

    private List<Item> carregarItems() {
        List<Item> lista = new ArrayList<>();
        String savedData = sharedPreferences.getString(ITEMS_DATA_KEY, null);
        if (savedData != null) {
            Type listType = new TypeToken<List<Item>>() {}.getType();
            List<Item> savedList = new Gson().fromJson(savedData, listType);
            if (savedList != null) {
                lista.addAll(savedList);
            }
        }

        return lista;
    }
//
//    private List<Item> oldCarregarItems() {
//        List<Item> items = new ArrayList<>();
//
//        List<Categoria> categorias = CategoriaControl.getInstance().retrieveCategorias();
//        Categoria livro = categorias.get(0);
//        Categoria revista = categorias.get(1);
//
//        Item.Builder livroBuilder = new Item.Builder(System.nanoTime(), "Java for Beginners")
//                .setAno(2013).setAutor("Glauber Rocha").setQuantidade(3)
//                .setDescricao("Livro para programadores iniciantes.")
//                .setCategoria(livro);
//
//        Item.Builder revistaBuilder = new Item.Builder(System.nanoTime(), "Geek on the table")
//                .setAno(2015).setAutor("Robson Duarte").setQuantidade(2)
//                .setDescricao("Revista para geeks.")
//                .setCategoria(revista);
//
//        items.add(livroBuilder.build());
//        items.add(revistaBuilder.build());
//
//        return items;
//    }
}
