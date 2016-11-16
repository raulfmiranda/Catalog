package catalog.raulfmiranda.com.catalog.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catalog.raulfmiranda.com.catalog.model.Categoria;
import catalog.raulfmiranda.com.catalog.model.Item;

public final class ItemControl {
    private final Map<Long, Item> itemMap = new HashMap<>();
    private static final ItemControl INSTANCE = new ItemControl();

    private ItemControl() {
        if(INSTANCE != null) {
            throw new IllegalStateException("ItemControl já foi instanciado.");
        }
    }

    public static synchronized ItemControl getInstance() {
        return INSTANCE;
    }

    public void addItem(Item item) {
        itemMap.put(item.getId(), item);
    }

    public List<Item> retrieveItems() {
        if(itemMap.isEmpty()) {
            List<Item> items = carregarItems();
            if(items != null) {
                for(Item item : items) {
                    itemMap.put(item.getId(), item);
                }
            }
        }
        return new ArrayList<>(itemMap.values());
    }

    private List<Item> carregarItems() {
        List<Item> items = new ArrayList<>();

        Categoria livro = CategoriaControl.getInstance().retrieveCategorias().get(0);
        Categoria revista = CategoriaControl.getInstance().retrieveCategorias().get(1);

        Item.Builder livroBuilder = new Item.Builder(System.nanoTime(), "Java for Beginners")
                .setAno(2013).setAutor("Glauber Rocha").setQuantidade(3)
                .setDescricao("Livro para programadores iniciantes.")
                .setCategoria(livro);

        Item.Builder revistaBuilder = new Item.Builder(System.nanoTime(), "Geek on the table")
                .setAno(2015).setAutor("Robson Duarte").setQuantidade(2)
                .setDescricao("Revista para geeks.")
                .setCategoria(revista);

        items.add(livroBuilder.build());
        items.add(revistaBuilder.build());

        return items;
    }
}
