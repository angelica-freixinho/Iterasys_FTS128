package api;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Usuario2 {

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void ct_01_incluir_usuario() throws IOException {
        String jsonBody = lerJson("src/test/resources/data/usuario2.json");
//        String jsonConcat = "{" + "\n" + "  \"id\": " + "1," + "\n" + "  \"username\": " + "\"ana\"," + "\n" + "  \"firstName\": " + "\"Ana\"," + "\n" + "  \"lastName\": " + "\"Nunes\"," + "\n" + "  \"email\": " + "\"ana.nunes@teste.com.br\"," + "\n" + "  \"password\": " + "\"12345678\"," + "\n" + "  \"phone\": " + "\"1199999998\"," + "\n" + "  \"userStatus\": " + "1" + "\n" + "}";
//        System.out.printf("JSONSTRING: \n%s\n", jsonConcat);
        given() // Dado
            .contentType("application/json") // O conteudo é um json
            .log().all()                     // registre tudo que acontecer
            .body(jsonBody)                 // o json para enviar é este
            .when() //Quando
            .post("https://petstore.swagger.io/v2/user") //Inclua neste endpoint
            .then() // Entao
            .log().all()                    // registre tudo que acontecer na volta
            .statusCode(200)            // valide se retornou o codigo 200 (ok)
            .body("code", is(200)) // idem a linha acima
            .body("type", is("unknown")) //valida se o tipo é desconhecido
            .body("message", is("1"))
        ;
        System.out.println("CT01 - Inclui o Usuário");
    }

    @Test
    public void ct_02_consultar_usuario() {
        String telefoneEsperado = "1199999998";

        given()
            .contentType("application/json")
            .log().all()
            .when()
            .get("https://petstore.swagger.io/v2/user/ana")
            .then()
            .log().all()
            .statusCode(200)
            .body("phone", is(telefoneEsperado))
        ;
    }

    @Test
    public void ct_03_alterar_usuario() throws IOException {
        //String jsonBody = lerJson("src/test/resources/data/usuario2.json");
        String jsonStr = "{" + "\n" + "  \"id\": " + "7," + "\n" + "  \"username\": " + "\"ana\"," + "\n" + "  \"firstName\": " + "\"Ana\"," + "\n" + "  \"lastName\": " + "\"bli\"," + "\n" + "  \"email\": " + "\"ana.nunes@yahoo.com.br\"," + "\n" + "  \"password\": " + "\"12345678\"," + "\n" + "  \"phone\": " + "\"1199999998\"," + "\n" + "  \"userStatus\": " + "1" + "\n" + "}";

        given()
           .contentType("application/json")
           .log().all()
           .body(jsonStr)                 // o json para enviar é este
           .when() //Quando
           .put("https://petstore.swagger.io/v2/user/ana") //Inclua neste endpoint
           .then() // Entao
           .log().all()                    // registre tudo que acontecer na volta
           .statusCode(200)            // valide se retornou o codigo 200 (ok)
           .body("type", is("unknown")) //valida se o tipo é desconhecido
           .body("message", is("7"))
        ;
    }

    @Test
    public void ct_04_deletar_usuario() throws IOException {

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .delete("https://petstore.swagger.io/v2/user/ana")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", is("unknown")) //valida se o tipo é desconhecido
                .body("message", is("ana"))
        ;
    }

}
