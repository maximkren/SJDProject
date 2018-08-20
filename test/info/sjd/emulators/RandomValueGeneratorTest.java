package info.sjd.emulators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandomValueGeneratorTest {

	@Test
	void testGetRandomValue() {

		int result;
		for (int i = 0; i < 10000; i++) {
			result = new RandomValueGenerator().getRandomValue(100, 1000);


			assertTrue(result >= 100 && result <= 1000);
		}

	}

}
