package cn.edu.seu.wh.drools.springboot.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: drools-demo
 * @description: 请求返回类
 * @author: Wang Huan
 * @Github: https://github.com/njustwh2014
 * @create: 2020-06-05 16:34
 **/
@Setter
@Getter
public class JsonResponse {
    private int code;
    private String msg;
    private Object data;

    public JsonResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResponse ok(){
        return new JsonResponse(200,"ok",null);
    }

    public static JsonResponse ok(String msg){
        return new JsonResponse(200,msg,null);
    }

    public static JsonResponse ok(Object data){
        return new JsonResponse(200,"ok",data);
    }

    public static JsonResponse ok(String msg,Object data){
        return new JsonResponse(200,msg,data);
    }




}
