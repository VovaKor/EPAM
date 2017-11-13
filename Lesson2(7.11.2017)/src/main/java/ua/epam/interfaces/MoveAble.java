package ua.epam.interfaces;

public interface MoveAble {
    default String move(){
        return "I can move";
    };
}
