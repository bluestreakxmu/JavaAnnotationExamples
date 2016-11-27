package org.bluestreakxmu;

import org.bluestreakxmu.annotation.UseCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiboLee
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases, 47, 48, 49, 50);
        trackUseCases(userCases, PasswordUtils.class);

    }

    private static void trackUseCases(List<Integer> userCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                userCases.remove(new Integer(uc.id()));
            }
        }

        for (int i : userCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

}
