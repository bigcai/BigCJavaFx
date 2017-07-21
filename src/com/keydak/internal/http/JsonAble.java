package com.keydak.internal.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * User: vk
 * Date: 2017/4/28
 * Time: 17:56
 * Description:
 */
public class JsonAble<T> {

    public String toJson( T t ) {
        return new Gson().toJson( t );
    }

    public T fromJson( String json, Class<T> clazz ) {
        return new Gson().fromJson( json, clazz );
    }

    public List<T> arrayFromJson(String json, Class<T> clazz) {

        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        List<T> list = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            list.add(new Gson().fromJson(jsonObject, clazz ));
        }
        return list;
    }

    public String toJsonArray( List<T> ts ) {
        return new Gson().toJson( ts );
    }
}
