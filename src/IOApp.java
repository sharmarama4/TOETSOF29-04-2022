import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOApp {
    public static void main(String[] args) {

        Path pathOfMessage = Paths.get("../../FromRambaToPearl/message.txt");
        try {
            if (Files.notExists(pathOfMessage.getParent())) {
                Files.createDirectory(pathOfMessage.getParent());
            }

            if (Files.notExists(pathOfMessage)) {
                Files.createFile(pathOfMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path pathOfAnimal = Path.of("../../FromRambaToPearl/animal.txt");
        try {
            if (Files.notExists(pathOfAnimal.getParent())) {
                Files.createDirectory(pathOfAnimal.getParent());
            }

            if (Files.notExists(pathOfAnimal)) {
                Files.createFile(pathOfMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("--My paths---");
        System.out.println(pathOfMessage.getParent());
        System.out.println(pathOfAnimal.getParent());

        System.out.println("This is the message for you");

        try (FileWriter fileWriter = new FileWriter(pathOfMessage.toString());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            bufferedWriter.write("I am writing the message for you Pearl\n");
            bufferedWriter.write("You are my favorite instructor and you are so friendly and clear");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(pathOfMessage.toString());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------");
        System.out.println("This is the message for Animal");
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(pathOfAnimal.toString())
        )) {

            bufferedWriter.write("Cow is famous animal in our country\n");
            bufferedWriter.write("She gives us a lof milk and I love her");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(pathOfAnimal.toString());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         System.out.println("------");
        System.out.println("Here is my Animal class");
        Animal animal = new Animal("Cat",false);
        Animal animal1=new Animal("Cow",false);
        Animal animal2 =new Animal("Sheep",true);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream
                (new FileOutputStream("../../FromRambaToPearl/animal.txt"))) {
            objectOutputStream.writeObject(animal);
            objectOutputStream.writeObject(animal1);
            objectOutputStream.writeObject(animal2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream
                (new FileInputStream("../../FromRambaToPearl/animal.txt"))) {
            // Animal someone=(Animal) objectInputStream.getObjectInputFilter();
            //   System.out.println(someone);This is for one Animal
            Animal someone;
            while ((someone = (Animal) objectInputStream.readObject()) != null) {
                System.out.println(someone);//This for more Animal
            }
        } catch (EOFException e){
            System.out.println("Happy ending of my File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}