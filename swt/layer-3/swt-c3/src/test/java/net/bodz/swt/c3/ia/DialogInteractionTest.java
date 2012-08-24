package net.bodz.swt.c3.ia;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.gui.ia.Proposals;
import net.bodz.bas.util.example.Address;
import net.bodz.bas.util.example.Person;

public class DialogInteractionTest
        extends Assert {

    DialogInteraction ia;
    Person person;
    {
        person = new Person("Lily", 12, true);
        person.setLocation(new Address("12 River", "Z.J.", "CN", 12345, "999-888-777"));
    }

    String lily = "LiLY";
    Person lily2 = person;

    public DialogInteractionTest() {
        ia = new DialogInteraction(new Shell());
    }

    @Test
    public void testAlert() {
        ia.alert("Hello", "World");
        ia.alert("Hello", lily);
        IOException ex = new IOException("outer", new RuntimeException("inner"));
        ia.alert("Exception Sample", ex);
    }

    @Test
    public void testConfirm() {
        boolean b = ia.confirm("Continue?", "Do you want to continue the operation?");
        if (b) {
            b = ia.confirm("Kiss lily?", lily);
            if (b)
                ia.alert("Kissed");
        }
    }

    @Test
    public void testAsk() {
        int answer = ia.ask("Error happens", new ExpectedException(), //
                Proposals.retry, Proposals.ignore, Proposals.cancel);
        System.out.println("Answer: " + answer);
    }

    @Test
    public void testPrompt() {
        String yourName = ia.prompt("What's your name?");
        System.out.println("Your name = " + yourName);
        Float num = ia.prompt("Enter a number", "Enter a number in decimal", Float.class, 100f);
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

    static class Dog
            extends Animal {

        public Object owner;

        public Dog(String name, String color, Object owner) {
            super(name, color);
            this.owner = owner;
        }

    }

    static Animal[] animals;
    static Map<Integer, Object> numbers;
    static {
        animals = new Animal[] {
                //
                new Animal("Cat", "yellow"), //
                new Dog("Dog", "white", "Jerry"), //
                new Dog("Dog", "blue", Person.Lucy), //
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

    @Test
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

    @Test
    public void testChoices() {
        int[] choices = ia.choices("Choice animals: ", Arrays.asList(animals));
        if (choices == null)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + StringArray.joinByDot(choices));

        Set<Integer> kv = ia.choices("Choice numbers: ", numbers, 2, 4);
        if (kv == null)
            System.out.println("nothing choiced");
        else
            System.out.println("Choiced: " + StringArray.join(", ", kv));
    }

}
