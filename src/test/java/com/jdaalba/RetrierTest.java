package com.jdaalba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

class RetrierTest {

    UserRepository repository;

    Retrier retrier;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UserRepository.class);
        retrier = new Retrier(() -> repository.update("foo"));
    }

    interface UserRepository {
        void update(String s);
    }

    @Test
    void execute() {
        assertThatCode(retrier::execute).doesNotThrowAnyException();

        Mockito.verify(repository).update("foo");
    }

    @Test
    void execute_whenBusinessExceptionIsThrown_shouldRaiseIt() {
        // given
        // when
        doThrow(new BusinessException())
                .when(repository)
                .update(anyString());
        // then
        var exception = catchThrowableOfType(retrier::execute, BusinessException.class);
        assertThat(exception).isNotNull();
    }

    @Test
    void execute_whenTechnicalExceptionIsThrown_shouldRetryIt() {
        doThrow(new TechnicalException())
                .doNothing()
                .when(repository)
                .update(anyString());

        assertThatCode(retrier::execute).doesNotThrowAnyException();
        assertThat(retrier).hasFieldOrPropertyWithValue("numberOfAttempts", 2);

        Mockito.verify(repository, Mockito.times(2)).update("foo");
    }

    @Test
    void execute_whenTechnicalExceptionIsThrownTooManyTimes_itShouldBeRaised() {
        doThrow(new TechnicalException())
                .doThrow(new TechnicalException())
                .doThrow(new TechnicalException())
                .when(repository)
                .update(anyString());

        var exception = catchThrowableOfType(retrier::execute, TechnicalException.class);
        assertThat(exception).isNotNull();
        assertThat(retrier).hasFieldOrPropertyWithValue("numberOfAttempts", 0);

        Mockito.verify(repository, Mockito.times(3)).update("foo");
    }
}
