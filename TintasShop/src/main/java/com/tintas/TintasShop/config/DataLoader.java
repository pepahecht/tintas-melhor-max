package com.tintas.TintasShop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tintas.TintasShop.model.Tinta;
import com.tintas.TintasShop.repository.TintaRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final TintaRepository tintaRepository;

    public DataLoader(TintaRepository tintaRepository) {
        this.tintaRepository = tintaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tintaRepository.count() == 0) {
            Tinta t1 = new Tinta();
            t1.setName("Ocean Breeze");
            t1.setBrand("Color House");
            t1.setType("Interior");
            t1.setFinish("Matte");
            t1.setColorCode("#4A90E2");
            t1.setDescription("A fresh, calming blue.");
            t1.setPrice(45.99);
            t1.setStock(50);
            t1.setProductImage("");

            Tinta t2 = new Tinta();
            t2.setName("Sunset Glow");
            t2.setBrand("Behr");
            t2.setType("Exterior");
            t2.setFinish("Satin");
            t2.setColorCode("#F5A623");
            t2.setDescription("Warm orange with a sunset vibe.");
            t2.setPrice(39.50);
            t2.setStock(30);

            Tinta t3 = new Tinta();
            t3.setName("Forest Green");
            t3.setBrand("Valspar");
            t3.setType("Interior");
            t3.setFinish("Eggshell");
            t3.setColorCode("#50E3C2");
            t3.setDescription("Deep, natural green.");
            t3.setPrice(42.00);
            t3.setStock(20);

            Tinta t4 = new Tinta();
            t4.setName("Royal Purple");
            t4.setBrand("Benjamin Moore");
            t4.setType("Interior");
            t4.setFinish("Gloss");
            t4.setColorCode("#BD10E0");
            t4.setDescription("Vibrant purple for accents.");
            t4.setPrice(55.25);
            t4.setStock(15);

            tintaRepository.save(t1);
            tintaRepository.save(t2);
            tintaRepository.save(t3);
            tintaRepository.save(t4);
        }
    }
}
