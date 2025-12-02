import java.util.List;

public class TraditionalRecipe extends Recipe {
    private String region;
    
    public TraditionalRecipe(String name, List<String> requiredIngredients, String instructions, String category, String region) {
        super(name, requiredIngredients, instructions, category);
        this.region = region;
    }
    
    public String getRegion() {
        return region;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("              =============================================");
        System.out.println("              |       [*] TRADITIONAL INFO [*]             |");
        System.out.println("              =============================================");
        System.out.println("                  Region: " + region);
        System.out.println("              =============================================\n");
    }
    
    @Override
    public void displayRecipe() {
        displayInfo();
    }
}