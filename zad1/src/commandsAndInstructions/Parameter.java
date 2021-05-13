package zad1.src.commandsAndInstructions;

import java.util.ArrayList;

public class Parameter {
    private double doubleParam;
    private int intParam;
    private ArrayList<Instruction> enumParam;

    public Parameter(double param) {
        doubleParam = param;
    }

    public Parameter(int param) {
        intParam = param;
        doubleParam = param;
    }

    public Parameter(ArrayList<Instruction> param) {
        enumParam = param;
    }

    public double getDoubleParam() {
        return doubleParam;
    }

    public int getIntParam() {
        return intParam;
    }

    public ArrayList<Instruction> getEnumParam() {
        return enumParam;
    }
}
