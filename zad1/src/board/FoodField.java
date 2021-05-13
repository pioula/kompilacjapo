package zad1.src.board;

public class FoodField extends Field{
    private int timeToGrowFood;

    public FoodField(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isThereFood() {
        return timeToGrowFood == 0;
    }

    @Override
    public boolean nextRound() {
        if (timeToGrowFood - 1 == 0) {
            timeToGrowFood = 0;
            return true;
        }
        else {
            timeToGrowFood = Math.max(0, timeToGrowFood - 1);
            return false;
        }
    }

    @Override
    public void clearFood() {
        assert timeToGrowFood == 0 : "THERE IS NO FOOD YET!";
        timeToGrowFood = foodGrowth;
    }

    @Override
    public String toString() {
        if (timeToGrowFood == 0)
            return "x";
        else
            return " ";
    }

}
