package dev.phquartin.magicfridgeai.model;

public enum Categoria {
    BEBIDA("Bebida"),
    CARNE("Carne"),
    LATICINIO("Laticínio"),
    FRUTA("Fruta"),
    VEGETAL("Vegetal"),
    GRAO("Grão"),
    DOCE("Doce"),
    CONGELADO("Congelado"),
    PADARIA("Padaria"),
    MASSA("Massa");

    private final String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

