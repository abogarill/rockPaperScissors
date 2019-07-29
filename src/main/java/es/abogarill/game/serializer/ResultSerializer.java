package es.abogarill.game.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import es.abogarill.game.Result;
import java.io.IOException;

/**
 * Result JSON Serializer
 * @author abogarill
 */
public class ResultSerializer  extends StdSerializer {

    public ResultSerializer() {
        super(Result.class);
    }
 
    public ResultSerializer(Class t) {
        super(t);
    }    
    
    @Override
    public void serialize(Object t, JsonGenerator jg, SerializerProvider sp) 
            throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeFieldName("result");
        jg.writeString(((Result)t).toString());
        jg.writeEndObject();        
    }
    
}
