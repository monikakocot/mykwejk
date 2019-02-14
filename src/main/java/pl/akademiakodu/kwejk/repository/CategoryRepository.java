package pl.akademiakodu.kwejk.repository;

import pl.akademiakodu.kwejk.model.Category;
import pl.akademiakodu.kwejk.model.Gif;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryRepository {

    private static List<Category> ALL_CATEGORIES = new ArrayList<>();

    static {
        ALL_CATEGORIES.add(new Category(1,"Programming"));
        ALL_CATEGORIES.add(new Category(2,"Fun"));
        ALL_CATEGORIES.add(new Category(3,"Bot"));
        ALL_CATEGORIES.add(new Category(3,"UPLOADED"));
    }

    public List<Category> findAll(){
        return ALL_CATEGORIES;
    }

    public Category findByCategory(int categoryId){
        return ALL_CATEGORIES.stream().filter(category -> category.getId()==categoryId).collect(Collectors.toList()).get(0);
    }
    public List<Category> findCategoriesByNameIgnoreCase(String name){
        return ALL_CATEGORIES.stream().filter(category->category.getName().toLowerCase()
                .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public void addNewCategory (Category newCategory){
        ALL_CATEGORIES.add(newCategory);
    }

}
