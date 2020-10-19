package mecs.camel.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;


public class ImgCodeUtil {
	public static BufferedImage newImgCode(int width,int height,String code) {
		int size = code.length();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(100, 100, 100));
		g.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
		Random random = new Random();
		int goCount = 4;
		for (int i = 0; i < size*goCount; i++) {
			int x = random.nextInt(width);
			int y  = random.nextInt(height);
			int x1 = random.nextInt(13);
			int y1 = random.nextInt(15);
			g.drawLine(x, y, x+x1, y+y1);
			
		}
		String randomString = code;
		for (int i = 1; i <= size; i++) {
			g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
			g.drawString(randomString.charAt(i-1)+"", 18*i,26);
			
		}
		return image;
				
		
	}
       public static InputStream getImputStream(BufferedImage buff) throws IOException {
    	   ByteArrayOutputStream bytea = new ByteArrayOutputStream();
    	   ImageIO.write(buff, "jpg", bytea);
    	   InputStream inputStream =new ByteArrayInputStream(bytea.toByteArray());
    	   return inputStream;
       }
       
       public static String getFourCode() {
    	   
    	   
    	   String key = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    	   Random r = new Random();
    	   StringBuffer buf = new StringBuffer("");
    	   
    	   for (int i = 0; i < 4; i++) {
    			int index = r.nextInt(key.length());
				char c = key.charAt(index);
				buf.append(c);
    	   }
    	   String s = buf.toString();
		return s;
    	   
       }
}
