package zad1.src.board;

public class EmptyField extends Field{
    public EmptyField(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isThereFood() {
        return false;
    }

    @Override
    public boolean nextRound() {
        return false;
    }

    @Override
    public void clearFood() {
        assert false : "THERE IS NO FOOD!";
    }

    @Override
    public String toString() {
        return " ";
    }
}
