package id.ac.ui.cs.advprog.eshop.controller;


import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HomeControllerTest {

    @Test
    void testHomeProductViewName() {
        HomeController homeController = new HomeController();
        String viewName = homeController.homeProduct(mock(Model.class));

        assertEquals("homeProduct", viewName);
    }

}
