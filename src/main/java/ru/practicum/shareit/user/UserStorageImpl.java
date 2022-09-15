package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.DataConflictException;
import ru.practicum.shareit.exceptions.EntityNotFoundException;
import ru.practicum.shareit.exceptions.ValidationFailException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class UserStorageImpl implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private final Set<String> emails = new HashSet<>();
    private long countId = 0;

    @Override
    public User create(User user) {
        String email = user.getEmail();
        validateEmail(email);
        checkEmailConflict(email);
        user.setId(++countId);
        users.put(user.getId(), user);
        emails.add(email);
        return user;
    }

    @Override
    public User read(long id) {
        checkUserExistence(id);
        return users.get(id);
    }

    @Override
    public User update(User user) {
        checkUserExistence(user.getId());
        User updatingUser = users.get(user.getId());
        if (user.getName() != null) {
            updatingUser.setName(user.getName());
        }
        String email = user.getEmail();
        if (email != null) {
            if (!updatingUser.getEmail().equals(email)) {
                validateEmail(email);
                checkEmailConflict(email);
                emails.remove(updatingUser.getEmail());
                emails.add(email);
                updatingUser.setEmail(email);
            }
        }
        return updatingUser;
    }

    @Override
    public User delete(long id) {
        checkUserExistence(id);
        User deletedUser = users.remove(id);
        emails.remove(deletedUser.getEmail());
        return deletedUser;
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(users.values());
    }

    private void checkEmailConflict(String email) {
        if (emails.contains(email)) {
            throw new DataConflictException(String.format("Email '%s' is already occupied!", email));
        }
    }

    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new ValidationFailException(String.format("Email '%s' is not valid!", email));
        }
    }

    private void checkUserExistence(long id) {
        if (!users.containsKey(id)) {
            throw new EntityNotFoundException(String.format("User with id = %s not found!", id));
        }
    }
}
