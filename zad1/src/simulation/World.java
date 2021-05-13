package zad1.src.simulation;

import zad1.src.board.Board;
import zad1.src.board.Field;
import zad1.src.commandsAndInstructions.*;
import zad1.src.robs.Probability;
import zad1.src.robs.Rob;
import zad1.src.statistics.RoundStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class World {
    private static final int numberOfCommands = 15;
    private int nrRounds, roundCost, multiplyLimit, outputFreq, beginEnergy;
    private double probMultiply, partOfParentEnergy, probRem, probAdd, probChangeCommand;
    private final Board board;
    private ArrayList<Instruction> baseProgram, instructions;
    private ArrayList<Rob> robs;
    private RoundStatistic statistic;

    private static boolean isTheNumberOfCommandsCorrect(ArrayList<Command> commands) {
        commands.sort(Comparator.comparing(Command::getPriority));

        int commandsSize = commands.size();
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getCommand() == Commands.BOARD_SIZE_X ||
                commands.get(i).getCommand() == Commands.BOARD_SIZE_Y) {
                commandsSize--;
            }
            else if (i + 1 < commands.size() &&
                    commands.get(i).getCommand() == commands.get(i+1).getCommand()) {
                return false;
            }
        }

        return commandsSize == numberOfCommands;
    }

    public World(Board board, ArrayList<Command> commands) throws Exception{
        if (!isTheNumberOfCommandsCorrect(commands))
            throw new Exception("Wrong number of commands!");
        this.board = board;
        robs = new ArrayList<>();

        for (Command command : commands) {
            command.applyCommand(this);
        }

        statistic = new RoundStatistic();
    }

    public void createRobs(int numberOfRobs) {
        Random r = new Random();
        for (int i = 0; i < numberOfRobs; i++) {
            Field f = board.getField(r.nextInt(board.getNumberOfRows()), r.nextInt(board.getNumberOfCols()));
            robs.add(new Rob(f, beginEnergy, baseProgram));
        }
    }

    public void simulation() {
        System.out.println(this);
        int howManyLeftToOutput = outputFreq;

        for (int i = 0; i < nrRounds; i++) {
            if (i != 0)
                board.nextRound();

            howManyLeftToOutput--;
            Collections.shuffle(robs);
            ArrayList<Rob> newRobs = new ArrayList<>();

            statistic.resetStatistics();

            for(Rob rob : robs) {
                for (Instruction instruction : rob.getProgram()) {
                    if (rob.getEnergy() <= 0)
                        break;
                    instruction.applyInstruction(rob, this);
                }

                if (rob.isAlive()) {
                    if (rob.getEnergy() >= multiplyLimit && Probability.isHappened(probMultiply)) {
                        Rob child = rob.multiplyRob(probAdd, probRem, probChangeCommand,
                                partOfParentEnergy, instructions);
                        child.nextRound(roundCost);
                        if (child.isAlive()) {
                            newRobs.add(child);
                            statistic.updateStatistics(child);
                        }
                    }

                    rob.raiseAge();
                    rob.nextRound(roundCost);

                    if (rob.isAlive()) {
                        newRobs.add(rob);
                        statistic.updateStatistics(rob);
                    }
                }
            }

            robs = newRobs;

            if (howManyLeftToOutput == 0) {
                System.out.println(this);
                howManyLeftToOutput = outputFreq;
            }

            if (newRobs.size() == 0) {
                System.out.println("Wszystkie roby nie żyją!!!");
                break;
            }

            System.out.println(i + 1 + ", rob: " + robs.size() + ", żyw: " + board.getNumberOfFoodFields() +
                    ", " + statistic);
        }

        System.out.println(this);
    }

    public void setBeginEnergy(int beginEnergy) {
        this.beginEnergy = beginEnergy;
    }

    public void setNrRounds(int nrRounds) {
        this.nrRounds = nrRounds;
    }

    public void setBaseProgram(ArrayList<Instruction> baseProgram) {
        this.baseProgram = baseProgram;
    }

    public void setRoundCost(int roundCost) {
        this.roundCost = roundCost;
    }

    public void setProbMultiply(double probMultiply) {
        this.probMultiply = probMultiply;
    }

    public void setPartOfParentEnergy(double partOfParentEnergy) {
        this.partOfParentEnergy = partOfParentEnergy;
    }

    public void setMultiplyLimit(int multiplyLimit) {
        this.multiplyLimit = multiplyLimit;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void setOutputFreq(int outputFreq) {
        this.outputFreq = outputFreq;
    }

    public void setProbAdd(double probAdd) {
        this.probAdd = probAdd;
    }

    public void setProbRem(double probRem) {
        this.probRem = probRem;
    }

    public void setProbChangeCommand(double probChangeCommand) {
        this.probChangeCommand = probChangeCommand;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder robsString = new StringBuilder();
        for (int i = 0 ; i < robs.size(); i++) {
            robsString.append(robs.get(i));
            if (i + 1 != robs.size())
                robsString.append('\n');
        }

        return "PLansza\n" + board.toString() + "\nKoniec Planszy\n" + robsString;
    }
}
