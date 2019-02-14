package pl.akademiakodu.kwejk.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiakodu.kwejk.model.Category;
import pl.akademiakodu.kwejk.repository.CategoryRepository;

import java.util.List;

@RestController
public class CategoryApiController {

    private CategoryRepository categoryRepository = new CategoryRepository();

    @GetMapping("api/categories")
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @GetMapping("api/categories/find")
    public List<Category> findByName(@RequestParam String name){
        return categoryRepository.findCategoriesByNameIgnoreCase(name);
    }

/*
    @GetMapping("api/categories/find")
    public List<Category> findByName(@RequestParam String name){
        List<Category> categories = categoryRepository.findCategoriesByNameIgnoreCase(name);
        if(categories.isEmpty())
        return categoryRepository.findAll();
    else
        return categoryRepository.findCategoriesByNameIgnoreCase(name);
    }
*/
}

