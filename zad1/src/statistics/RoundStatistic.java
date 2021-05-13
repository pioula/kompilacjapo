package zad1.src.statistics;

import zad1.src.robs.Rob;

public class RoundStatistic {
    Statistic energy, age, programLength;

    public RoundStatistic() {
        energy = new Statistic();
        age = new Statistic();
        programLength = new Statistic();
    }

    public void resetStatistics() {
        energy.reset();
        age.reset();
        programLength.reset();
    }

    public void updateStatistics(Rob rob) {
        energy.updateStatistic(rob.getEnergy());
        age.updateStatistic(rob.getAge());
        programLength.updateStatistic(rob.getProgram().size());
    }

    @Override
    public String toString() {
        return "prg: " + programLength + ", energ: " + energy + ", wiek: " + age;
    }
}
