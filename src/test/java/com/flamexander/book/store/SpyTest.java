package com.flamexander.book.store;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

// todo оставить только Mockito без Спринг окружения
@SpringBootTest
public class SpyTest {
    @Spy
    private List<Integer> spiedList = new ArrayList<>();

    @Test
    public void spyTest() {
        spiedList.add(1);
        spiedList.add(2);
        spiedList.add(3);

        Mockito.verify(spiedList).add(1);
        Mockito.verify(spiedList).add(2);
        Mockito.verify(spiedList).add(3);

        assertEquals(3, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();

        assertEquals(100, spiedList.size());

        System.out.println(spiedList.getClass().getName());
    }
}
