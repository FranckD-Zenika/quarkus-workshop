package com.zenika.quarkus.workshop.utils;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class UriUtils {

    public static URI buildUri(Class<?> clazz, String path) {
        return UriBuilder.fromResource(clazz)
                .path(path)
                .build();
    }

    private UriUtils() {}
}
