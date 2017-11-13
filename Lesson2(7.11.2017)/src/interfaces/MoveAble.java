package interfaces;

public interface MoveAble {
    default String move(){
        return "I can move";
    };
}
