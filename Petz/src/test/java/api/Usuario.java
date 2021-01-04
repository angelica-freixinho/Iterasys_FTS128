package api;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.Json;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)

public class Usuario {
    String uri = "https://petstore.wagger.io/io/v2";
    String caminhoMassa = "src/test/resources/data/usuario.json"; // caminho da massa de teste
    JSONObject jsonObj = new JSONObject(Json.ler(caminhoMassa));  // Ler a massa de teste em formato json


    @Test
    public void criarUsuario(){
        given() // Dado
            .contentType("application/json") //tipo de conteudo da API
            .log().all() // logar tudo que acontecer na ida - request
            .body(jsonObj.getJSONObject("user1"))
        .when() // quando
            .post(uri + "/user") // criar um usuario
        .then()
            .log().all() // logar tudo que acontecer na volta - response
            .statusCode(200) // checagem minima de que a request foi atendida
            .body("code", equalTo(200)) // verifica se o valor do codigo no body da response = 200
            .body("type", equalTo("unknown"))
            .body("message", equalTo("id"))
        ;
    }
}
