package com.dream;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> impl);

    Reflections getScanner();
}
