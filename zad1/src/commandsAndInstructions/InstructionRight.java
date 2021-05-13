package zad1.src.commandsAndInstructions;

import zad1.src.robs.Directions;
import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public class InstructionRight extends Instruction {
    private static InstructionRight INSTANCE = new InstructionRight();

    private InstructionRight() {}

    public static InstructionRight getInstance() {
        return INSTANCE;
    }

    @Override
    public void applyInstruction(Rob rob, World world) {
        switch(rob.getDirection()) {
            case UP -> rob.setDirection(Directions.RIGHT);
            case DOWN -> rob.setDirection(Directions.LEFT);
            case LEFT -> rob.setDirection(Directions.UP);
            case RIGHT -> rob.setDirection(Directions.DOWN);
        }

        rob.useEnergyForInstruction();
    }

    @Override
    public String toString() {
        return "p";
    }
}
