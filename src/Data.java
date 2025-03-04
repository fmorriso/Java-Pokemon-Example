import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/* Change this class name to a meaningful name for one record from the dataset.
* Example: Country,Year,Birth Rate,Business Tax Rate,CO2 Emissions,Days to Start Business,Ease of Business,Energy Usage,GDP,Health Exp % GDP,Health Exp/Capita,Hours to do Tax,Infant Mortality Rate,Internet Usage,Lending Interest,Life Expectancy Female,Life Expectancy Male,Mobile Phone Usage,Number of Records,Population 0-14,Population 15-64,Population 65+,Population Total,Population Urban,Region,Tourism Inbound,Tourism Outbound
*  */
class OneItem
{

    // Add at least 3 attributes found in the data file
    private String location;
    private int year;
    private String month;
    private String period;
    private String indicator;
    private int dataValue;

    // Add a constructor that initializes the attributes
    public OneItem(String location, int year, String month){
        this(location, year, month, "Monthly" , "Number of", 0);
    }

    public OneItem(String location, int year, String month, String period, String indicator, int dataValue){
        this.location = location;
        this.year = year;
        this.month = month;
        this.period = period;
        this.indicator = indicator;
        this.dataValue = dataValue;
    }

    // Add any getters and toString methods that you need
    public String getLocation() { return location; }
    public int getYear() { return year; }
    public String getMonth() { return month; }
    public String getBirthRate() { return period; }
    public String getIndicator() { return indicator; }
    public int getDataValue() { return dataValue; }

    @Override
    public String toString() {
        return new StringBuilder().append("OneItem{")
                .append("location='").append(location)
                .append(", year='").append(year)
                .append(", month='").append(month)
                .append(", period='").append(period)
                .append(", indicator='").append(indicator)
                .append(", dataValue='").append(dataValue)
                .append('}').toString();
    }


}

/* Change this class name to a meaningful name for the whole dataset. */
public class Data
{
    // Declare an ArrayList of your class type
    List<OneItem> lstOneItems = new ArrayList<OneItem>();

    private int tryParseInt(String possibleInt){
        try {
            return Integer.parseInt(possibleInt);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Write a method to read in the data (it may throw an exception)
    // STATE,Population in 2016 from census.gov,Non-insured percentage (percentage of people who do not have health insurance in 2016 from CDC.gov),Firearms Death RATE of death from firearms for every 100K population in 2016 from CDC.gov,Firearm Deaths totals in 2016,Drug Overdose death rates per 100K population 2016,Drug overdose deaths total,URL for state info: base url https://www.cdc.gov
    public void importData(){
        String location, month, period, indicator;
        int year, dataValue;
        String[] tokens = null;
        Path filePath = Paths.get("data\\StateData.csv");
        String fileNameWithPath = filePath.toAbsolutePath().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameWithPath))) {
            String line;
            // Loop through each row to split it into attributes.
            while ((line = reader.readLine()) != null) {
                tokens = line.split(","); // Split line by commas
                location = tokens[0];
                year = tryParseInt(tokens[1]);
                month = tokens[2];
                period = tokens[3];
                indicator = tokens[4];
                dataValue = tryParseInt(tokens[5]);
                //     Create a new  object from the attributes.
                //     and save it into the ArrayList.
                lstOneItems.add( new OneItem(location, year, month, period, indicator, dataValue));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write a method that does something with the data
    // for example find the object with a min or max attribute value
    // or print out all the objects of a certain attribute value.
    public void display2023Information(){
        for (OneItem oneItem : lstOneItems){
            if(oneItem.getYear() == 2023){
                System.out.println(oneItem);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        // STATE,Population in 2016 from census.gov,Non-insured percentage (percentage of people who do not have health insurance in 2016 from CDC.gov),Firearms Death RATE of death from firearms for every 100K population in 2016 from CDC.gov,Firearm Deaths totals in 2016,Drug Overdose death rates per 100K population 2016,Drug overdose deaths total,URL for state info: base url https://www.cdc.gov
        Data d = new Data(); // change to your class name

        // Call your method to read in the data
        d.importData();

        // Call your method to do something with the data
        d.display2023Information();

    }
}

