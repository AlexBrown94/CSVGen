import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class CSVTest
{

    public static void main(String[] args)
    {
        CSVUnigueNamesTest(Paths.get(args[0]));
    }
    @Test
    public static void CSVUnigueNamesTest(Path filePath)
    {
        try
        {
            String firstName;
            String lastName;
            Set<String> nameSet = new HashSet<String>();

            BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
            br.readLine();
            String data = br.readLine();

            while(data != null)
            {
                firstName = data.split(",")[0];
                lastName = data.split(",")[1];
                if(nameSet.add(firstName + "," + lastName) == false)    //Set returns false if an entry already exists in the set
                {
                    fail("A duplicate " + firstName + "," + lastName + " already exists");
                }
                data = br.readLine();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //@Test
    //public void
}