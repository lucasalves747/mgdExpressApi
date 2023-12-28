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
                            height: 600px;
                            /* Ajuste a altura conforme necessário */
                        }
                                
                        nav button {
                            background-color: #4CAF50;
                            color: white;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                            font-size: 16px;
                            margin-left: 15px;
                        }
                                
                        nav button:hover {
                            background-color: #45a049;
                        }
                    </style>
                </head>
                                
                                
                <nav>
                    <h2>MGD EXPRESS</h2>
                    <div>
                        <button onclick="carregarPagina('https://mgdexpressapi-production.up.railway.app/site/gerente/criar')">Novo
                            Pedido</button>
                        <button onclick="listarPedidos()">Meus Pedidos</button>
                        <button onclick="carregarPagina('')">Em Andamento</button>
                        <button onclick="listarHistorico()">Histórico</button>
                    </div>
                </nav>
                                
                                
                <main>
                    <div id="map"></div>
                </main>
                
                <script>
                    buscarMotoboys();
                    
                    function initMap(localizacoes) {
                        // Configurações iniciais do mapa
                        var mapOptions = {
                            center: { lat: -23.550520, lng: -46.633308 }, // Coordenadas iniciais
                            zoom: 10
                        };
                   \s
                        // Criação do mapa
                        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
                   \s
                        // Lista de marcadores
                        var markers = localizacoes;
                   \s
                        // Criação dos marcadores iniciais
                        markers.forEach(function(markerInfo) {
                            var marker = new google.maps.Marker({
                                position: markerInfo.position,
                                map: map,
                                title: markerInfo.title
                            });
                   \s
                            // Adiciona o marcador ao array para referência futura
                            markerInfo.marker = marker;
                        });
                   \s
                        // Função para atualizar as posições dos marcadores
                        function updateMarkersPosition(localizacoes) {
                            // Itera sobre a lista de marcadores e atualiza suas posições
                            markers.forEach(function(markerInfo) {
                               \s
                                var newPosition = {
                                    lat:parseFloat(localizacao.localizacao.latitude),// Adiciona um pequeno valor aleatório para simular movimento
                                    lng: parseFloat(localizacao.localizacao.longitude)
                                };
                   \s
                                // Atualiza a posição do marcador
                                markerInfo.marker.setPosition(newPosition);
                            });
                        }
                        // Define o intervalo para atualizar as posições dos marcadores a cada 5 segundos (5000 milissegundos)
                        setInterval(buscarMotoboys2, 5000);
                    }
                    </script>
                    
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async
                    defer></script> """;
    }
}


