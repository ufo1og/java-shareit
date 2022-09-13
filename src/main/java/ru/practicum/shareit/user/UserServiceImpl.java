package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    public UserDto create(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User createdUser = userStorage.create(user);
        log.info("Created new User: {}.", createdUser);
        return UserMapper.toDto(createdUser);
    }

    public UserDto read(long id) {
        User readUser = userStorage.read(id);
        log.info("Read User: {}.", readUser);
        return UserMapper.toDto(readUser);
    }

    public UserDto update(long id, UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        user.setId(id);
        User updatedUser = userStorage.update(user);
        log.info("Updated User: {}.", updatedUser);
        return UserMapper.toDto(updatedUser);
    }

    public UserDto delete(long id) {
        User deletedUser = userStorage.delete(id);
        log.info("Deleted User: {}.", deletedUser);
        return UserMapper.toDto(deletedUser);
    }

    public List<UserDto> readAll() {
        List<User> readUsers = userStorage.readAll();
        return readUsers.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
