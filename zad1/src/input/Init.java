package zad1.src.input;

import zad1.src.commandsAndInstructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import zad1.src.commandsAndInstructions.Commands.*;

public abstract class Init {
    private static void patternsInit(Pattern[] patterns) throws Exception {
        File file = new File("./patterns/commands_patterns.txt");
        try {
            Scanner sc = new Scanner(file);
            int patternCounter = 0;

            while(sc.hasNextLine()) {
                Scanner lineSc = new Scanner(sc.nextLine());
                lineSc.useDelimiter("\n");
                patterns[patternCounter] = Pattern.compile(lineSc.next());
                patternCounter++;
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            throw new Exception("commands_patterns.txt not found!");
        }
    }

    private static void mapCommandsInit(Map<String, Commands> commands) {
        commands.put("ile_tur", Commands.NR_ROUNDS);
        commands.put("rozmiar_planszy_x", Commands.BOARD_SIZE_X);
        commands.put("rozmiar_planszy_y", Commands.BOARD_SIZE_Y);
        commands.put("pocz_ile_robów", Commands.BEGIN_NR_ROBS);
        commands.put("pocz_progr", Commands.BEGIN_PROGR);
        commands.put("pocz_energia", Commands.BEGIN_ENERGY);
        commands.put("ile_daje_jedzenie", Commands.FOOD_QUALITY);
        commands.put("ile_rośnie_jedzenie", Commands.FOOD_GROWTH);
        commands.put("koszt_tury", Commands.ROUND_COST);
        commands.put("pr_powielenia", Commands.PROB_MULTIPLY);
        commands.put("ułamek_energii_rodzica", Commands.PART_OF_PARENT_ENERGY);
        commands.put("limit_powielenia", Commands.MULTIPLY_LIMIT);
        commands.put("pr_usunięcia_instr", Commands.PROB_REM_COMMAND);
        commands.put("pr_dodania_instr", Commands.PROB_ADD_COMMAND);
        commands.put("pr_zmiany_instr", Commands.PROB_CHANGE_COMMAND);
        commands.put("spis_instr", Commands.INSTRUCTIONS);
        commands.put("co_ile_wypisz", Commands.OUTPUT_FREQ);
    }

    private static void mapInstructionsInit(Map<Character, Instruction> instructions) {
        instructions.put('l', InstructionLeft.getInstance());
        instructions.put('p', InstructionRight.getInstance());
        instructions.put('i', InstructionWalk.getInstance());
        instructions.put('j', InstructionEat.getInstance());
        instructions.put('w', InstructionSniff.getInstance());
    }

    protected static void inputInit(Pattern[] patterns,
                                  Map<String, Commands> commands,
                                  Map<Character, Instruction> instructions) throws Exception{
        patternsInit(patterns);
        mapCommandsInit(commands);
        mapInstructionsInit(instructions);
    }
}
