import java.util.List;

public class Recipe {
    private String name;
    private List<String> requiredIngredients;
    private String instructions;
    private String category;

    public Recipe(String name, List<String> requiredIngredients, String instructions, String category) {
        this.name = name;
        this.requiredIngredients = requiredIngredients;
        this.instructions = instructions;
        this.category = category;
    }

    // Getters
    public String getName() {
        return name;
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

    /**
     * Displays the full recipe details.
     */
    public void displayRecipe() {
        System.out.println("\n\n--- 🍽️ RECIPE: " + name.toUpperCase() + " ---");
        System.out.println("Category: " + category);

        System.out.println("\n** INGREDIENTS NEEDED **");
        for (String ingredient : requiredIngredients) {
            System.out.println("* " + ingredient);
        }

        System.out.println("\n** COOKING INSTRUCTIONS **");
        System.out.println(instructions);

        System.out.println("-----------------------------------\n");
    }
}