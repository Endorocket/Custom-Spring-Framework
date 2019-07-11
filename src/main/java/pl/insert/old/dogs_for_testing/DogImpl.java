package pl.insert.old.dogs_for_testing;

public class DogImpl implements Dog {

    @Override
    public String barkName(String name) {
        return name;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi, I am a dog");
    }
}
