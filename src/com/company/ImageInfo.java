package com.company;

// Import statements allows us to have access to other packages and classes.
// Meaning we have access to use pieces of code held in the packages and classes in this program.
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class ImageInfo {
    // private, an access modifier, meaning that it is only accessible within the class.
    // Below creates variables to be used with the private access modifier.
    private BufferedImage bImage; // BufferedImage is a subclass of Image class. It is used to handle and manipulate the image data.
    private final FileDialog sFile; // final means that value set cannot be changed.

    // Constructor used to initialise object. Set initial values.
    public ImageInfo (String imagePath, FileDialog selectFile) {
        // The try statement allows you to define a block of code to be tested for errors while it is being executed.
        // The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.
        try {
            this.bImage = ImageIO.read(new File((imagePath))); // Gets the passed in image path and reads it, so information about can be retrieved.
        } catch (IOException e) { // Exception is related to input and output operations
            e.printStackTrace(); // Tool to handle exceptions and errors.
            // Helps with diagnosing exceptions. Tells you what has happened and where in the code.
        }
        this.sFile = selectFile; // this keyword refers to the current object in a method or constructor.
    }

    // Get width of image and return it.
    public int getWidth() {
        return bImage.getWidth();
    }

    // Get height of image and return it.
    public int getHeight() {
        return bImage.getHeight();
    }

    // Get name of image and return it.
    public String getName() {
        return sFile.getFile();
    }

    // Get path of image and return it.
    public String getPath() {
        return sFile.getDirectory() + sFile.getFile();
    }

    // Get size of image and return it.
    public long getSize() {
        Path path = Paths.get(getPath());
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Get date of image and return it.
    public String getDate() {
        Path path = Paths.get(getPath());
        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return String.valueOf(attr.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
            return String.valueOf(0);
        }
    }

    // Get dimension of image and return it.
    public String getDimensions() {
        return getWidth() + " x " + getHeight();
    }
}
