import java.util.Scanner;

public class Calk {
    static String[] arrArab = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] arrRim = new String[] {"i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x"};
    static String[] arrLiteral = new String[] {"+", "-", "*", "/"};

    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();
        System.out.println(Calc(input.toLowerCase()));

    }

    public static String Calc(String input) throws Exception
    {
        boolean isArab = false;
        String literal = "";
        int positionLiteral = -1;
        int counterLiteral = 0;
        for(int x = 0; x < arrLiteral.length; x++)
        {
            for(int y  = 0; y < input.length(); y++)
            {
                if(arrLiteral[x].charAt(0) == input.charAt(y))
                {
                    literal = arrLiteral[x];
                    positionLiteral =input.indexOf(arrLiteral[x]);
                    counterLiteral++;
                }
            }
        }

        if(counterLiteral == 0)
            throw new Exception("строка не является математической операцией");

        if(counterLiteral > 1)
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if(positionLiteral == -1) {
            throw new Exception("строка не является математической операцией");
        }

        String valueStr1 = input.substring(0, positionLiteral).trim();
        String valueStr2 = input.substring(positionLiteral + literal.length(), input.length()).trim();
        int value1 = GetIndex(arrArab, valueStr1);
        int value2 = -1;
        if(value1 != -1) {
            value2 = GetIndex(arrArab, valueStr2);
            if(value2 == -1)
                throw new Exception("используются одновременно разные системы счисления");
            else
                isArab = true;
        }
        else
        {
            value1 = GetIndex(arrRim, valueStr1);
            if(value1 != -1) {
                value2 = GetIndex(arrRim, valueStr2);
                if(value2 == -1)
                    throw new Exception("используются одновременно разные системы счисления");
                else
                    isArab = false;
            }
            else
                throw new Exception("строка не является математической операцией");
        }

        if(value1 == -1 || value2 == -1)
            throw new Exception("строка не является математической операцией");

        int result = 0;
        value1++;
        value2++;
        switch(literal.trim())
        {
            case "+":
                result =value1 + value2;
                break;
            case "-":
                result =value1 - value2;
                break;
            case "*":
                result =value1 * value2;
                break;
            case "/":
                result =value1 / value2;
                break;
            default:
                throw new Exception("строка не является математической операцией");
        }
        if(isArab)
            return Integer.toString(result);
        else
        {
            if(result < 0)
                throw new Exception("в римской системе нет отрицательных чисел");
            else {
                String resStr = "";
                int decCount = result / 10;
                for (int x = 0; x < decCount; x++)
                    resStr += "x";
                if (result % 10 != 0)
                    resStr += arrRim[(result % 10) - 1];
                return resStr;
            }
        }
    }

    static int GetIndex(String[] arr, String value)
    {
        for(int x = 0; x < arr.length; x++)
        {
            if(arr[x].equals(value))
                return x;
        }
        return  -1;
    }


}
