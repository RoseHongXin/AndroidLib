package hx.ui.widgets;

import android.text.TextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rose on 16-8-23.
 */

public class Jackson {

    public static <T> String toStr(T obj){
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result =  mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            if(TextUtils.isEmpty(result)) result = obj.toString();
        }
        return result;
    }

    public static <T> T fromStr(String str, Class<T> clz){
        if(TextUtils.isEmpty(str)) return null;
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(str, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> ArrayList<T> fromStr2List(String str, Class<T> clz){
        if(TextUtils.isEmpty(str)) return null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clz);
            //return mapper.readValue(str, new TypeReference<ArrayList<T>>(){});
            return mapper.readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
