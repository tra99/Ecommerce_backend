package project.ip.ecommerce.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.repository.CategoryRepository;

@Component
public class CategoryDataSeeder implements CommandLineRunner{

    // private static final Logger logger = LoggerFactory.getLogger(CategoryDataSeeder.class);

    CategoryRepository categoryRepository;

    // @Autowired
    // public CategoryDataSeeder(CategoryRepository categoryRepository) {
    //     this.categoryRepository = categoryRepository;
    // }

    // @PostConstruct
    // public void seedData() {
    //     logger.info("Data seeding started...");
    //     seedCategories();
    //     logger.info("Data seeding completed.");
    // }

    // private void seedCategories() {
    //     try {
    //         if (categoryRepository.count() == 0) {
    //             // Only seed data if the repository is empty
    //             Category category1 = new Category();
    //             category1.setCategoryName("Electronics");
    //             categoryRepository.save(category1);

    //             Category category2 = new Category();
    //             category2.setCategoryName("Clothing");
    //             categoryRepository.save(category2);
    //         }
    //     } catch (Exception e) {
    //         logger.error("Error during data seeding: {}", e.getMessage());
    //         e.printStackTrace(); 
    //     }
    // }
    @Override
    public void run(String... args)throws Exception{
        loadCatagoryData();
    }

    private void loadCatagoryData(){
        try {
            if (categoryRepository.count() == 0) {
                // Only seed data if the repository is empty
                Category category1 = new Category();
                category1.setCategoryName("Electronics");
                categoryRepository.save(category1);

                Category category2 = new Category();
                category2.setCategoryName("Clothing");
                categoryRepository.save(category2);
            }
        } catch (Exception e) {
            System.err.println(e); 
        }
    }
}
