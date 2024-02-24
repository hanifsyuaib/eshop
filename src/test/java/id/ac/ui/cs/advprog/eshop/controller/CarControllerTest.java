package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarControllerTest {
    @Mock
    CarService carService;
    @InjectMocks
    CarController carController;
    Car car;
    Car car2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Avanza");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(100);

        this.car2 = new Car();
        this.car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        this.car2.setCarName("CRV");
        this.car2.setCarColor("Hitam");
        this.car2.setCarQuantity(99);
    }
    @Test
    void testCarListPageViewNameAndModel() {
        List<Car> cars = new ArrayList<>();

        cars.add(car);
        cars.add(car2);

        when(carService.findAll()).thenReturn(cars);
        Model model = mock(Model.class);
        String viewName = carController.carListPage(model);

        assertEquals("CarList", viewName);
        verify(model, times(1)).addAttribute("cars", cars);
    }
    @Test
    void testCreateCarPageViewName() {
        String viewName = carController.createCarPage(mock(Model.class));

        assertEquals("CreateCar", viewName);
    }
    @Test
    void testCreateCarPostRedirectsToListPage() {
        String viewName = carController.createCarPost(car, mock(Model.class));

        assertEquals("redirect:listCar", viewName);
    }
    @Test
    void testEditCarPageViewName() {
        Model model = mock(Model.class);
        String viewName = carController.editCarPage(car.getCarId(), model);

        assertEquals("EditCar", viewName);
    }
    @Test
    void testEditCarPostRedirectsToListPage() {
        String viewName = carController.editCarPost(car, mock(Model.class));

        assertEquals("redirect:listCar", viewName);
    }
    @Test
    void testDeleteCarRedirectsToListPage() {
        String viewName = carController.deleteCar(car.getCarId());

        assertEquals("redirect:listCar", viewName);
    }
}
