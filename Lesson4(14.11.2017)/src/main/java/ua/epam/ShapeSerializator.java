package ua.epam;

import ua.epam.shapes.Shape;

import java.io.*;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class ShapeSerializator {
    private String fileName;

    public ShapeSerializator(String fileName) {
        this.fileName = fileName;
    }

    public boolean serialize(List<Shape> s) {
        boolean flag = false;

        File f = new File(fileName);

        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ostream = new ObjectOutputStream(fos);
            ostream.writeObject(s);
            flag = true;

        } catch (FileNotFoundException e) {
            System.err.println("Can't create file: " + e);
        } catch (NotSerializableException e) {
            System.err.println("Class should implement Serializable: " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing stream");
            }
        }
        return flag;
    }

    public List<Shape> deserialize() throws InvalidObjectException {

        File fr = new File(fileName);

        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);

            List<Shape> st = (List<Shape>) istream.readObject();
            return st;
        } catch (ClassNotFoundException ce) {
            System.err.println("Class not found: " + ce);
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found: " + e);
        } catch (InvalidClassException ioe) {
            System.err.println("Invalid Class: " + ioe);
        } catch (IOException ioe) {
            System.err.println("IO Exception: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing stream");
            }
        }
        throw new InvalidObjectException("Couldn't deserialize object");
    }

}
