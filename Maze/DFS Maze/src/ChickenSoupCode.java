public class ChickenSoupCode {
    public static boolean hope = false;
    public static boolean persistence = true;


    public static boolean life(boolean hope){
        return hope?hope:newLife(persistence);
    }
    private static boolean newLife(boolean persistence){
        hope = persistence?true:false;
        return hope;
    }
}
