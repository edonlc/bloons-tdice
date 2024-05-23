package helpz;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class ImgFix {


    // Rotacionar
    public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {
        
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

    // Camadas

    public static BufferedImage buildImage(BufferedImage[] imgs) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for(BufferedImage img : imgs) {
            g2d.drawImage(img, 0, 0, null);
        }
        g2d.dispose();
        return newImg;
    }


    //ROTACIONAR SEGUNDA IMAGEM
    public static BufferedImage getBuildRotImage(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {

        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for(int i=0; i < imgs.length; i++) {
            if(rotAtIndex == i)
                g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
            g2d.drawImage(imgs[i], 0, 0, null);
            if(rotAtIndex == i)
                g2d.rotate(Math.toRadians(-rotAngle), w/2, h/2);
        }
        g2d.dispose();
        return newImg;
    }
}
