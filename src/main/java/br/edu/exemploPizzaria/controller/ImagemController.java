package br.edu.exemploPizzaria.controller;

import br.edu.exemploPizzaria.model.Imagem;
import br.edu.exemploPizzaria.model.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/imagem")
public class ImagemController {

    private static String caminhoImagem = "/home/bruno/Documentos";
    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping("salvar")
    public ModelAndView salvar(Imagem imagem , @RequestParam("file")MultipartFile arquivo){
        try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagem+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);
                imagem.setNomeImagem(arquivo.getOriginalFilename());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        imagemRepository.save(imagem);
        ModelAndView modelAndView = new ModelAndView("exibirImagem");
        modelAndView.addObject("imagens", imagemRepository.findAll());
        return modelAndView;
    }


    @GetMapping("/mostrarimagem/{imagem}")
    @ResponseBody
    public byte[] exibirImagens(@PathVariable String imagem, Model model){
        File imagemArquivo = new File(caminhoImagem+imagem);
        if (imagem != null || imagem.trim().length() >0){
            try{
                model.addAttribute("imagens",imagemRepository.findAll());
                return Files.readAllBytes(imagemArquivo.toPath());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return new byte[0];
    }


    @GetMapping
    public String uparImagem(){
        return "uparImagem";
    }

    @GetMapping("/mostrarimagem")
    public ModelAndView listaImagens(){
        ModelAndView modelAndView = new ModelAndView("exibirImagem");
        modelAndView.addObject("imagens", imagemRepository.findAll());
        return modelAndView;
    }
//falta as restrições de tamanho de imagem
}
