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
        person = new Person(
                Messages.getString("SWTInteractionTest.0"), 12, true); //$NON-NLS-1$
        person
                .setLocation(new Address(
                        Messages.getString("SWTInteractionTest.1"), Messages.getString("SWTInteractionTest.2"), Messages.getString("SWTInteractionTest.3"), 12345, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        Messages.getString("SWTInteractionTest.4"))); //$NON-NLS-1$
    }

    String         lily  = Messages.getString("SWTInteractionTest.5"); //$NON-NLS-1$
    Person         lily2 = person;

    public SWTInteractionTest() {
        ia = new SWTInteraction(new Shell());
    }

    public void testAlert() {
        ia
                .alert(
                        Messages.getString("SWTInteractionTest.6"), Messages.getString("SWTInteractionTest.7")); //$NON-NLS-1$ //$NON-NLS-2$
        ia.alert(Messages.getString("SWTInteractionTest.8"), lily); //$NON-NLS-1$
    }

    public void testConfirm() {
        boolean b = ia.confirm(Messages.getString("SWTInteractionTest.9"), //$NON-NLS-1$
                Messages.getString("SWTInteractionTest.10")); //$NON-NLS-1$
        if (b) {
            b = ia.confirm(Messages.getString("SWTInteractionTest.11"), lily); //$NON-NLS-1$
            if (b)
                ia.alert(Messages.getString("SWTInteractionTest.12")); //$NON-NLS-1$
        }
    }

    public void testPrompt() {
        String yourName = ia
                .prompt(Messages.getString("SWTInteractionTest.13")); //$NON-NLS-1$
        System.out
                .println(Messages.getString("SWTInteractionTest.14") + yourName); //$NON-NLS-1$
        Float num = ia
                .prompt(
                        Messages.getString("SWTInteractionTest.15"), Messages.getString("SWTInteractionTest.16"), //$NON-NLS-1$ //$NON-NLS-2$
                        Float.class, 100f);
        if (num == null)
            System.out.println(Messages.getString("SWTInteractionTest.17")); //$NON-NLS-1$
        else
            System.out.printf(
                    Messages.getString("SWTInteractionTest.18"), (float) num); //$NON-NLS-1$
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
            return Messages.getString("SWTInteractionTest.19") + color + Messages.getString("SWTInteractionTest.20") + name + Messages.getString("SWTInteractionTest.21"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
            new Animal(
                    Messages.getString("SWTInteractionTest.22"), Messages.getString("SWTInteractionTest.23")), // //$NON-NLS-1$ //$NON-NLS-2$
            new Dog(
                    Messages.getString("SWTInteractionTest.24"), Messages.getString("SWTInteractionTest.25"), Messages.getString("SWTInteractionTest.26")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            new Dog(
                    Messages.getString("SWTInteractionTest.27"), Messages.getString("SWTInteractionTest.28"), Person.Lucy), // //$NON-NLS-1$ //$NON-NLS-2$
            new Animal(
                    Messages.getString("SWTInteractionTest.29"), Messages.getString("SWTInteractionTest.30")), // //$NON-NLS-1$ //$NON-NLS-2$
            new Animal(
                    Messages.getString("SWTInteractionTest.31"), Messages.getString("SWTInteractionTest.32")), // //$NON-NLS-1$ //$NON-NLS-2$
        };
        numbers = new HashMap<Integer, Object>();
        numbers.put(10, Messages.getString("SWTInteractionTest.33")); //$NON-NLS-1$
        numbers.put(20, Messages.getString("SWTInteractionTest.34")); //$NON-NLS-1$
        numbers.put(30, Messages.getString("SWTInteractionTest.35")); //$NON-NLS-1$
        numbers.put(40, Messages.getString("SWTInteractionTest.36")); //$NON-NLS-1$
        numbers.put(50, Messages.getString("SWTInteractionTest.37")); //$NON-NLS-1$
    }

    public void testChoice() {
        int choice = ia
                .choice(
                        Messages.getString("SWTInteractionTest.38"), Arrays.asList(animals)); //$NON-NLS-1$
        if (choice == -1)
            System.out.println(Messages.getString("SWTInteractionTest.39")); //$NON-NLS-1$
        else
            System.out
                    .println(Messages.getString("SWTInteractionTest.40") + animals[choice]); //$NON-NLS-1$

        Integer k = ia.choice(
                Messages.getString("SWTInteractionTest.41"), numbers, 20); //$NON-NLS-1$
        if (k == null)
            System.out.println(Messages.getString("SWTInteractionTest.42")); //$NON-NLS-1$
        else
            System.out.println(Messages.getString("SWTInteractionTest.43") + k); //$NON-NLS-1$
    }

    public void testChoices() {
        int[] choices = ia
                .choices(
                        Messages.getString("SWTInteractionTest.44"), Arrays.asList(animals)); //$NON-NLS-1$
        if (choices == null)
            System.out.println(Messages.getString("SWTInteractionTest.45")); //$NON-NLS-1$
        else
            System.out
                    .println(Messages.getString("SWTInteractionTest.46") + Strings.joinDot(choices)); //$NON-NLS-1$

        Set<Integer> kv = ia.choices(Messages
                .getString("SWTInteractionTest.47"), numbers, 2, 4); //$NON-NLS-1$
        if (kv == null)
            System.out.println(Messages.getString("SWTInteractionTest.48")); //$NON-NLS-1$
        else
            System.out
                    .println(Messages.getString("SWTInteractionTest.49") + Strings.join(Messages.getString("SWTInteractionTest.50"), kv)); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static void main(String[] args) {
        SWTInteractionTest test = new SWTInteractionTest();
        test.testAlert();
        test.testConfirm();
        test.testPrompt();
        test.testChoice();
        test.testChoices();
    }

}
