package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.repository.VariantRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        List<String> productIds = Arrays.asList("28a8e40e-37c1-48dc-a082-002dd79ae12e", "3bf4e9c1-efab-444b-b50b-7fd8b95ab7cc", "6be3069b-1169-4a78-9320-7eb79294b0af");

        seedVariantData(productIds);
    }

    private void seedVariantData(List<String> productIds) {
        try {
            for (String productId : productIds) {
                Optional<Product> existingProduct = productRepository.findById(productId);

                if (existingProduct.isPresent()) {
                    seedSingleVariant(existingProduct.get(), "ColorA", "SizeA");
                    seedSingleVariant(existingProduct.get(), "ColorB", "SizeB");

                    // Inserting a variant with color "C" for productId2
                    if ("28a8e40e-37c1-48dc-a082-002dd79ae12e".equals(productId)) {
                        seedSingleVariant(existingProduct.get(), "ColorC", "SizeC");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void seedSingleVariant(Product product, String color, String size) {
        Variant variant = new Variant();
        variant.setId(UUID.randomUUID().toString());
        variant.setColor(color);
        variant.setSize(size);
        variant.setProduct(product);

        variantRepository.save(variant);
    }
}
