package pl.insert.old.dogs_for_testing;

public class DogDynamicProxyTest {

    public static void main(String[] args) {

        Dog dog = (Dog) DogProxyFactory.newInstance(new DogImpl());

        dog.sayHi();
        String name = dog.barkName("Bella");

        System.out.println(name);

    }

}
