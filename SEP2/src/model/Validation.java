package model;

public class Validation {
    public Validation()
    {

    }

    public boolean onlyLetters(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean onlyNumbers(String name)
    {
        if(name.matches("[0-9]+") && Integer.parseInt(name) >= 0)
        {
            return true;
        }
        return false;
    }

}
