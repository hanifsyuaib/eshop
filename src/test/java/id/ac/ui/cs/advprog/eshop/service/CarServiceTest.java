package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class CarServiceTest {
    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarServiceImpl carService;
    Car car;
    Car car2;
    Car car3;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Avanza");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(100);

        this.car2 = new Car();
        this.car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        this.car2.setCarName("CRV");
        this.car2.setCarColor("Hitam");
        this.car2.setCarQuantity(-99);

        this.car3 = new Car();
        this.car3.setCarId("cc888e9f-4c41-460e-8860-99af6af57ss5");
        this.car3.setCarName("Mobilio");
        this.car3.setCarColor("Silver");
        this.car3.setCarQuantity(50);
    }

    @Test
    void testCreateCar() {
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        assertEquals(car, createdCar);
        verify(carRepository, times(1)).create(car);
    }
    @Test
    void testFindAllCars() {
        List<Car> carList = new ArrayList<>();

        carList.add(car);
        carList.add(car3);

        Iterator<Car> iterator = carList.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> foundCars = carService.findAll();

        assertEquals(carList.size(), foundCars.size());
        verify(carRepository, times(1)).findAll();
    }
    @Test
    void testFindCarById() {
        List<Car> carList = new ArrayList<>();

        carList.add(car);
        carList.add(car3);

        when(carRepository.findById(car.getCarId())).thenReturn(car);
        Car foundCar = carService.findById(car.getCarId());

        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById(car.getCarId());
    }

    @Test
    void testEditCar() {
        carService.update(car.getCarId(), car);

        verify(carRepository, times(1)).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        carService.deleteCarById(car.getCarId());

        verify(carRepository, times(1)).delete(car.getCarId());
    }
}
