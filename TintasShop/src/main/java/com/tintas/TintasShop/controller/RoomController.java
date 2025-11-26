package com.tintas.TintasShop.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tintas.TintasShop.model.PaletteSuggestion;
import com.tintas.TintasShop.model.Room;
import com.tintas.TintasShop.repository.RoomRepository;
import com.tintas.TintasShop.service.GeminiService;

@Controller
@RequestMapping("/inspiration")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GeminiService geminiService;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "inspiration";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("room", new Room());
        return "inspiration-form";
    }

    @PostMapping
    public String createRoom(@ModelAttribute Room room, Model model) {
        room.setCreatedAt(LocalDateTime.now());
        Room saved = roomRepository.save(room);
        List<PaletteSuggestion> suggestions = geminiService.generatePalettes(saved);
        model.addAttribute("room", saved);
        model.addAttribute("suggestions", suggestions);
        return "inspiration-view";
    }

    @GetMapping("/view/{id}")
    public String viewRoom(@PathVariable Long id, Model model) {
        Optional<Room> r = roomRepository.findById(id);
        if (r.isEmpty()) return "redirect:/inspiration";
        Room room = r.get();
        List<PaletteSuggestion> suggestions = geminiService.generatePalettes(room);
        model.addAttribute("room", room);
        model.addAttribute("suggestions", suggestions);
        return "inspiration-view";
    }
}
