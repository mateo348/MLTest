package com.example.test.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Definición de scope para la activity ItemsList
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemsListScope {
}
