package org.art.samples.core.algorithms.codewars;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Write the method that takes the list of strings that represents 'LastName FirstName'
 * with space as a delimiter. Method logic should filter persons with the same Last Name and return sorted names list.
 */
@Slf4j
public class SortUsers {

    private static final String DELIMITER = " ";

    private static final List<String> NAMES = List.of(
        "Smith Ivan",
        "Miller Serge",
        "Miller Alexsander",
        "Potter Nikita",
        "Smith Max");

    public static void main(String[] args) {
        log.info(processNames(NAMES).toString());
    }

    public static List<String> processNames(List<String> names) {
        List<User> users = toUsers(names);

        return users.stream()
            .collect(Collectors.groupingBy(User::getLastName, Collectors.toList()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue().size() > 1)
            .flatMap(entry -> entry.getValue().stream())
            .map(User::getName)
            .sorted()
            .collect(Collectors.toList());
    }

    private static List<User> toUsers(List<String> names) {
        return names.stream()
            .filter(Objects::nonNull)
            .map(name -> name.split(DELIMITER))
            .filter(lastFirstNames -> lastFirstNames.length > 1)
            .map(lastFirstNames -> new User(lastFirstNames[1], lastFirstNames[0]))
            .collect(Collectors.toList());
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    static class User {

        private final String firstName;

        private final String lastName;

        String getName() {
            return lastName + DELIMITER + firstName;
        }

    }
}
