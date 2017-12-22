package com.korobko.interfaces;

public interface SwimAble {
    default String swim(){
        return "I can swim";
    };
}
