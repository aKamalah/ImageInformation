package com.company;

// Import statements allows us to have access to other packages and classes.
// Meaning we have access to use pieces of code held in the packages and classes in this program.
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Extend allows us to inherit attributes and methods from the superclass and use them within the program. JComponent is the superclass here.
public class Display extends JComponent {
    // private, an access modifier, meaning that it is only accessible within the class.
    // Below creates variables to be used with the private access modifier.
    private BufferedImage displayImage; // BufferedImage is a subclass of Image class. It is used to handle and manipulate the image data.
    private String displayName;
    private String displayPath;
    private String displaySize;
    private String displayDate;
    private String displayDimensions;

    // Constructor used to initialise object. Set initial values.
    public Display (String strName, String strPath, String strSize, String strDate, String strDimensions) {
        this.displayName = strName; // this keyword refers to the current object in a method or constructor.
        this.displayPath = strPath;
        this.displaySize = strSize;
        this.displayDate = strDate;
        this.displayDimensions = strDimensions;
    }

    // Set the image.
    public void setImage(String imagePath) {
        // The try statement allows you to define a block of code to be tested for errors while it is being executed.
        // The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.
        try {
            this.displayImage = ImageIO.read(new File(imagePath)); // Gets the passed in image path and reads it, so it can be drawn.
        } catch (IOException e) { // Exception is related to input and output operations
            e.printStackTrace(); // Tool to handle exceptions and errors.
            // Helps with diagnosing exceptions. Tells you what has happened and where in the code.
        }
        setPreferredSize(new Dimension(displayImage.getWidth(), displayImage.getHeight())); // Sets the size of the component.
    }

    // Set the name.
    public void setName(String imageName) {
        this.displayName = imageName; // Gets the passed in parameter and set the name to it, so it can be drawn.
    }

    // Set the path.
    public void setPath(String imagePath) {
        this.displayPath = imagePath; // Gets the passed in parameter and set the path to it, so it can be drawn.
    }

    // Set the size.
    public void setSize(String imageSize) {
        this.displaySize = imageSize; // Gets the passed in parameter and set the size to it, so it can be drawn.
    }

    // Set the date.
    public void setDate(String imageDate) {
        this.displayDate = imageDate; // Gets the passed in parameter and set the date to it, so it can be drawn.
    }

    // Set the dimensions.
    public void setDimensions(String imageDimensions) {
        this.displayDimensions = imageDimensions; // Gets the passed in parameter and set sDimension to it, so it can be drawn.
    }

    @Override // @Override is used to override a method in a subclass.
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Arguably most important feature in the program. Paints the component as if you hadn't overridden the paintComponent method.
        g.setColor(Color.RED); // Set the graphics colour to red. All text will be drawn in red.
        g.drawImage(displayImage, 0,0, null); // Draws the image in the top left corner of the screen.
        g.drawString(displayName, 0, 12); // Draws the name text on screen. At the specified coordinates.
        g.drawString(displayPath, 0, 24); // Draws the path text on screen. At the specified coordinates.
        g.drawString(displaySize, 0, 36); // Draws the size text on screen. At the specified coordinates.
        g.drawString(displayDate, 0, 48); // Draws the date text on screen. At the specified coordinates.
        g.drawString(displayDimensions, 0, 60); // Draws the dimensions text on screen. At the specified coordinates.
    }
}

