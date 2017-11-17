package ua.epam.shapes;

import ua.epam.Validator;

import static ua.epam.Constants.COLOR;
import static ua.epam.Validator.isTriangleValid;

public class Triangle extends Shape implements Cloneable{
    private Point apexA, apexB, apexC;
    private Line sideAB, sideAC, sideBC;

    public Triangle(Point apexA, Point apexB, Point apexC) {
        if(isTriangleValid(apexA, apexB, apexC))
            throw new IllegalArgumentException("Points on one line");
        this.apexA = apexA;
        this.apexB = apexB;
        this.apexC = apexC;
    }

    public Triangle(Point apexA, Point apexB, Point apexC, int color) {
        this(apexA, apexB, apexC);
        setProperty(COLOR, Validator.validateColor(color));
    }

    public Line getSideAB(){
        if(sideAB==null)sideAB = new Line(apexA, apexB);
        return sideAB;
    }

    public Line getSideAC(){
        if(sideAC==null)sideAC = new Line(apexA, apexC);
        return sideAC;
    }

    public Line getSideBC(){
        if(sideBC==null)sideBC = new Line(apexB, apexC);
        return sideBC;
    }

    public void setApexA(Point p) throws IllegalArgumentException{
        if(isTriangleValid(p, getApexB(), getApexC()))
            throw new IllegalArgumentException("Given apexA will put points on one line");
        apexA = p;
        sideAB = null;
        sideAC = null;
    }

    public void setApexB(Point p) throws IllegalArgumentException{
        if(isTriangleValid(getApexA(), p, getApexC()))
            throw new IllegalArgumentException("Given apexB will put points on one line");
        apexB = p;
        sideAB = null;
        sideBC = null;
    }

    public void setApexC(Point p) throws IllegalArgumentException{
        if(isTriangleValid(getApexA(), getApexB(), p))
            throw new IllegalArgumentException("Given apexC will put points on one line");
        apexC = p;
        sideAC = null;
        sideBC = null;
    }

    public Point getApexA() {
        return apexA;
    }

    public Point getApexB() {
        return apexB;
    }

    public Point getApexC() {
        return apexC;
    }

    @Override
    protected Object clone() {
        try {
            Triangle result = (Triangle) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (apexA != null ? !apexA.equals(triangle.apexA) : triangle.apexA != null) return false;
        if (apexB != null ? !apexB.equals(triangle.apexB) : triangle.apexB != null) return false;
        return apexC != null ? apexC.equals(triangle.apexC) : triangle.apexC == null;
    }

    @Override
    public int hashCode() {
        int result = apexA != null ? apexA.hashCode() : 0;
        result = 31 * result + (apexB != null ? apexB.hashCode() : 0);
        result = 31 * result + (apexC != null ? apexC.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" + "apexA=" + apexA + ", apexB=" + apexB + ", apexC=" + apexC + '}';
    }
}
