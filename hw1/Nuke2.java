import java.io.*;

class Nuke2 {
    public static void main(String[] arg) throws IOException {

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Please enter a string: ");
        System.out.flush();  

        String inputLine = keyboard.readLine();
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < inputLine.length(); i ++) {
            if (i != 1) {
                newString.append(inputLine.charAt(i));
            }
        }
        System.out.println(newString.toString());
    }
}
