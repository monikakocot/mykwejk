package pl.akademiakodu.kwejk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import pl.akademiakodu.kwejk.model.Category;
import pl.akademiakodu.kwejk.model.Gif;
import pl.akademiakodu.kwejk.repository.CategoryRepository;
import pl.akademiakodu.kwejk.repository.GifRepository;
import pl.akademiakodu.kwejk.service.GifListService;
import sun.net.www.content.image.gif;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class GifController {

    @Autowired
    private GifRepository gifRepository = new GifRepository();
    private CategoryRepository categoryRepository = new CategoryRepository();

    @Autowired
    GifListService gifListService;

    @GetMapping("/")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("gifs", gifRepository.findAll());
//service to show give from filePath and to add Gif to List<Gif>
        modelMap.addAttribute("newGif",gifListService.getGifsFromList());
//service to show gif from filePath and to add Gif to List<Gif>
        return "home";
    }

/*//showing one gif
     @GetMapping("/exampleGif")
     public String hello(ModelMap modelMap){
     modelMap.addAttribute("gif", new Gif("compiler-bot"));
     modelMap.addAttribute("gif", new Gif("compiler-bot").getThymeleafFilePath()); // wtedy w home sam 'gif'
     return "home";
     }

    @GetMapping("/easyExampleGif")
    public String easy (){
        return "home";
    }
*/

    @GetMapping("/gif/{name}")
    public String displayGif (@PathVariable String name, ModelMap modelMap) {

//        if (name.getClass().getSimpleName().equals(GifRepository.class)) {
            modelMap.addAttribute("gif", gifRepository.findByName(name).orElse(new Gif("android-explosion", true)));
//       }
/*
    } else if (gifListService.getGifsFromList().contains(name)) {
        modelMap.addAttribute("gif", gifListService.findByNameNew(name));
    }
*/
        return "gif-details";
    }

    @GetMapping("/favorites")

    public String getFavorites (ModelMap modelmap){

        modelmap.addAttribute("gifs",gifRepository.findFavorites());
        return "favorites";
    }

    @GetMapping("/gifs/search")
    public String searchGif (@RequestParam String q,ModelMap modelMap){

        List<Gif> gifsList = gifRepository.findAllByNameIgnoreCase(q);
        if (gifsList.isEmpty())
            modelMap.addAttribute("gifs", gifRepository.findAll());
        else
            modelMap.addAttribute("gifs",gifsList);
        return "home";
    }

    @GetMapping("/search")
    public String searchGifOrCategory (@RequestParam String q,ModelMap modelMap, Category category){

        List<Gif> gifsList = gifRepository.findAllByNameIgnoreCase(q);
        List<Category> categoryList = categoryRepository.findCategoriesByNameIgnoreCase(q);
        if (gifsList.isEmpty() && categoryList.isEmpty()) {
            modelMap.addAttribute("gifs", gifRepository.findAll());
            modelMap.addAttribute("categories",categoryRepository.findAll());}
        else if (!gifsList.isEmpty()){
            modelMap.addAttribute("gifs",gifsList);
            return "home";
        }
        else if (!categoryList.isEmpty()){
            modelMap.addAttribute("categories", categoryList);
            return "categories";
        }
        return "home";
    }

}

