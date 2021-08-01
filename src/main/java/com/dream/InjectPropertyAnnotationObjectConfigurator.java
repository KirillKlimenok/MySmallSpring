package com.dream;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {
    Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        this.propertiesMap = lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class<?> impClass = t.getClass();
        for (Field field : impClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            if (annotation != null) {
                String value;
                field.setAccessible(true);
                if (annotation.value().isEmpty()) {
                    value = propertiesMap.get(field.getName());
                } else {
                    value = propertiesMap.get(annotation.value());
                }
                field.set(t, value);
            }
        }
    }
}
