package com.dream;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> impClass) {
        T t = create(impClass);

        configure(t);

        invokeInit(impClass, t);

        t = wrapWithProxyIfNeeded(impClass, t);


        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> impClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithIfNeeded(t, impClass);
        }
        return t;
    }

    private <T> void invokeInit(Class<T> impClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : impClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> impClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = impClass.getDeclaredConstructor().newInstance();
        return t;
    }

}
