

package com.mycompany.testandoapi;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.Scanner;

public class TestandoAPI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cidade;
        String pergunta;
        //inicio
        System.out.println("Seja bem vindo a nossa aplicação!!Aproveite!\n ");
        System.out.println("Insira a cidade:");
        cidade =sc.nextLine();

        //api
        String apiKey = "12505bf3858c1ad843e458dc0615100e";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cidade + "&appid=" + apiKey + "&units=metric&lang=pt_br";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String jsonData = response.body().string();

            JsonObject json = JsonParser.parseString(jsonData).getAsJsonObject();
            JsonObject main = json.getAsJsonObject("main");

            double temperatura = main.get("temp").getAsDouble();
            double sencacao = main.get("feels_like").getAsDouble();
            System.out.println("Temperatura em " + cidade + ": " + temperatura + "°C"+ " com sencacao de "+sencacao+"°C");
            //pergunta
            System.out.println("Deseja receber algumas pequenas dicas de cuidados nessas temperaturas? s/n");
            pergunta = sc.nextLine();
            if(pergunta.trim().equalsIgnoreCase("s")){
                System.out.println("---------------------- \nLista de Atividades Recomendadas:");
                if(temperatura>35){
                    System.out.println("  *Ficar em locais frescos e bem ventilados.\n  *Reduzir esforços fisicos.\n  *Hidratar-se constantemente.");
                }
                else if(temperatura>30){
                    System.out.println("  *Hidratar-se e usar roupas leve.\n  *Evitar exercicios intensos entre 10h e 16h.");
                }
                else if(temperatura>26){
                    System.out.println("  *Evitar sol direto nas horas mais quentes.\n  *Preferir atividades em locias sombreados. ");
                }
                else if(temperatura>21){
                    System.out.println("  *Ideal para atividades ao ar livre.\n  *Temperatura ideal para encontros em locais externos.");
                }
                else if(temperatura>10){
                    System.out.println("  *Caminhar ao ar livre.\n  *Boa temperatura para estudar e se concentrar.");
                }
            }
            else{
                System.out.println("Ok, obrigado... ate a proxima :)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
