package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class Home {

    public static String home(String url){
        return """
                         
                <head>
                    <title>MGD EXPRESS</title>
                    <style>
                        body {
                            margin: 0;
                            padding: 0;
                            font-family: Arial, sans-serif;
                        }
                                       
                        nav {
                            background-color: #333;
                            color: white;
                            padding: 10px;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                        }
                                       
                        nav h2 {
                            margin: 0;
                        }
                                       
                        nav a {
                            color: white;
                            text-decoration: none;
                            margin-left: 15px;
                        }
                                       
                        main {
                            text-align: center;
                            padding: 20px;
                        }
                                       
                        #map {
                            width: 100%;
                            height: 600px ; /* Ajuste a altura conforme necessário */
                        }
                    </style>
                </head>
                                       
                                       
                <nav>
                    <h2>MGD EXPRESS</h2>
                    <div>
                        <button onclick="carregarPagina('"""+url+"""
                        site/gerente/criar')" >Novo Pedido</button>
                        <button onclick="listarPedidos ()">Meus Pedidos</button>
                        <button onclick="carregarPagina('')">Em Andamento</button>
                        <button onclick="listarHistorico()">Histórico</button>
                    </div>
                </nav>
                                       
                <main>
                    <div id="map"></div>
                </main>
                                       
                <script>
                    // Substitua 'SUA_CHAVE_DO_MAPA_AQUI' pela sua chave de API do mapa
                    function initMap() {
                        var mapOptions = {
                            center: { lat: -23.5505, lng: -46.6333 }, // Coordenadas de São Paulo, por exemplo
                            zoom: 12
                        };
                        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
                    }
                </script>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async defer></script>
                                       
                                       
                        """;
    }
}
