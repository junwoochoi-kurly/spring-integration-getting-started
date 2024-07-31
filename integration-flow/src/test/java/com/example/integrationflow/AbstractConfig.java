package com.example.integrationflow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractConfig {

    private static final String INPUT_DIR = "test";
    private static final String TEST_FILE_NAME = "testFile.txt";

    @BeforeEach
    public void setup() throws IOException {
        File inputDir = new File(INPUT_DIR);
        if (!inputDir.exists()) {
            inputDir.mkdir();
        }

        File testFile = new File(inputDir, TEST_FILE_NAME);
        if (!testFile.exists()) {
            testFile.createNewFile();
        }

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("hello world");
        }
    }

    @AfterEach
    public void cleanup() {
        File inputDir = new File(INPUT_DIR);
        File testFile = new File(inputDir, TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
        if (inputDir.exists()) {
            inputDir.delete();
        }
    }

}
