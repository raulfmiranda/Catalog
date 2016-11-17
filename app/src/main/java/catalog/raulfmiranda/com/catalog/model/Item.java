package catalog.raulfmiranda.com.catalog.model;

public class Item {
    private Long id;
    private int ano, quantidade;
    private String titulo, descricao, autor;
    private Categoria categoria;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (ano != item.ano) return false;
        if (quantidade != item.quantidade) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (titulo != null ? !titulo.equals(item.titulo) : item.titulo != null) return false;
        if (descricao != null ? !descricao.equals(item.descricao) : item.descricao != null)
            return false;
        if (autor != null ? !autor.equals(item.autor) : item.autor != null) return false;
        return categoria != null ? categoria.equals(item.categoria) : item.categoria == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ano;
        result = 31 * result + quantidade;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private Long id;
        private int ano, quantidade;
        private String titulo, descricao, autor;
        private Categoria categoria;

        public Builder(Long id, String titulo) {
            this.id = id;
            this.titulo = titulo;
        }

        public Builder setQuantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public Builder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder setCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder setAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public Builder setAno(int ano) {
            this.ano = ano;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.setAno(ano);
            item.setId(id);
            item.setAutor(autor);
            item.setCategoria(categoria);
            item.setDescricao(descricao);
            item.setQuantidade(quantidade);
            item.setTitulo(titulo);
            return item;
        }
    }
}
