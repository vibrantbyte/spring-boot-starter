package io.github.vibrantbyte.config.hotload.refresh.tests;

import io.github.vibrantbyte.config.hotload.refresh.config.Business;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModifySubDirectoryConfigFileTests {

    @Autowired
    private Business business;

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
        new Thread(() -> changeBusinessYml("", "")).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(business.getFirst()).isNullOrEmpty();
        assertThat(business.getSecond()).isNullOrEmpty();
    }

    @Test
    @SneakyThrows
    public void delete_configuration_file() {
        new Thread(this::deleteBusinessYml).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(business.getFirst()).isNullOrEmpty();
        assertThat(business.getSecond()).isNullOrEmpty();
    }

    @SneakyThrows
    private void changeConfig() {
        String id = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        new Thread(() -> changeBusinessYml(id, name)).start();
        TimeUnit.SECONDS.sleep(15);
        assertThat(business.getFirst()).isEqualTo(id);
        assertThat(business.getSecond()).isEqualTo(name);
    }

    @SneakyThrows
    private void changeBusinessYml(String first, String second) {
        File businessDirectory = new File("src/test/resources/business/");
        if (!businessDirectory.exists()) {
            businessDirectory.mkdir();
        }

        File file = new File("src/test/resources/business/bus.yml");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(
                "business:\n" +
                    "  first: " + first + "\n" +
                    "  second: " + second + "\n");
        }
    }

    @SneakyThrows
    private void deleteBusinessYml() {
        File file = new File("src/test/resources/business/bus.yml");
        file.delete();
    }
}
