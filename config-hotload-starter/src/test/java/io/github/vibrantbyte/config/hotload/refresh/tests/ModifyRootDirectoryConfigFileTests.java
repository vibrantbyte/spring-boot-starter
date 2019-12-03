package io.github.vibrantbyte.config.hotload.refresh.tests;

import io.github.vibrantbyte.config.hotload.refresh.config.Config;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModifyRootDirectoryConfigFileTests {

    @Autowired
    private Config config;

    @Value("${spring.config.location}")
    private String location;

    @Test
    @SneakyThrows
    public void add_configuration() {
        changeConfig();
    }

    @Test
    @SneakyThrows
    public void change_configuration_first() {
        changeConfig();
    }

    @Test
    @SneakyThrows
    public void change_configuration_second() {
        changeConfig();
    }

    @Test
    @SneakyThrows
    public void delete_configuration() {
        new Thread(() -> changeApplicationYml("", "")).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(config.getId()).isNullOrEmpty();
        assertThat(config.getName()).isNullOrEmpty();
    }

    @Test
    @SneakyThrows
    public void renew_configuration() {
        new Thread(this::renewApplicationYml).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(config.getId()).isNullOrEmpty();
        assertThat(config.getName()).isNullOrEmpty();
    }

    @SneakyThrows
    private void changeConfig() {
        String first = RandomStringUtils.randomAlphabetic(10);
        String second = RandomStringUtils.randomAlphabetic(10);
        new Thread(() -> changeApplicationYml(first, second)).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(config.getId()).isEqualTo(first);
        assertThat(config.getName()).isEqualTo(second);
    }

    @SneakyThrows
    private void changeApplicationYml(String id, String name) {
        try (PrintWriter writer = new PrintWriter(new File("src/test/resources/application.yml"))) {
            writer.print(
                "config:\n" +
                    "  id: " + id + "\n" +
                    "  name: " + name + "\n" +
                    "\n" +
                    "spring:\n" +
                    "  config:\n" +
                    "    location: " + location + "\n" +
                    "    name: bus,application\n");
        }
    }

    @SneakyThrows
    private void renewApplicationYml() {
        try (PrintWriter writer = new PrintWriter(new File("src/test/resources/application.yml"))) {
            writer.write(
                "spring:\n" +
                    "  config:\n" +
                    "    location: " + location + "\n" +
                    "    name: bus,application\n");
        }
    }
}
