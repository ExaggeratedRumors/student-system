package com.zpo.studentsystem.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * Class agregating loading configuration files.
 */
public class Utils {
    @Getter
    static LinkedHashMap<Double, Double> grades;
    static {
        String gradesSourcePath = "/grades.yaml";
        grades = readGradesFromYaml(gradesSourcePath);
    }

    @SneakyThrows
    public static LinkedHashMap<Double, Double> readGradesFromYaml(String sourcePath) {
        InputStream resource = Utils.class.getResourceAsStream(sourcePath);
        Yaml yaml = new Yaml();
        return yaml.load(resource);
    }
}