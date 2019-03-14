package pl.akademiakodu.kwejk.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import pl.akademiakodu.kwejk.model.Gif;
import pl.akademiakodu.kwejk.repository.GifRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS) // zmiana  z singletonu na zasięg sesyjny

//todo session scope works
/*czyli teraz Bean Service będzie tworzony od nowa dla każdej sesji.
czyli jak wejdziemy z innej przeglądarki, innego komputera to lista alertów jest tworzona od nowa
zastosowanie np. system logowania - stan na sesję zalogowania; koszyk  w sklepie.
*/

//service to show gif from filePath and to add Gif to List<Gif>
public class GifListService {

    public static List<Gif> gifList = new ArrayList<>();

//    public GifListService() {
//        gifList = new ArrayList<>();
//    }

//GifDetails for newGif
    public List<Gif>  addGifToServiceList (MultipartFile file){
        String gifName = file.getOriginalFilename();
        String gifNameCorrect = new StringBuilder(gifName).substring(0,gifName.length()-4);
        gifList.add(new Gif(gifNameCorrect,false));
        return  gifList;
    }

    public List<Gif>  addGifToList (MultipartFile file){
        String gifName = file.getOriginalFilename();
        String gifNameCorrect = new StringBuilder(gifName).substring(0,gifName.length()-4);
        GifRepository.getAllGifs().add(new Gif(gifNameCorrect,false,"user",4));
        return GifRepository.getAllGifs();
    }
    public  List<Gif> getGifsFromList() {
        return gifList;
    }

    public String buildGifFilePath(MultipartFile file) {
        //return new StringBuilder("").append("/gifs/").append(file.getOriginalFilename()).toString();
        return new StringBuilder("").append("/gifs2/").append(file.getOriginalFilename()).toString();
    }
    public String getThymeleafFilePathForNewGif(){
        return new StringBuilder("").append("/gifs2/").append(gifList.get(0).getName()).append(".gif").toString();
    }

    public Optional<Gif> findByNameNew(String name){

        return gifList.stream().filter(p-> p.getName().equals(name)).findFirst();
    }

}