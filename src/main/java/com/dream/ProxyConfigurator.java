package com.dream;

public interface ProxyConfigurator {
    Object replaceWithIfNeeded(Object t, Class implClass);
}
