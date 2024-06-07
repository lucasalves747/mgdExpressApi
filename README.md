  <h1>API de Gerenciamento de Entregas para Motoboys</h1>

  <h2>Descrição</h2>
  <p>A API de Gerenciamento de Entregas para Motoboys é uma aplicação desenvolvida em Spring Boot para facilitar o gerenciamento de entregas em estabelecimentos comerciais. Ela permite que lojistas visualizem a localização dos motoboys em tempo real, atribuam pedidos a eles e acompanhem o histórico de entregas. A API faz uso de tecnologias como Spring Security, Spring Data JPA, MySQL, Docker, Tokens JWT e integração com a API do iFood para obtenção de pedidos.</p>

  <h2>Imagens do app do motoboy</h2>
  <div style="display:inline;">
      <img src="src/main/java/br/com/api/mgdexpress/MGD/EXPRESS/site/page/Imagem do WhatsApp de 2024-06-06 à(s) 23.23.11_4a9b8b67.jpg" style="heigth:20%;width:20%;">
      <img src="src/main/java/br/com/api/mgdexpress/MGD/EXPRESS/site/page/Imagem do WhatsApp de 2024-06-06 à(s) 23.23.11_4a9b8b67.jpg" style="heigth:20%;width:20%;margin-left:10px">
   </div>
  <h2>Funcionalidades Principais</h2>
  <ul>
    <li>Recebimento e armazenamento da localização dos motoboys.</li>
    <li>Visualização em tempo real da localização dos motoboys em um mapa para os lojistas.</li>
    <li>Atribuição de pedidos a motoboys por meio de arrastar e soltar na interface do lojista.</li>
    <li>Integração com a API do iFood para obtenção de pedidos da loja.</li>
    <li>Registro e visualização do histórico de entregas realizadas no dia, incluindo o valor a pagar para cada motoboy.</li>
  </ul>

  <h2>Tecnologias Utilizadas</h2>
  <ul>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>Spring Data JPA</li>
    <li>MySQL</li>
    <li>Docker</li>
    <li>Tokens JWT</li>
    <li>HTML/CSS (Frontend para os lojistas)</li>
    <li>API do iFood</li>
  </ul>

  <h2>Documentação</h2>
  <p>A documentação da API está disponível no <a href="https://mgdexpress-production-bdc8.up.railway.app/swagger-ui/index.html#/">Swagger UI</a> para facilitar o entendimento dos endpoints e dos dados esperados.</p>

  <h2>Configuração</h2>
  <ol>
    <li>Clone o repositório: <code>git clone https://github.com/lucasalves747/mgdExpressApi.git</code></li>
    <li>Configure as credenciais do banco de dados MySQL no arquivo <code>application.properties</code>.</li>
    <li>Compile o projeto e execute-o: <code>mvn clean install && java -jar target/nome-do-arquivo.jar</code>.</li>
  </ol>

  <h2>Utilização</h2>
  <ol>
    <li>Acesse a interface do lojista em <a href="link-do-front-end">link-do-front-end</a> para visualizar o mapa com a localização dos motoboys e atribuir pedidos.</li>
    <li>Utilize as APIs REST para receber e enviar dados sobre a localização dos motoboys e atribuir pedidos.</li>
  </ol>

  <h2>Contribuição</h2>
  <p>Contribuições são bem-vindas! Por favor, siga as diretrizes de contribuição do projeto ao enviar pull requests.</p>

  <h2>Autores</h2>
  <p>Lucas Alves dos Santos (lucasalves747)</p>

  
