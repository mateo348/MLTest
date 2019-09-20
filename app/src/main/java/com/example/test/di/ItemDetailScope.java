package com.example.test.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Definición de scope para la activity ItemDetail
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemDetailScope {
}
