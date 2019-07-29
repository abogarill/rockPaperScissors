package es.abogarill.game.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import es.abogarill.game.Result;
import es.abogarill.game.Round;
import java.io.IOException;

/**
 * Round JSON Serializer
 * @author abogarill
 */
public class RoundSerializer  extends StdSerializer {

    public RoundSerializer() {
        super(Result.class);
    }
 
    public RoundSerializer(Class t) {
        super(t);
    }    
    
    @Override
    public void serialize(Object t, JsonGenerator jg, SerializerProvider sp) 
            throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeFieldName("player1");
        jg.writeString(((Round)t).getPlayer1().toString());
        jg.writeFieldName("player2");
        jg.writeString(((Round)t).getPlayer2().toString());
        jg.writeFieldName("result");
        jg.writeString(((Round)t).getResult().toString());
        jg.writeEndObject();        
    }
    
}
