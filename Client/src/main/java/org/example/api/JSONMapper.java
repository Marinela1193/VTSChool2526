package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class JSONMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T mapJSONObject(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        }catch(Exception e){
            throw new RuntimeException("Error mapping JSON response", e);
        }
    }

    public static <T> Set<T> mapJSONArray(String jsonArrayString, Class<T> clase) {
        try {
            return mapper.readValue(
                    jsonArrayString,
                    mapper.getTypeFactory().constructCollectionType(Set.class, clase)
            );
        }catch (Exception e){
            throw new RuntimeException("Error mapping JSON response", e);
        }
    }

    public static String mapToJSON(Object object) {
        try{
            return mapper.writeValueAsString(object);
        }catch(Exception e){
            throw new RuntimeException("Error mapping JSON response", e);
        }
    }

}
