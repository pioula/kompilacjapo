package zad1.src.commandsAndInstructions;

import zad1.src.robs.Directions;
import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public class InstructionSniff extends Instruction {
    private static InstructionSniff INSTANCE = new InstructionSniff();

    private InstructionSniff() {}

    public static InstructionSniff getInstance() {
        return INSTANCE;
    }

    @Override
    public void applyInstruction(Rob rob, World world) {
        Directions direction = world.getBoard().findFoodCross(rob.getPosition());
        if (direction != null) {
            rob.setDirection(direction);
        }

        rob.useEnergyForInstruction();
    }

    @Override
    public String toString() {
        return "w";
    }
}
