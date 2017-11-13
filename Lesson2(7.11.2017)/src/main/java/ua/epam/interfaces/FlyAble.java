package ua.epam.interfaces;

public interface FlyAble {
    default String fly(){
        return "I can fly";
    };
}
