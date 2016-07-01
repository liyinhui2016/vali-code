package com.wanda.exchange.valicode;

import java.io.OutputStream;

/**
 * Created by liyh on 16/6/30.
 */
public interface VCodeGen {
    /**
     * generate validate code image .
     * @param code
     * @param width
     * @param height
     * @return
     */
    void genCode(String code,int width ,int height,int fontSize,OutputStream stream);


    /**
     *
     * @param len code length
     * @param width image width
     * @param height image height
     * @param out
     */
    void genCode(int len,int width,int height,OutputStream out);


    /**
     * simple generate
     * @param out
     */
    void genCode(String code ,OutputStream out);

    /**
     * simple generate
     * @param out
     */
    void genCommonCode(OutputStream out);

    /**
     * width of code image
     * @param width
     */
    void setWidth(int width);

    /**
     * width of image code
     * @param height
     */
    void setHeight(int height);

    /**
     * font size of code
     * @param fontSize
     */
    void setFontSize(int fontSize);

    /**
     * dose to do triangle transformation
     * @param triangleFlag
     */
    void setTriangleTransFlag(boolean triangleFlag);

    /**
     * cycle of triangle transformation
     * @param cycle
     */
    void setTriangleCycle(double cycle);

    /**
     * range of triangle function
     * @param range
     */
    void setTriangleRange(int range);

    /**
     * weight of noise line .
     * @param weight
     */
    void setNoiseWeight(float weight);

    /**
     * numbe of noise line ,actually double of them will appear .
     * and half of then drawn before triangle transformation and
     * the other half after it .
     * @param number
     */
    void setNoiseNumber(int number);



}
