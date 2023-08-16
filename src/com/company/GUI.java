package com.company;

// Import statements allows us to have access to other packages and classes.
// Meaning we have access to use pieces of code held in the packages and classes in this program.
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

// extends keyword allows us to inherit attributes and methods from the superclass and use them within the program. JPanel is the superclass here.
// implements keyword allows us to put into practice an interface. ActionListener and ItemListener are the interfaces here.
public class GUI extends JPanel implements ActionListener, ItemListener {
    JFrame frame = new JFrame("Image Information"); // Creates a new JFrame. Titled "Image Information".
    JButton selectImageB = new JButton("Select Image"); // Creates a JButton. With JButton labeled as "Select Image".

    // Below creates multiple JCheckBoxes. All with specific allocated text.
    JCheckBox nameJCB = new JCheckBox("Name");
    JCheckBox pathJCB = new JCheckBox("Path");
    JCheckBox sizeJCB = new JCheckBox("Size");
    JCheckBox dateJCB = new JCheckBox("Date");
    JCheckBox dimensionsJCB = new JCheckBox("Dimensions");

    // FileDialog class displays a dialog window from which the user can select a file.
    FileDialog selectFile = new FileDialog(frame, "Select Image", FileDialog.LOAD);
    // LOAD, file dialog is finding a file to read, files that are shown are those that are in the current directory.

    // Below creates a new instance of the Display class.
    // The class is used to display the image selected as well the information in relation to the image.
    // We will set and pass the image and the information to the class, so it can be displayed.
    // paintComponent is used to draw, display, all things required.
    Display display = new Display("","","","","");
    // We pass in "" to the constructor rather than null so that there are no errors when the program is run.

    String imagePath; // Creates a variable called imagePath of type String. It would store the image path when the program is executed.

    // The ImageInfo class contains all information of the object required by the program. E.g. Size.
    // We will call upon the class methods to get information about the image.
    ImageInfo imageInfo;

    public void initialise() {
        frame.setSize(750,100); // Sets GUI size.
        frame.setLocation(0,0); // Set location GUI will spawn on screen. 0,0 top left corner of screen.
        frame.setBackground(Color.LIGHT_GRAY); // Set colour of GUI to light gray.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit JFrame. Exit button functionality.

        // The FlowLayout class provides a very simple layout manager that is used, by default, by the JPanel objects.
        frame.setLayout(new FlowLayout()); // Set the frame layout to the FrameLayout class.

        frame.add(selectImageB); // Adds the select image button to the frame.
        selectImageB.addActionListener(this); // .addActionListener allows button to have a functionality when pressed.

        // Add JCheckBoxes into the JFrame.
        // .addItemListener allows the JCheckBoxes to have functionality when checked and unchecked.
        frame.add(nameJCB);
        nameJCB.addItemListener(this);
        frame.add(pathJCB);
        pathJCB.addItemListener(this);
        frame.add(sizeJCB);
        sizeJCB.addItemListener(this);
        frame.add(dateJCB);
        dateJCB.addItemListener(this);
        frame.add(dimensionsJCB);
        dimensionsJCB.addItemListener(this);

        frame.add(display); // Adds the display into the frame.

        frame.setVisible(true); // setVisible(true) makes frame window appear on the screen. Used instead of .show() as it gets deprecated.
    }

    public void loadImage() {
        selectFile.setVisible(true); // Displays FileDialog box. Used .setVisible(true) instead of .show() as it gets deprecated.

        // Stores the directory of the file selected as well as the file selected into a concatenated string.
        imagePath = (selectFile.getDirectory() + selectFile.getFile());

        // The try statement allows you to define a block of code to be tested for errors while it is being executed.
        // The catch statement allows you to define a block of code to be executed, if an error occurs in the try block.
        try {
            if(ImageIO.read(new File(imagePath)) == null) { // If the file that is read is not an image execute the following piece of code.
                // Print statements displaying a warning message. \n can be used to make print statement all in one line.
                System.out.println("*********WARNING********");
                System.out.println("*     INVALID FILE     *");
                System.out.println("*     NOT AN IMAGE     *");
                System.out.println("*   SELECT NEW IMAGE   *");
                System.out.println("*********WARNING********");
                loadImage();
            }
        } catch (IOException e) { // Exception is related to input and output operations
            e.printStackTrace(); // Tool to handle exceptions and errors.
            // Helps with diagnosing exceptions. Tells you what has happened and where in the code.
        }

        // Below creates a new ImageInfo object.
        // Passes in the path of the image and a file dialog.
        imageInfo = new ImageInfo (imagePath, selectFile);

        int width = imageInfo.getWidth(); // Get image width from the ImageInformation class.
        int height = imageInfo.getHeight(); // Get image height from the ImageInformation class.

        frame.setSize(width,height); // Sets the frame a new size. The size of the image.

        display.setImage(imagePath); // Passes in the path of the image to the class and sets the image within the class.
        frame.repaint(); // Redraws the paint component. Image now displayed.
    }

    // When the JButton ,selectImageB, is pressed the following code executes.
    @Override // @Override is used to override a method in a subclass.
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == selectImageB) {
            loadImage(); // Calls the loadImage() method when selectImageB is pressed.
        }
    }

    // When a JCheckBox is checked or unchecked the following code executes.
    @Override
    public void itemStateChanged(ItemEvent e) {
        // if the name check box is selected.
        if (nameJCB.isSelected()) {
            String imageName = "Name: " + imageInfo.getName(); // Create a string getting the name from the imageInfo class.
            display.setName(imageName); // Pass the string into the display class and set the name to be drawn as text on the screen.
        } else {
            display.setName(""); // If the checkbox is not selected display nothing. Therefore, "".
        }

        // if the path check box is selected.
        if (pathJCB.isSelected()) {
            String imagePath = "Path: " + imageInfo.getPath(); // Create a string getting the path from the imageInfo class.
            display.setPath(imagePath); // Pass the string into the display class and set the path to be drawn as text on the screen.
        } else {
            display.setPath(""); // If the checkbox is not selected display nothing. Therefore, "".
        }

        // if the size check box is selected.
        if (sizeJCB.isSelected()) {
            String imageSize = "Size: " + imageInfo.getSize() + " Bytes"; // Create a string getting the size from the imageInfo class.
            display.setSize(imageSize); // Pass the string into the display class and set the size to be drawn as text on the screen.
        } else {
            display.setSize(""); // If the checkbox is not selected display nothing. Therefore, "".
        }

        // if the date check box is selected.
        if (dateJCB.isSelected()) {
            String imageDate = "Date: " + imageInfo.getDate(); // Create a string getting the date from the imageInfo class.
            display.setDate(imageDate); // Pass the string into the display class and set the date to be drawn as text on the screen.
        } else {
            display.setDate(""); // If the checkbox is not selected display nothing. Therefore, "".
        }

        // if the dimensions check box is selected.
        if (dimensionsJCB.isSelected()) {
            String imageDimensions = "Dimensions: " + imageInfo.getDimensions(); // Create a string getting the dimensions from the imageInfo class.
            display.setDimensions(imageDimensions); // pass the string into the display class and set the dimensions to be drawn as text on the screen.
        } else {
            display.setDimensions(""); // If the checkbox is not selected display nothing. Therefore, "".
        }

        frame.repaint(); // Redraws the paint component. Image information checked now displayed.
    }
}
