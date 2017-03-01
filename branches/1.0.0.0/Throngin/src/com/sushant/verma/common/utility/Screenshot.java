
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;

public class Screenshot
{
  static String currentTime;
  static String fileName;
  static String folderName;
  static String subFolderName;
  static String screenShotFileLocation;
  static File screenShotFile;

  public static void main(String[] args)
    throws Exception
  {
    screenShotFileLocation = getScreenShotFileLocation();
    System.out.println("\nscreenShotFileLocation=" + screenShotFileLocation);

    screenShotFile = createFile(new File(screenShotFileLocation), fileName + ".jpg");

    System.out.println("<<<Capturing Image>>>");
    captureImage(screenShotFile);
    System.out.println("***Image saved ==" + screenShotFileLocation + "\\" + fileName + ".jpg");
  }

  public static String getScreenShotFileLocation()
  {
    Calendar now = Calendar.getInstance();
    Integer d = Integer.valueOf(now.get(5));
    Integer m = Integer.valueOf(now.get(2) + 1);
    Integer y = Integer.valueOf(now.get(1));
    Integer h = Integer.valueOf(now.get(11));
    Integer min = Integer.valueOf(now.get(12));
    Integer sec = Integer.valueOf(now.get(13));
    Integer msec = Integer.valueOf(now.get(14));

    currentTime = h.toString() + "." + min.toString() + "." + 
      sec.toString() + "." + msec.toString();
    fileName = currentTime;

    File currentDir = new File("");
    String rootFilePath = currentDir.getAbsolutePath();

    folderName = now.getTime().toString();
    folderName = folderName.replace(":", "-");
    folderName = folderName.substring(8, 11) + 
      folderName.substring(4, 8) + 
      y.toString() + " (" + 
      folderName.substring(0, 4) + ")";

    String monthDigit = m.toString();
    if (m.intValue() < 10) {
      monthDigit = "0" + m;
    }
    screenShotFileLocation = rootFilePath + "\\" + y.toString() + "\\" + monthDigit + "_" + folderName.substring(3, 6) + "\\" + folderName;

    System.out.println("rootFilePath=" + rootFilePath + 
      "\nfolderName=" + folderName + 
      "\nfileName=" + fileName);
    return screenShotFileLocation;
  }

  public static File createFile(File destDir, String fileName)
  {
    File file = new File(destDir, fileName);
    File parent = file.getParentFile();
    if (parent != null)
      parent.mkdirs();
    return file;
  }

  public static void captureImage(File screenShotFile)
    throws Exception
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle screenRectangle = new Rectangle(screenSize);
    Robot robot = new Robot();
    BufferedImage image = robot.createScreenCapture(screenRectangle);
    ImageIO.write(image, "JPEG", screenShotFile);
  }
}
