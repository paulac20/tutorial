package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/* Indica que se inicie el contexto de la aplicación */
@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    public static final String CATEGORY_NAME = "CAT1";
    public static final Long EXISTS_CATEGORY_ID = 1L;

    @Test
    public void findAllShouldReturnAllCategories() {

        List<Category> list = new ArrayList<>();
        list.add(mock(Category.class));

        // Comprobamos el método findAll()
        when(categoryRepository.findAll()).thenReturn(list);

        List<Category> categories = categoryService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    @Test
    public void saveNotExistsCategoryIdShouldInsert() {

        // Construimos un objeto CategoryDto para darle nombre a la categoría
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class);

        // Pasamos un ID nulo para ponerlo a 0
        categoryService.save(null, categoryDto);

        // Capturamos la categoría que se proporciona al repository al intentar realizar
        // la acción ficticia de guardado
        verify(categoryRepository).save(category.capture());

        // Comprobamos que el valor sea el que se proporciona en la invocación
        assertEquals(CATEGORY_NAME, category.getValue().getName());
    }

    @Test
    public void saveExistsCategoryIdShouldUpdate() {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        Category category = mock(Category.class);
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        categoryService.save(EXISTS_CATEGORY_ID, categoryDto);

        verify(categoryRepository).save(category);
    }

    @Test
    public void deleteExistsCategoryIdShouldDelete() throws Exception {

        Category category = mock(Category.class);
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        categoryService.delete(EXISTS_CATEGORY_ID);

        verify(categoryRepository).deleteById(EXISTS_CATEGORY_ID);
    }

}
