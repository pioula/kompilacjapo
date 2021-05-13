package zad1.src.board;

public interface FieldInterface {
    boolean isThereFood();

    void clearFood();

    //returns true if the food grows on this field, else returns false
    boolean nextRound();

    @Override
    String toString();
}
