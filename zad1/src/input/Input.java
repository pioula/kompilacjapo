package zad1.src.input;

import zad1.src.board.Board;
import zad1.src.commandsAndInstructions.*;
import zad1.src.simulation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
    private static final int numberOfCommands = 17;
    private static final Pattern boardPattern = Pattern.compile("^[ x]+$");

    private Pattern[] patterns;
    private Map<String, Commands> commands;
    private Map<Character, Instruction> instructions;

    public Input() throws Exception {
        patterns = new Pattern[numberOfCommands];
        commands = new HashMap<>();
        instructions = new HashMap<>();
        Init.inputInit(patterns, commands, instructions);
    }

    private Board readBoard(String boardPath) throws Exception {
        try {
            Scanner sc = new Scanner(new File(boardPath));

            int numberOfCols = 0;
            ArrayList<String> rows = new ArrayList<>();

            while(sc.hasNextLine()) {
                Scanner lineSc = new Scanner(sc.nextLine());
                lineSc.useDelimiter("\0");
                String row = lineSc.next();

                if (row.length() == 0 ||
                        !boardPattern.matcher(row).matches() ||
                        (row.length() != numberOfCols && numberOfCols != 0))
                    throw new Exception("Wrong board!");

                numberOfCols = row.length();
                rows.add(row);
            }

            sc.close();
            return new Board(rows);
        }
        catch (FileNotFoundException e) {
            throw new Exception("Board not found!");
        }
    }

    private ArrayList<Instruction> recognizeInstructions(String instructionsString) throws Exception {
        ArrayList<Instruction> instructionList = new ArrayList<>();
        for (int i = 0; i < instructionsString.length(); i++) {
            Instruction instr = instructions.get(instructionsString.charAt(i));

            if (instr == null)
                throw new Exception("Wrong instruction!");

            instructionList.add(instr);
        }
        return instructionList;
    }

    private ArrayList<Command> readParameters(String parametersPath) throws Exception {
        try {
            Scanner sc = new Scanner(new File(parametersPath));

            ArrayList<Command> commandLines = new ArrayList<>();

            while(sc.hasNextLine()) {
                Scanner isCorrect = new Scanner(sc.nextLine());
                isCorrect.useDelimiter("\n");
                String commandLine = isCorrect.next();
                boolean matched = false;

                for (Pattern pattern : patterns) {
                    matched = pattern.matcher(commandLine).matches();
                    if (matched)
                        break;
                }

                if (!matched)
                    throw new Exception("Wrong Command");

                Scanner lineSc = new Scanner(commandLine);
                lineSc.useDelimiter(" ");

                Commands command = commands.get(lineSc.next());
                Parameter arg = null;

                if (lineSc.hasNextInt())
                    arg = new Parameter(lineSc.nextInt());

                if (lineSc.hasNextDouble())
                    arg = new Parameter(lineSc.nextDouble());

                if (lineSc.hasNext())
                    arg = new Parameter(recognizeInstructions(lineSc.next()));

                if (arg == null)
                    return null;

                commandLines.add(new Command(command, arg));
            }

            sc.close();
            return commandLines;
        }
        catch (FileNotFoundException e) {
            throw new Exception("parametry.txt not found!");
        }
    }

    public World readInput(String boardPath, String parametersPath) throws Exception {
        Board board = readBoard(boardPath);
        if (board == null)
            return null;

        ArrayList<Command> parameters = readParameters(parametersPath);
        if (parameters == null)
            return null;

        return new World(board, parameters);
    }
}
