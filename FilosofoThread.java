import java.util.HashMap;
import java.util.Map;

public class FilosofoThread implements Runnable{
    private Map<Integer, Boolean> tenedores = new HashMap<>();
    private int filosofo;

    public FilosofoThread(int i){
        tenedores.put(1, false);
        tenedores.put(2, false);
        tenedores.put(3, false);
        tenedores.put(4, false);
        tenedores.put(5, false);
        filosofo = i;
    }

    @Override
    public  void run(){
        while(true){
            pensando();
            tomarTenedores();
            comer();
            dejarTenedores();
        }
    }

    public void pensando(){
        System.out.println(Thread.currentThread().getName() + " está pensando");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void tomarTenedores(){
        while(tenedores.get(filosofo) || tenedores.get(filosofo == 1 ? 5 : filosofo -1)){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tenedores.put(filosofo, true);
        tenedores.put(filosofo == 1 ? 5 : filosofo -1, true);
        System.out.println(Thread.currentThread().getName() + " Toma su tenedor izquierdo");
        System.out.println(Thread.currentThread().getName() + " Toma su tenedor derecho");
    }

    public synchronized void dejarTenedores(){
        tenedores.put(filosofo, false);
        tenedores.put(filosofo == 1 ? 5 : filosofo -1, false);
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " dejó de comer");
    }

    public void comer(){
        System.out.println(Thread.currentThread().getName() + " se prepara para comer");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " está comiendo");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



