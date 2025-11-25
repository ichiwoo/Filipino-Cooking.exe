import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner;
    private Cookbook cookbook;
    // Polymorphism: List can hold Ingredient objects
    private List<Ingredient> inventory; 

    public Main() {
        this.scanner = new Scanner(System.in);
        this.cookbook = new Cookbook();
        this.inventory = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        displayMessage();
        mainMenu();
    }

    private void displayMessage() {
        System.out.println("==============================================");
        System.out.println("🇵🇭 FILIPINO COOKING ASSISTANT v1.0 (OOP Edition)");
        System.out.println("==============================================");
    }

    // --- Main Menu Loop ---
    private void mainMenu() {
        String choice = "";
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Start (tara luto tayo!)");
            System.out.println("2. Check my inventory");
            System.out.println("3. View full recipe catalog");
            System.out.println("4. help/about");
            System.out.println("5. exit (byebye)");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextLine().trim();
            System.out.println(); // Newline for clean output

            switch (choice) {
                case "1":
                    startCooking();
                    break;
                case "2":
                    checkInventory();
                    break;
                case "3":
                    viewRecipeCatalog();
                    break;
                case "4":
                    displayHelp();
                    break;
                case "5":
                    System.out.println("Salamat! Ingat at Kain Na! 👋");
                    choice = "exit";
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    // --- 1. Start Cooking ---
    private void startCooking() {
        inventory.clear(); // Clear old ingredients for a new session
        System.out.println("--- 🔪 START COOKING ---");
        System.out.println("Please enter the ingredients you have right now, separated by commas (e.g., chicken, soy sauce, garlic, onion).");
        System.out.println("Type 'done' when you are finished.");

        System.out.print("Input Ingredients: ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty() || input.trim().toLowerCase().equals("done")) {
            System.out.println("Nothing entered. Returning to Main Menu.");
            return;
        }

        String[] parts = input.split(",");
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                inventory.add(new Ingredient(part));
            }
        }
        
        System.out.println("\n(Transition) Processing ingredients..... 🔄");
        processIngredients();
    }

    private void processIngredients() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty. Cannot suggest a meal.");
            return;
        }

        // Convert inventory to a list of ingredient names for the Cookbook search
        List<String> userIngredientsNames = inventory.stream()
            .map(Ingredient::getName)
            .collect(Collectors.toList());

        // Abstraction: Cookbook handles the matching logic
        Map<Recipe, Integer> results = cookbook.findPossibleRecipes(userIngredientsNames);

        System.out.println("\n--- 🌟 RESULTS: WHAT YOU CAN COOK RIGHT NOW! ---");
        
        // Filter and sort results
        Map<Recipe, Integer> sortedResults = results.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<String> recipeChoices = new ArrayList<>();
        int recipeCount = 1;

        for (Map.Entry<Recipe, Integer> entry : sortedResults.entrySet()) {
            Recipe recipe = entry.getKey();
            int missing = entry.getValue();
            recipeChoices.add(recipe.getName());
            
            String status;
            if (missing == 0) {
                status = "**PERFECT!** (All ingredients found!) 🎉";
            } else if (missing <= 2) {
                status = "Missing " + missing + " ingredient(s).";
            } else {
                status = "Missing " + missing + " or more ingredients. (May require a market run!)";
            }
            
            System.out.printf("%d. %s - %s\n", recipeCount++, recipe.getName(), status);
        }

        if (recipeChoices.isEmpty()) {
            System.out.println("💔 Sorry, based on your ingredients, we couldn't find any match.");
            return;
        }

        // User chooses a recipe
        System.out.print("\nSelect a number to view the full recipe, or type 'M' to go back to the Main Menu: ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("M")) {
            return;
        }

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < recipeChoices.size()) {
                String selectedRecipeName = recipeChoices.get(index);
                Recipe selectedRecipe = cookbook.getRecipeByName(selectedRecipeName);
                if (selectedRecipe != null) {
                    selectedRecipe.displayRecipe();
                }
            } else {
                System.out.println("❌ Invalid selection number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Returning to Main Menu.");
        }
    }


    // --- 2. Check Inventory ---
    private void checkInventory() {
        System.out.println("--- 📦 MY INVENTORY ---");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is currently empty. Go to 'Start' to input ingredients.");
            return;
        }

        System.out.println("\n** Current Ingredients **");
        for (int i = 0; i < inventory.size(); i++) {
            // Accessing Ingredient name via getter (Encapsulation)
            System.out.printf("%d. %s\n", (i + 1), inventory.get(i).getName());
        }

        System.out.print("\nWould you like to see what you can cook with these ingredients? (Y/N): ");
        String choice = scanner.nextLine();
        
        if (choice.equalsIgnoreCase("Y")) {
            processIngredients();
        }
    }
    
    // --- 3. View Full Recipe Catalog ---
    private void viewRecipeCatalog() {
        System.out.println("--- 📖 FULL RECIPE CATALOG ---");
        Map<String, List<Recipe>> categorizedRecipes = cookbook.getAllRecipesByCategory();
        
        int categoryCounter = 1;
        List<String> categoryOrder = new ArrayList<>(categorizedRecipes.keySet());

        System.out.println("\n** RECIPE CATEGORIES **");
        for (int i = 0; i < categoryOrder.size(); i++) {
            System.out.printf("%d. %s (%d recipes)\n", (i + 1), categoryOrder.get(i), categorizedRecipes.get(categoryOrder.get(i)).size());
        }

        System.out.print("\nEnter the number of the category you want to view, or 'M' for Main Menu: ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("M")) {
            return;
        }
        
        try {
            int categoryIndex = Integer.parseInt(choice) - 1;
            if (categoryIndex >= 0 && categoryIndex < categoryOrder.size()) {
                String selectedCategory = categoryOrder.get(categoryIndex);
                System.out.println("\n** RECIPES in " + selectedCategory.toUpperCase() + " **");
                
                List<Recipe> recipes = categorizedRecipes.get(selectedCategory);
                Map<Integer, String> recipeMap = new HashMap<>();
                
                for (int i = 0; i < recipes.size(); i++) {
                    System.out.printf("%d. %s\n", (i + 1), recipes.get(i).getName());
                    recipeMap.put(i + 1, recipes.get(i).getName());
                }

                System.out.print("\nSelect a recipe number for details, or 'M' for Main Menu: ");
                String recipeChoice = scanner.nextLine();
                
                if (recipeChoice.equalsIgnoreCase("M")) {
                    return;
                }
                
                int recipeNum = Integer.parseInt(recipeChoice);
                String selectedRecipeName = recipeMap.get(recipeNum);
                
                if (selectedRecipeName != null) {
                    cookbook.getRecipeByName(selectedRecipeName).displayRecipe();
                } else {
                    System.out.println("❌ Invalid recipe number.");
                }
            } else {
                System.out.println("❌ Invalid category number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Returning to Main Menu.");
        }
    }


    // --- 4. Help/About ---
    private void displayHelp() {
        System.out.println("--- ℹ️ HELP / ABOUT ---");
        System.out.println("Welcome to the Filipino Cooking Assistant! Your personal guide to Pinoy dishes.");
        System.out.println("\n** How to Use **");
        System.out.println("1. **Start (tara luto tayo!)**: This is where the magic happens! Enter a list of ingredients you have (e.g., 'pork, soy sauce, garlic'). The program will check against its catalog and show you what you can cook.");
        System.out.println("2. **Check my inventory**: Views the ingredients you last input during the 'Start' option and gives you the choice to re-run the cooking suggestion.");
        System.out.println("3. **View full recipe catalog**: See all the delicious Filipino recipes available, categorized for easy browsing.");
        System.out.println("4. **help/about**: You are here!");
        System.out.println("5. **exit**: Close the program.");
    }
}