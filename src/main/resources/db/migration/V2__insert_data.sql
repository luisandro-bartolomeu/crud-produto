-- Inserir utilizadores (perfil: ADMIN ou USER)
INSERT INTO utilizador (name, username, password, perfil) VALUES
                                                              ('Administrador', 'admin', '$2a$10$N.ZuNaqH.Q0nIJKjKqxqEunQhJkRq3QJqRq3QJqRq3QJqRq3QJqRq', 'ADMIN'),
                                                              ('João Silva', 'joao_silva', '$2a$10$N.ZuNaqH.Q0nIJKjKqxqEunQhJkRq3QJqRq3QJqRq3QJqRq3QJqRq', 'USER'),
                                                              ('Maria Santos', 'maria_santos', '$2a$10$N.ZuNaqH.Q0nIJKjKqxqEunQhJkRq3QJqRq3QJqRq3QJqRq3QJqRq', 'USER');

-- Inserir categorias
INSERT INTO categoria (nome, descricao) VALUES
                                            ('Eletrônicos', 'Produtos eletrônicos e gadgets'),
                                            ('Roupas', 'Vestuário em geral'),
                                            ('Livros', 'Livros e publicações'),
                                            ('Esportes', 'Equipamentos esportivos'),
                                            ('Casa e Jardim', 'Produtos para casa e jardinagem');

-- Inserir produtos
INSERT INTO produto (nome, descricao, preco, stock, categoria_id) VALUES
                                                                      ('Smartphone XYZ', 'Smartphone com 128GB, tela 6.5"', 1999.99, 50, 1),
                                                                      ('Notebook ABC', 'Notebook 16GB RAM, 512GB SSD', 4599.99, 30, 1),
                                                                      ('Camiseta Polo', 'Camiseta polo 100% algodão', 89.90, 100, 2),
                                                                      ('Calça Jeans', 'Calça jeans azul', 149.90, 80, 2),
                                                                      ('Livro Spring Boot', 'Aprenda Spring Boot do zero', 79.90, 45, 3),
                                                                      ('Livro Java', 'Java para iniciantes', 69.90, 40, 3),
                                                                      ('Bola de Futebol', 'Bola oficial de futebol', 129.90, 60, 4),
                                                                      ('Tênis de Corrida', 'Tênis profissional para corrida', 299.90, 35, 4),
                                                                      ('Vaso Decorativo', 'Vaso de cerâmica decorativo', 49.90, 70, 5),
                                                                      ('Kit Jardinagem', 'Kit completo de jardinagem', 89.90, 55, 5);

-- Inserir encomendas (assumindo que utilizador tem IDs 1,2,3)
INSERT INTO encomenda (usuario_id, estado, data_criacao, total) VALUES
                                                                    (2, 'ENTREGUE', '2026-01-15 10:30:00', 2179.88),
                                                                    (2, 'PENDENTE', '2026-06-01 14:20:00', 919.80),
                                                                    (3, 'ENTREGUE', '2026-02-20 09:15:00', 219.80),
                                                                    (3, 'PENDENTE', '2026-06-02 11:45:00', 4599.99);

-- Inserir itens
INSERT INTO encomenda_item (encomenda_id, produto_id, quantidade, preco_unitario) VALUES
                                                                                      (1, 1, 1, 1999.99), (1, 5, 2, 79.90),
                                                                                      (2, 3, 2, 89.90), (2, 7, 1, 129.90), (2, 9, 1, 49.90),
                                                                                      (3, 4, 1, 149.90), (3, 6, 1, 69.90),
                                                                                      (4, 2, 1, 4599.99);