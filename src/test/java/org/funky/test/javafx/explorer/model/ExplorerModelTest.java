package org.funky.test.javafx.explorer.model;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.Assertions.assertThat;

public class ExplorerModelTest {

    private ExplorerModel explorerModel;

    @Before
    public void setUp() throws Exception {
        explorerModel = new ExplorerModel();
    }

    @Test
    public void test__GetStateAfterConstruction() throws Exception {
        assertThat(explorerModel.getCurrentPath()).isNull();
        assertThat(explorerModel.filesProperty()).isEmpty();
    }

    @Test
    public void test__filesProperty() throws Exception {
        explorerModel.setCurrentPath(getFilePath("data/directory1"));
        assertThat(explorerModel.filesProperty())
                .hasSize(1)
                .extracting(FileModel::getName).contains("file1.txt");
    }

    @Test
    public void test__SetInvalidPath() throws Exception {
        explorerModel.setCurrentPath(getFilePath("data/directory1") + "/unknown-directory");
        assertThat(explorerModel.filesProperty()).isEmpty();
        assertThat(explorerModel.getErrorsProperty())
                .hasSize(1)
                .extracting(Object::getClass)
                .containsOnly(NoSuchFileException.class);
    }

    private String getFilePath(String path) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (resource == null) {
            throw new NullPointerException("Path " + path + " was not found");
        }
        return resource.getPath();
    }
}