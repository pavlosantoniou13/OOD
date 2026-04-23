package presentation;

import application.MaterialService;
import domain.Material;

import java.util.List;

public class MaterialMenu {
    private final MaterialService materialService;
    private final InputParser inputParser;
    private final OutputFormatter formatter;

    public MaterialMenu(MaterialService materialService, InputParser inputParser, OutputFormatter formatter) {
        this.materialService = materialService;
        this.inputParser = inputParser;
        this.formatter = formatter;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            showOptions();
            int choice = inputParser.readMenuChoice(1, 3);

            switch (choice) {
                case 1 -> createMaterial();
                case 2 -> listMaterials();
                case 3 -> back = true;
            }
        }
    }

    private void showOptions() {
        formatter.printHeader("Material Menu");
        formatter.printMenuOption(1, "Create Material");
        formatter.printMenuOption(2, "List Materials");
        formatter.printMenuOption(3, "Back to Main Menu");
        formatter.printDivider();
    }

    private void createMaterial() { // TODO

        
    }

    private void listMaterials() { // TODO
      
    }
}