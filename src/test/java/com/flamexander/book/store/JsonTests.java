package com.flamexander.book.store;

import com.flamexander.book.store.entities.Role;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        // {
        //   "id": 1,
        //   "name": "USER"
        // }

        assertThat(jackson.write(role))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(jackson.parse(content)).isEqualTo(realRole);
        assertThat(jackson.parseObject(content).getName()).isEqualTo("ADMIN");
    }
}