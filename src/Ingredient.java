public class Ingredient extends FoodItem {
    public Ingredient(String name) {
        super(name.trim().toLowerCase()); // call parent
    }
    
    @Override
    public void displayInfo() {
        System.out.println("🥬 Ingredient: " + name);
    }
}