package ua.epam.shapes;

import ua.epam.Validator;

import static ua.epam.Constants.COLOR;

public class Line extends Shape implements Cloneable{
    private Point beg;
    private Point end;

    public Line(Point beg, Point end) {
        this.beg = beg;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1,y1), new Point(x2,y2));
    }

    public Line(int x1, int y1, int x2, int y2, int color) {
        this(new Point(x1,y1), new Point(x2,y2));
        setProperty(COLOR, Validator.validateColor(color));
    }

    public double getLength() {
        return Math.sqrt(Math.pow(beg.getX() - end.getX(), 2d) + Math.pow(beg.getY() - end.getY(), 2d));
    }

    public Point getBeg() {
        return beg;
    }

    public void setBeg(Point beg) {
        this.beg = beg;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    protected Object clone() {
        try {
            Line result =  (Line) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Line{" + "beg=" + getBeg() + ", end=" + getEnd() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (beg != null ? !beg.equals(line.beg) : line.beg != null) return false;
        return end != null ? end.equals(line.end) : line.end == null;
    }

    @Override
    public int hashCode() {
        int result = beg != null ? beg.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
