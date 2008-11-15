package net.bodz.swt.gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.test.types.Address;
import net.bodz.bas.test.types.Person;
import net.bodz.bas.types.util.Strings;

import org.eclipse.swt.widgets.Shell;

public class SWTInteractionTest {

    SWTInteraction ia;
    Person         person;
    {
        person = new Person("Lily", 12, true);
        person.setLocation(new Address("12 River", "Z.J.", "CN", 12345,
                "999-888-777"));
    }

    String         lily  = "LiLY";
    Person         lily2 = person;

    public SWTInteractionTest() {
        ia = new SWTInteraction(new Shell());
    }

    public void testAlert() {
        ia.alert("Hello", "World");
        ia.alert("Hello", lily);
    }

    public void testConfirm() {
        boolean b = ia.confirm("Continue?",
                "Do you want to continue the operation?");
        if (b) {
            b = ia.confirm("Kiss lily?", lily);
            if (b)
                ia.alert("Kissed");
        }
    }

    public void testPrompt() {
        String yourName = ia.prompt("What's your name?");
        System.out.println("Your name = " + yourName);
        Float num = ia.prompt("Enter a number", "Enter a number in decimal",
                Float.class, 100f);
        if (num == null)
            System.out.println("Canceled");
        else
            System.out.printf("The number is %.3f", (float) num);
    }

    static class Animal {
        public String name;
        public String color;

        public Animal(String name, String color) {
            super();
            this.name = name;
            this.color = color;
        }

        @Override
        public String toString() {
            return "<A " + color + " " + name + ">";
        }
    }

    static Animal[]             animals;
    static Map<Integer, Object> numbers;
    static {
        animals = new Animal[] { //
        new Animal("Cat", "yellow"), //
            new Animal("Dog", "white"), //
            new Animal("Chinese Tiger in Dongbei Province", "red"), //
            new Animal("Rabbit", "gray"), //
        };
        numbers = new HashMap<Integer, Object>();
        numbers.put(10, "One");
        numbers.put(20, "Two");
        numbers.put(30, "Three");
        numbers.put(40, "Four");
        numbers.put(50, "Five apples grows on an big tall arabic tree.");
    }

    public void testChoice() {
        int choice = ia.choice("Choice an animal: ", Arrays.asList(animals));
        if (choice == -1)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + animals[choice]);

        Integer k = ia.choice("Choice a number: ", numbers, 20);
        if (k == null)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + k);
    }

    public void testChoices() {
        int[] choices = ia.choices("Choice animals: ", Arrays.asList(animals));
        if (choices == null)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + Strings.joinDot(choices));

        Set<Integer> kv = ia.choices("Choice numbers: ", numbers, 2, 4);
        if (kv == null)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + Strings.join(", ", kv));
    }

    public static void main(String[] args) {
        SWTInteractionTest test = new SWTInteractionTest();
//        test.testAlert();
//        test.testConfirm();
//        test.testPrompt();
//        test.testChoice();
        test.testChoices();
    }

}
