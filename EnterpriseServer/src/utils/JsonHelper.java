/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author jaspertomas
 */
public class JsonHelper {
    public static void main(String args[])
    {
        String json="{\"name\":{\"first\":\"Joe\",\"last\":\"Sixpack\"},\"gender\":\"MALE\",\"verified\":false,\"userImage\":\"YWJjZA==\"}";
        try {
            Map<String,Object> map = toMap(json);
            for(String k:map.keySet())
            {
                System.out.println(k);
                System.out.println(map.get(k));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    public static Map<String,Object> toMap(String json) throws IOException {
        Map<String,Object> userData = mapper.readValue(json, Map.class);
//            Map<String,String> name=(Map<String,String>)userData.get("name");
        return userData;
    }
    public static String toJson(Map<String,Object> userData) throws IOException {
        return mapper.writeValueAsString(userData);
    }
}
