package com.handson.basic.utils;

import org.mockito.Mockito;

import java.security.Principal;

import static org.mockito.Mockito.mock;

public class MockedPrincipal {

    public static Principal toPrincipal(String userName) {
        Principal principal = mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn(userName);
        return principal;
    }

}
