import java.util.*;
import java.util.stream.Collectors;

public class Cookbook {
    private final List<Recipe> allRecipes;

    public Cookbook() {
        this.allRecipes = new ArrayList<>();
        initializeRecipes();
        addSpecializedRecipes();
    }

    private void initializeRecipes() {
        // meat and poultry
        allRecipes.add(new Recipe(
            "Adobo (Chicken/Pork)",
            Arrays.asList("chicken or pork", "soy sauce", "vinegar", "garlic", "peppercorns", "bay leaf"),
            "1. Marinate meat in soy sauce, vinegar, garlic, peppercorns, and bay leaf for 30 mins. 2. Boil until meat is tender and sauce reduces. 3. Serve with rice.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Pork Menudo",
            Arrays.asList("pork", "liver", "potatoes", "carrots", "bell pepper", "tomato sauce", "garlic", "onion", "soy sauce", "oil"),
            "1. Sauté garlic and onion. 2. Add pork and cook. 3. Add tomato sauce and simmer. 4. Add vegetables and liver. 5. Cook until tender.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Chicken Afritada",
            Arrays.asList("chicken", "potatoes", "carrots", "bell pepper", "tomato sauce", "garlic", "onion", "oil"),
            "1. Sauté garlic and onion. 2. Add chicken and cook. 3. Add tomato sauce and simmer. 4. Add vegetables. 5. Cook until tender.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Bistek Tagalog",
            Arrays.asList("beef", "soy sauce", "calamansi", "onion", "pepper", "oil"),
            "1. Marinate beef in soy sauce and calamansi. 2. Fry beef. 3. Sauté onions. 4. Simmer beef in marinade. 5. Add onions and serve.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Pork Humba",
            Arrays.asList("pork belly", "soy sauce", "vinegar", "brown sugar", "garlic", "banana blossoms", "peppercorn", "bay leaves", "black beans"),
            "1. Brown pork. 2. Add soy sauce, vinegar, sugar, and spices. 3. Simmer until tender. 4. Add banana blossoms and black beans. 5. Reduce sauce.",
            "Meat and Poultry"
        ));

        allRecipes.add(new QuickRecipe(
            "Crispy Fried Chicken",
            Arrays.asList("chicken", "flour", "cornstarch", "garlic powder", "salt", "pepper", "oil"),
            "1. Season chicken. 2. Coat in flour and cornstarch. 3. Fry until golden and crispy. 4. Serve.",
            "Meat and Poultry",
            20
        ));

        allRecipes.add(new Recipe(
            "Chicken Curry (Filipino Style)",
            Arrays.asList("chicken", "curry powder", "potatoes", "carrots", "bell pepper", "coconut milk", "garlic", "onion", "oil"),
            "1. Sauté garlic and onion. 2. Add chicken and cook. 3. Add curry powder. 4. Add coconut milk and vegetables. 5. Simmer until tender.",
            "Meat and Poultry"
        ));

        allRecipes.add(new Recipe(
            "Pork Sinigang",
            Arrays.asList("pork", "tamarind mix", "tomatoes", "onion", "kangkong", "radish", "string beans", "eggplant", "water"),
            "1. Boil pork until tender. 2. Add tomatoes and onion. 3. Add vegetables. 4. Add tamarind mix. 5. Simmer and serve.",
            "Meat and Poultry"
        ));

        allRecipes.add(new QuickRecipe(
            "Lucban Longganisa",
            Arrays.asList("longganisa", "water", "oil"),
            "1. Place longganisa in pan with water. 2. Cook until water evaporates. 3. Add oil and fry until browned. 4. Serve.",
            "Meat and Poultry",
            15
        ));

        allRecipes.add(new Recipe(
            "Asadong Baboy",
            Arrays.asList("pork shoulder", "soy sauce", "vinegar", "brown sugar", "onion", "garlic", "star anise", "bay leaves", "water or pork broth", "cooking oil", "salt", "pepper"),
            "1. Brown pork in oil and set aside. 2. Sauté garlic and onion. 3. Return pork and add soy sauce, vinegar, brown sugar, star anise, and bay leaves. 4. Add water or broth and simmer 1–1.5 hours. 5. Add liver or liver spread near the end. 6. Add eggs and simmer 5 minutes. 7. Season and serve.",
            "Meat and Poultry"
        ));

        // fish and seafood
        allRecipes.add(new Recipe(
            "Ginataang Tilapia",
            Arrays.asList("tilapia", "coconut milk", "ginger", "garlic", "onion", "chili (optional)", "malunggay leaves"),
            "1. Sauté ginger, garlic, and onion. 2. Add coconut milk and simmer. 3. Place fish in the sauce and cook until done. 4. Add malunggay leaves.",
            "Fish and Seafood"
        ));

        allRecipes.add(new Recipe(
            "Paksiw na Isda",
            Arrays.asList("fish", "vinegar", "water", "ginger", "garlic", "onion", "long green pepper", "ampalaya (optional)", "eggplant (optional)", "peppercorns", "fish sauce or salt"),
            "1. Layer ginger, garlic, onion, ampalaya, and eggplant in a pan. 2. Place fish on top. 3. Add vinegar, water, fish sauce, and peppercorns. 4. Boil then simmer for 10–15 minutes. 5. Add long green peppers in last 5 minutes. 6. Adjust seasoning and serve.",
            "Fish and Seafood"
        ));

        allRecipes.add(new Recipe(
            "Adobong Pusit",
            Arrays.asList("squid", "soy sauce", "vinegar", "garlic", "onion", "bay leaves", "pepper", "water"),
            "1. Sauté garlic and onion. 2. Add squid and cook briefly. 3. Add soy sauce, vinegar, bay leaves, and pepper. 4. Simmer until sauce thickens. 5. Serve.",
            "Fish and Seafood"
        ));

        allRecipes.add(new Recipe(
            "Sweet and Sour Fish",
            Arrays.asList("fish fillet", "cornstarch", "bell pepper", "carrots", "onion", "pineapple", "vinegar", "sugar", "ketchup", "water", "oil"),
            "1. Coat fish in cornstarch and fry. 2. Sauté vegetables. 3. Mix vinegar, sugar, ketchup, and water. 4. Pour sauce over vegetables. 5. Add fried fish. 6. Serve.",
            "Fish and Seafood"
        ));

        allRecipes.add(new TraditionalRecipe(
            "Rellenong Bangus",
            Arrays.asList("milkfish", "garlic", "onion", "tomatoes", "carrots", "raisins", "egg", "soy sauce", "oil"),
            "1. Debone and flake bangus meat. 2. Sauté garlic, onion, tomatoes, and vegetables. 3. Mix with bangus meat and egg. 4. Stuff the fish skin. 5. Fry until golden. 6. Serve.",
            "Fish and Seafood",
            "Bulacan"
        ));

        allRecipes.add(new Recipe(
            "Sinigang na Hipon",
            Arrays.asList("shrimp", "tomatoes", "onion", "radish", "kangkong", "string beans", "tamarind mix", "water", "salt"),
            "1. Boil tomatoes and onion. 2. Add radish and string beans. 3. Add tamarind mix and shrimp. 4. Simmer until shrimp turns pink. 5. Add kangkong. 6. Serve.",
            "Fish and Seafood"
        ));

        allRecipes.add(new QuickRecipe(
            "Garlic Butter Shrimp",
            Arrays.asList("shrimp", "garlic", "butter", "lemon or calamansi", "salt", "pepper", "parsley (optional)"),
            "1. Melt butter and sauté garlic. 2. Add shrimp and cook until pink. 3. Add lemon, salt, and pepper. 4. Serve.",
            "Fish and Seafood",
            10
        ));

        allRecipes.add(new Recipe(
            "Fish Sarciado",
            Arrays.asList("fried fish", "tomatoes", "garlic", "onion", "egg", "water", "salt", "pepper"),
            "1. Sauté garlic, onion, and tomatoes. 2. Add water and simmer. 3. Add fried fish. 4. Pour beaten eggs and cook until thickened. 5. Serve.",
            "Fish and Seafood"
        ));

        allRecipes.add(new QuickRecipe(
            "Inihaw na Pusit",
            Arrays.asList("squid", "soy sauce", "vinegar", "garlic", "onion", "sugar", "pepper"),
            "1. Mix marinade ingredients. 2. Marinate squid. 3. Grill until cooked. 4. Serve with dipping sauce.",
            "Fish and Seafood",
            15
        ));

        allRecipes.add(new QuickRecipe(
            "Kinilaw na Tuna",
            Arrays.asList("fresh tuna", "vinegar or calamansi", "ginger", "onion", "chili", "cucumber", "salt", "pepper"),
            "1. Combine tuna with vinegar or calamansi. 2. Add ginger, onion, chili, and cucumber. 3. Season. 4. Chill and serve.",
            "Fish and Seafood",
            5
        ));

        // vegetables
        allRecipes.add(new Recipe(
            "Pinakbet",
            Arrays.asList("eggplant", "ampalaya", "okra", "squash", "string beans", "tomatoes", "bagoong alamang", "garlic", "onion", "cooking oil", "water"),
            "1. Sauté garlic, onion, and tomatoes. 2. Add bagoong and mix. 3. Add all vegetables. 4. Add a little water, cover, and simmer until tender. 5. Serve.",
            "Vegetables"
        ));

        allRecipes.add(new QuickRecipe(
            "Ginisang Ampalaya",
            Arrays.asList("ampalaya", "egg", "tomatoes", "garlic", "onion", "salt", "pepper", "cooking oil"),
            "1. Sauté garlic, onion, and tomatoes. 2. Add ampalaya and stir. 3. Add beaten egg and let it set. 4. Season with salt and pepper. 5. Serve.",
            "Vegetables",
            10
        ));

        allRecipes.add(new Recipe(
            "Ginisang Monggo",
            Arrays.asList("mung beans", "garlic", "onion", "tomatoes", "malunggay or spinach", "fish sauce", "cooking oil", "water"),
            "1. Boil mung beans until soft. 2. Sauté garlic, onion, and tomatoes. 3. Add softened mung beans. 4. Pour water and simmer. 5. Add malunggay or spinach. 6. Season and serve.",
            "Vegetables"
        ));

        allRecipes.add(new Recipe(
            "Chopsuey",
            Arrays.asList("carrots", "cabbage", "cauliflower", "bell pepper", "snow peas", "garlic", "onion", "oyster sauce", "soy sauce", "water", "cooking oil"),
            "1. Sauté garlic and onion. 2. Add hard vegetables first (carrots, cauliflower). 3. Add cabbage, bell pepper, and snow peas. 4. Add oyster sauce, soy sauce, and water. 5. Simmer briefly and serve.",
            "Vegetables"
        ));

        allRecipes.add(new QuickRecipe(
            "Ginisang Sayote",
            Arrays.asList("sayote", "garlic", "onion", "tomatoes", "salt", "pepper", "cooking oil"),
            "1. Sauté garlic, onion, and tomatoes. 2. Add sliced sayote. 3. Add a little water and simmer until tender. 4. Season and serve.",
            "Vegetables",
            8
        ));

        allRecipes.add(new Recipe(
            "Adobong Sitaw",
            Arrays.asList("string beans", "garlic", "soy sauce", "vinegar", "pepper", "cooking oil", "water"),
            "1. Sauté garlic. 2. Add string beans. 3. Add soy sauce, vinegar, and water. 4. Simmer until beans are tender. 5. Serve.",
            "Vegetables"
        ));

        allRecipes.add(new TraditionalRecipe(
            "Laing",
            Arrays.asList("dried taro leaves", "coconut milk", "coconut cream", "ginger", "garlic", "chili", "dried fish or shrimp (optional)", "salt"),
            "1. Combine coconut milk, ginger, garlic, and chili in pot and simmer. 2. Add taro leaves without stirring. 3. Cook on low until leaves soften. 4. Add coconut cream and optional dried fish. 5. Simmer until thick. 6. Serve.",
            "Vegetables",
            "Bicol"
        ));

        // soup
        allRecipes.add(new Recipe(
            "Tinola",
            Arrays.asList("chicken", "ginger", "garlic", "onion", "fish sauce", "green papaya or chayote", "chili leaves or malunggay", "cooking oil", "water"),
            "1. Sauté ginger, garlic, and onion. 2. Add chicken and cook until white. 3. Add water, boil, and simmer for 30–40 minutes. 4. Add fish sauce and green papaya; simmer until papaya is tender. 5. Add chili leaves or malunggay and turn off heat.",
            "Soup"
        ));

        allRecipes.add(new Recipe(
            "Arroz Caldo",
            Arrays.asList("chicken", "rice", "garlic", "ginger", "onion", "fish sauce", "water or broth", "safflower (kasubha)", "boiled egg", "calamansi", "spring onions"),
            "1. Sauté garlic, ginger, and onion. 2. Add chicken and cook. 3. Add rice and stir. 4. Pour broth and simmer until porridge thickens. 5. Add kasubha. 6. Serve with egg, calamansi, and spring onions.",
            "Soup"
        ));

        allRecipes.add(new Recipe(
            "Chicken Sopas",
            Arrays.asList("chicken breast", "macaroni", "garlic", "onion", "carrots", "cabbage", "evaporated milk", "butter", "water", "salt", "pepper"),
            "1. Boil chicken and shred. 2. Sauté garlic and onion in butter. 3. Add carrots and shredded chicken. 4. Add water and macaroni; cook until soft. 5. Add cabbage and milk. 6. Season and serve warm.",
            "Soup"
        ));

        allRecipes.add(new TraditionalRecipe(
            "Molo Soup",
            Arrays.asList("ground pork", "molo wrappers", "garlic", "onion", "chicken broth", "spring onions", "fish sauce", "pepper"),
            "1. Mix pork with seasoning and wrap in molo wrappers. 2. Sauté garlic and onion. 3. Add chicken broth and bring to boil. 4. Add molo dumplings and simmer. 5. Add spring onions and serve.",
            "Soup",
            "Iloilo"
        ));

        allRecipes.add(new Recipe(
            "Beef Bulalo",
            Arrays.asList("beef shanks with bone marrow", "corn", "potatoes", "pechay", "cabbage", "onion", "fish sauce", "peppercorns", "water"),
            "1. Boil beef shanks until tender. 2. Add onion and peppercorns. 3. Add corn and potatoes. 4. Add cabbage and pechay. 5. Season with fish sauce and serve hot.",
            "Soup"
        ));

        // snack / dessert
        allRecipes.add(new Recipe(
            "Turon (Banana Lumpia)",
            Arrays.asList("saba bananas", "jackfruit (optional)", "spring roll wrappers", "brown sugar", "cooking oil", "water"),
            "1. Coat banana pieces in brown sugar. 2. Place banana (and jackfruit) on wrapper and roll tightly, sealing with water. 3. Fry until golden and crispy. 4. Drain and serve.",
            "Snack/Dessert"
        ));

        allRecipes.add(new QuickRecipe(
            "Banana Cue", 
            Arrays.asList("saba bananas", "brown sugar", "cooking oil", "bamboo skewers"),
            "1. Skewer the bananas. 2. Melt brown sugar in oil. 3. Add bananas and cook while turning until coated and tender. 4. Drain, cool slightly, and serve.",
            "Snack/Dessert",
            10
        ));

        allRecipes.add(new QuickRecipe(
            "Kamote Cue", 
            Arrays.asList("sweet potatoes", "brown sugar", "cooking oil", "bamboo skewers"),
            "1. Cut and skewer kamote pieces. 2. Fry for 5–7 minutes until tender. 3. Melt sugar in a small amount of oil. 4. Return skewers and coat with caramelized sugar. 5. Cool briefly and serve.",
            "Snack/Dessert",
            15
        ));

        allRecipes.add(new Recipe(
            "Ice Candy",
            Arrays.asList("fruit cocktail", "evaporated milk", "condensed milk", "all-purpose cream"),
            "1. Drain juice from fruit cocktail. 2. Mix all ingredients. 3. Pour into ice candy bags. 4. Freeze. 5. Serve.",
            "Snack/Dessert"
        ));
    }

    public Map<Recipe, Integer> findPossibleRecipes(List<String> userIngredientsList) {
        Map<Recipe, Integer> results = new HashMap<>();
        
        // normalize user ingredients
        Set<String> userIngredients = userIngredientsList.stream()
                                      .map(ing -> ing.trim().toLowerCase())
                                      .collect(Collectors.toSet());

        for (Recipe recipe : allRecipes) {
            int missingCount = 0;
            
            for (String required : recipe.getRequiredIngredients()) {
                // check match
                boolean found = userIngredients.stream()
                    .anyMatch(userIng -> containsIngredient(userIng, required.toLowerCase()));
                
                if (!found) {
                    missingCount++;
                }
            }
            
            results.put(recipe, missingCount);
        }
        return results;
    }

    // helper: ingredient matching
    private boolean containsIngredient(String userIngredient, String requiredIngredient) {
        // handle 'or' options
        if (requiredIngredient.contains(" or ")) {
            String[] options = requiredIngredient.split(" or ");
            for (String option : options) {
                if (userIngredient.contains(option.trim()) || option.trim().contains(userIngredient)) {
                    return true;
                }
            }
            return false;
        }
        
        // basic contains check
        return userIngredient.contains(requiredIngredient) || requiredIngredient.contains(userIngredient);
    }

    public Map<String, List<Recipe>> getAllRecipesByCategory() {
        return allRecipes.stream()
            .collect(Collectors.groupingBy(Recipe::getCategory));
    }
    
    public Recipe getRecipeByName(String name) {
        return allRecipes.stream()
            .filter(r -> r.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public Recipe[] getAllRecipesArray() {
        return allRecipes.toArray(new Recipe[0]);
    }
    
    public Recipe getRecipeByNameWithException(String name) throws RecipeNotFoundException {
        Recipe recipe = getRecipeByName(name);
        if (recipe == null) {
            throw new RecipeNotFoundException("Recipe '" + name + "' not found");
        }
        return recipe;
    }
    
    private void addSpecializedRecipes() {
        allRecipes.add(new TraditionalRecipe(
            "Kare-Kare",
            Arrays.asList("oxtail", "peanut butter", "eggplant", "string beans", "banana blossom", "shrimp paste"),
            "1. Boil oxtail until tender. 2. Add vegetables and peanut sauce. 3. Simmer until thick. 4. Serve with shrimp paste.",
            "Meat and Poultry",
            "Central Luzon"
        ));
        
        allRecipes.add(new QuickRecipe(
            "Tortang Talong", 
            Arrays.asList("eggplant", "eggs", "onion", "garlic", "salt", "pepper"),
            "1. Grill eggplant until soft. 2. Peel and flatten. 3. Dip in beaten eggs. 4. Pan-fry until golden brown.",
            "Vegetables",
            15
        ));
    }
}
