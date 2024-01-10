package project.ip.ecommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.repository.VariantRepository;

@Service
public class VariantService {
    private final VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public Variant createOrUpdateVariant(Variant variant) {
        return variantRepository.save(variant);
    }

    public Optional<Variant> getVariantById(String id) {
        return variantRepository.findById(id);
    }

    // Other methods for variant CRUD operations or business logic...
}
