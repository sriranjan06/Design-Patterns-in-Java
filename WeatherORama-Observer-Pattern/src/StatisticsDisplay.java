public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

//	public void update() {
//		tempSum += temp;
//		numReadings++;
//
//		if (temp > maxTemp) {
//			maxTemp = temp;
//		}
//
//		if (temp < minTemp) {
//			minTemp = temp;
//		}
//
//		display();
//	}

	public void update() {
		float temp = weatherData.getTemperature(); // Retrieve the temperature correctly
		tempSum += temp;
		numReadings++;

		if (temp > maxTemp) { maxTemp = temp; }
		if (temp < minTemp) { minTemp = temp; }

		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}
}
