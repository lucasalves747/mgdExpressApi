package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;

public class DetalhePedido {

    public static String detalhar(Pedido dados){
        return """
                
                       <head>
                           <title>Detalhe do Histórico</title>
                           <style>
                               body {
                                   font-family: Arial, sans-serif;
                                   margin: 0;
                                   padding: 0;
                                   background-color: #f4f4f4;
                                   color: #333;
                               }
                       
                               nav {
                                   background-color: #333;
                                   color: white;
                                   padding: 10px;
                                   text-align: center;
                               }
                       
                               main {
                                   padding: 20px;
                               }
                       
                               .card {
                                   background-color: #fff;
                                   border-radius: 8px;
                                   box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                                   margin-bottom: 20px;
                                   padding: 20px;
                               }
                       
                               .card p {
                                   margin: 0;
                                   margin-bottom: 10px;
                               }
                       
                               .card h3 {
                                   margin-top: 0;
                               }
                           </style>
                       </head>
                       
                       
                       <nav>
                           <h2>Detalhe do Pedido</h2>
                       </nav>
                       
                       <main>
                           <div class="card">
                               <h3>Pedido</h3>
                               <p>Nome do Estabelecimento: string</p>
                               <p>Local de Origem: string               Local de Destino: string</p>
                               <p>Valor: 0</p>
                               <p>Observação: string</p>
                               <p>Itens do Pedido: string</p>
                               <p>Data de Criação: 2023-12-06   Data de Entrega: 2023-12-06</p>
                               <h3>Motoboy:</h3>
                               <ul>
                       
                                   <li>Nome: string</li>
                                   <li>Telefone: string</li>
                                   <li>Email: string</li>
                       
                               </ul>
                               <h3>Gerente:</h3>
                               <ul>
                                   <li>Nome: string</li>
                                   <li>Telefone: string</li>
                                   <li>Email: string</li>
                               </ul>
                       
                           </div>
                       </main>
                """;
    }
}
