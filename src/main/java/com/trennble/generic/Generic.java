package com.trennble.generic;

/**
 * @ClasssName Generic
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-6 16:14
 * @Version 1.0
 **/
public class Generic {

    class House<T>{
        private T item;
        public House(T t){
            item=t;
        }
        public void setItem(T t){
            item=t;
        }
        public T getItem(){
            return item;
        }
    }

    class Animal{}

    class Dog extends Animal{}

    class Cat extends Animal{}

    class Puppy extends Dog{}


    /**
     * 为什么要用通配符和边界？
     * 就算容器里装的东西之间有继承关系，但容器之间是没有继承关系的
     *
     * 通配符<?>和类型参数<T>的区别就在于，对编译器来说所有的T都代表同一种类型
     *
     * PECS
     */
    public void test1(){
        /**
         * 上界
         */
        // 报错：容器里面的东西有继承关系不代表容器具有继承关系
        // House<Animal> animalHouse=new House<Dog>(new Dog());

        // 报错：只能装<? extends T>中T的子类
        // House<? extends Dog> dogHouse = new House<Animal>(new Animal());
        House<? extends Animal> animalHouse = new House<Dog>(new Dog());

        /**
         * 上界限制
         * 上界set会因为不知道具体是哪一个子类，所以不能赋值
         * 上界get知道获取到的肯定是T的子类，所以可以返回类型T
         */
        // 上界<? extends T>不能往里存，只能往外取（原因是编译器只知道容器内是Animal或者它的派生类，具体类型不知道）
        Animal item1 = animalHouse.getItem();
        // animalHouse.setItem(new Animal());
        // animalHouse.setItem(new Dog());

        /**
         * 下界
         */
        House<? super Dog> dogHouse=new House<Animal>(new Dog());
        // 报错，？ super Dog 实际类型可能是其他父类，比如介于Animal和Dog之间的类，如：Canine，这时候就把Animal向下转型就会出错
        // dogHouse.setItem(new Animal());
        dogHouse.setItem(new Dog());
        dogHouse.setItem(new Puppy());
        Object item = dogHouse.getItem();

        /**
         * 下界限制
         * 下界set没有问题，因为知道占位的是T的基类，所以只要是竭诚T的都可以顺利编译
         * 下界get出来是Object，因为不知道具体的基类类型，只能返回顶级基类
         */
    }
}
