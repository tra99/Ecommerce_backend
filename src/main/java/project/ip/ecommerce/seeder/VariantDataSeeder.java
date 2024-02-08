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

        //Addidas
        if ("Samba OG Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorA", "SizeA", "Samba OG Shoes", product)
            );
        } else if ("Velosamba Leather Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Velosamba Leather Shoes", product)
            );
        } else if ("Y-3 Gazelle".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Y-3 Gazelle", product)
            );
        } else if ("Gazelle Bold Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Gazelle Bold Shoes", product)
            );
        } else if ("Samba ADV Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Samba ADV Shoes", product)
            );
        } else if ("Lite Racer Adapt 4 Slip-On Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Lite Racer Adapt 4 Slip-On Shoes", product)
            );
        } else if ("Ultraboost 1.0 Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Ultraboost 1.0 Shoes", product)
            );
        } else if ("Rivalry 86 Low Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Rivalry 86 Low Shoes", product)
            );
        } else if ("Adizero SL".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Adizero SL", product)
            );
        } else if ("NMD_G1 Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "NMD_G1 Shoes", product)
            );



        //Nike

        } else if ("Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Pegasus Running Shoes", product)
            );
        } else if ("Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Retcon", product)
            );
        } else if ("Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Air Force 1", product)
            );
        } else if ("Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Dunk", product)
            );
        } else if ("Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Air Max", product)
            );
        } else if ("LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "LeBron", product)
            );
        } else if ("Nike Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Nike Blazer", product)
            );
        } else if ("Nike Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Nike Dunk Low", product)
            );
        } else if ("Nike Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Nike Dunk Low Retro Preminum", product)
            );
        } else if ("Nike Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Nike Air Max", product)
            );
        //Jordan

        
        } else if ("Jordan Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Pegasus Running Shoes", product)
            );
        } else if ("Jordan Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Retcon", product)
            );
        } else if ("Jordan Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Air Force 1", product)
            );
        } else if ("Jordan Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Dunk", product)
            );
        } else if ("Jordan Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Air Max", product)
            );
        } else if ("Jordan LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan LeBron", product)
            );
        } else if ("Jordan Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Blazer", product)
            );
        } else if ("Jordan Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Dunk Low", product)
            );
        } else if ("Jordan Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Dunk Low Retro Preminum", product)
            );
        } else if ("Jordan Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Jordan Air Max", product)
            );

        //Converse

        } else if ("Converse Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Pegasus Running Shoes", product)
            );
        } else if ("Converse Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Retcon", product)
            );
        } else if ("Converse Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Air Force 1", product)
            );
        } else if ("Converse Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Dunk", product)
            );
        } else if ("Converse Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Air Max", product)
            );
        } else if ("Converse LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse LeBron", product)
            );
        } else if ("Converse Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Blazer", product)
            );
        } else if ("Converse Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Dunk Low", product)
            );
        } else if ("Converse Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Dunk Low Retro Preminum", product)
            );
        } else if ("Converse Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Converse Air Max", product)
            );

        //Vans

        } else if ("Vans Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Pegasus Running Shoes", product)
            );
        } else if ("Vans Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Retcon", product)
            );
        } else if ("Vans Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Air Force 1", product)
            );
        } else if ("Vans Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Dunk", product)
            );
        } else if ("Vans Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Air Max", product)
            );
        } else if ("Vans LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans LeBron", product)
            );
        } else if ("Vans Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Blazer", product)
            );
        } else if ("Vans Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Dunk Low", product)
            );
        } else if ("Vans Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Dunk Low Retro Preminum", product)
            );
        } else if ("Vans Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Vans Air Max", product)
            );
        

        //Puma

        } else if ("Puma Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Pegasus Running Shoes", product)
            );
        } else if ("Puma Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Retcon", product)
            );
        } else if ("Puma Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Air Force 1", product)
            );
        } else if ("Puma Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Dunk", product)
            );
        } else if ("Puma Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Air Max", product)
            );
        } else if ("Puma LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma LeBron", product)
            );
        } else if ("Puma Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Blazer", product)
            );
        } else if ("Puma Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Dunk Low", product)
            );
        } else if ("Puma Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Dunk Low Retro Preminum", product)
            );
        } else if ("Puma Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "Puma Air Max", product)
            );


        //361

        } else if ("361 Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Pegasus Running Shoes", product)
            );
        } else if ("361 Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Retcon", product)
            );
        } else if ("361 Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Air Force 1", product)
            );
        } else if ("361 Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Dunk", product)
            );
        } else if ("361 Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Air Max", product)
            );
        } else if ("361 LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 LeBron", product)
            );
        } else if ("361 Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Blazer", product)
            );
        } else if ("361 Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Dunk Low", product)
            );
        } else if ("361 Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Dunk Low Retro Preminum", product)
            );
        } else if ("361 Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "361 Air Max", product)
            );

        //New Balance

        } else if ("New Balance Pegasus Running Shoes".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Pegasus Running Shoes", product)
            );
        } else if ("New Balance Retcon".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Retcon", product)
            );
        } else if ("New Balance Air Force 1".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Air Force 1", product)
            );
        } else if ("New Balance Dunk".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Dunk", product)
            );
        } else if ("New Balance Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Air Max", product)
            );
        } else if ("New Balance LeBron".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance LeBron", product)
            );
        } else if ("New Balance Blazer".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Blazer", product)
            );
        } else if ("New Balance Dunk Low".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Dunk Low", product)
            );
        } else if ("New Balance Dunk Low Retro Preminum".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Dunk Low Retro Preminum", product)
            );
        } else if ("New Balance Air Max".equals(productName)) {
            newVariants = List.of(
                    createVariant("ColorC", "SizeC", "New Balance Air Max", product)
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