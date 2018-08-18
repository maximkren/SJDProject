package info.sjd.emulators;

import java.util.Random;

public class RandomValueGenerator {

	private int randomValue;

	
	
	public int getRandomValue(int minValue, int maxValue) {
		
		int temp = new Random().nextInt(maxValue);
		
		if (temp >= minValue) {
			randomValue = temp;
		} else {
			getRandomValue(minValue, maxValue);
		}

		return randomValue;
	}

}
