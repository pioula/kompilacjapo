package zad1.src.statistics;

public class Statistic {
    private int maxValue, minValue, querries;
    private double sumValue;

    public Statistic() {}

    public void reset() {
        maxValue = 0;
        minValue = Integer.MAX_VALUE;
        sumValue = 0;
        querries = 0;
    }

    public void updateStatistic(int value) {
        maxValue = Math.max(maxValue, value);
        minValue = Math.min(minValue, value);
        sumValue += value;
        querries++;
    }

    @Override
    public String toString() {
        return minValue + "/" + sumValue / querries + "/" + maxValue;
    }
}
