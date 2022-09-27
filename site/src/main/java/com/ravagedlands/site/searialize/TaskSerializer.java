package com.ravagedlands.site.searialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.ravagedlands.site.model.Task;

public class TaskSerializer extends StdSerializer<Task> {
	
	private static final long serialVersionUID = 1L;
    public TaskSerializer(Class<Task> t) {
        super(t);
    }
    public TaskSerializer() {
        this(null);
    }
    @Override
    public void serialize(Task task, JsonGenerator jg, SerializerProvider sp)
            throws IOException, JsonGenerationException {
        jg.writeStartObject();
        jg.writeStringField("name", task.getName());
        jg.writeStringField("description", task.getDescription());
        jg.writeBooleanField("done", task.isDone());
  
        jg.writeEndObject();
    }

}
