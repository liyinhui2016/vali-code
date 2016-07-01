package com.wanda.exchange.valicode;

import javafx.scene.paint.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by liyh on 16/6/30.
 */
public class PlainCodeGen implements VCodeGen {


    private String charSet = "abcdefghijkmnpqrstuvwxyABCDEFGHIJKLMNPQRSTUVWXYZ3456789";

    /**
     * validate code image width
     */
    private int width=160;
    /**
     * validate code image height
     */
    private int height=50;

    /**
     * color of background .
     */
    int bgRgb;

    /**
     * dose to change gap between chars randomly
     */
    private boolean gapRandom = true;

    private BufferedImage img;
    private Graphics2D g;
    /**
     * size of font
     */
    private int fontSize;

    /**
     * dose to do triangle transformation;
     */
    boolean trans = true;

    /**
     * cycle of sin transformation function
     */
    double cycle = 2;
    /**
     * range of triangle function
     */
    private int kw = 10;

    /**
     * weight of noise line ;
     */
    private float noiseWeight = 3.f;

    /**
     * number of noise line .
     * draws noiseLineNum before triangle transformation the same as after .
     */
    private int noiseLineNum = 2;


    /**
     * font array ,will be choosen randomly for each char .
     */
    String[] fonts = {
            "courier new",
            "lucida sans typewriter",
            "menlo",
            "pcmyungjo",
            "ayuthaya",
            "pt mono"
    };


    @Override
    public void genCode(String code, int width, int height, int fontSize, OutputStream out) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.genCode(code,out);

    }

    @Override
    public void genCode(int len, int width, int height,OutputStream out) {
        this.setTriangleRange((int) (this.height*0.2));
        this.setWidth(width);
        this.setHeight(height);
        this.setFontSize((int) (height*0.9));
        this.genCode(randStr(len),out);
    }

    @Override
    public void genCode(String code ,OutputStream out) {
        this.init();
        this.initBg();
        this.drawString(code);
        this.noiseLine();
        this.dispose();
        this.dispose();
        if(trans)
            tringleTrans();
        this.noiseLine();
        this.dispose();
        this.out(out);
    }

    @Override
    public void genCommonCode(OutputStream out) {

        this.setNoiseWeight(2);
        this.setTriangleCycle(1.5);
        this.setNoiseNumber(3);
        this.genCode(5,160,50,out);
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public void setTriangleTransFlag(boolean triangleFlag) {
        this.trans = trans;
    }

    @Override
    public void setTriangleCycle(double cycle) {
        this.cycle = cycle;
    }

    @Override
    public void setTriangleRange(int range) {
        this.kw = range;
    }

    @Override
    public void setNoiseWeight(float weight) {
        this.noiseWeight  = weight;
    }

    @Override
    public void setNoiseNumber(int number) {
        this.noiseLineNum =  number;
    }


    protected void init() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = img.createGraphics();
    }

    protected void initBg() {
        int br = rand(190, 255);
        int bg = rand(190, 255);
        int bb = rand(190, 255);
        bgRgb = (((br << 8) + bg) << 8) + bb;
        g.setColor(new Color(br, bg, bb));
        g.fillRect(0, 0, width, height);

    }

    protected void drawString(String code) {
        int w = this.width / code.length();
        for (int i = 0; i < code.length(); ++i) {
            g.setColor(new Color(rand(60, 155), rand(60, 155), rand(60, 155)));
            Font font = new Font(fonts[rand(0, fonts.length)], Font.PLAIN, fontSize);
            g.setFont(font);
            int x = i * w;
            int h = height;
            if (gapRandom ) {
                if(i!=0 && i!=code.length()-1)
                    x = i * w + ((int) ((Math.random() - 0.5) * w * 0.5));
                h = height - ((int) (height * Math.random() * 0.2)+(int)(height*0.1));
            }
            g.drawString(String.valueOf(code.charAt(i)), x, h);
        }
    }

    protected void tringleTrans() {
        int mvVal = rand(0,width);
        for (int x = 0; x < width; x++) {
            int dy = SinTrans.transDis(x, width, kw,mvVal, cycle);
            if (dy > 0) {

                for (int y = height - 1; y >= 0; y--) {
                    int newY = y - dy;
                    if (newY > 0 && newY < height)
                        img.setRGB(x, y, img.getRGB(x, newY));
                    else
                        img.setRGB(x, y, bgColor());
                }
            } else {
                for (int y = 0; y < height; ++y) {
                    int newY = y - dy;
                    if (newY > 0 && newY < height)
                        img.setRGB(x, y, img.getRGB(x, newY));
                    else
                        img.setRGB(x, y, bgColor());
                }
            }
        }
    }

    protected void noiseLine() {
        g = img.createGraphics();
        g.setStroke(new BasicStroke(noiseWeight));
        for (int i = 0; i < noiseLineNum; ++i) {

            int x1 = rand(0, width);
            int x2 = rand(0, width);
            int y1 = rand(0, height);
            int y2 = rand(0, height);
            g.setColor(new Color(rand(60, 155), rand(60, 155), rand(60, 155)));
            g.drawLine(x1, y1, x2, y2);
        }
    }

    protected void dispose() {
        g.dispose();

    }

    protected void out(OutputStream out) {
        try {
            ImageIO.write(this.img, "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected int bgColor() {
        return bgRgb;
    }

    private int rand(int from, int to) {
        return (int) (Math.random() * (to - from) + from);
    }

    protected String randStr(int len ){
        StringBuilder  sb = new StringBuilder();
        for ( int i = 0 ;i  < len ; ++ i){
            sb.append(charSet.charAt(rand(0,charSet.length())));
        }
        return sb.toString();
    }


}
