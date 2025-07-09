CREATE TABLE tb_food_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Categoria ENUM(
        'BEBIDA',
        'CARNE',
        'LATICINIO',
        'FRUTA',
        'VEGETAL',
        'GRAO',
        'DOCE',
        'CONGELADO',
        'PADARIA',
        'MASSA'
    ) NOT NULL,
    Quantidade INT NOT NULL,
    Validade DATE NOT NULL
);