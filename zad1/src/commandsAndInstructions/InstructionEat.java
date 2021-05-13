package zad1.src.commandsAndInstructions;

import zad1.src.board.Field;
import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public class InstructionEat extends Instruction {
    private static InstructionEat INSTANCE = new InstructionEat();

    private InstructionEat() {}

    public static InstructionEat getInstance() {
        return INSTANCE;
    }

    @Override
    public void applyInstruction(Rob rob, World world) {
        Field p = world.getBoard().findFood(rob.getPosition());
        if (p.getRow() != -1) {
            if(rob.eatFood(p)) {
                world.getBoard().remFoodField();
            }
            rob.setPosition(p);
        }

        rob.useEnergyForInstruction();
    }

    @Override
    public String toString() {
        return "j";
    }
}
