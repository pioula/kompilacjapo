package zad1.src.commandsAndInstructions;

import zad1.src.robs.Directions;
import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public class InstructionLeft extends Instruction {
    private static InstructionLeft INSTANCE = new InstructionLeft();

    private InstructionLeft() {}

    public static InstructionLeft getInstance() {
        return INSTANCE;
    }

    @Override
    public void applyInstruction(Rob rob, World world) {
        switch(rob.getDirection()) {
            case UP -> rob.setDirection(Directions.LEFT);
            case DOWN -> rob.setDirection(Directions.RIGHT);
            case LEFT -> rob.setDirection(Directions.DOWN);
            case RIGHT -> rob.setDirection(Directions.UP);
        }

        rob.useEnergyForInstruction();
    }

    @Override
    public String toString() {
        return "l";
    }
}
