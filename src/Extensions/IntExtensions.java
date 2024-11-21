package Extensions;

public class IntExtensions {
    public boolean IsInteger(String value){
        try{
            Integer.parseInt(value);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
