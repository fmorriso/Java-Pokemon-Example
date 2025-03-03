import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Change this class name to a meaningful name for one record from the dataset.
* Example: Country,Year,Birth Rate,Business Tax Rate,CO2 Emissions,Days to Start Business,Ease of Business,Energy Usage,GDP,Health Exp % GDP,Health Exp/Capita,Hours to do Tax,Infant Mortality Rate,Internet Usage,Lending Interest,Life Expectancy Female,Life Expectancy Male,Mobile Phone Usage,Number of Records,Population 0-14,Population 15-64,Population 65+,Population Total,Population Urban,Region,Tourism Inbound,Tourism Outbound
*  */
class OneItem
{

    // Add at least 3 attributes found in the data file
    private String state;
    private String year;
    private String month;

    // Add a constructor that initializes the attributes
    public OneItem(String country, String date, String birthRate){
        this.state = country;
        this.year = date;
        this.month = birthRate;
    }

    // Add any getters and toString methods that you need
    public String getState() { return state; }
    public String getYear() { return year; }
    public String getBirthRate() { return month; }

    @Override
    public String toString() {
        return "OneItem{" +
                "country='" + state + '\'' +
                ", date='" + year + '\'' +
                ", birthRate='" + month + '\'' +
                '}';
    }
}

/* Change this class name to a meaningful name for the whole dataset. */
public class Data
{
    // Declare an ArrayList of your class type
    List<OneItem> lstOneItems = new ArrayList<OneItem>();

    // Write a method to read in the data (it may throw an exception)
    // STATE,Population in 2016 from census.gov,Non-insured percentage (percentage of people who do not have health insurance in 2016 from CDC.gov),Firearms Death RATE of death from firearms for every 100K population in 2016 from CDC.gov,Firearm Deaths totals in 2016,Drug Overdose death rates per 100K population 2016,Drug overdose deaths total,URL for state info: base url https://www.cdc.gov
    public void importData(){
        Path filePath = Paths.get("data\\StateData.csv");
        String fileNameWithPath = filePath.toAbsolutePath().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameWithPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Split line by commas
                System.out.println(Arrays.toString(values)); // Print each row's values
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Loop through each row to split it into attributes.
    //     Create a new  object from the attributes.
    //     and save it into the ArrayList.

    // Write a method that does something with the data
    // for example find the object with a min or max attribute value
    // or print out all the objects of a certain attribute value.

    public static void main(String[] args) throws IOException
    {
        // STATE,Population in 2016 from census.gov,Non-insured percentage (percentage of people who do not have health insurance in 2016 from CDC.gov),Firearms Death RATE of death from firearms for every 100K population in 2016 from CDC.gov,Firearm Deaths totals in 2016,Drug Overdose death rates per 100K population 2016,Drug overdose deaths total,URL for state info: base url https://www.cdc.gov
        Data d = new Data(); // change to your class name
        // Call your method to read in the data
        d.importData();

        // Call your method to do something with the data

    }
}

