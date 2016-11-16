package catalog.raulfmiranda.com.catalog.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catalog.raulfmiranda.com.catalog.model.Categoria;

public final class CategoriaControl {
    private static Map<Long, Categoria> categoriaMap = new HashMap<>();
    private final static CategoriaControl INSTANCE = new CategoriaControl();

    private CategoriaControl() {
        if(INSTANCE != null) {
            throw new IllegalStateException("CategoriaControl já foi instanciada.");
        }
    }

    public static synchronized CategoriaControl getInstance() {
        return INSTANCE;
    }

    public void addCategoria(Categoria categoria) {
        categoriaMap.put(categoria.getCodigo(), categoria);
    }

    public List<Categoria> retrieveCategorias() {
        if(categoriaMap.isEmpty()) {
            List<Categoria> categorias = carregarCategorias();
            if(categorias != null) {
                for(Categoria categoria : categorias) {
                    categoriaMap.put(categoria.getCodigo(), categoria);
                }
            }
        }
        return new ArrayList<>(categoriaMap.values());
    }

    private List<Categoria> carregarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(System.nanoTime(), "LIVRO"));
        categorias.add(new Categoria(System.nanoTime(), "REVISTA"));

        return categorias;
    }
}
