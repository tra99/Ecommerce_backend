package project.ip.ecommerce.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.repository.VariantRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class VariantDataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(VariantDataSeeder.class);

    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;

    @Autowired
    public VariantDataSeeder(VariantRepository variantRepository, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        seedVariantData();
    }

    private void seedVariantData() {
        try {
            if (variantRepository.count() == 0) {
                // Only seed data if no variants exist

                List<String> productIds = productRepository.findAllProductIds();

                for (String productId : productIds) {
                    Product product = productRepository.findById(productId).orElse(null);

                    if (product == null) {
                        logger.error("Product with ID {} does not exist.", productId);
                        continue;
                    }

                    // Check if variants already exist for the product
                    List<Variant> existingVariants = variantRepository.findByProduct(product);
                    if (!existingVariants.isEmpty()) {
                        logger.info("Variants already exist for product: {}", product.getName());
                        continue; // Skip seeding for this product
                    }

                    List<Variant> newVariants = generateVariantsForProduct(product);
                    variantRepository.saveAll(newVariants);
                }
                logger.info("Variant data seeded successfully.");
            } else {
                logger.info("Variant data already exists. Skipping seeding.");
            }
        } catch (Exception e) {
            logger.error("Error while seeding variant data: {}", e.getMessage());
        }
    }
    private List<Variant> generateVariantsForProduct(Product product) {
        String productName = product.getName();

        List<Variant> newVariants;
        if ("Product1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorA", "SizeA", "Product1", product),
                    createVariant("ColorB", "SizeB", "Product1", product)
            );
        } else if ("Product2".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Product2", product),
                    createVariant("ColorD", "SizeD", "Product2", product)
            );
        } else {
            // Add more conditions for other products
            newVariants = Collections.emptyList();
        }
        return newVariants;
    }

    private Variant createVariant(String color, String size, String name, Product product) {
        Variant variant = new Variant();
        variant.setId(UUID.randomUUID().toString());
        variant.setColor(color);
        variant.setSize(size);
        variant.setName(name);
        variant.setProduct(product);
        return variant;
    }
}
