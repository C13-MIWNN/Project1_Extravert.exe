package nl.mitw.extrovert.exe.demo.recipesdemo;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Ingredient;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.Unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
class RecipesDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Should calculate calories")
	void shouldCalculateCalories() {

		//Arrange a test case
		int protein = 10;
		int carbs = 20;
		int fat = 10;
		Ingredient ingredient = new Ingredient
				(1L, "Test Ingredient", Unit.GRAM, protein, carbs, fat,null);
		int expectedCalories = (4 * protein) + (4 * carbs) + (9 * fat);

		//Act on the target behavior
		int actualCalories = ingredient.calculateCalories();

		//Assert that the results match as expected
		assertEquals(expectedCalories,actualCalories, "Calculated calories should match expected calories");
	}
}
