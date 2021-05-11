package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.ProgLang;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.CategoryDto;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.ProgLangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProgLangRepository progLangRepository;

    public List<Category> getCategory() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        if (categoryRepository.existsByName(categoryDto.getName())) {
            return new ApiResponse("This category name already exists!", false);
        }

        category.setName(categoryDto.getName());
        category.setStarNumber(categoryDto.getStarNumber());
        category.setDescription(categoryDto.getDescription());

        List<Integer> languageId = categoryDto.getLanguageId();
        List<ProgLang> progLangList = new ArrayList<>();

        for (Integer integer : languageId) {
            Optional<ProgLang> optionalProgLang = progLangRepository.findById(integer);
            if (!optionalProgLang.isPresent()) {
                return new ApiResponse("Programming language not found!", false);
            }
            progLangList.add(optionalProgLang.get());
        }
        category.setLanguageList(progLangList);
        categoryRepository.save(category);
        return new ApiResponse("Category added!", true);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {

            Category category = optionalCategory.get();
            if (categoryRepository.existsByName(categoryDto.getName())) {
                return new ApiResponse("This category name already exists!", false);
            }

            category.setName(categoryDto.getName());
            category.setStarNumber(categoryDto.getStarNumber());
            category.setDescription(categoryDto.getDescription());

            List<Integer> languageId = categoryDto.getLanguageId();
            List<ProgLang> progLangList = new ArrayList<>();

            for (Integer integer : languageId) {
                Optional<ProgLang> optionalProgLang = progLangRepository.findById(integer);
                if (!optionalProgLang.isPresent()) {
                    return new ApiResponse("Programming language not found!", false);
                }
                progLangList.add(optionalProgLang.get());
            }
            category.setLanguageList(progLangList);
            categoryRepository.save(category);
            return new ApiResponse("Category editing!", true);
        }
        return new ApiResponse("Category not found!", false);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category deleting!", true);
        } catch (Exception e) {
            return new ApiResponse("Category not found!", false);
        }
    }

}
