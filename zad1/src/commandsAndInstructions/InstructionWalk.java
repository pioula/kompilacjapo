package zad1.src.commandsAndInstructions;

import zad1.src.board.Field;
import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public class InstructionWalk extends Instruction {
    private static InstructionWalk INSTANCE = new InstructionWalk();

    private InstructionWalk() {}

    public static InstructionWalk getInstance() {
        return INSTANCE;
    }

    @Override
    public void applyInstruction(Rob rob, World world) {
        switch(rob.getDirection()) {
            case Directions.UP -> {
                Field f = world.getBoard().getField(rob.getPosition().getRow() - 1, rob.getPosition().getCol());
                if (rob.eatFood(f)) {
                            world.getBoard().remFoodField();
                        }
                rob.setPosition(f);
            }
            case Directions.DOWN -> {
                Field f = world.getBoard().getField(rob.getPosition().getRow() + 1, rob.getPosition().getCol());
                if (rob.eatFood(
                        world.getBoard().getField(rob.getPosition().getRow() + 1, rob.getPosition().getCol()))) {
                    world.getBoard().remFoodField();
                }
                rob.setPosition(f);
            }
            case Directions.LEFT -> {
                Field f = world.getBoard().getField(rob.getPosition().getRow(), rob.getPosition().getCol() - 1);
                if(rob.eatFood(
                    world.getBoard().getField(rob.getPosition().getRow(), rob.getPosition().getCol() - 1))) {
                    world.getBoard().remFoodField();
                }
                rob.setPosition(f);
            }
            case Directions.RIGHT -> {
                Field f = world.getBoard().getField(rob.getPosition().getRow(), rob.getPosition().getCol() + 1);
                if(rob.eatFood(
                        world.getBoard().getField(rob.getPosition().getRow(), rob.getPosition().getCol() + 1))) {
                    world.getBoard().remFoodField();
                }
                rob.setPosition(f);
            }
        }

        rob.useEnergyForInstruction();
    }

    @Override
    public String toString() {
        return "i";
    }
}
