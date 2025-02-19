package ec.gob.seps.base.rest;

import com.reto.AppWebApplication;
import com.reto.postgres.repository.ICuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes= AppWebApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaJpaTest {
    @Autowired
    private transient ICuentaRepository cuentaRepository;

    @Test
    public void testConsultaBaseDeDatos() {
        Long count = cuentaRepository.obtenerPorId(); // Suponiendo que tu repositorio tiene un método contarElementos()
        assertEquals(5, count); // Suponiendo que esperas 5 elementos en la base de datos
    }
}