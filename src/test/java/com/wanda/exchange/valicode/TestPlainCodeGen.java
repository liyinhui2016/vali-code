package com.wanda.exchange.valicode;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by liyh on 16/6/30.
 */
public class TestPlainCodeGen {


    @Test
    public void testGenCode() throws FileNotFoundException {
        File f = new File("./test.jpg");
        if(f.exists()){
            f.delete();
        }
        VCodeGen gen = new PlainCodeGen();
        gen.genCode("3eRz",120,50,50,new FileOutputStream(f));

    }

    @Test
    public void testGenRandCode() throws FileNotFoundException {
        File f = new File("./test.jpg");
        if(f.exists()){
            f.delete();
        }
        VCodeGen gen = new PlainCodeGen();
        gen.genCode(5,160,50,new FileOutputStream(f));

    }

    @Test
    public void testGenCode3() throws FileNotFoundException {
        File f = new File("./test.jpg");
        if (f.exists()) {
            f.delete();
        }
        VCodeGen gen = new PlainCodeGen();
        gen.genCommonCode(new FileOutputStream(f));
    }


    @Test
    public void testGenCode4(){

        int count  =  1000;
        long t1 = System.currentTimeMillis();
        for (int i  = 0; i< count ; ++ i){
            VCodeGen gen  = new PlainCodeGen();
            gen.genCommonCode(new ByteArrayOutputStream(1024*1024));
        }
        long t2 = System.currentTimeMillis();

        System.out.printf("generate %s codes ,time used %s ms ,every image used %s ms in average .\n",count,t2-t1,(double)(t2-t1)/count);
    }
}
