package com.kevadiyakrunalk.bindValidation.forJava;

public interface Rule<T> {

    boolean isValid(T t);

    String getErrorMessage();
}
