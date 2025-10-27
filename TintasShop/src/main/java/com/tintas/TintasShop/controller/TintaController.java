package com.tintas.TintasShop.controller;

import com.tintas.TintasShop.model.Tinta;
import com.tintas.TintasShop.repository.TintaRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tintas")
public class TintaController {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Autowired
    private TintaRepository tintaRepository;

    @GetMapping
    public String listTintas(Model model) {
        model.addAttribute("tintas", tintaRepository.findAll());
        return "tintas-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tinta", new Tinta());
        return "tinta-form";
    }

    @PostMapping
    public String saveTinta(@ModelAttribute Tinta tinta, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            String fileName = originalFilename != null ? StringUtils.cleanPath(originalFilename) : "";
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                imageFile.transferTo(filePath);
                tinta.setProductImage("/images/" + fileName);
            } catch (IOException e) {
            }
        }
        tintaRepository.save(tinta);
        return "redirect:/tintas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Tinta> tinta = tintaRepository.findById(id);
        if (tinta.isPresent()) {
            model.addAttribute("tinta", tinta.get());
            return "tinta-form";
        }
        return "redirect:/tintas";
    }

    @GetMapping("/view/{id}")
    public String viewTinta(@PathVariable Long id, Model model) {
        Optional<Tinta> tinta = tintaRepository.findById(id);
        if (tinta.isPresent()) {
            model.addAttribute("tinta", tinta.get());
            return "tinta-view";
        }
        return "redirect:/tintas";
    }

    @GetMapping("/delete/{id}")
    public String deleteTinta(@PathVariable Long id) {
        tintaRepository.deleteById(id);
        return "redirect:/tintas";
    }
}
