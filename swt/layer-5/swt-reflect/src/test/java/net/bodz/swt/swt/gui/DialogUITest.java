package net.bodz.swt.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.lang.err.ExpectedException;
import net.bodz.bas.test.types.Address;
import net.bodz.bas.test.types.Person;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.ui.Proposals;

import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class DialogUITest {

    DialogUI ia;
    Person   person;
    {
        person = new Person("Lily", 12, true); //$NON-NLS-1$
        person.setLocation(new Address("12 River", "Z.J.", "CN", 12345, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                "999-888-777")); //$NON-NLS-1$
    }

    String   lily  = "LiLY"; //$NON-NLS-1$
    Person   lily2 = person;

    public DialogUITest() {
        ia = new DialogUI(new Shell());
    }

    @Test
    public void testAlert() {
        ia.alert("Hello", "World"); //$NON-NLS-1$ //$NON-NLS-2$
        ia.alert("Hello", lily); //$NON-NLS-1$
        IOException ex = new IOException("outer", new RuntimeException("inner")); //$NON-NLS-1$ //$NON-NLS-2$
        ia.alert("Exception Sample", ex); //$NON-NLS-1$
    }

    @Test
    public void testConfirm() {
        boolean b = ia.confirm("Continue?", //$NON-NLS-1$
                "Do you want to continue the operation?"); //$NON-NLS-1$
        if (b) {
            b = ia.confirm("Kiss lily?", lily); //$NON-NLS-1$
            if (b)
                ia.alert("Kissed"); //$NON-NLS-1$
        }
    }

    @Test
    public void testAsk() {
        int answer = ia.ask("Error happens", new ExpectedException(), // //$NON-NLS-1$
                Proposals.retry, Proposals.ignore, Proposals.cancel);
        System.out.println("Answer: " + answer); //$NON-NLS-1$
    }

    @Test
    public void testPrompt() {
        String yourName = ia.prompt("What's your name?"); //$NON-NLS-1$
        System.out.println("Your name = " + yourName); //$NON-NLS-1$
        Float num = ia.prompt("Enter a number", "Enter a number in decimal", //$NON-NLS-1$ //$NON-NLS-2$
                Float.class, 100f);
        if (num == null)
            System.out.println("Canceled"); //$NON-NLS-1$
        else
            System.out.printf("The number is %.3f", (float) num); //$NON-NLS-1$
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
            return "<A " + color + " " + name + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }

    static class Dog extends Animal {

        public Object owner;

        public Dog(String name, String color, Object owner) {
            super(name, color);
            this.owner = owner;
        }

    }

    static Animal[]             animals;
    static Map<Integer, Object> numbers;
    static {
        animals = new Animal[] {
        //
            new Animal("Cat", "yellow"), // //$NON-NLS-1$ //$NON-NLS-2$
            new Dog("Dog", "white", "Jerry"), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            new Dog("Dog", "blue", Person.Lucy), // //$NON-NLS-1$ //$NON-NLS-2$
            new Animal("Chinese Tiger in Dongbei Province", "red"), // //$NON-NLS-1$ //$NON-NLS-2$
            new Animal("Rabbit", "gray"), // //$NON-NLS-1$ //$NON-NLS-2$
        };
        numbers = new HashMap<Integer, Object>();
        numbers.put(10, "One"); //$NON-NLS-1$
        numbers.put(20, "Two"); //$NON-NLS-1$
        numbers.put(30, "Three"); //$NON-NLS-1$
        numbers.put(40, "Four"); //$NON-NLS-1$
        numbers.put(50, "Five apples grows on an big tall arabic tree."); //$NON-NLS-1$
    }

    @Test
    public void testChoice() {
        int choice = ia.choice("Choice an animal: ", Arrays.asList(animals)); //$NON-NLS-1$
        if (choice == -1)
            System.out.println("nothing choiced"); //$NON-NLS-1$
        else
            System.out.println("Choiced: " + animals[choice]); //$NON-NLS-1$

        Integer k = ia.choice("Choice a number: ", numbers, 20); //$NON-NLS-1$
        if (k == null)
            System.out.println("nothing choiced"); //$NON-NLS-1$
        else
            System.out.println("Choiced: " + k); //$NON-NLS-1$
    }

    @Test
    public void testChoices() {
        int[] choices = ia.choices("Choice animals: ", Arrays.asList(animals)); //$NON-NLS-1$
        if (choices == null)
            System.out.println("nothing choiced"); //$NON-NLS-1$
        else
            System.out.println("Choiced: " + Strings.joinDot(choices)); //$NON-NLS-1$

        Set<Integer> kv = ia.choices("Choice numbers: ", numbers, 2, 4); //$NON-NLS-1$
        if (kv == null)
            System.out.println("nothing choiced"); //$NON-NLS-1$
        else
            System.out.println("Choiced: " + Strings.join(", ", kv)); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
