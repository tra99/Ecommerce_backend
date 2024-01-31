package project.ip.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import project.ip.ecommerce.entity.Promotion;
import project.ip.ecommerce.repository.PromotionRepository;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(String id) {
        return promotionRepository.findById(id);
    }

    public Promotion createOrUpdatePromotion(Promotion promotion) {
        // Add any additional logic/validation if needed
        return promotionRepository.save(promotion);
    }

    public void deletePromotionById(String id) {
        promotionRepository.deleteById(id);
    }
}