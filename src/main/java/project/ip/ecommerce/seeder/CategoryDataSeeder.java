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
            if (categoryRepository.count() == 0) {
                // Only seed data if the repository is 1
                
                Category category1 = new Category();
                category1.setCategoryName("Addidas");
                categoryRepository.save(category1);

                Category category2 = new Category();
                category2.setCategoryName("Nike");
                categoryRepository.save(category2);

                Category category3 = new Category();
                category3.setCategoryName("Puma");
                categoryRepository.save(category3);

                Category category4 = new Category();
                category4.setCategoryName("Converse");
                categoryRepository.save(category4);

                Category category5 = new Category();
                category5.setCategoryName("Vans");
                categoryRepository.save(category5);

                Category category6 = new Category();
                category6.setCategoryName("Jordan");
                categoryRepository.save(category6);

                Category category7 = new Category();
                category7.setCategoryName("New Balance");
                categoryRepository.save(category7);

                Category category8 = new Category();
                category8.setCategoryName("361");
                categoryRepository.save(category8);

                logger.info("Category data seeded successfully.");
            } else {
                logger.info("Category data already exists. Skipping seeding.");
            }
        } catch (Exception e) {
            logger.error("Error while seeding category data: {}", e.getMessage());
        }
    }
}