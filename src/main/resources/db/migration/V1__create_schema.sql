-- Tabela Utilizador
CREATE TABLE utilizador (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            username VARCHAR(50) NOT NULL UNIQUE,
                            password VARCHAR(255) NOT NULL,
                            perfil VARCHAR(10) NOT NULL CHECK (perfil IN ('ADMIN', 'USER'))
);

-- Tabela Categoria
CREATE TABLE categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL UNIQUE,
                           descricao VARCHAR(500)
);

-- Tabela Produto
CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(200) NOT NULL,
                         descricao VARCHAR(1000),
                         preco DECIMAL(10,2) NOT NULL,
                         stock INT NOT NULL,
                         categoria_id BIGINT NOT NULL
);

-- Tabela Encomenda
CREATE TABLE encomenda (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           usuario_id BIGINT NOT NULL,
                           estado VARCHAR(20) NOT NULL CHECK (estado IN ('PENDENTE', 'ENTREGUE')),
                           data_criacao TIMESTAMP NOT NULL,
                           total DECIMAL(10,2) NOT NULL
);

-- Tabela EncomendaItem
CREATE TABLE encomenda_item (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                encomenda_id BIGINT NOT NULL,
                                produto_id BIGINT NOT NULL,
                                quantidade INT NOT NULL,
                                preco_unitario DECIMAL(10,2) NOT NULL
);

-- Índices
CREATE INDEX idx_utilizador_username ON utilizador(username);
CREATE INDEX idx_categoria_nome ON categoria(nome);
CREATE INDEX idx_produto_nome ON produto(nome);
CREATE INDEX idx_produto_preco ON produto(preco);
CREATE INDEX idx_produto_categoria ON produto(categoria_id);
CREATE INDEX idx_encomenda_usuario ON encomenda(usuario_id);
CREATE INDEX idx_encomenda_estado ON encomenda(estado);
CREATE INDEX idx_encomenda_data ON encomenda(data_criacao);
CREATE INDEX idx_encomenda_item_encomenda ON encomenda_item(encomenda_id);
CREATE INDEX idx_encomenda_item_produto ON encomenda_item(produto_id);