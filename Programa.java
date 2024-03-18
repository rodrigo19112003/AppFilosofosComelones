public class Programa {
    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++){
            Thread filosofos = new Thread(new FilosofoThread(i), "El filósofo " + (i));
            filosofos.start();
        }
    }
}




