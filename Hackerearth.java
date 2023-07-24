public class Hackerearth {
    public static void main(String[] args) {
        hack2 h = new hack2();
        h.hack_method();
        h.hack_method2();
    }
}

abstract class hack1{
    hack1(){
        System.out.println("Hello");
    }

    abstract void hack_method();
    void hack_method2(){
        System.out.println("Hi");
    }
}

class hack2 extends hack1{
    void hack_method(){
        System.out.println("Hackeearth");
    }
}