package hu.nye.progkor.fruits.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.model.exception.NotFoundException;
import hu.nye.progkor.fruits.service.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FruitServiceImplTest {
  private static final Fruit APPLE = new Fruit(1L, "Apple", "Golden delicious", 25, false);
  private static final Fruit PEAR = new Fruit(2L, "Pear", "Asian pear", 39, true);
  private static final Fruit PLUM = new Fruit(3L, "Plum", "Damson Plum", 10, false);
  private static final List<Fruit> FRUITS = List.of(APPLE, PEAR, PLUM);

  public static final Long UNKNOWN_FRUIT_ID = -23L;

  private FruitService underTest;
  private FruitServiceImpl fruitServiceImpl;

  @BeforeEach
  void setUp() {
    underTest = new FruitServiceImpl(FRUITS);
    fruitServiceImpl = new FruitServiceImpl();
  }

  @Test
  void getAllFruitsShouldReturnAllFruits() {
    // when
    final List<Fruit> actual = underTest.getAllFruits();
    // then
    assertThat(actual).isEqualTo(FRUITS);
  }

  @Test
  void getFruitShouldReturnFruitWhenGivenIdOfExistingFruit() {
    // when
    final Fruit actual = underTest.getFruit(PEAR.getId());
    // then
    assertThat(actual).isEqualTo(PEAR);
  }

  @Test
  void getFruitShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingFruit() {
    // when then
    assertThrows(NotFoundException.class, () -> underTest.getFruit(UNKNOWN_FRUIT_ID));
  }

  @Test
  void createFruitShouldReturnFruitWhenDelegateIt() {
    // given
    final Fruit redGrape = new Fruit(null, "Grape", "Red grape", 23, null);
    final Fruit expectedFruit = new Fruit(4L, "Grape", "Red grape", 23, false);
    // when
    final Fruit actual = underTest.addFruit(redGrape);
    // then
    assertThat(actual).isEqualTo(expectedFruit);
  }

  @Test
  void createFruitShouldIncreaseFruitQuantityWhenItExisting() {
    // given
    final Fruit pearFruit = new Fruit(null, "Pear", "Asian pear", 23, true);
    final Fruit expectedFruit = new Fruit(2L, "Pear", "Asian pear", 62, true);
    // when
    final Fruit addPearFruit = underTest.addFruit(pearFruit);
    final Fruit actual = underTest.updateFruit(expectedFruit.getId(), addPearFruit);
    System.out.println(actual + "\n" + expectedFruit);
    // then
    assertThat(actual).isEqualTo(expectedFruit);
  }

  @Test
  void updateFruitShouldReturnUpdatedFruitWhenGivenIdOfExistingFruit() {
    // given
    final Fruit redGrape = new Fruit(null, "Grape", "Red grape", 23, true);
    final Fruit expectedRolePlay = new Fruit(PLUM.getId(), "Grape", "Red grape", 23, true);
    // when
    final Fruit actual = underTest.updateFruit(PLUM.getId(), redGrape);
    // then
    assertThat(actual).isEqualTo(expectedRolePlay);
  }

  @Test
  void updateFruitShouldReturnUpdatedFruitWhenGivenIdOfExistingFruitButOrganicIsNull() {
    // given
    final Fruit redGrape = new Fruit(null, "Grape", "Red grape", 23, null);
    final Fruit expectedRolePlay = new Fruit(PLUM.getId(), "Grape", "Red grape", 23, false);
    // when
    final Fruit actual = underTest.updateFruit(PLUM.getId(), redGrape);
    // then
    assertThat(actual).isEqualTo(expectedRolePlay);
  }

  @Test
  void updateFruitShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingFruit() {
    // given
    final Fruit redGrape = new Fruit(null, "Grape", "Red grape", 23, false);
    // when then
    assertThrows(NotFoundException.class, () -> underTest.updateFruit(UNKNOWN_FRUIT_ID, redGrape));
  }

  @Test
  void deleteFruitShouldDeleteFruitWhenGivenIdOfFruit() {
    // given
    final List<Fruit> expectedFruit = List.of(PEAR, PLUM);
    // when
    underTest.deleteFruit(APPLE.getId());
    final List<Fruit> actual = underTest.getAllFruits();
    // then
    assertThat(actual).isEqualTo(expectedFruit);
  }

  @Test
  void fruitServiceImplShouldAddFruitToDataBase() {

  }
}
