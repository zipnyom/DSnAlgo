
/****
 * 
 * 
 * 
 * 개와 고양이만 분양하는 분양소가 있다.
 * 분양받는 사람은 동물의 종류만 고를 수 있고,
 * 분양소에서 가장 오래 있은 순서로 자동으로 분양될 동물이 정해지는
 * 클래스를 구현하시오. 
 * 
 * 
 ***/

import java.util.LinkedList;
import java.util.NoSuchElementException;

enum AnimalType {
    Dog, Cat
}

abstract class Animal {
    AnimalType type;
    String name;
    int order;

    Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void printInfo() {
        System.out.println(this.order + ") type: " + type + ", name : " + name);
    }

}

class Dog extends Animal {

    // this.type=AnimalType.Dog;
    // 실수 1. 위와 같이 부모의 변수를 바로 바꿀 수 없다?
    // Dog(String name) {
    // this. name = name;
    // }
    // 위와 같이 쓰면 안된다.
    Dog(String name) {
        super(AnimalType.Dog, name);
    }

}

class Cat extends Animal {
    Cat(String name) {
        super(AnimalType.Cat, name);
    }
}

class AnmimalShelter {

    LinkedList<Dog> doglist = new LinkedList<Dog>(); // dogs, cats라는 이름이 짧고 직관적
    LinkedList<Cat> catlist = new LinkedList<Cat>();
    int order;

    AnmimalShelter() {
        order = 1; // 넣는 것과 위치 실수
    }

    public void enqueue(Animal animal) {
        // 순서부터 장해주자
        animal.setOrder(order);
        order++;
        if (animal.type == AnimalType.Dog) {
            doglist.addLast((Dog) animal);
        } else {
            catlist.addLast((Cat) animal);
        }
    }

    public Dog dequeueDog() {
        if (doglist.isEmpty()) {
            throw new NoSuchElementException();
        } // 선생님은 poll만 호출하심
        return doglist.poll();
    }

    public Cat dequeueCat() {
        if (catlist.isEmpty()) {
            throw new NoSuchElementException();
        }
        return catlist.poll();
    }

    public Animal dequeue() {

        if (doglist.isEmpty() && catlist.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (doglist.isEmpty()) {
            return dequeueCat();
        }
        if (catlist.isEmpty()) {
            return dequeueDog();
        }

        int dOrder = doglist.peek().order;
        int cOrder = catlist.peek().order;

        if (dOrder < cOrder) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }

    }

}

public class Queue2 {

    public static void main(final String[] args) {


        AnmimalShelter shelter = new AnmimalShelter();
        shelter.enqueue(new Dog("doggy"));
        shelter.enqueue(new Dog("moggy"));
        shelter.enqueue(new Dog("boggy"));
        shelter.enqueue(new Dog("soggy"));
        shelter.enqueue(new Cat("meow"));
        shelter.enqueue(new Cat("tom"));

        shelter.dequeueCat().printInfo();
        shelter.dequeue().printInfo();
        shelter.dequeue().printInfo();
        shelter.dequeue().printInfo();
        shelter.dequeue().printInfo();
        shelter.dequeue().printInfo();
        shelter.dequeue().printInfo();

    }
}