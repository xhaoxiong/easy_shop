package utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtils {

    public void returnJson(HttpServletResponse response,int status , String message) {
        JSONObject data = new JSONObject();

        data.put("message", message);
        data.put("status", status);
        try {
            response.getWriter().print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnSuccess(HttpServletResponse response, String key, Object datas, String message) {
        JSONObject data = new JSONObject();

        if (message != "") {
            data.put("message", message);
        } else {
            data.put("message", "success");
        }
        data.put(key, datas);
        data.put("status", 10000);
        try {
            response.getWriter().print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
