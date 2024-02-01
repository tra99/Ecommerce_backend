package project.ip.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ip.ecommerce.entity.Promotion;
import project.ip.ecommerce.service.PromotionService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable String id) {
        return promotionService.getPromotionById(id)
                .map(promotion -> new ResponseEntity<>(promotion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        Promotion createdPromotion = promotionService.createOrUpdatePromotion(promotion);
        promotion.setStartDate(LocalDateTime.now());

        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable String id, @RequestBody Promotion promotion) {
        if (!promotionService.getPromotionById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        promotion.setId(id);
        Promotion updatedPromotion = promotionService.createOrUpdatePromotion(promotion);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePromotionById(@PathVariable String id) {
        promotionService.deletePromotionById(id);
        return new ResponseEntity<>("Promotion with ID " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
