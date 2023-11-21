package paralisme_test;

//Implémentation simplifiée en Java
class Philosopher extends Thread {
 private Object leftFork;
 private Object rightFork;

 public Philosopher(Object leftFork, Object rightFork) {
     this.leftFork = leftFork;
     this.rightFork = rightFork;
 }

 public void run() {
     while (true) {
         // Pensée
         System.out.println("Philosopher " + this.getId() + " is thinking");

         // Tentative de saisie des fourchettes
         synchronized (leftFork) {
             synchronized (rightFork) {
                 // Manger
                 System.out.println("Philosopher " + this.getId() + " is eating");
             }
         }
     }
 }
 public static void main(String[] args) {
     // Create five forks (resources)
     Object[] forks = new Object[5];
     for (int i = 0; i < 5; i++) {
         forks[i] = new Object();
     }

     // Create five philosophers
     Philosopher[] philosophers = new Philosopher[5];
     for (int i = 0; i < 5; i++) {
         // Each philosopher shares a fork with the next philosopher to avoid deadlock
         philosophers[i] = new Philosopher(forks[i], forks[(i + 1) % 5]);
     }

     // Start the philosophers
     for (Philosopher philosopher : philosophers) {
         philosopher.start();
     }
 }
}

