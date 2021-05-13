package zad1.src.board;

public abstract class Field implements FieldInterface{
    protected int foodGrowth;
    protected int col;
    protected int row;
    protected int foodQuality;

    public Field(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public void setFoodGrowth(int foodGrowth) {
        this.foodGrowth = foodGrowth;
    }

    public int getFoodQuality() {
        return foodQuality;
    }

    public void setFoodQuality(int foodQuality) {
        this.foodQuality = foodQuality;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
