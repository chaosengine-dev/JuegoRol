package tpfinal.persistencia;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PersistenciaJson {
    public <T> void serializar(T objeto, String pathJson) {
        File file = new File(pathJson);
        ObjectMapper mapper = new ObjectMapper();

        try {
            try {
                mapper.writeValue(file, objeto);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }

    }

    public <T> T deserializar(String pathJson, Class<T> clase) {

        File file = new File(pathJson);
        ObjectMapper mapper = new ObjectMapper();
        T objeto = null;

        try {
            objeto = mapper.readValue(file, clase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            return objeto;
        }
    }
}
