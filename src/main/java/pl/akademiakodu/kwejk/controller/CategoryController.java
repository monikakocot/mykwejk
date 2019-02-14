package pl.akademiakodu.kwejk.controller;


import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.kwejk.model.Category;
import pl.akademiakodu.kwejk.repository.CategoryRepository;
import pl.akademiakodu.kwejk.repository.GifRepository;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository = new CategoryRepository();
    private GifRepository gifRepository= new GifRepository();

//KOD12 - must added Category category for form
    @GetMapping("/categories")
    public String displayAll(ModelMap modelMap, Category category){

        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

//KOD12
    @GetMapping("/newCategory")
    public String addNewCategory (Category category){
        return "form";
    }
    @PostMapping("/categories")
    public String newCategories (@ModelAttribute Category category,
                                 ModelMap modelMap){

        category = new Category(category.getName());
        modelMap.addAttribute("category", category);
        categoryRepository.addNewCategory(category);
        modelMap.addAttribute("categories", categoryRepository.findAll());

        return "categories";
    }
//KOD12

    //KOD 7
    @GetMapping("/category/{id}")
    public String displayCategory(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("category",categoryRepository.findByCategory(id));
        modelMap.addAttribute("gifs",gifRepository.findByCategoryId(id));
        return "category";
    }
//KOD 7

    //KOD 9
    @GetMapping("/categories/search")
    public String searchCategory (@RequestParam String q,ModelMap modelMap, Category category){
        List<Category> categoryList = categoryRepository.findCategoriesByNameIgnoreCase(q);
        if (categoryList.isEmpty())
            modelMap.addAttribute("categories",categoryRepository.findAll());
        else
            modelMap.addAttribute("categories", categoryList);
        return "categories";
    }
//KOD 9
}



