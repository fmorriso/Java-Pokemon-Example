import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void importDataFromFile(){
        String location, month, period, indicator;
        int year, dataValue;
        String[] tokens = null;
        Path filePath = Paths.get("data\\StateData.csv");
        String fileNameWithPath = filePath.toAbsolutePath().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameWithPath))) {
            String line;
            // Loop through each row to split it into attributes.
            while ((line = reader.readLine()) != null) {
                OneItem oneItem = null;
                try {
                    oneItem = extractItemFromText(line);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //     Create a new  object from the attributes.
                //     and save it into the ArrayList.
                //lstOneItems.add( oneItem );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OneItem extractItemFromText(String line){
        String[] tokens = line.split(","); // Split line by commas
        String location = tokens[0];
        int year = tryParseInt(tokens[1]);
        String month = tokens[2];
        String period = tokens[3];
        String indicator = tokens[4];
        int dataValue = tryParseInt(tokens[5]);
        return new OneItem(location, year, month, period, indicator, dataValue);
    }

    /**
     * Import a list of OneItem instances from a .csv file stored on the web in DropBox and publicly accessible.
     */
    public void importDateFromUrl() {
        // IMPORTANT NOTE: When reading a text file from DropBox, you MUST change the end of the generated
        // public link from &dl=0 to &dl=1; otherwise, you get HTML instead of text.
        final String fileURL = "https://www.dropbox.com/s/t8vh8a1rgq41d86/StateData.csv?st=p2b1ypzw&dl=1";
        try {
            URI uri = new URI(fileURL); // because new URL(fileUrl) has been deprecated.
            URL url = uri.toURL();

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";

            while ((line = reader.readLine()) != null) {
                OneItem oneItem = extractItemFromText(line);
                lstOneItems.add( oneItem );
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
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
        //d.importDataFromFile();
        d.importDateFromUrl();

        // Call your method to do something with the data
        d.display2023Information();

    }
}

