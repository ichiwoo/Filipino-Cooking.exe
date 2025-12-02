import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner;
    private Cookbook cookbook;
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
        displayWelcome();
        loadingTransition();
        mainMenu();
    }

    private void loadingTransition() {
        System.out.print("\n                  Loading.");
        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(500);
                System.out.print("...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }

    private void displayWelcome() {
        System.out.println("\n");
        System.out.println("              =============================================");
        System.out.println("              |                                           |");
        System.out.println("              |       [*] Filipino Cooking.exe [*]        |");
        System.out.println("              |                                           |");
        System.out.println("              |               Tara luto tayo!             |");
        System.out.println("              |                                           |");
        System.out.println("              =============================================");
        System.out.println("\n");
        System.out.println("                   Welcome to your cooking companion!");
        System.out.println("              Discover delicious Filipino recipes today.\n");
    }

    private void mainMenu() {
        String choice = "";
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("\n              =============================================");
            System.out.println("              |             [*] MAIN MENU [*]             |");
            System.out.println("              |===========================================|");
            System.out.println("              |  1. [>] Start (tara luto tayo!)           |");
            System.out.println("              |  2. [+] Add ingredients to inventory      |");
            System.out.println("              |  3. [=] Check my inventory                |");
            System.out.println("              |  4. [@] View full recipe catalog          |");
            System.out.println("              |  5. [i] Ingredient Index                  |");
            System.out.println("              |  6. [?] Help/About                        |");
            System.out.println("              |  7. [X] Exit                              |");
            System.out.println("              =============================================");
            System.out.print("                Enter your choice (1-7): ");

            choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    startCooking();
                    break;
                case "2":
                    addIngredients();
                    break;
                case "3":
                    checkInventory();
                    break;
                case "4":
                    viewRecipeCatalog();
                    break;
                case "5":
                    showIngredientIndex();
                    break;
                case "6":
                    displayHelp();
                    break;
                case "7":
                    System.out.println("\n              =============================================");
                    System.out.println("              |  Thank you for using Filipino Cooking!   |");
                    System.out.println("              |             Salamat!                     |");
                    System.out.println("              =============================================");
                    choice = "exit";
                    break;
                default:
                    System.out.println("                [!] Invalid choice. Please enter 1-7.");
            }
        }
    }

    private void startCooking() {
        System.out.println("\n              =============================================");
        System.out.println("              |           [>] START COOKING [>]           |");
        System.out.println("              =============================================");
        System.out.println("\n                 Enter ingredients separated by commas:");
        System.out.println("                 Example: chicken, soy sauce, garlic, onion");
        System.out.println("\n                 [!] NOTE: Starting again will clear your current");
        System.out.println("                 inventory and start fresh. Use 'Add ingredients'");
        System.out.println("                 to append instead.\n");
        System.out.print("                  Your ingredients: ");
        
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("\n                  [!] No ingredients entered. Returning to menu.");
            return;
        }
        
        Set<String> uniqueIngredients = new HashSet<>();
        String[] parts = input.split(",");
        for (String part : parts) {
            String ingredient = part.trim().toLowerCase();
            if (!ingredient.isEmpty()) {
                uniqueIngredients.add(ingredient);
            }
        }

        if (uniqueIngredients.isEmpty()) {
            System.out.println("\n                  [!] No valid ingredients found.");
            return;
        }

        if (uniqueIngredients.size() < 3) {
            System.out.println("\n                  [!] Please enter at least 3 ingredients to get good recipe matches.");
            System.out.println("                  Try adding more ingredients or use 'Add ingredients' to append to your inventory.");
            return;
        }

        // replace inventory for fresh search
        inventory.clear();
        for (String ing : uniqueIngredients) {
            inventory.add(new Ingredient(ing));
        }

        System.out.println("\n                  [*] Processing ingredients...");
        processIngredients();
    }

    private void addIngredients() {
        System.out.println("\n              =============================================");
        System.out.println("              |       [+] ADD INGREDIENTS TO INVENTORY    |");
        System.out.println("              =============================================");
        System.out.println("\n                  Enter ingredients to add (separated by commas):");
        System.out.print("                  Your input: ");

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("\n                  [!] No ingredients entered. Returning to menu.");
            return;
        }

        Set<String> existing = inventory.stream()
            .map(Ingredient::getName)
            .map(String::toLowerCase)
            .collect(Collectors.toSet());

        String[] parts = input.split(",");
        int added = 0;
        for (String part : parts) {
            String name = part.trim().toLowerCase();
            if (!name.isEmpty() && !existing.contains(name)) {
                inventory.add(new Ingredient(name));
                existing.add(name);
                added++;
            }
        }

        System.out.println("\n                  [+] Added " + added + " new ingredient(s) to your inventory.");
        System.out.println("                  You now have " + inventory.size() + " ingredient(s).\n");
    }

    private void processIngredients() {
        List<String> userIngredientsNames = inventory.stream()
            .map(Ingredient::getName)
            .collect(Collectors.toList());

        Map<Recipe, Integer> results = cookbook.findPossibleRecipes(userIngredientsNames);

        System.out.println("\n              =============================================");
        System.out.println("              |          [*] WHAT YOU CAN COOK [*]        |");
        System.out.println("              =============================================");
        System.out.println("");
        
        // filter: include close matches (missing<=2) or >=50% matched
        List<Map.Entry<Recipe, Integer>> filteredResults = results.entrySet().stream()
            .filter(entry -> {
                int missing = entry.getValue();
                int total = entry.getKey().getRequiredIngredients().size();
                int matched = total - missing;
                boolean closeByCount = missing <= 2;
                boolean halfOrMoreMatched = matched >= Math.ceil(total / 2.0);
                return closeByCount || halfOrMoreMatched;
            })
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toList());

        if (filteredResults.isEmpty()) {
            System.out.println("                  [X] No close matches found (missing ≤2 or ≥50% match).");
            System.out.println("                  Try adding more ingredients or check your spelling.");
            return;
        }

        // display recipes
        int recipeCount = 1;
        List<String> recipeChoices = new ArrayList<>();
        
        for (Map.Entry<Recipe, Integer> entry : filteredResults) {
            Recipe recipe = entry.getKey();
            int missing = entry.getValue();
            int total = recipe.getRequiredIngredients().size();
            recipeChoices.add(recipe.getName());
            
            String status;
            if (missing == 0) {
                status = "[+] COMPLETE - Ready to cook!";
            } else if (missing == 1) {
                status = "[~] ALMOST - Missing 1 ingredient";
            } else if (missing == 2) {
                status = "[-] CLOSE - Missing 2 ingredients";
            } else if (missing <= 4) {
                status = "[*] NEEDS SOME - Missing " + missing + " of " + total;
            } else {
                status = "[!] NEEDS MANY - Missing " + missing + " of " + total;
            }
            
            System.out.printf("                  %d. %s\n                     %s\n\n", recipeCount++, recipe.getName(), status);
        }

        System.out.print("                  Select recipe number for details, or 'M' for menu: ");
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
                System.out.println("                  [X] Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("                  [X] Please enter a valid number.");
        }
    }

    private void checkInventory() {
        System.out.println("\n              =============================================");
        System.out.println("              |            [=] MY INVENTORY [=]           |");
        System.out.println("              =============================================");
        
        if (inventory.isEmpty()) {
            System.out.println("\n                  Your inventory is empty.");
            System.out.println("                  Go to 'Start Cooking' to add ingredients.");
            return;
        }

        boolean viewingInventory = true;
        while (viewingInventory) {
            System.out.println("\n                  Your ingredients:");
            System.out.println("                  -----------------");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.printf("                  %d. [+] %s\n", (i + 1), inventory.get(i).getName());
            }

            System.out.println("\n                  Options:");
            System.out.print("                  Find recipes? (Y), Remove ingredient? (R), or Back? (B): ");
            String choice = scanner.nextLine().trim().toUpperCase();
            
            if (choice.equals("Y")) {
                processIngredients();
                viewingInventory = false;
            } else if (choice.equals("R")) {
                System.out.print("                  Enter ingredient number to remove (1-" + inventory.size() + "): ");
                try {
                    int removeIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (removeIndex >= 0 && removeIndex < inventory.size()) {
                        String removed = inventory.get(removeIndex).getName();
                        inventory.remove(removeIndex);
                        System.out.println("                  [✓] Removed: " + removed);
                    } else {
                        System.out.println("                  [!] Invalid number. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("                  [!] Please enter a valid number.");
                }
            } else if (choice.equals("B")) {
                viewingInventory = false;
            } else {
                System.out.println("                  [!] Invalid choice. Please enter Y, R, or B.");
            }
        }
    }
    
    private void viewRecipeCatalog() {
        System.out.println("\n              =============================================");
        System.out.println("              |            [@] RECIPE CATALOG [@]         |");
        System.out.println("              =============================================");
        
        Map<String, List<Recipe>> categorizedRecipes = cookbook.getAllRecipesByCategory();
        
        List<String> categories = new ArrayList<>(categorizedRecipes.keySet());

        System.out.println("\n                  Categories:");
        System.out.println("                  -----------");
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("                  %d. [>] %s\n", (i + 1), categories.get(i));
        }

        System.out.print("\n                  Select category number, or 'M' for menu: ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("M")) {
            return;
        }
        
        try {
            int categoryIndex = Integer.parseInt(choice) - 1;
            if (categoryIndex >= 0 && categoryIndex < categories.size()) {
                String selectedCategory = categories.get(categoryIndex);
                List<Recipe> recipes = categorizedRecipes.get(selectedCategory);
                
                System.out.println("\n              =============================================");
                System.out.printf("              | %s\n", String.format("Category: %-43s |", "[>] " + selectedCategory.toUpperCase()));
                System.out.println("              =============================================");
                System.out.println("");
                
                for (int i = 0; i < recipes.size(); i++) {
                    System.out.printf("                  %d. %s\n", (i + 1), recipes.get(i).getName());
                }

                System.out.print("\n                  Select recipe number for details, or 'M' for menu: ");
                String recipeChoice = scanner.nextLine();
                
                if (recipeChoice.equalsIgnoreCase("M")) {
                    return;
                }
                
                int recipeNum = Integer.parseInt(recipeChoice);
                if (recipeNum > 0 && recipeNum <= recipes.size()) {
                    recipes.get(recipeNum - 1).displayRecipe();
                } else {
                    System.out.println("                  [X] Invalid recipe number.");
                }
            } else {
                System.out.println("                  [X] Invalid category number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("                  [X] Please enter a valid number.");
        }
    }

    private void displayHelp() {
        System.out.println("\n              =============================================");
        System.out.println("              |            [?] HELP & ABOUT [?]           |");
        System.out.println("              |===========================================|");
        System.out.println("              |  How to use:                              |");
        System.out.println("              |  1. [>] Start Cooking                     |");
        System.out.println("              |     Enter at least 3 ingredients          |");
        System.out.println("              |     to find recipe matches                |");
        System.out.println("              |                                           |");
        System.out.println("              |  2. [+] Add Ingredients                   |");
        System.out.println("              |     Append ingredients to your            |");
        System.out.println("              |     current inventory                     |");
        System.out.println("              |                                           |");
        System.out.println("              |  3. [=] Check Inventory                   |");
        System.out.println("              |     View your saved ingredients           |");
        System.out.println("              |                                           |");
        System.out.println("              |  4. [@] View Recipes                      |");
        System.out.println("              |     Browse all available recipes          |");
        System.out.println("              |                                           |");
        System.out.println("              |  5. [i] Ingredient Index                  |");
        System.out.println("              |     See all possible ingredients          |");
        System.out.println("              |     used in recipes                       |");
        System.out.println("              |                                           |");
        System.out.println("              |  6. [?] Help                              |");
        System.out.println("              |     Show this information                 |");
        System.out.println("              |                                           |");
        System.out.println("              |  7. [X] Exit                              |");
        System.out.println("              |     Close the program                     |");
        System.out.println("              |===========================================|");
        System.out.println("              |  Minimum 3 ingredients required           |");
        System.out.println("              |  for best recipe matches.                 |");
        System.out.println("              |                                           |");
        System.out.println("              =============================================");
        System.out.println("\n");
    }

    private void showIngredientIndex() {
        System.out.println("\n              =============================================");
        System.out.println("              |        [i] INGREDIENT INDEX [i]           |");
        System.out.println("              =============================================");
        System.out.println("\n                  All available ingredients in recipes:\n");

        Set<String> allIngredients = new TreeSet<>();
        for (Recipe recipe : cookbook.getAllRecipesArray()) {
            for (String ingredient : recipe.getRequiredIngredients()) {
                if (ingredient.contains(" or ")) {
                    String[] parts = ingredient.split(" or ");
                    for (String part : parts) {
                        allIngredients.add(part.trim());
                    }
                } else {
                    allIngredients.add(ingredient.trim());
                }
            }
        }

        List<String> ingredientList = new ArrayList<>(allIngredients);
        int perRow = 3;
        for (int i = 0; i < ingredientList.size(); i++) {
            if (i % perRow == 0) {
                System.out.print("                  ");
            }
            System.out.printf("%-25s", "[+] " + ingredientList.get(i));
            if ((i + 1) % perRow == 0) {
                System.out.println();
            }
        }
        System.out.println("\n\n                  Total unique ingredients: " + allIngredients.size());
        System.out.println("                  Use these names when entering ingredients for better matches.");
        System.out.println("                  (Names are not case-sensitive.)\n");
    }
}
