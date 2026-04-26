package presentation;

import application.MaterialService;


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

    private void createMaterial() { 
        

        String name = inputParser.readString("Enter material name: ");
        Double Impactvalue = inputParser.readDouble("Enter material impact value: ");
        String recyclingInstruction = inputParser.readString("Enter recycling instruction: ");
        try {
            materialService.addMaterial(name, Impactvalue, recyclingInstruction);
            formatter.printSuccess("Material created successfully.");
        } catch (Exception e) {
            formatter.printError("Failed to create material: " + e.getMessage());
        }
    }

    private void listMaterials() { // TODO: Implement listing materials
        formatter.printHeader("Materials List");
        materialService.getAllMaterials().forEach(material -> {
            System.out.println("Name: " + material.getName());
            System.out.println("Impact Value: " + material.getEnvironmentalImpactValue());
            System.out.println("Recycling Instruction: " + material.getRecyclingInstruction());
            formatter.printDivider();
        });

      
    }
}