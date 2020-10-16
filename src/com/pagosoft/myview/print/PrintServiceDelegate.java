/*
 * Copyright (c) 2010 Patrick Gotthardt. All rights reserved.
 */

package com.pagosoft.myview.print;

import com.google.inject.BindingAnnotation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by IntelliJ IDEA.
 * User: pago
 * Date: 04.01.2010
 * Time: 22:47:28
 * To change this template use File | Settings | File Templates.
 */
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
public @interface PrintServiceDelegate {
}
