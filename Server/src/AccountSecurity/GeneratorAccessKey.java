package AccountSecurity;

public class GeneratorAccessKey {
    public static int generateKey(){
        int a = (int)(Math.random()*(999999-111111+1)+111111);
        return a;
    }
}
