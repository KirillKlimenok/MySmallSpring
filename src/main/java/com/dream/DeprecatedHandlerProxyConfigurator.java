package com.dream;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithIfNeeded(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, (InvocationHandler) (o, method, objects) -> getInvocationLogic(t, method, objects));
            }


            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> getInvocationLogic(t, method, args));
        } else {
            return t;
        }
    }

    private Object getInvocationLogic(Object t, Method method, Object[] objects) throws IllegalAccessException, InvocationTargetException {
        System.out.println("что ж ты делаешь урод!! ");
        return method.invoke(t, objects);
    }

}
