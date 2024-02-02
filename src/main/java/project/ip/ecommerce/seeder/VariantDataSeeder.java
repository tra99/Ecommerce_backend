package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.repository.VariantRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class VariantDataSeeder implements CommandLineRunner {

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
            List<String> productIds = productRepository.findAllProductIds();
            
            for (String productId : productIds) {
                Product product = productRepository.findById(productId).orElse(null);

                if (product == null) {
                    System.err.println("Product with ID " + productId + " does not exist.");
                }

                List<Variant> variantsForProduct = generateVariantsForProduct(product);
                variantRepository.saveAll(variantsForProduct); 
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private List<Variant> generateVariantsForProduct(Product product) {
        String productName = product.getName();
        
        if ("Product1".equals(productName)) {
            return List.of(
                    createVariant("ColorA", "SizeA","Product1", product),
                    createVariant("ColorB", "SizeB","Product1", product)
            );
        } else if ("Product2".equals(productName)) {
            return List.of(
                    createVariant("ColorC", "SizeC","Product2", product),
                    createVariant("ColorD", "SizeD","Product2", product)
            );
        }
        // Add more conditions for other products
    
        return Collections.emptyList();
    }

    private Variant createVariant(String color, String size,String name,Product product) {
        Variant variant = new Variant();
        variant.setId(UUID.randomUUID().toString());
        variant.setColor(color);
        variant.setSize(size);
        variant.setName(name);
        variant.setProduct(product);
        return variant;
    }
}
