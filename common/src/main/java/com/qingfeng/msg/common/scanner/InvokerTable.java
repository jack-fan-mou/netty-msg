package com.qingfeng.msg.common.scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InvokerTable {

    private static ConcurrentHashMap<String, Map<String, Invoker>> invokerTable = new ConcurrentHashMap<>();

    public static void addInvoker(String module, String cmd, Invoker invoker){
        Map<String, Invoker> invokerMap = invokerTable.get(module);
        if(invokerMap == null){
            invokerMap = new HashMap<>();
            invokerTable.put(module, invokerMap);
        }
        invokerMap.put(cmd, invoker);
    }
    public static Invoker getInvoker(String module, String cmd){
        Map<String, Invoker> invokerMap = invokerTable.get(module);
        if(invokerMap !=null){
            return invokerMap.get(cmd);
        }
        return null;
    }
}
