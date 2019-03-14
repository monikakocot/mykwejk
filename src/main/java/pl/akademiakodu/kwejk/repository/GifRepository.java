package pl.akademiakodu.kwejk.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import pl.akademiakodu.kwejk.model.Gif;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GifRepository {


    private static List<Gif> ALL_GIFS = new ArrayList<>();

    static{
        ALL_GIFS.add(new Gif ("android-explosion",true,"ben",1));
        ALL_GIFS.add(new Gif ("book-dominos",true,"ada",2));
        ALL_GIFS.add(new Gif ("compiler-bot",false,"piotr",2));
        ALL_GIFS.add(new Gif ("cowboy-coder",false,"ola",3));
        ALL_GIFS.add(new Gif ("infinite-andrew",false,"adam",3));
        ALL_GIFS.add(new Gif ("ben-and-mike",true,"michal",1));
        //ALL_GIFS.add(new Gif ("pies",true,"Monika","1"));
    }

    public List<Gif> findAll(){
        return ALL_GIFS;
    }

    public static List<Gif> getAllGifs() {
        return ALL_GIFS;
    }

    public static List<Gif> findFavorites() {
        return ALL_GIFS.stream().filter(p -> p.isFavorite()).collect(Collectors.toList());
    }

    public Optional<Gif> findByName(String name){
        return ALL_GIFS.stream().filter(p-> p.getName().equals(name)).findFirst();
    }

    public List<Gif> findByCategoryId( int categoryId){
        return ALL_GIFS.stream().filter(p->p.getCategoryId()==categoryId).collect(Collectors.toList());
    }

    public List<Gif> findAllByNameIgnoreCase (String name){
        return ALL_GIFS.stream().filter(p->p.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public static void setAllGifs(List<Gif> allGifs) {
        ALL_GIFS = allGifs;
    }

/*//GifDetails for newGif

    public List<Gif>  addGifToList (MultipartFile file){
        String gifName = file.getOriginalFilename();
        String gifNameCorrect = new StringBuilder(gifName).substring(0,gifName.length()-4);
        ALL_GIFS.add(new Gif(gifNameCorrect,false,"user","3"));
        return ALL_GIFS;
    }
*/

//GifDetails for newGif

}

