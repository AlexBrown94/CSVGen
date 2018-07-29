import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.*;

public class CSVGen {

    private int numOfLines = 0;
    private FileOutputStream fos;
    protected Path filePath;

    public static void main(String[] args)
    {
        CSVGen csv = new CSVGen();
        csv.init(args);
    }

    public void init(String[] args)
    {
        try
        {
            numOfLines = Integer.parseInt(args[0]);
            filePath = Paths.get(args[1]);

            if(1 < numOfLines && numOfLines < 10000)
            {
                GenerateCSV(numOfLines, filePath);
            }
            else
                System.out.println("Number of lines must be between 1 and 10000. NumOfLines:" + numOfLines);

            System.out.println("Num of Lines:" + numOfLines + "\n" + filePath.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void GenerateCSV(int numOfLines, Path filePath)
    {
        Map nameMap = new HashMap();
        PrintWriter pw;

        for(int i = 0; i < numOfLines; i++)
        {
            Person a = new Person();
            String tempKey = a.firstName + "," + a.lastName;
            if(nameMap.get(tempKey) != null)    //Check to see if entry already exists
            {
                System.out.println(tempKey + " already exists");
                i--;
            }
            else
                nameMap.put(tempKey, a.age);
        }

        try {
            if (filePath.toString().endsWith(".csv")) {
                System.out.println("Writing to file");
                pw = new PrintWriter(filePath.toAbsolutePath().toString());
                pw.write("firstName,lastName,age\n");
                nameMap.forEach((k, v) -> {             //Write each key value pair to the file, each key is a firstName,lastName.  The value then is the age
                    pw.write(k + "," + v + "\n");
                });
                pw.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}

 class Person
{
    String firstName;
    String lastName;
    int age;

    public Person()
    {
         String[] firstNames = new String[] {
                "Ada", "Albert", "Alexandra", "Alfredo", "Allen", "Andre", "Angelica",
                "Anna", "Anthony", "Antonio", "Ashley", "Audrey", "Beatrice",
                "Benjamin", "Billy", "Bobby", "Bradley", "Bryant", "Candace",
                "Carole", "Carrie", "Claire", "Clifford", "Clint", "Clyde", "Cory",
                "Dale", "Danielle", "Daryl", "Delia", "Devin", "Douglas", "Eddie",
                "Ella", "Erica", "Erika", "Eva", "Frank", "Gayle", "George", "Georgia",
                "Geraldine", "Gina", "Gwen", "Hector", "Homer", "Irene", "James",
                "Jamie", "Jeremiah", "Joann", "Josefina", "Juan", "Karen", "Kenneth",
                "Laurie", "Lee", "Leland", "Leroy", "Levi", "Lewis", "Lillian",
                "Lillie", "Lorenzo", "Louise", "Lucas", "Lynn", "Marc", "Marcella",
                "Marlon", "Marvin", "Micheal", "Miranda", "Miriam", "Misty", "Naomi",
                "Natasha", "Nelson", "Oliver", "Pete", "Rafael", "Randall", "Raul",
                "Rebecca", "Reginald", "Roger", "Ruby", "Rufus", "Sabrina", "Sean",
                "Steven", "Stuart", "Terence", "Terry", "Van", "Velma", "Vincent",
                "Wanda", "Willard", "Winifred"
        };
         String[] lastNames = new String[] {
                "Adkins", "Aguilar", "Anderson", "Armstrong", "Arnold", "Bailey",
                "Banks", "Barrett", "Bates", "Bennett", "Bowers", "Bradley", "Brown",
                "Bryant", "Buchanan", "Bush", "Butler", "Cain", "Carlson", "Carroll",
                "Cummings", "Diaz", "Doyle", "Duncan", "Dunn", "Fernandez", "Foster",
                "Fowler", "Fox", "Francis", "French", "Garrett", "Gill", "Glover",
                "Goodwin", "Gordon", "Grant", "Griffin", "Gross", "Guerrero", "Hale",
                "Harvey", "Holland", "Ingram", "Jacobs", "James", "Lamb", "Lowe",
                "Lucas", "Mann", "Marshall", "Martin", "Martinez", "May", "Mcdaniel",
                "Mendoza", "Meyer", "Moody", "Moreno", "Nelson", "Nichols", "Norton",
                "Obrien", "Osborne", "Padilla", "Page", "Parks", "Parsons", "Payne",
                "Pearson", "Powell", "Reese", "Reeves", "Reyes", "Reynolds",
                "Richardson", "Rios", "Ross", "Russell", "Saunders", "Sharp", "Simon",
                "Smith", "Steele", "Stephens", "Stokes", "Summers", "Thomas",
                "Thompson", "Tyler", "Wagner", "Ward", "Washington", "Watkins",
                "Watson", "Weber", "West", "Willis", "Young", "Zimmerman"
        };

        Random r = new Random();
        this.age = r.nextInt(100 - 1) + 1;

        this.firstName = firstNames[r.nextInt(firstNames.length)];
        this.lastName = lastNames[r.nextInt(lastNames.length)];
    }

    public String toString()
    {
        return firstName + "," + lastName +"," + age;
    }
}
