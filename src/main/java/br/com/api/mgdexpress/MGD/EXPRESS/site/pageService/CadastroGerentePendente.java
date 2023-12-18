package br.com.api.mgdexpress.MGD.EXPRESS.site.pageService;

public class CadastroGerentePendente {

    public static String page(){
        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Confetes Animados</title>
                    <style>
                        body {
                            margin: 0;
                            overflow: hidden;
                            background-color: #f0f0f0;
                        }
                                
                        .confete {
                            width: 10px;
                            height: 20px; /* Altura maior para torná-los retangulares */
                            background-color: #e74c3c;
                            position: absolute;
                            opacity: 0;
                            animation: confeteFall linear infinite;
                        }
                                
                        @keyframes confeteFall {
                            0% {
                                transform: translateY(0) rotate(0deg);
                                opacity: 1;
                            }
                            100% {
                                transform: translateY(100vh) rotate(360deg);
                                opacity: 0;
                            }
                        }
                                
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            text-align: center;
                            padding: 20px;
                        }
                                
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            background-color: #fff;
                            padding: 20px;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                                
                        h1 {
                            color: #333;
                        }
                                
                        p {
                            color: #666;
                            margin-bottom: 20px;
                        }
                                
                        button {
                            background-color: #3498db;
                            color: #fff;
                            padding: 10px 20px;
                            border: none;
                            border-radius: 5px;
                            font-size: 16px;
                            cursor: pointer;
                        }
                                
                        button:hover {
                            background-color: #2184b5;
                        }
                    </style>
                </head>
                <body>
                <div class="container">
                    <h1>Parabens! enviamos para analize </h1>
                    <p>Quando for aprovado enviaremos um email</p>
                </div>
                                
                <script>
                    document.addEventListener("DOMContentLoaded", function() {
                        const numberOfConfetes = 25;
                                
                        for (let i = 0; i < numberOfConfetes; i++) {
                            createConfete();
                        }
                                
                        function createConfete() {
                            const confete = document.createElement("div");
                            confete.className = "confete";
                            confete.style.left = `${Math.random() * window.innerWidth}px`;
                            confete.style.backgroundColor = getRandomColor(); // Cor variada
                            document.body.appendChild(confete);
                                
                            // Random delay for the confete to start falling
                            const delay = Math.random() * 3;
                            confete.style.animationDuration = `${Math.random() * 2 + 2}s`;
                            confete.style.animationDelay = `-${delay}s`;
                                
                            // Remove the confete after it falls off the bottom of the screen
                            setTimeout(() => {
                                confete.remove();
                            }, (delay + 3) * 1000);
                        }
                                
                        function getRandomColor() {
                            const letters = 'FF75556789ABCDEF';
                            let color = '#';
                            for (let i = 0; i < 6; i++) {
                                color += letters[Math.floor(Math.random() * 5)];
                            }
                            return color;
                        }
                    });
                </script>
                                
                </body>
                </html>
                """;
    }
}
