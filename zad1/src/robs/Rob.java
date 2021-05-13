package zad1.src.robs;

import zad1.src.board.Field;
import zad1.src.commandsAndInstructions.Instruction;

import java.util.ArrayList;
import java.util.Random;

public class Rob {
    private Field position;
    private int energy, age;
    private Directions direction;
    private ArrayList<Instruction> program;

    public Rob(Field position, int energy, ArrayList<Instruction> program) {
        this.position = position;
        this.energy = energy;
        this.program = program;
        this.age = 0;
        //początkowo, każdy rob patrzy w górę planszy
        this.direction = Directions.UP;
    }

    public boolean isAlive() {
        return energy > 0;
    }

    public void nextRound(int roundCost) {
        energy -= roundCost;
    }

    public void raiseAge() {
        age++;
    }

    public int getAge() {
        return age;
    }

    public boolean eatFood(Field field) {
        if (field.isThereFood()) {
            energy += field.getFoodQuality();
            field.clearFood();
            return true;
        }

        return false;
    }

    public Rob multiplyRob(double probAdd, double probRem, double probChange,
                           double partOfParentEnergy, ArrayList <Instruction> instructions) {
        Random r = new Random();
        ArrayList<Instruction> newProgram = new ArrayList<>();

        //removing instruction with index removedIndex. If failed removedIndex = -1 so i != removedIndex is always true
        int removedIndex = Probability.isHappened(probRem) && program.size() > 0 ? r.nextInt(program.size()) : -1;
        for (int i = 0; i < program.size(); i++) {
            if (i != removedIndex)
                newProgram.add(program.get(i));
        }

        if (Probability.isHappened(probAdd))
            newProgram.add(instructions.get(r.nextInt(instructions.size())));

        if (Probability.isHappened(probChange) && newProgram.size() > 0)
            newProgram.set(r.nextInt(newProgram.size()),
                    instructions.get(r.nextInt(instructions.size())));

        int newEnergy = (int)(energy * partOfParentEnergy);
        energy -= newEnergy;
        return new Rob(position, newEnergy, newProgram);
    }

    public void useEnergyForInstruction() {
        energy--;
    }

    public Field getPosition() {
        return position;
    }

    public ArrayList<Instruction> getProgram() {
        return program;
    }

    public int getEnergy() {
        return energy;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setPosition(Field position) {
        this.position = position;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Rob: ");

        if (program.size() == 0)
            s.append("Brak instrukcji");

        for (Instruction instruction : program) {
            s.append(instruction);
        }

        String kierunek = "";
        switch(direction) {
            case UP:
                kierunek = "góra";
                break;
            case DOWN:
                kierunek = "dół";
                break;
            case LEFT:
                kierunek = "lewo";
                break;
            case RIGHT:
                kierunek = "prawo";
                break;
        }

        //if energy = 0, then Rob has just appear.
        return s + ", poz: (" + position.getRow() + ", " + position.getCol() + ")" +
                ", energ: " + energy + ", wiek: " + age + ", kier: " + kierunek;
    }
}
