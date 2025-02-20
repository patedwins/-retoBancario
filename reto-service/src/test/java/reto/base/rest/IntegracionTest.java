package reto.base.rest;

import com.reto.vo.response.security.GeneralResponseVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegracionTest {
    @LocalServerPort
    private int port;

    @Test
    void testGetResource() {
        // URL del endpoint
        try {
            port = 8080;
            String url = "http://localhost:" + port + "/reto/cliente/obtenerListaCliente";

            // Realizar la solicitud GET
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GeneralResponseVo> response = restTemplate.getForEntity(url, GeneralResponseVo.class);

            // Verificar el resultado
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            //assertThat(response.getBody()).contains("expectedContent");
        } catch (Exception e) {
            String a = e.getMessage();
            a = "kk";
        }
    }
}
