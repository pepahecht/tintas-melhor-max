package com.tintas.TintasShop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tintas.TintasShop.model.Tinta;
import com.tintas.TintasShop.repository.TintaRepository;

@Controller
@RequestMapping("/products")
public class TintaController {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Autowired
    private TintaRepository tintaRepository;

    @GetMapping
    public String listTintas(Model model) {
        model.addAttribute("products", tintaRepository.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Tinta());
        return "product-form";
    }

    @PostMapping
    public String saveTinta(@ModelAttribute Tinta tinta, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
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
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Tinta> tinta = tintaRepository.findById(id);
        if (tinta.isPresent()) {
            model.addAttribute("product", tinta.get());
            return "product-edit";
        }
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String viewTinta(@PathVariable Long id, Model model) {
        Optional<Tinta> tinta = tintaRepository.findById(id);
        if (tinta.isPresent()) {
            model.addAttribute("product", tinta.get());
            return "product-view";
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteTinta(@PathVariable Long id) {
        tintaRepository.deleteById(id);
        return "redirect:/products";
    }
}
