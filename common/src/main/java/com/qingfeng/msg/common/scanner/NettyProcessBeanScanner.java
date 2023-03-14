package com.qingfeng.msg.common.scanner;

import com.qingfeng.msg.common.annotation.Cmd;
import com.qingfeng.msg.common.annotation.Module;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class NettyProcessBeanScanner implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Module module = clazz.getAnnotation(Module.class);
        if(module !=null){
            Method[] methods = clazz.getMethods();
            if(methods !=null && methods.length > 0){
                for(Method method: methods){
                    Cmd cmd = method.getAnnotation(Cmd.class);
                    if(cmd !=null){
                        Invoker invoker = InvokerTable.getInvoker(module.module(), cmd.cmd());
                        if(invoker == null){
                            invoker = Invoker.createInvoker(method, bean);
                            InvokerTable.addInvoker(module.module(), cmd.cmd(), invoker);
                        }else{
                            System.out.println(String.format("module=%s, cmd=%s: 已存在", module, cmd));
                        }
                    }
                }
            }
        }
        return bean;
    }
}
