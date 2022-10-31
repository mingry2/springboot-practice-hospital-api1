package com.springboot.hello01.parser;

public interface Parser<T> {
    T parse(String str);

}

