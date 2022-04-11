package com.example.search.filter;

public class CorelationIdFromThread {
    private static final ThreadLocal<String> id = new ThreadLocal<String>();
    public static final String CO_RELATION_ID = "cid";

    public static String getId(){
        if(id == null){
            return null;
        }
        return id.get();
    }

    public static void setId(String correlationId){
        if(id != null){
            id.set(correlationId);
        }

    }
}
