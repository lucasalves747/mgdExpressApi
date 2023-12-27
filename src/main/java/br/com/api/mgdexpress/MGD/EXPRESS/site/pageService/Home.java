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
                        <button onclick="carregarPagina('https://mgdexpressapi-production.up.railway.app/site/gerente/criar')" >Novo Pedido</button>
                        <button onclick="listarPedidos ()">Meus Pedidos</button>
                        <button onclick="carregarPagina('')">Em Andamento</button>
                        <button onclick="listarHistorico()">Histórico</button>
                    </div>
                </nav>
                                
                <main>
                    <div id="map"></div>
                </main>
                                
                <script>
                    buscarMotoboys()
                                
                    setTimeout(buscarMotoboys,2000)
                    function inicializarMapa(localizacoes) {
                        // Coordenadas iniciais
                        var latitudeInicial = -23.550520;
                        var longitudeInicial = -46.633308;
                                
                        // Opções do mapa
                        var options = {
                            center: { lat: latitudeInicial, lng: longitudeInicial },
                            zoom: 12, // Nível de zoom
                        };
                                
                        // Criar o mapa
                        var map = new google.maps.Map(document.body, options);
                                
                        // Iterar sobre a lista de localizações e adicionar marcadores
                        localizacoes.forEach(function (localizacao) {
                            var latitude = parseFloat(localizacao.localizacao.latitude);
                            var longitude = parseFloat(localizacao.localizacao.longitude);
                                
                            // Adicionar um marcador
                            var marker = new google.maps.Marker({
                                position: { lat: latitude, lng: longitude },
                                map: map,
                                title: localizacao.nome
                            });
                        });
                    }
                    function mapaSemMotoboy(){
                             // Coordenadas iniciais
                             var latitude = -23.550520;
                            var longitude = -46.633308;
                    
                            // Opções do mapa
                            var options = {
                                center: { lat: latitude, lng: longitude },
                                zoom: 12, // Nível de zoom
                            };
                    
                            // Criar o mapa
                            var map = new google.maps.Map(document.body, options);
                        }
                               
                </script>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsTWHMwA_agU_-o35U_3b606930nBrsY8&callback=initMap" async defer></script>
                                
                                   
                        """;
    }
}


