# 🚀 DSCommerce

## 📝 Sobre o Projeto

O projeto **DSCommerce** foi desenvolvido durante o Curso Java Spring Professional, ministrado pelo Professor Nelio Alves da [DevSuperior](https://devsuperior.com.br).

Este projeto consiste na criação de uma API REST para um e-commerce, onde é possível realizar o gerenciamento de produtos, clientes e pedidos. A concepção do sistema partiu das seguintes premissas do curso:

* **Modelo de Domínio Abrangente:** Possui um modelo de domínio que, embora simples, explora diversos tipos de relacionamentos entre entidades (um-para-muitos, muitos-para-muitos), essencial para o entendimento de sistemas complexos.
* **Funcionalidades Essenciais:** Contém as funcionalidades chave que um desenvolvedor júnior deve saber construir, como cadastros completos (CRUD) e fluxos de casos de uso realistas.

## ⚙️ Modelo Conceitual

O diagrama abaixo representa o modelo conceitual do sistema, ilustrando as entidades de negócio e seus relacionamentos.

![Modelo Conceitual](https://raw.githubusercontent.com/brunoalfeu/dscommerce/main/docs/modelagem-conceitual.png)

**Considerações:**
* **OrderItem:** Cada item de pedido armazena a quantidade e o preço do produto no momento da compra. Isso garante um histórico preciso, mesmo que o preço do produto mude futuramente.
* **User Roles:** Um usuário pode ter um ou mais perfis de acesso (`roles`), como `client` ou `admin`.

---

## 📋 Casos de Uso

O escopo funcional do sistema é definido pelos seguintes casos de uso, que são ilustrados no diagrama e detalhados a seguir.

![Diagrama de Casos de Uso](https://raw.githubusercontent.com/brunoalfeu/dscommerce/main/docs/casos-de-uso.png)

### Atores

| Ator                 | Responsabilidade                                                                                                                                                                 |
| :------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Usuário anônimo** | Pode navegar no catálogo, gerenciar o carrinho de compras, fazer login e se cadastrar no sistema.                                                                                  |
| **Cliente** | Além das permissões de usuário anônimo, pode gerenciar seus dados pessoais e visualizar o histórico de seus pedidos. Todo usuário cadastrado torna-se um Cliente por padrão.        |
| **Admin** | Possui acesso total à área administrativa para gerenciar cadastros (produtos, categorias, usuários) e visualizar relatórios. Também pode executar todas as ações de um Cliente. |

### Visão Geral

| Caso de uso             | Visão geral                                            | Acesso        |
| :---------------------- | :----------------------------------------------------- | :------------ |
| **Consultar catálogo** | Listar produtos disponíveis, com filtro por nome.      | Público       |
| **Gerenciar carrinho** | Incluir/remover itens do carrinho e alterar quantidades. | Público       |
| **Login / Sign up** | Efetuar login e cadastrar-se no sistema.               | Público       |
| **Registrar pedido** | Salvar um pedido a partir do carrinho de compras.      | Usuário logado |
| **Visualizar pedidos** | Visualizar os próprios pedidos já realizados.          | Usuário logado |
| **Manter produtos** | CRUD de produtos, com filtro por nome.                 | Somente Admin |
| **Manter categorias** | CRUD de categorias, com filtro por nome.               | Somente Admin |
| **Registrar pagamento** | Salvar os dados de pagamento de um pedido.             | Somente Admin |


### Detalhamento dos Principais Casos de Uso

<details>
<summary><strong>1. Consultar Catálogo</strong></summary>

* **Atores:** Usuário anônimo, Cliente, Admin.
* **Visão geral:** Listar produtos disponíveis, podendo filtrar pelo nome.
* **Cenário principal:**
    1.  O sistema exibe uma listagem paginada de produtos (nome, imagem, preço), ordenados por nome.
    2.  Opcionalmente, o usuário informa parte de um nome para filtrar a busca.
    3.  O sistema atualiza a listagem com base no filtro.

</details>

<details>
<summary><strong>2. Manter Produtos (Admin)</strong></summary>

* **Atores:** Admin.
* **Precondições:** Usuário logado.
* **Visão geral:** Gerenciamento completo (CRUD) de produtos.
* **Cenário principal:**
    * **Inserir:** O admin informa nome, preço, descrição, URL da imagem e categorias para cadastrar um novo produto.
    * **Atualizar:** O admin seleciona um produto, edita seus dados e salva as alterações.
    * **Deletar:** O admin seleciona um produto para removê-lo do sistema.
* **Exceções:** O sistema valida os dados (nome entre 3 e 80 caracteres, preço positivo, etc.) e trata erros como ID não encontrado ou violação de integridade referencial.

</details>

<details>
<summary><strong>3. Gerenciar Carrinho</strong></summary>

* **Atores:** Usuário anônimo.
* **Visão geral:** Incluir e remover itens do carrinho, bem como alterar suas quantidades.
* **Cenário principal:**
    1.  O usuário seleciona um produto do catálogo.
    2.  O sistema exibe os detalhes do produto.
    3.  O usuário adiciona o produto ao carrinho.
    4.  O sistema exibe os dados atualizados do carrinho (lista de itens e valor total).
    5.  Opcionalmente, o usuário pode incrementar ou decrementar a quantidade de um item.

</details>

<details>
<summary><strong>4. Registrar Pedido</strong></summary>

* **Atores:** Cliente.
* **Precondições:** Usuário logado e carrinho de compras não vazio.
* **Pós-condições:** O carrinho de compras fica vazio.
* **Visão geral:** Salva um novo pedido no sistema com base nos itens do carrinho.
* **Cenário principal:**
    1.  O cliente finaliza a compra a partir do carrinho.
    2.  O sistema salva o pedido, associa ao cliente, limpa o carrinho e informa o ID do novo pedido.

</details>

---

## 💻 Tecnologias Utilizadas

O projeto foi construído com um conjunto de tecnologias modernas e amplamente utilizadas no mercado.

![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=openjdk)
![Spring](https://img.shields.io/badge/Spring_Boot-3.5.4-green?style=for-the-badge&logo=spring)
![Spring Security](https://img.shields.io/badge/Spring_Security-OAuth2-blueviolet?style=for-the-badge&logo=spring-security)
![JPA](https://img.shields.io/badge/JPA-Hibernate-orange?style=for-the-badge&logo=hibernate)
![H2](https://img.shields.io/badge/H2_Database-red?style=for-the-badge&logo=h2)
![Maven](https://img.shields.io/badge/Apache_Maven-gray?style=for-the-badge&logo=apache-maven)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman)

---

## 🛠️ Como Executar o Projeto

Siga os passos abaixo para executar a aplicação em seu ambiente local.

### Pré-requisitos
* **Java JDK 21** ou superior.
* **Maven** para gerenciamento de dependências e build do projeto.

### Instalação e Execução
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/brunoalfeu/dscommerce.git](https://github.com/brunoalfeu/dscommerce.git)
    ```
2.  **Acesse o diretório do projeto:**
    ```bash
    cd dscommerce
    ```
3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  A aplicação estará disponível em `http://localhost:8080`.
5.  O console do banco de dados em memória **H2** pode ser acessado em `http://localhost:8080/h2-console` (utilizando o perfil `test`).

---

## 🧪 Testando a API com o Postman
Para facilitar o teste dos endpoints, você pode importar a coleção e o ambiente do Postman disponibilizados.

1.  **Instale o Postman:**
    * Baixe-o gratuitamente no [site oficial](https://www.postman.com/downloads/).

2.  **Importe os arquivos:**
    * **Opção 1 (Recomendado):** Use os links diretos abaixo para importar no Postman.
        * **[Clique aqui para importar a Collection](https://brunoalfeu.postman.co/workspace/projetos~018ecba4-a028-490b-a7e6-66d5241d34fb/collection/44931028-1d77590e-3af1-4e3b-902f-cad9b05b0fdf?action=share&source=copy-link&creator=44931028)**
        * **[Clique aqui para importar o Environment](https://brunoalfeu.postman.co/workspace/projetos~018ecba4-a028-490b-a7e6-66d5241d34fb/environment/44931028-461065cc-f467-42af-a57e-b562776d8a75?action=share&creator=44931028&active-environment=44931028-461065cc-f467-42af-a57e-b562776d8a75)**
    * **Opção 2:** Se preferir baixar os arquivos, vá em `File -> Import` no Postman e use os arquivos baixados.

3.  **Comece a testar:**
    * Selecione o ambiente **"Dscommerce"** no canto superior direito do Postman.
    * Navegue pela coleção, abra qualquer requisição e clique em **"Send"** para testá-la.

### Demonstração em Vídeo

Uma demonstração de todas as requisições sendo testadas no Postman pode ser visualizada no link abaixo.

**[Clique aqui para assistir ao vídeo de demonstração](https://youtu.be/rZ_2x1Cy3AE)**

---

## 👨‍💻 Autor

**Bruno Alfeu**

* [LinkedIn](https://www.linkedin.com/in/bruno-alfeu-dev/)
* [GitHub](https://github.com/brunoalfeu)
