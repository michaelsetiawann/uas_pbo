package Model;

/**
 *
 * @author Lenovo
 */
public class CategoryUser {

    private int categoryId;
    private String categoryName;

    public CategoryUser(int categoryId, String cateogryName) {
        this.categoryId = categoryId;
        this.categoryName = cateogryName;
    }   

    public CategoryUser() {
       
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String cateogryName) {
        this.categoryName = cateogryName;
    }
    
}
