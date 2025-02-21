package reto.base.rest;

import com.reto.AppWebApplication;
import com.reto.config.core.AppPostgresConfig;
import com.reto.postgres.repository.ICuentaRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integrationJpa")
@DataJpaTest
@ContextConfiguration(classes = {AppWebApplication.class, AppPostgresConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaJpaTest {
    @Autowired
    private transient ICuentaRepository cuentaRepository;

    @Test
    public void testConsultaBaseDeDatos() {
        Long count = cuentaRepository.obtenerPorId(); // Suponiendo que tu repositorio tiene un método contarElementos()
        assertEquals(1, count); // Suponiendo que esperas 5 elementos en la base de datos
    }
}