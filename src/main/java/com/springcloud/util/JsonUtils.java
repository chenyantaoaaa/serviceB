package com.springcloud.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

/**
 * Created by yantao.chen on 2018-08-13.
 */
public class JsonUtils {
    /**
     * 对象转化为ES查询参数json
     * @return
     */
    public static JSONObject obj2EsParams(Object obj){
        JSONObject query = new JSONObject();
        JSONObject match = new JSONObject();
        JSONObject param = JSONObject.fromObject(obj);
        match.put("match",param);
        query.put("query",match);
        return query;
    }

    public static JSONObject obj2EsParamsNoNull(Object model) {
        JSONObject query = new JSONObject();
        JSONObject match = new JSONObject();
        JSONObject param = new JSONObject();
        Field [] fields = model.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            Method m = null;
            String value = null;
            try {
                m = model.getClass().getMethod("get"+name.substring(0,1).toUpperCase()+name.substring(1));
                value = (String) m.invoke(model);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
            if(StringUtils.isNotBlank(value)){
                param.put(name,value);
            }
        }
        match.put("match",param);
        query.put("query",match);
        return query;
    }
}
