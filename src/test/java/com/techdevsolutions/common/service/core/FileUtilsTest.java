package com.techdevsolutions.common.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void listDirectories() {
        System.out.println(FileUtils.ListDirectories("/"));
    }

    @Ignore
    @Test
    public void traverseStart() throws JsonProcessingException {
        Timer timer = new Timer().start();
        FileUtils.traverseStart("Z:\\chris\\My Documents Store\\Images\\Family\\Chris\\Wedding\\From Corinne");
//        System.out.println(timer.stopAndGetDiff() + "ms");
        Assert.assertTrue(true);
    }
}