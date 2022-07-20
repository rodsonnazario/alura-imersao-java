import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão http e pegar os dados do endpoint que lista os top 250 filmes
        var url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        var endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        var body = response.body();        

        // converter o json recebido em uma lista
        var listaDeFilmes = JsonParser.parse(body);

        // manipular e exibir os dados
        listaDeFilmes.forEach(filme -> {            
            System.out.println("Posição: " + filme.get("rank"));
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Ano: " + filme.get("year"));
            System.out.println("Classificação: " + filme.get("imDbRating"));       
            System.out.println();     
        });
    }
}
