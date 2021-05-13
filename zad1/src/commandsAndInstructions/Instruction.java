package zad1.src.commandsAndInstructions;

import zad1.src.robs.Rob;
import zad1.src.simulation.World;

public abstract class Instruction{
    public abstract void applyInstruction(Rob rob, World world);

    @Override
    public abstract String toString();
}
