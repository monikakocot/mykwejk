package pl.akademiakodu.kwejk.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiakodu.kwejk.model.Gif;
import pl.akademiakodu.kwejk.repository.GifRepository;

import java.util.List;

/*
Interfejs programowania aplikacji, interfejs programistyczny aplikacji (ang. Application Programming Interface, API) –
sposób, rozumiany jako ściśle określony zestaw reguł i ich opisów, w jaki programy komputerowe komunikują się między sobą.
Definiuje się go na poziomie kodu źródłowego dla składników oprogramowania, na przykład aplikacji, bibliotek, systemu operacyjnego.
Zadaniem interfejsu programowania aplikacji jest dostarczenie odpowiednich specyfikacji podprogramów, struktur danych, klas obiektów
i wymaganych protokołów komunikacyjnych.
 */
/*
JSON (JavaScript Object Notation) jest prostym formatem wymiany danych. Zapis i odczyt danych w tym formacie jest
łatwy do opanowania przez ludzi. Jednocześnie, z łatwością odczytują go i generują komputery. Jego definicja opiera się
o podzbiór języka programowania JavaScript, Standard ECMA-262 3rd Edition - December 1999. JSON jest formatem tekstowym,
całkowicie niezależnym od języków programowania, ale używa konwencji, które są znane programistom korzystającym z języków
z rodziny C, w tym C++, C#, Java, JavaScript, Perl, Python i wielu innych. Właściwości te czynią JSON idealnym językiem wymiany danych.
Wykorzystanie:
JSON jest jednym z nieformalnych sposobów przekazywania danych do aplikacji opartych o AJAX. W typowych przypadkach dane w
formacie JSON są pobierane z serwera jako tekst przy wykorzystaniu obiektu XMLHttpRequest języka JavaScript, a następnie
przekształcane w obiekt. Tekst powinien być kodowany za pomocą UTF-8, który jest w JSON domyślny.
 */

@RestController
public class GifApiController {

    private GifRepository gifRepository = new GifRepository();

    @GetMapping("/api/gifs")
    public List<Gif> gifs(){
        return gifRepository.findAll();
    }
    @GetMapping("api/gifs/find")
    public List<Gif> findGifs(@RequestParam String name){
        return gifRepository.findAllByNameIgnoreCase(name);
    }

}

