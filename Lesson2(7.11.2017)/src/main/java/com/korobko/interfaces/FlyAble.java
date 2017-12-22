package com.korobko.interfaces;

public interface FlyAble {
    default String fly(){
        return "I can fly";
    };
}
