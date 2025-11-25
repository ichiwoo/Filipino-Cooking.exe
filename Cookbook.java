import java.util.*;
import java.util.stream.Collectors;

public class Cookbook {
    private final List<Recipe> allRecipes;

    public Cookbook() {
        this.allRecipes = new ArrayList<>();
        initializeRecipes(); // Abstraction: hides the complex data setup
    }

    // --- Abstraction: Hides the data creation complexity ---
    private void initializeRecipes() {
        // --- Meat and Poultry ---
        allRecipes.add(new Recipe(
            "Adobo (Chicken/Pork)",
            Arrays.asList("chicken or pork", "soy sauce", "vinegar", "garlic", "peppercorns", "bay leaf"),
            "1. Marinate meat in soy sauce, vinegar, garlic, peppercorns, and bay leaf for 30 mins. 2. Boil until meat is tender and sauce reduces. 3. Serve with rice.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Sinigang na Baboy",
            Arrays.asList("pork belly", "sinigang mix", "water", "radish", "taro (gabi)", "kangkong (water spinach)", "long green beans"),
            "1. Boil pork until tender. 2. Add taro and radish. 3. Add sinigang mix. 4. Add beans and kangkong, cook briefly. 5. Serve hot.",
            "Meat and Poultry"
        ));

        // --- Fish and Seafood ---
        allRecipes.add(new Recipe(
            "Ginataang Tilapia",
            Arrays.asList("tilapia", "coconut milk", "ginger", "garlic", "onion", "chili (optional)", "malunggay leaves"),
            "1. Sauté ginger, garlic, and onion. 2. Add coconut milk and simmer. 3. Place fish in the sauce and cook until done. 4. Add malunggay leaves.",
            "Fish and Seafood"
        ));

        // --- Vegetables ---
        allRecipes.add(new Recipe(
            "Pinakbet",
            Arrays.asList("squash (kalabasa)", "okra", "eggplant", "long beans", "shrimp paste (bagoong)"),
            "1. Sauté shrimp paste. 2. Add squash and cook until tender. 3. Add other vegetables and cook until just done. 4. Serve immediately.",
            "Vegetables"
        ));
    }
    // --- End of Data Abstraction ---

    /**
     * Core logic: Finds recipes that can be made with the user's ingredients.
     * @param userIngredientsList The list of ingredient names provided by the user.
     * @return A map where the key is the Recipe and the value is the count of missing ingredients.
     */
    public Map<Recipe, Integer> findPossibleRecipes(List<String> userIngredientsList) {
        Map<Recipe, Integer> results = new HashMap<>();
        Set<String> userIngredients = userIngredientsList.stream()
                                          .map(String::toLowerCase)
                                          .collect(Collectors.toSet());

        for (Recipe recipe : allRecipes) {
            int missingCount = 0;
            
            for (String required : recipe.getRequiredIngredients()) {
                if (!userIngredients.contains(required.toLowerCase())) {
                    missingCount++;
                }
            }
            
            results.put(recipe, missingCount);
        }
        return results;
    }

    /**
     * @return All recipes, sorted by category.
     */
    public Map<String, List<Recipe>> getAllRecipesByCategory() {
        return allRecipes.stream()
            .collect(Collectors.groupingBy(Recipe::getCategory));
    }
    
    /**
     * Finds a recipe by its name.
     */
    public Recipe getRecipeByName(String name) {
        return allRecipes.stream()
            .filter(r -> r.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}