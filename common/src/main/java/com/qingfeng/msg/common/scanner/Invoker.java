package com.qingfeng.msg.common.scanner;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class Invoker {

    private Method method;

    private Object target;

    public static Invoker createInvoker(Method method, Object target){
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }
    public Object invoke(Object... params){
        try {
            return this.method.invoke(target, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
