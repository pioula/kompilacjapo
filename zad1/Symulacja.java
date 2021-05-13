package zad1;

import zad1.src.input.Input;
import zad1.src.simulation.World;

public class Symulacja {
    public static void main(String[] args) throws Exception {
        Input input = new Input();
        World world = input.readInput(args[0], args[1]);
        world.simulation();
    }
}
