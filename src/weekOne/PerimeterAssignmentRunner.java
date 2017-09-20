package weekOne;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

import java.io.File;


public class PerimeterAssignmentRunner {
    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        double aveLength = getAverageLength(s);
        double longestSide = getLargestSide(s);
        System.out.println("perimeter = " + length);
        System.out.println("average length of a side = "+aveLength);
        System.out.println("longest side in the shape = "+longestSide);
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point currPt : s.getPoints()){
            count++;
        }
        //number of points = number of sides of the shape!
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        //returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S
        double perimeter = getPerimeter(s);
        double sides = getNumPoints(s);
        if(sides < 2){
            return 0.0;
        }
        return perimeter/sides;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(currDist > largestSide){
                largestSide = currDist;
            }

            prevPt = currPt;
        }
        return largestSide;
    }

    //This method returns a number of type double that is the largest x value over all the points in the Shape s
    public double getLargestX(Shape s) {
        // Put code here
        double largestX = Double.MIN_VALUE;
        for(Point currPt : s.getPoints()){
            largestX = Math.max(largestX, currPt.getX());
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = Double.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            largestPerimeter = Math.max(largestPerimeter, getPerimeter(s));
        }
        return largestPerimeter;


    }

    public String getFileWithLargestPerimeter() {
        String fileName = "";
        double largestPerimeter = Double.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double thisPerimeter = getPerimeter(s);
            if(thisPerimeter > largestPerimeter){
                largestPerimeter = thisPerimeter;
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testMaximumPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }


}
