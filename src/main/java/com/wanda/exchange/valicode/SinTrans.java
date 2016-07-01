package com.wanda.exchange.valicode;

/**
 * Created by liyh on 16/7/1.
 */
public class SinTrans {

    public  static  int transDis(int x ,int width ,int k,int mvVal ,double cycle){
        return (int)(k*Math.sin(((double)(x+mvVal)/width)*cycle*2*Math.PI));
    }

}
