package reto.base.rest;

import com.reto.vo.response.security.EntidadResponseVo;
import com.reto.vo.response.security.GeneralResponseVo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegracionTest {
    @LocalServerPort
    private int port;
    private static final Logger LOG = LoggerFactory.getLogger(IntegracionTest.class);

    @Test
    void testGetResource() {
        // URL del endpoint
        try {
            port = 8080;
            String url = "http://localhost:" + port + "/reto/entidad/obtenerListaEntidad";

            // Realizar la solicitud GET
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GeneralResponseVo> response = restTemplate.getForEntity(url, GeneralResponseVo.class);
            // Verificar el resultado
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            GeneralResponseVo responseEntidad = response.getBody();
            List<EntidadResponseVo> listEntidad = (List<EntidadResponseVo>) responseEntidad.getObject();
            EntidadResponseVo cliente = listEntidad.get(0);
            String url2 = "http://localhost:" + port + "/reto/movimiento/obtenerMovimientosPorEntidad?idEntidad=" + cliente.getId();

            // Realizar la solicitud GET
            RestTemplate restTemplate2 = new RestTemplate();
            ResponseEntity<GeneralResponseVo> response2 = restTemplate2.getForEntity(url2, GeneralResponseVo.class);
            assertThat(response2.getStatusCode().is2xxSuccessful()).isTrue();
        } catch (Exception e) {
            LOG.error("Error en test integración: " + e.getMessage());
        }
    }
}
