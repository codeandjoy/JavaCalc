import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringToMathResult{

    // Algorithm example
    // "2+2+2*3+4" ->
    // [+, +, *, +] [2, 2, 2, 3, 4] ->
    // [+, +, +] [2, 2, 6, 4] ->
    // [+, +] [4, 6, 4] ->
    // [+] [10, 4] ->
    // [] [14] ->*
    public static int calc(String inputString) throws InvalidMathInputString{
        List<Integer> numbersList = new ArrayList<Integer>();
        List<String> operationsList = new ArrayList<String>();
        
        //
        // Prepare arrays
        //
        
        // Add dummy space to input string
        inputString += " ";
        // Scan string for digits
        String preNumber = "";
        for(int i = 0; i < inputString.length(); i++){
            if (Character.isDigit(inputString.charAt(i))){
                preNumber += inputString.charAt(i);
            }
            else{
                if (preNumber != ""){
                    numbersList.add(Integer.parseInt(preNumber));
                    preNumber = "";
                }
            }
        }
        System.out.println(numbersList);

        // Scan for operations
        Pattern p = Pattern.compile("[+\\-*\\/]"); // + - * /
        for(int i = 0; i < inputString.length(); i++){
            // if operation
            if (p.matcher(Character.toString(inputString.charAt(i))).matches()){
                operationsList.add(Character.toString(inputString.charAt(i)));
            }
        }
        System.out.println(operationsList);

        //
        // Validate
        //

        // is number of operations and numbers appropriate
        if (!(numbersList.size() - operationsList.size() == 1)){
            throw new InvalidMathInputString();
        }
        
        //
        // Calculate
        //

        // Calculate
        int calculationResult = 0;
        while (operationsList.size() != 0){
            //
            // Order of operations
            //
            // Find first * or / .. OR if none IDX == 0
            int operationIDX = 0;
            if (operationsList.contains("*") && operationsList.contains("/")){
                if (operationsList.indexOf("*") < operationsList.indexOf("/")){
                    operationIDX = operationsList.indexOf("*");
                }
                else if (operationsList.indexOf("/") < operationsList.indexOf("*")){
                    operationIDX = operationsList.indexOf("/");
                }
            }
            else if (operationsList.contains("*")){
                operationIDX = operationsList.indexOf("*");
            }
            else if (operationsList.contains("/")){
                operationIDX = operationsList.indexOf("/");
            }

            //
            // Perform operation on correct numbers (IDX operation IDX+1)
            //
            int operationResult = 0;
            
            String currentOperation = operationsList.get(operationIDX);
            switch (currentOperation){
                case "*":
                    operationResult = numbersList.get(operationIDX) * numbersList.get(operationIDX+1);
                    break;
                case "/":
                    operationResult = numbersList.get(operationIDX) / numbersList.get(operationIDX+1);
                    break;
                case "+":
                    operationResult = numbersList.get(operationIDX) + numbersList.get(operationIDX+1);
                    break;
                case "-":
                    operationResult = numbersList.get(operationIDX) - numbersList.get(operationIDX+1);
                    break;
            }
            System.out.println("Operation result: " + Integer.toString(operationResult));

            //
            // Clean up arrays from used values
            //
            // e.g. [+, +, *, +] [2, 2, 2, 3, 4] --> [+, +, +] [2, 2, 6, 4]

            // Swap used numbers with result
            numbersList.set(operationIDX, operationResult);
            numbersList.remove(operationIDX+1);

            System.out.println(numbersList);
            
            // Remove performed operation
            operationsList.remove(operationIDX);

            System.out.println(operationsList);

            // If no operations left the result can be obtained
            if (operationsList.size() == 0){
                calculationResult = numbersList.get(0);
                System.out.println("Finished");
            }
        }

        return calculationResult;
    }
}