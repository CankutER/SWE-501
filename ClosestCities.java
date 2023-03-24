import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ClosestCities {
  public static void main(String[] args) throws FileNotFoundException {
    String cities = "cities.txt";

    File citiesFile = new File(cities);
    // Exit if file is not found
    if (!citiesFile.exists()) {
      System.out.printf("%s can not be found.", cities);
      System.exit(1);
    }

    Scanner citiesText = new Scanner(citiesFile);
    // consume redundant first line
    citiesText.nextLine();
    // get line number to find array size
    int lineNumber = 0;
    while (citiesText.hasNextLine()) {
      lineNumber++;
      citiesText.nextLine();
    }
    //
    String[] cityNames = new String[lineNumber];
    double[][] cityCoords = new double[lineNumber][2];
    citiesText.close();
    // re-instantiate scanner and read data to fill arrays
    citiesText = new Scanner(citiesFile);
    citiesText.nextLine();
    for (int i = 0; i < lineNumber; i++) {
      cityNames[i] = citiesText.next();
      cityCoords[i][0] = citiesText.nextDouble();
      cityCoords[i][1] = citiesText.nextDouble();
      System.out.println(cityNames[i] + " " + cityCoords[i][0] + " " + cityCoords[i][1]);
    }
    citiesText.close();
    //

    // Calculate the distances and find minimum

    String closestCities = "nothing found";
    double minimumDistance = Double.MAX_VALUE;
    for (int i = 0; i < cityCoords.length; i++) {
      for (int j = 0; j < cityCoords.length; j++) {

        // dont compare the same cities to avoid zero distance
        if (i == j) {
          continue;
        }
        //
        double distance = Math.hypot((cityCoords[i][0] - cityCoords[j][0]), (cityCoords[i][1] - cityCoords[j][1]));
        if (distance < minimumDistance) {
          minimumDistance = distance;
          closestCities = cityNames[i] + " and " + cityNames[j];
        }
      }
    }
    System.out.println("Closest Cities are: " + closestCities);
    System.out.println("Distance is: " + minimumDistance);
  }
}