package api;

import gherkin.deps.com.google.gson.JsonObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Usuario {
    String caminhoMassa; // caminho da massa de teste
    JsonObject jsonObj;  // Ler a massa de teste em formato json

    @Test
    public void criarUsuario(){
        given() // Dado
            .contentType("application/json")
            .log().all()
            //.body()

        ;



    }




}
