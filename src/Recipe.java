import java.util.List;

public class Recipe extends FoodItem {
    private List<String> requiredIngredients;
    private String instructions;
    private String category;

    public Recipe(String name, List<String> requiredIngredients, String instructions, String category) {
        super(name);
        this.requiredIngredients = requiredIngredients;
        this.instructions = instructions;
        this.category = category;
    }

    public List<String> getRequiredIngredients() {
        return requiredIngredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getCategory() {
        return category;
    }

    public void displayRecipe() {
        displayInfo();
    }

    @Override
    public void displayInfo() {
        System.out.println("\n              =============================================");
        System.out.println("              |        [*] RECIPE: " + String.format("%-24s |", name.toUpperCase()));
        System.out.println("              =============================================");
        System.out.println("              |  Category: " + String.format("%-34s |", category));
        System.out.println("              =============================================");
        
        System.out.println("\n                  [+] INGREDIENTS:");
        System.out.println("                  -----------------");
        for (String ingredient : requiredIngredients) {
            System.out.println("                    * " + ingredient);
        }

        System.out.println("\n                  [*] INSTRUCTIONS:");
        System.out.println("                  -----------------");
        // format instructions
        String[] steps = instructions.split("\\d+\\.");
        for (int i = 1; i < steps.length; i++) {
            System.out.println("                    " + i + ". " + steps[i].trim());
        }
        System.out.println("\n              =============================================\n");
    }
}