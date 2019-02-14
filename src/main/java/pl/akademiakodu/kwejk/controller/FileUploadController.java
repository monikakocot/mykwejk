package pl.akademiakodu.kwejk.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.akademiakodu.kwejk.repository.GifRepository;
import pl.akademiakodu.kwejk.service.GifListService;
import pl.akademiakodu.kwejk.storage.StorageFileNotFoundException;
import pl.akademiakodu.kwejk.storage.StorageService;


@Controller
public class FileUploadController {

    private final StorageService storageService;
    GifRepository gifRepository;

    @Autowired
    GifListService gifListService;
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
//service to show give from filePath and to add Gif to List<Gif>
        gifListService.buildGifFilePath(file);
//GifDetails for newGif
        gifListService.addGifToServiceList(file);
        gifListService.addGifToList(file);

//service to show give from filePath and to add Gif to List<Gif>

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
//service to show give from filePath and to add Gif to List<Gif>
        redirectAttributes.addFlashAttribute("filepath",gifListService.buildGifFilePath(file));
        redirectAttributes.addFlashAttribute("newGif",gifListService.getGifsFromList());
// service to show give from filePath and to add Gif to List<Gif>
        return "redirect:/upload";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
