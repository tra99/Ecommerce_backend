package project.ip.ecommerce.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.repository.CategoryRepository;

@Component
public class CategoryDataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDataSeeder.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDataSeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategoryData();
    }

    private void loadCategoryData() {
        try {
            if (categoryRepository.count() == 1) {
                // Only seed data if the repository is 1
                Category category1 = new Category();
                category1.setCategoryName("Electronics");
                categoryRepository.save(category1);

                Category category2 = new Category();
                category2.setCategoryName("Clothing");
                categoryRepository.save(category2);

                logger.info("Category data seeded successfully.");
            } else {
                logger.info("Category data already exists. Skipping seeding.");
            }
        } catch (Exception e) {
            logger.error("Error while seeding category data: {}", e.getMessage());
        }
    }
}
