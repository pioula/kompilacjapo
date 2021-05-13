package zad1.src.commandsAndInstructions;

import zad1.src.simulation.World;

public class Command {
    private final Commands command;
    private final Parameter parameter;
    private int priority;

    private void getPriority(Commands command) {
        switch(command) {
            case NR_ROUNDS:
                priority = 0;
                break;
            case BOARD_SIZE_X:
                priority = 1;
                break;
            case BOARD_SIZE_Y:
                priority = 2;
                break;
            case BEGIN_ENERGY:
                priority = 3;
                break;
            case BEGIN_NR_ROBS:
                priority = 16;
                break;
            case BEGIN_PROGR:
                priority = 15;
                break;
            case FOOD_QUALITY:
                priority = 4;
                break;
            case FOOD_GROWTH:
                priority = 5;
                break;
            case ROUND_COST:
                priority = 6;
                break;
            case PROB_MULTIPLY:
                priority = 7;
                break;
            case PART_OF_PARENT_ENERGY:
                priority = 8;
                break;
            case MULTIPLY_LIMIT:
                priority = 9;
                break;
            case PROB_REM_COMMAND:
                priority = 10;
                break;
            case PROB_ADD_COMMAND:
                priority = 11;
                break;
            case INSTRUCTIONS:
                priority = 14;
                break;
            case PROB_CHANGE_COMMAND:
                priority = 12;
                break;
            case OUTPUT_FREQ:
                priority = 13;
                break;
        }
    }

    public Command(Commands command, Parameter parameter) {
        this.command = command;
        this.parameter = parameter;
        getPriority(command);
    }

    public void applyCommand(World world) throws Exception{
        switch (command) {
            case NR_ROUNDS:
                world.setNrRounds(parameter.getIntParam());
                break;
            case BOARD_SIZE_X:
                world.getBoard().setNumberOfCols(parameter.getIntParam());
                break;
            case BOARD_SIZE_Y:
                world.getBoard().setNumberOfRows(parameter.getIntParam());
                break;
            case BEGIN_ENERGY:
                world.setBeginEnergy(parameter.getIntParam());
                break;
            case BEGIN_NR_ROBS:
                world.createRobs(parameter.getIntParam());
                break;
            case BEGIN_PROGR:
                world.setBaseProgram(parameter.getEnumParam());
                break;
            case FOOD_QUALITY:
                world.getBoard().setFoodQuality(parameter.getIntParam());
                break;
            case FOOD_GROWTH:
                world.getBoard().setFoodGrowth(parameter.getIntParam());
                break;
            case ROUND_COST:
                world.setRoundCost(parameter.getIntParam());
                break;
            case PROB_MULTIPLY:
                world.setProbMultiply(parameter.getDoubleParam());
                break;
            case PART_OF_PARENT_ENERGY:
                world.setPartOfParentEnergy(parameter.getDoubleParam());
                break;
            case MULTIPLY_LIMIT:
                world.setMultiplyLimit(parameter.getIntParam());
                break;
            case PROB_REM_COMMAND:
                world.setProbRem(parameter.getDoubleParam());
                break;
            case PROB_ADD_COMMAND:
                world.setProbAdd(parameter.getDoubleParam());
                break;
            case INSTRUCTIONS:
                world.setInstructions(parameter.getEnumParam());
                break;
            case PROB_CHANGE_COMMAND:
                world.setProbChangeCommand(parameter.getDoubleParam());
                break;
            case OUTPUT_FREQ:
                world.setOutputFreq(parameter.getIntParam());
                break;
        }
    }

    public Commands getCommand() {
        return command;
    }

    public int getPriority() {
        return priority;
    }
}
