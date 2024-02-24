package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;
    Car car;
    Car car2;
    Car car3;
    Car car4;
    @BeforeEach
    void setUp() {
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

        this.car4 = new Car();
        this.car4.setCarId(null);
        this.car4.setCarName("Xenia");
        this.car4.setCarColor("Silver");
        this.car4.setCarQuantity(50);
    }

    @Test
    void testCreateAndFind() {
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }
    @Test
    void testCreateIfMinusQuantityAndFind() {
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertEquals(car2.getCarName(), savedCar.getCarName());
        assertEquals(car2.getCarColor(), savedCar.getCarColor());
        assertEquals(0, savedCar.getCarQuantity());

    }
    @Test
    void testCreateIfNoCarIdAndFind() {
        carRepository.create(car4);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertNotEquals(null, savedCar.getCarId());
        assertEquals(car4.getCarName(), savedCar.getCarName());
        assertEquals(car4.getCarColor(), savedCar.getCarColor());
        assertEquals(car4.getCarQuantity(), savedCar.getCarQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneCar() {
        carRepository.create(car);
        carRepository.create(car3);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car3.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindByIdIfEmpty() {
        Car carEmpty = carRepository.findById(car3.getCarId());
        assertEquals(null,carEmpty);
    }
    @Test
    void testFindById() {
        carRepository.create(car);
        Car savedCar = carRepository.findById(car.getCarId());
        assertEquals(car, savedCar);
    }
    @Test
    void testEditAndFind() {
        carRepository.create(car);

        car.setCarQuantity(99);
        carRepository.update(car.getCarId(), car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }
    @Test
    void testEditIfMinusQuantityAndFind() {
        carRepository.create(car);

        car.setCarQuantity(-99);
        carRepository.update(car.getCarId(), car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(0, savedCar.getCarQuantity());
    }
    @Test
    void testEditNotSavedCarAndFind() {
        carRepository.create(car);

        car3.setCarQuantity(30);
        carRepository.update(car3.getCarId(), car3);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testDeleteAndFindIfEmpty() {
        carRepository.create(car);

        carRepository.delete(car.getCarId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testDeleteAndFindIfMoreThanOneCar() {
        carRepository.create(car);
        carRepository.create(car2);

        carRepository.delete(car.getCarId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertNotEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testDeleteEmptyCar() {
        carRepository.create(car);

        carRepository.delete(car.getCarId());
        carRepository.delete(car.getCarId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

}
