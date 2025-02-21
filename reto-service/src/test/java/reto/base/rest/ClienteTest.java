package reto.base.rest;

import com.reto.postgres.entity.ClienteEntity;
import com.reto.postgres.entity.PersonaEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integrationCliente")
public class ClienteTest {
    @Test
    public void testClienteEntity() {
        // Crear un objeto Cliente
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1);
        cliente.setPersona(new PersonaEntity().toBuilder().id(1).nombre("Juan Pérez").genero("H").identificacion("1804265146").build());
        cliente.setContrasena("Prueba123");

        // Verificar que los valores se asignaron correctamente
        assertEquals(1, cliente.getId());
        assertEquals("Juan Pérez", cliente.getPersona().getNombre());
        assertEquals("Prueba123", cliente.getContrasena());
    }
}
