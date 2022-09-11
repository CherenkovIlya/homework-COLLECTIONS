package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    ArrayList<Player> expected = new ArrayList<>();
    Game game = new Game();

    Player player1 = new Player(1, "Игрок1", 1000);
    Player player2 = new Player(2, "Игрок2", 900);
    Player player3 = new Player(3, "Игрок3", 1000);

    //регистрация игрока
    @Test
    public void shouldRegisteredPlayer() {
        expected.add(player1);
        game.register(player1);
        ArrayList<Player> actual = new ArrayList<>(game.players);

        Assertions.assertEquals(expected, actual);
    }

    //исключение, первый игрок не зарегистрирован
    @Test
    public void shouldThrowNotRegisteredExceptionIfFirstPlayerIsNotRegistered() {
        game.register(player2);
        Assertions.assertThrows(NotRegisteredException.class,
                () -> game.round("Игрок1", "Игрок2")
        );
    }

    //исключение, второй игрок не зарегистрирован
    @Test
    public void shouldThrowNotRegisteredExceptionIfSecondPlayerIsNotRegistered() {
        game.register(player1);
        Assertions.assertThrows(NotRegisteredException.class,
                () -> game.round("Игрок1", "Игрок2")
        );
    }

    //первый победил
    @Test
    public void shouldWinFirstPlayer() {
        game.register(player1);
        game.register(player2);

        int expected = 1;
        int actual = game.round("Игрок1", "Игрок2");

        Assertions.assertEquals(expected, actual);
    }

    //первый победил
    @Test
    public void shouldWinSecondPlayer() {
        game.register(player2);
        game.register(player3);

        int expected = 2;
        int actual = game.round("Игрок2", "Игрок3");

        Assertions.assertEquals(expected, actual);
    }

    //первый победил
    @Test
    public void deadHeat() {
        game.register(player1);
        game.register(player3);

        int expected = 0;
        int actual = game.round("Игрок1", "Игрок3");

        Assertions.assertEquals(expected, actual);
    }


}