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
