package com.techdevsolutions.common.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileUtils {
    public static List<File> ListDirectories(String path) {
        File directory = new File(path);

        File[] filesArray = directory.listFiles();

        if (filesArray != null) {
            return new ArrayList<>(Arrays.asList(filesArray));
        } else {
            return new ArrayList<>();
        }
    }

    public static void traverseStart(String path) throws JsonProcessingException {
        Map<String, Object> map = FileUtils.traverse(path);
        String json = new ObjectMapper().writeValueAsString(map);
        System.out.println(JsonUtils.toPrettyJson(json));
    }

    public static Map<String, Object> traverse(String directoryPath) {
        File directory = new File(directoryPath);
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());

        Arrays.asList(directory.listFiles()).parallelStream().forEach((i)->{
            try {
                String path = i.getCanonicalPath().replace("\\\\", "\\");

                if (i.isDirectory()) {
                    System.out.println(path + "\\");
                    map.put(path, FileUtils.traverse(path));
                } else {
                    try {
                        System.out.println(path);

                        Map<String, Object> fileMap = new HashMap<>();
                        fileMap.put("name", path);
                        fileMap.put("sha1", HashUtils.sha1(Files.readAllBytes(i.toPath())));
                        fileMap.put("size", i.length());
                        fileMap.put("modified", DateUtils.DateToISO(i.lastModified()));
                        fileMap.put("hidden", i.isHidden());

                        map.put(i.getName(), fileMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        return map;
    }
}
