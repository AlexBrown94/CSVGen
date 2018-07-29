The program is setup to use a MySQL instance running on port 3306, with root/root as the userName/password.
You will need to set this up on your own machine to get this running. 
Call each java file as follows

java CSVGen <number of Lines> <filePath to write to, must end in .csv>
java CSVdb <filePath to read file from, should use same as previous> <MySQL database url, I used localHost for my local instance>
java CSVTest



Notes on Performance
The CSV generates only unique names, to do this I used a list of First Names and a list of Last Name and combine them randomly. I used a set to make sure that no duplicate entries are entered. As of now, the program can reliably generate 10000 unique names, there will be duplicates but they wont make it into the file, the program will keep attempting to generate a unique name.
 If there was more names to pull from then the program could generate even more names possibly even faster considering there will be less chance of duplicates occuring, possibly slowing the program down.
Using a HashSet to contain all the names uses significantly more memory than just using an ArrayList, and is slower to retrieve the data. However it doesn't allow duplicates which is why I chose it. In a more real world example, if performance was an issue, I probably would have used ArayList instead and then done the duplication check myself.
