#图片验证码生成api
用于生成图片验证码，可指定的干扰方式：增加干扰噪音，做三角变换。
##void genCode(String code,int width ,int height,int fontSize,OutputStream stream);
code ：要生成的验证码
width：图片宽度
height：图片高度
fontSize：字体大小
stream：输出流，可以到文件，网络响应，内存等。
## void genCode(int len,int width,int height,OutputStream out);
len：随机生成长度为len的字符串，去掉易混字符
width：图片宽度
height：图片高度
stream：输出流，可以到文件，网络响应，内存等。
**fontSize 默认为height的 90%**
##public void genCode(String code ,OutputStream out)
按照设置参数生成验证码图片
##public void genCommonCode(OutputStream out)
生成默认图片：
1. 参数甚至
	this.setNoiseWeight(2);
	this.setTriangleCycle(1.5);
	this.setNoiseNumber(3);
2. 其他默认项：
	5个字符（[a-zA-Z0-9]）,去掉易混字符
	高度：50
	宽度：160
##设置参数
### void setWidth(int width);
设置图片宽度
###void setHeight(int height);
设置图片高度
###void setFontSize(int fontSize);
设置字体大小
###void setTriangleTransFlag(boolean triangleFlag);
设置是否进行三角变换
###void setTriangleCycle(double cycle);
三角变换周期：周期越大，图片约难以辨认
###void setTriangleRange(int range);
三角变换缩放比例：比例越大越难易辨认
###void setNoiseWeight(float weight);
甚至噪音的粗细：越大越难以辨认
### void setNoiseNumber(int number);
设置噪音的数量：越大越难以辨认
**会同时绘制 number ＊ 2数量的噪音线，其中一半在三角变换之前，另一半在三角变换之后**


# 使用举例 （java）

	public void testGenCode3() throws FileNotFoundException {
        File f = new File("./test.jpg");
        if(f.exists()){
            f.delete();
        }
        VCodeGen gen  = new PlainCodeGen();
        gen.genCommonCode(new FileOutputStream(f));
    }


