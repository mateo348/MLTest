package com.example.test.di.ItemDetails;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Dagger2: Definición de scope para la activity ItemDetail
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemDetailScope {
}
