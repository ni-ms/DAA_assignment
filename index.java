import java.util.ArrayList;
import java.lang.Math;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
//Import file reading
import java.io.File;

/**
 * @brief A class to create the gui using swing
 */
class plotEx extends JPanel{
    polygon poly;
    boolean drawEdges = true;
    boolean drawDiagonals = false;
    public plotEx(polygon poly){
        this.poly = poly;
    }
    public void printListOfVertices(){
        // Print the vertices of the polygon
        System.out.println("Vertices:");
        for(int i = 0; i < poly.numV; i++){
            System.out.println(poly.vertices.get(i).x + " " + poly.vertices.get(i).y);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Paint background white
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        // Draw the polygon
        g2.setColor(Color.white);

        g2.setStroke(new BasicStroke(2));
        // use numV and draw lines between vertices
        for(int i = 0; i < poly.numV - 1; i++){
            g2.drawLine((int) poly.vertices.get(i).x, (int) poly.vertices.get(i).y, (int) poly.vertices.get(i+1).x, (int) poly.vertices.get(i+1).y);
            // Add a label above the vertex indicating coordinates
            String label = "(" + Integer.toString((int) poly.vertices.get(i).x) + ", " + Integer.toString((int) poly.vertices.get(i).y) + ")";
            // Draw the label above the vertex
            g2.drawString(label, (int) poly.vertices.get(i).x, (int) poly.vertices.get(i).y);

        }
        // Join last vertex to first vertex
        g2.drawLine((int) poly.vertices.get(poly.numV-1).x, (int) poly.vertices.get(poly.numV-1).y, (int) poly.vertices.get(0).x, (int) poly.vertices.get(0).y);
        // Add label for last vertex
        String label = "(" + Integer.toString((int) poly.vertices.get(poly.numV-1).x) + ", " + Integer.toString((int) poly.vertices.get(poly.numV-1).y) + ")";
        // Draw the label above the vertex
        g2.drawString(label, (int) poly.vertices.get(poly.numV-1).x, (int) poly.vertices.get(poly.numV-1).y);

        // Now draw the edges
        if(drawEdges){
            g2.setColor(Color.cyan);
            g2.setStroke(new BasicStroke(2));
            for(int i = 0; i < poly.numE; i++){
                g2.drawLine((int) poly.vertices.get(poly.edgesF.get(i).origin).x, (int) poly.vertices.get(poly.edgesF.get(i).origin).y, (int) poly.vertices.get(poly.edgesF.get(i).dest).x, (int) poly.vertices.get(poly.edgesF.get(i).dest).y);
            }
        }

        if(drawDiagonals){
            g2.setColor(Color.orange);
            g2.setStroke(new BasicStroke(2));
            for(int i = 0; i < poly.numE; i++){
                g2.drawLine((int) poly.vertices.get(poly.edgesF.get(i).origin).x, (int) poly.vertices.get(poly.edgesF.get(i).origin).y, (int) poly.vertices.get(poly.edgesF.get(i).dest).x, (int) poly.vertices.get(poly.edgesF.get(i).dest).y);
            }
        }





    }
}
/**
 * @brief A class for our main function
 */
public class index{
    public static void main(String[] args) {

        //polygon initialization
        polygon li = new polygon();

        for(int i = 0; i < args.length; i+=2){
            // li.addVertex(double.parsedouble(args[i]), double.parsedouble(args[i+1]));
        }

        // li.addVertex(117, 305);

        // li.addVertex(524, 296);

        // li.addVertex(562, 172);

        // li.addVertex(238, 24);

        // li.addVertex(402, 123);

        // li.addVertex(417, 216);

        // li.addVertex(340, 266);

        // Read from input.txt
        // Create a temporary arraylist to store the vertices
        ArrayList<vertex> temp = new ArrayList<vertex>();
        try{
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNext()){
                // Add vertices to polygon
                li.addVertex(sc.nextDouble(), sc.nextDouble());
            }
        }
        catch(Exception e){
            System.out.println("Error reading from file");
        }
        //Add the vertices to the polygon
        for(int i = 0; i < temp.size(); i++){
            li.addVertex(temp.get(i).x, temp.get(i).y);
        }

        // Print polygon vertices
        for (int i = 0; i < li.numV; i++) {
            System.out.println(li.vertices.get(i).x + " " + li.vertices.get(i).y);
        }


//        li.addVertex(0, 0);
//        li.addVertex(5, -5);
//        li.addVertex(5, 10);
//        li.addVertex(0, 5);
//        li.addVertex(-5, 10);
//        li.addVertex(-5, -5);

        int initV = li.numV;

        int scaleFactor = 1, translateFactor = 0;
        // Create a frame to input the above numbers
        JFrame frameOrig = new JFrame("Input");
        frameOrig.setVisible(true);
        frameOrig.setSize(600, 500);
        frameOrig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set layout to a column layout
        frameOrig.setLayout(new BoxLayout(frameOrig.getContentPane(), BoxLayout.Y_AXIS));


        // Use box.createRigidArea to add padding
        frameOrig.add(Box.createRigidArea(new Dimension(0, 10)));


        // Create a text field and ensure that they are on the same line
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Create a label for the input field
        JLabel label = new JLabel("Amount to scale");
        // Create a text field to input the number of times to multiply the polygon
        JTextField textField = new JTextField(10);
        // Create a label for the input field

        // Add a space between the two text boxes
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        JLabel label2 = new JLabel("Amount to move on x axis");
        // Create a text field to input the number of times to translate the polygon
        JTextField textField2 = new JTextField(10);



        // Create a button to submit the input

        // Create an input button
        JButton inputButton = new JButton("Input");
        JButton nextButton = new JButton("Next");
        //Set the color of the button
        inputButton.setBackground(Color.green);
        nextButton.setBackground(Color.green);


        // Add the label and text field to the panel
        panel.add(label);
        panel.add(textField);
        panel.add(label2);
        panel.add(textField2);
        panel.add(nextButton);
// Hide the next button until the user clicks the input button
        nextButton.setVisible(false);

        // Add input button to panel
        panel.add(inputButton);

        // Add the panel to the frame
        frameOrig.add(panel);
        // Create a frame to display the polygon
        // Multiply the x and y vertices by the scale factor

        JFrame frame = new JFrame("Polygon");
     // Don't load the frame until the user clicks the next button
        frame.setVisible(false);


        inputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int scaleFactor1 = Integer.parseInt(textField.getText());
                int translateFactor1 = Integer.parseInt(textField2.getText());

                // Print the scale factor and translation factor
                System.out.println(scaleFactor1);
                System.out.println(translateFactor1);

                for(int i = 0; i < li.numV; i++){
                    int id = li.vertices.get(i).id;
                    vertex v = new vertex(li.vertices.get(i).x * scaleFactor1, li.vertices.get(i).y * scaleFactor1,id );
                    li.vertices.set(i, v);


                }
                for(int i = 0; i < li.numV; i++){
                    int id = li.vertices.get(i).id;
                    vertex v = new vertex(li.vertices.get(i).x + translateFactor1, li.vertices.get(i).y + translateFactor1, id);
                    li.vertices.set(i, v);
                }

                // Print the vertices
                System.out.println("Vertices");
                for(int i = 0; i < li.numV; i++){
                    System.out.println(li.vertices.get(i).x + " " + li.vertices.get(i).y);
                }
        // Set the next button to visible
                nextButton.setVisible(true);



            }

        });
// For the next Button, load the next frame
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Remove the input frame
                frameOrig.setVisible(false);
                // Load the next frame
                frame.setVisible(true);
                frame.setSize(600, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });





//
//        for(int i = 0;i < li.numV; i++)
//            li.vertices.get(i).y = 360 - li.vertices.get(i).y;


        // for(int i = 0;i < li.numV; i++)
        //     li.vertices.get(i).y = 360 - li.vertices.get(i).y;


        // for(int i = 0;i < li.numV; i++);
        //     // li.vertices.get(i).y = 360 - li.vertices.get(i).y;

        // //for Anti-clockwise

       // Sleep thread until frame is visible
        while(!frame.isVisible()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            for (int i = 0; i < li.numV - 1; i++)
                li.addEdge(i, i + 1);
            li.addEdge(li.numV - 1, 0);

            //Add first face
            li.addFaceWithLastEdge();

            //Set nexts and prevs
            for (int i = 0; i < li.numE; i++) {
                li.edgesF.get(i).setNext(li.edgesB, i + 1 % li.numE);
                li.edgesF.get(i).setPrev(li.edgesB, i - 1 % li.numE);
            }

            li.edgesF.get(0).setPrev(li.edgesB, li.numE - 1);
            li.edgesF.get(li.numE - 1).setNext(li.edgesB, 0);

            algorithm al = new algorithm(li);
            li.printPoly();
            System.out.println("\n\n\nDecomposition:\n");

            al.runAlgorithm();
            System.out.println("====================================================================");
            al.printDecomp();

            polygon finalPoly = new polygon();
            finalPoly = al.origiPolygon;

            // Plotting the polygon
            // Frame to hold the drawing

            // Add a layout manager so that the plot is centered
            frame.setLayout(new BorderLayout());
            // Add a title at the top of the frame
            JLabel title = new JLabel("Polygon", JLabel.CENTER);
            frame.add(title, BorderLayout.NORTH);
            // Add the plot to the center of the frame
            // Add next and previous buttons next to each other below the plot
            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(1, 3));
            JButton next = new JButton("After Decomposition");
            JButton prev = new JButton("Before Decomposition");
            JButton afterMerge = new JButton("Final Result");
            //Set the color of the buttons to red, orange and green
            prev.setBackground(Color.red);
            next.setBackground(Color.orange);
            afterMerge.setBackground(Color.green);


            buttons.add(prev);
            buttons.add(next);
            buttons.add(afterMerge);
            frame.add(buttons, BorderLayout.SOUTH);

            // Create a temp variable to store old polygon
            polygon tempPoly = li;

            // Draw the polygon
            plotEx plotOut = new plotEx(li);
            plotOut.drawEdges = false;
            frame.add(plotOut, BorderLayout.CENTER);


            // On clicking next
            next.addActionListener(e -> {
                //Change the heading of the frame
                // If current title is decomposition, do nothing
                if (title.getText().equals("After Decomposition"))
                    return;

                title.setText("After Decomposition");

                // Remove the current plot
                frame.remove(frame.getContentPane());
                // Add the next plot
                plotEx plot = new plotEx(tempPoly);
                // Print the vertices of the final polygon
                //plot.printListOfVertices();
                frame.add(plot);
            });
            // on clicking previous
            prev.addActionListener(e -> {
                //Change the heading of the frame
                // If current title is polygon, do nothing
                if (title.getText().equals("Polygon"))
                    return;

                title.setText("Polygon");

                // Remove the current plot
                frame.remove(frame.getContentPane());
                // Add the next plot
                plotEx plot = new plotEx(al.origiPolygon);
                plot.drawEdges = false;
                // Print the vertices of the final polygon
                //plot.printListOfVertices();
                frame.add(plot);

                frame.setVisible(true);
            });
/** Merge function*/
mergeClass merge = new mergeClass(al.origiPolygon, initV);
// Print function
        merge.printData();
        merge.mergeFunction();
        merge.debugFunction();



            // on clicking after merge
            afterMerge.addActionListener(e -> {
                //Change the heading of the frame
                // If current title is polygon, do nothing
                if (title.getText().equals("Final Result"))
                    return;

                title.setText("Final Result");

                // Remove the current plot
                frame.remove(frame.getContentPane());
                // Add the next plot

                polygon ultimatePolygon = new polygon();
                ultimatePolygon = merge.getPolygon();
                plotEx plot = new plotEx(ultimatePolygon);

                plot.drawEdges = false;
                plot.drawDiagonals = true;
                // Print the vertices of the final polygon
                //plot.printListOfVertices();
                frame.add(plot);

                frame.setVisible(true);
            });

            // Display the frame
            return;

    }
}
/** MERGE FUNCTION */
class pointClass {
    double x, y;

    pointClass(){
        x = 0;
        y = 0;
    }
    pointClass(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void printPoint(){
        System.out.println(this.x + " " + this.y);
    }

    public static void printPointList(ArrayList<pointClass>arr){
        System.out.println("Print Point List");
        for(int i  = 0; i < arr.size();i++){
            arr.get(i).printPoint();
        }
        System.out.println("Point List End");
    }
}
class edgeClass {
    pointClass u, v;
    edgeClass(pointClass u, pointClass v){
        this.u = u;
        this.v = v;
    }

    public void printEdge(){
        System.out.println(this.u.x + " " + this.u.y + " " + this.v.x + " " + this.v.y);
    }

    public static void printEdgeList(ArrayList<edgeClass>arr){
        System.out.println("Print Edge List");
        for(int i  = 0; i < arr.size();i++){
            arr.get(i).printEdge();
        }
        System.out.println("Print Edge End");
    }

    public static void printEdge2DList(ArrayList<ArrayList<edgeClass>>arr){
        System.out.println("Print 2D Edge List");
        for(int i  = 0; i < arr.size();i++){
            printEdgeList(arr.get(i));
        }
        System.out.println("Print 2D Edge End");
    }

    public double calculateSlope(){
        double x1 = this.u.x, y1 = this.u.y;
        double x2 = this.v.x, y2 = this.v.y;

        if(x1 == x2)
            return 1e9;

        return (y2 - y1) / (x2 - x1);
    }

    public static double calculateAngle(edgeClass e1, edgeClass e2){
        double m1 = e1.calculateSlope();
        double m2 = e2.calculateSlope();

        double tan_theta = (m2 - m1) / (1 + m1*m2);
        if(tan_theta < 0){
            return Math.round((Math.toDegrees(Math.atan(tan_theta)) + 180)*100.0) / 100.0;
        }
        return Math.round(Math.toDegrees(Math.atan(tan_theta))*100.0) / 100.0;
    }

    public static boolean compareEdgesAndCoordinates(pointClass u, pointClass v, edgeClass e){
        if(e.u.x == u.x && e.u.y == u.y && e.v.x == v.x && e.v.y == v.y)
            return true;
        return false;
    }


    public static int findIndex(ArrayList<edgeClass> edges, edgeClass edge){

        for(int i = 0; i < edges.size(); i++){
            edgeClass e = edges.get(i);
            boolean res1 = compareEdgesAndCoordinates(e.u, e.v, edge);
            boolean res2 = compareEdgesAndCoordinates(e.v, e.u, edge);
            if(res1 || res2) {
                return i;
            }
        }

        return -1;
    }

    public static ArrayList<edgeClass> getEdgesInCounterClockWise(pointClass u, ArrayList<pointClass> coordinates, ArrayList<edgeClass> edges){
        ArrayList<edgeClass> counterClockWiseEdges = new ArrayList<edgeClass>();
        for(pointClass v : coordinates){
            for(edgeClass e : edges){
                if(compareEdgesAndCoordinates(u, v, e) || compareEdgesAndCoordinates(v, u, e)){
                    edgeClass newEdge = new edgeClass(u, v);
                    counterClockWiseEdges.add(newEdge);
                }
            }
        }
        return counterClockWiseEdges;
    }

    // Doubt vertex_edge_list kya hai? all edges and dia included or only pure edges list
    public static ArrayList<ArrayList<edgeClass>> getDiagonalDetails(ArrayList<edgeClass> vertexEdgeList, ArrayList<edgeClass> diagonals){
        ArrayList<ArrayList<edgeClass>> diagonalDetailsList = new ArrayList<ArrayList<edgeClass>>();

        for (edgeClass e : vertexEdgeList){
            int edgeIndex = edgeClass.findIndex(diagonals, e);
            if(edgeIndex == -1) continue;
            edgeIndex = edgeClass.findIndex(vertexEdgeList, e);
            int prevEdgeIndex = 0, nextEdgeIndex = 0;
            if(edgeIndex > 0){
                prevEdgeIndex = edgeIndex - 1;
            }
            else{
                prevEdgeIndex = vertexEdgeList.size() - 1;
            }

            if(edgeIndex == vertexEdgeList.size() - 1){
                nextEdgeIndex = 0;
            }else{
                nextEdgeIndex = edgeIndex + 1;
            }

            ArrayList<edgeClass> diagonalData = new ArrayList<edgeClass>();
            diagonalData.add(vertexEdgeList.get(prevEdgeIndex));
            diagonalData.add(vertexEdgeList.get(edgeIndex));
            diagonalData.add(vertexEdgeList.get(nextEdgeIndex));
            diagonalDetailsList.add(diagonalData);

        }
        return diagonalDetailsList;
    }

    public static ArrayList<edgeClass> getSpecificDiagonalDetails(ArrayList<edgeClass> edges, edgeClass diagonals) {

        int edgeIndex = edgeClass.findIndex(edges, diagonals);

        int prevEdgeIndex = 0, nextEdgeIndex = 0;
        if(edgeIndex > 0){
            prevEdgeIndex = edgeIndex - 1;
        }
        else{
            prevEdgeIndex = edges.size() - 1;
        }

        if(edgeIndex == edges.size() - 1){
            nextEdgeIndex = 0;
        }else{
            nextEdgeIndex = edgeIndex + 1;
        }

        ArrayList<edgeClass> diagonalData = new ArrayList<edgeClass>();
        diagonalData.add(edges.get(prevEdgeIndex));
        diagonalData.add(edges.get(edgeIndex));
        diagonalData.add(edges.get(nextEdgeIndex));

        return diagonalData;
    }
}

class mergeClass{
    ArrayList<pointClass> coordinates = new ArrayList<pointClass>();
    ArrayList<edgeClass> edges= new ArrayList<edgeClass>();
    ArrayList<edgeClass> pureEdges= new ArrayList<edgeClass>();
    ArrayList<edgeClass> diagonals= new ArrayList<edgeClass>();

    public mergeClass(polygon p, int initV){
        for(int i = 0; i < p.numV; i++){
            coordinates.add(new pointClass(p.vertices.get(i).x, p.vertices.get(i).y));
        }
        for(int i = 0; i < p.numE; i++){
            edges.add(new edgeClass(new pointClass(p.vertices.get(p.edgesF.get(i).origin).x, p.vertices.get(p.edgesF.get(i).origin).y), new pointClass(p.vertices.get(p.edgesF.get(i).dest).x, p.vertices.get(p.edgesF.get(i).dest).y)));
        }

        // remaining edges from initV to last edge are diagonals
        for(int i = initV; i < p.numE; i++){
            diagonals.add(new edgeClass(new pointClass(p.vertices.get(p.edgesF.get(i).origin).x, p.vertices.get(p.edgesF.get(i).origin).y), new pointClass(p.vertices.get(p.edgesF.get(i).dest).x, p.vertices.get(p.edgesF.get(i).dest).y)));
        }
    }
    public void mergeFunction(){
        for(pointClass vertex : coordinates){
            boolean restartVertex = true;

            while(restartVertex){
                restartVertex = false;

                System.out.println("Vertex: " + vertex.x + " " + vertex.y);

                ArrayList<edgeClass> vertexEdgeList = edgeClass.getEdgesInCounterClockWise(vertex, coordinates, edges);
                // DEBUG EDGE LIST
                edgeClass.printEdgeList(vertexEdgeList);

                ArrayList<ArrayList<edgeClass>> diagonalList = edgeClass.getDiagonalDetails(vertexEdgeList, diagonals);
                // DEBUG EDGE LIST
                System.out.println("DIAGONAL LIST *****");
                edgeClass.printEdge2DList(diagonalList);
                System.out.println("DIAGONAL LIST END *****");


                for(ArrayList<edgeClass> diagonalDetails : diagonalList){
                    // DEBUG DIAGONAL DETAILS
                    edgeClass.printEdgeList(diagonalDetails);

                    edgeClass previousEdge = diagonalDetails.get(0);
                    edgeClass diagonal = diagonalDetails.get(1);
                    edgeClass nextEdge = diagonalDetails.get(2);

                    double angleBetweenPrevEdgeAndDiagonal = edgeClass.calculateAngle(previousEdge, diagonal);
                    double angleBetweenNextEdgeAndDiagonal = edgeClass.calculateAngle(diagonal, nextEdge);
                    // DEBUG DIAGONAL ANGLES
                    System.out.println("The Angles are: " + angleBetweenPrevEdgeAndDiagonal + " " + angleBetweenNextEdgeAndDiagonal);

                    if(angleBetweenPrevEdgeAndDiagonal + angleBetweenNextEdgeAndDiagonal < 180){
                        edgeClass diagonalToRemove = diagonal;
                        // DEBUG DIAGONAL TO REMOVE
                        diagonalToRemove.printEdge();

                        pointClass otherVertex = diagonalToRemove.v;
                        // DEBUG OTHER VERTEX
                        otherVertex.printPoint();

                        ArrayList<edgeClass> otherVertexEdgeList = edgeClass.getEdgesInCounterClockWise(otherVertex, coordinates, edges);
                        // DEBUG OTHER VERTEX EDGE LIST
                        edgeClass.printEdgeList(otherVertexEdgeList);

                        ArrayList<edgeClass> otherVertexDiagonalDetails = edgeClass.getSpecificDiagonalDetails(otherVertexEdgeList, diagonalToRemove);
                        // DEBUG SPECIFIC DIAGONAL DETAILS
                        edgeClass.printEdgeList(otherVertexDiagonalDetails);

                        edgeClass previousEdgeOtherVertex = otherVertexDiagonalDetails.get(0);
                        diagonalToRemove = otherVertexDiagonalDetails.get(1);
                        edgeClass nextEdgeOtherVertex = otherVertexDiagonalDetails.get(2);

                        double angleBetweenPrevEdgeAndDiagonalOtherVertex = edgeClass.calculateAngle(previousEdgeOtherVertex, diagonalToRemove);
                        double angleBetweenNextEdgeAndDiagonalOtherVertex = edgeClass.calculateAngle(diagonalToRemove, nextEdgeOtherVertex);
                        // DEBUG DIAGONAL ANGLES
                        System.out.println("The Angles are: " + angleBetweenPrevEdgeAndDiagonalOtherVertex + " " + angleBetweenNextEdgeAndDiagonalOtherVertex);

                        if(angleBetweenPrevEdgeAndDiagonalOtherVertex + angleBetweenNextEdgeAndDiagonalOtherVertex < 180){
                            int indexEdge = edgeClass.findIndex(edges, diagonalToRemove);
                            edges.remove(edges.get(indexEdge));

                            int indexDiagonal = edgeClass.findIndex(diagonals, diagonalToRemove);
                            diagonals.remove(diagonals.get(indexDiagonal));
                            // DEBUG DIAGONAL REMOVED
                            diagonalToRemove.printEdge();

                            restartVertex = true;
                            break;
                        }
                    }
                }

            }
        }
    }

    public void debugFunction(){
        System.out.println("Printing EDGES: ");
        edgeClass.printEdgeList(edges);
        System.out.println("Printing DIAGONALS: ");
        edgeClass.printEdgeList(diagonals);
    }

    public void printData(){
        // Print all the arrays
        System.out.println("Coordinates");
        for(pointClass p : coordinates){
            System.out.println(p.x + " " + p.y);
        }
        System.out.println("Edges");
        for(edgeClass e : edges){
            System.out.println(e.u.x + " " + e.u.y + " " + e.v.x + " " + e.v.y);
        }
        System.out.println("Diagonals");
        for(edgeClass e : diagonals){
            System.out.println(e.u.x + " " + e.u.y + " " + e.v.x + " " + e.v.y);
        }


    }

    public polygon getPolygon(){
        polygon p = new polygon();

        for(pointClass c : coordinates){
            p.addVertex(c.x, c.y);
        }

        for (edgeClass e : edges){
            // Loop through the vertices and see if they match the edges
            for(int i = 0; i < p.numV; i++){
                if(p.vertices.get(i).x == e.u.x && p.vertices.get(i).y == e.u.y){
                    for(int j = 0; j < p.numV; j++){
                        if(p.vertices.get(j).x == e.v.x && p.vertices.get(j).y == e.v.y){
                            p.addEdge(i, j);
                        }
                    }
                }
            }
        }


        return p;
    }
}

/**
 * @brief A class describing a face
 */
class algorithm{
    ArrayList<polygon> decomp;
    ArrayList<Boolean> inPartition;

    polygon origiPolygon;

    int n;
    vertex v1, v2;

    /**
     * Creates a new person with the specified first and last name.
     * @param origiPolygon the polygon to triangulate
     */
    //constructor that initializes everyything
    public algorithm(polygon origiPolygon){
        this.origiPolygon = origiPolygon;

        decomp = new ArrayList<polygon>();
        inPartition = new ArrayList<Boolean>();

        n = origiPolygon.numV;

        v1 = origiPolygon.vertices.get(0);
        v2 = origiPolygon.vertices.get(origiPolygon.edgesF.get(v1.inc_Edge).dest);
    }

    //Function to run algorithm
    /**
     * @brief Runs a part of the algorithm
     */
    void runAlgorithm(){
        System.out.println("====================================================================");

        //checking for value of n before each run of algorithm on remaining polygon
        while(n > 3){
            // origiPolygon.printPoly();
            System.out.println(n);
            this.algo();
            System.out.println(n);
            System.out.println("====================================================================");
            // break;
        }
        // this.merger();
    }

    /**
     * @brief Runs the rest of algorithm
     */
    void algo(){
        //init of i, vi, v[i+1] and v[i-1]
        int i = 2;
        vertex vi = v2;
        vertex tempV = v2;
        System.out.println(origiPolygon.edgesF.get(v2.inc_Edge).dest);
        vertex vi1 = origiPolygon.vertices.get(origiPolygon.edgesF.get(v2.inc_Edge).dest);
        vertex vim1 = v1;
        System.out.println(vi1.stringForm());

        //find index of v1, for use when adding edge in the end
        int t = origiPolygon.findIndOfVert(v1);

        //vertices that can go in a (possible) new polygon
        ArrayList<vertex> verts = new ArrayList<vertex>();

        //A polygon created just to test for point inclusion
        polygon poly = new polygon();
        poly.addVertex(v1);
        poly.addVertex(v2);
        poly.addEdge(i-2, i-1);

        //Adds point until one of the three conditions from paper is violated (creation of a notch)
        while(threeConditionsDam(v1, v2, vi, vi1, vim1) && verts.size() < origiPolygon.numV - 2){
            i++;

            System.out.println("Adding: " + vi1.stringForm());
            verts.add(vi1);
            for(vertex te: verts){
                System.out.println(te.stringForm());
            }

            poly.addVertex(vi1);
            poly.addEdge(i-2, i-1);

            vim1 = vi;
            vi = vi1;

            vi1 =  origiPolygon.vertices.get(origiPolygon.edgesF.get(vi.inc_Edge).dest);
        }
        //adds edge in poly to connect first vertex with last one
        poly.addEdge(poly.numV-1, 0);

        // System.out.println("POLY SIZE BEFORE PRUNE: " + poly.numV + " numeE: " + poly.numE);
        // poly.printPoly();

        //New values of v1 and v2 in case no vertex needs to be removed
        v1 = vi;
        v2 = vi1;
        System.out.println("V1: " + v1.stringForm()  + " V2: " + v2.stringForm());

        for(vertex temp: origiPolygon.vertices){
            while(poly.isPointIn(temp) && poly.numV > 2){

                poly.removeLastEdge();
                poly.removeLastVertexAndEdge();

                v2 = verts.get(verts.size() - 1);   //as vertex is removed, v2 will be the last vertex currently
                verts.remove(verts.size() - 1);

                // v1 = verts.size() > 1 ? verts.get(verts.size() - 1) : vi;
                v1 = verts.size() >= 1 ? verts.get(verts.size() - 1) : tempV;   //and V1 the last vertex after removal

                poly.addEdge(poly.numV-1, 0);   //Seal up the polygon again

                System.out.println("Removing");
                for(vertex te: verts){
                    System.out.println(te.stringForm());
                }
                // System.out.println("Removing due to:" + temp.stringForm() + " new poly: " + " numeE: " + poly.numE);
                // poly.printPoly();
                // System.out.println("````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
            }
        }

        System.out.println("Pruning done: ");
        //Print the vertices in our "new" polygon after pruning
        for(vertex te: verts){
            System.out.println(te.stringForm());
        }

        // Print the new vertices that are V1 and V2
        System.out.println("V1: " + v1.stringForm() + " V2: " + v2.stringForm());

        // If poly has more than two vertices (it is not a line) then adding edge in origiPolygon to create a subdivision
        if(poly.numV > 2){

            int ind = origiPolygon.findIndOfVert(verts.get(verts.size() - 1));  //Index of last vertex
            // System.out.println("Last one ind: " + ind + " first one ind: " + t );



            origiPolygon.addEdge(t, ind);   //  adding edge between last vertex and first vertex
            origiPolygon.removeLastFace();  //  remove the last face
            origiPolygon.addFaceWithLastEdge(); //add 2 new faces

            n = n + 2 - poly.numV;  // As polygon changed, update n
        }

        // System.out.println("POLY SIZE: " + poly.numV);
        // poly.printPoly();
        return;
    }
    /*
    * Merge function new
     */
    // Create a class called edge which has two vertices
    class edgeClass{
        vertex origin;
        vertex dest;
        edgeClass(vertex origin, vertex dest){
            this.origin = origin;
            this.dest = dest;
        }
    }
    class vertexClass{
        vertex v;
        vertex nextVertex, prevVertex;
        vertexClass(vertex v, vertex nextVertex, vertex prevVertex){
            this.v = v;
            this.nextVertex = nextVertex;
            this.prevVertex = prevVertex;
        }

    }


    void merger(){
        ArrayList<ArrayList<Integer>> VDL = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Double>> VAL = new ArrayList<ArrayList<Double>>();

        for(vertex v: origiPolygon.vertices){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Double> an = new ArrayList<Double>();

            for(int i = 0; i < origiPolygon.numE; i++){
                edge e = origiPolygon.edgesF.get(i);
                vertex origin = origiPolygon.vertices.get(e.origin);
                vertex dest = origiPolygon.vertices.get(e.dest);
                double angle = Math.atan((dest.y - origin.y)/(dest.x - origin.y));
                if(e.origin == origiPolygon.findIndOfVert(v)){
                    temp.add(i);
                    an.add(angle);
                } else if (e.dest == origiPolygon.findIndOfVert(v)){
                    temp.add(-i);
                    an.add(Math.PI/2 - angle);
                }
            }
            VDL.add(temp);
            VAL.add(an);
        }

        for(int i = 0; i < VDL.size(); i++){
            ArrayList<Integer> DL = VDL.get(i);
            ArrayList<Double> AL = VAL.get(i);

            ArrayList<Integer> t1 = new ArrayList<Integer>();
            ArrayList<Double> t2 = new ArrayList<Double>();

            if(DL.size() <= 2){
                continue;
            }
            for(int j = 0; i < DL.size(); j++){
                int k = 0;
                for(int l = 0; l < t2.size(); l++){
                    if(t2.get(l) > AL.get(j)){
                        k = l;
                        break;
                    }
                }
                t1.add(k, DL.get(j));
                t2.add(k, AL.get(j));
            }
            VDL.set(i, t1);
            VAL.set(i, t2);
        }

        for(int i = 0; i < VDL.size(); i++){
            ArrayList<Integer> DL = VDL.get(i);
            ArrayList<Double> AL = VAL.get(i);

            for(int j = 1; j < DL.size() - 1 && DL.size() > 2; j++){
                double upperAngle = AL.get(j+1) - AL.get(j);
                double lowerAngle = AL.get(j) - AL.get(j-1);

                if(upperAngle + lowerAngle < Math.PI){
                    int otherVertex;
                    if(DL.get(j) >= 0){
                        otherVertex = origiPolygon.edgesF.get(DL.get(j)).dest;
                    }else{
                        otherVertex = origiPolygon.edgesB.get(-DL.get(j)).dest;
                    }
                    if(checkOppositeIndex(j, VDL.get(otherVertex), VAL.get(otherVertex))){
                        if(DL.get(j) > 0){
                            origiPolygon.removeEdge(DL.get(j));
                        }else{
                            origiPolygon.removeEdge(-DL.get(j));
                        }
                        DL.remove(j);
                        AL.remove(j);
                        j--;
                    }
                }
            }
        }
    }

    boolean checkOppositeIndex(int e, ArrayList<Integer> DL, ArrayList<Double> AL){
        int j = getIndexInDL(DL, e) == -1 ? getIndexInDL(DL, -e) : getIndexInDL(DL, e);
        double upperAngle = AL.get(j+1) - AL.get(j);
        double lowerAngle = AL.get(j) - AL.get(j-1);
        return upperAngle + lowerAngle < Math.PI;
    }

    int getIndexInDL(ArrayList<Integer> DL, int e){
        for(int i = 0; i < DL.size(); i++){
            if(DL.get(i) == e || DL.get(i) == -e)
                return i;
        }

        return -1;
    }

    //The three conditions described a is v1 and b is v2 but since they were class variables, I renamed to avoid confusion
    /**
     * @brief The three conditions described in the paper
     * @param a Usually v1 is given
     * @param b usually v2 is given
     * @param vi the v[i]th vertex (1 based indexing)
     * @param vi1 the v[i+1]th vertex (1 based indexing)
     * @param vim1 the v[i-1]th vertex (1 based indexing)
     * @return True if given vertices pass all the conditions, false otherwise
     */
    public boolean threeConditionsDam(vertex a, vertex b, vertex vi, vertex vi1, vertex vim1){
        if(origiPolygon.cross(vi1, vi, vim1) < 0){
            // System.out.println("Failed 1");
            return false;
        }
        if(origiPolygon.cross(a, vi1, vi) < 0){
            // System.out.println("Failed 2");
            return false;
        }
        if(origiPolygon.cross(b, a, vi1) < 0){
            // System.out.println("Failed 3");
            return false;
        }

        return true;
    }

    //Just prints the polygon
    /**
     * @brief Prints the polygon
     */
    public void printDecomp(){
        origiPolygon.printPoly();
    }
}

/**
 * @brief A class describing a face
 */
class polygon{
    ArrayList<vertex> vertices;
    ArrayList<edge> edgesF, edgesB;
    ArrayList<face> faces;

    int numV, numE;
    vertex rBL, rTR;    //rectangle bottom left and top right, respectively

    //Constructor class
    /**
     * @brief Creates a new polygon
     */
    public polygon(){
        vertices = new ArrayList<vertex>();
        edgesF = new ArrayList<edge>();
        edgesB = new ArrayList<edge>();
        faces = new ArrayList<face>();

        rBL = new vertex();
        rTR = new vertex();

        numV = 0;
        numE = 0;
    }

    //Find the index given a vertex (all of them have an ID associated with them)
    /**
     * @brief Finds the index of the vertex with the given ID
     * @param v The Vertex whos index we want
     * @return The index of the vertex
     */
    public int findIndOfVert(vertex v){
        int i = -1;

        for(int j = 0; j < vertices.size(); j+=1){
            if(vertices.get(j).id == v.id)
                i = j;
        }

        return i;
    }

    // Checks if point is in boundign rectangle, if so, checks if its in polygon
    /**
     * @brief Checks whether point is in the bounding rectangle, if so, checks more rigorously
     * @param v The Vertex we want to check inclusion for
     * @return true if the point is inside our polygon, false otherwise
     */
    public boolean isPointIn(vertex v){
        if(v.x < rTR.x && v.x > rBL.x){
            // System.out.println("inX");
            if(v.y < rTR.y && v.y > rBL.y){
                // System.out.println("inhere");
                return pointInclusion(v);
            }
        }

        return false;
    }

    //Checks if point is in polygon
    /**
     * @brief More rigorous check, uses cross product with every edge to check
     * @param v the vertex we want to check inclusion for
     * @return true if the point is inside, false otherwise
     */
    public boolean pointInclusion(vertex v){
        //loop through all and take cross products
        for(int k = 0; k < edgesF.size(); k++){
            edge e = edgesF.get(k);
            if(cross(v, vertices.get(e.dest), vertices.get(e.origin)) <= 0){
                return false;
            }
        }

        return true;
    }

    // Cross product
    /**
     * @brief The cross product of the vectors (dest - origin) and (v - dest)
     * @param v the new point
     * @param dest the destination vertex (of our edge)
     * @param origin the origin vertex (of our edge)
     */
    public double cross(vertex v, vertex dest, vertex origin){
        double aX = dest.x - origin.x;
        double aY = dest.y - origin.y;

        double bX = v.x - dest.x;
        double bY = v.y - dest.y;

        return aX*bY - bX*aY;
    }

    // Add vertex using just coordinates
    /**
     * @brief Creates a new vertex
     * @param x the x coordinate of vertex
     * @param y the y coordinate of vertex
     */
    public void addVertex(double x, double y){
        vertices.add(new vertex(x, y, numV));
        numV++;
    }

    // Add vertex using a existing one
    /**
     * @brief Creates a new vertex using a reference to an exisiting one
     * @param v the vertex who's coordinates we want to copy
     */
    public void addVertex(vertex v){
        vertices.add(new vertex(v.x, v.y, numV));
        numV++;
    }

    //Removes last edge
    /**
     * @brief Removes the last added edge of the polygon
     */
    public void removeLastEdge(){
        edgesB.remove(numE - 1);
        edgesF.remove(numE - 1);

        numE--;
    }

    // removes both last vertex and last edge
    /**
     * @brief Removes the last added edge and vertex of the polygon
     */
    public void removeLastVertexAndEdge(){
        vertices.remove(numV - 1);

        edgesB.remove(numE - 1);
        edgesF.remove(numE - 1);

        numE--;
        numV--;

        this.setRect();
    }

    // removes the last face
    /**
     * @brief Removes the last added face of the polygon
     */
    public void removeLastFace(){
        faces.remove(faces.size() - 1);
    }

    //Removes last vertex
    /**
     * @brief Removes the last added vertex of the polygon
     */
    public void removeLastVertex(){
        vertices.remove(numV - 1);

        numV--;
    }

    //adds edge and sents inc_edge and twin
    /**
     * @brief Adds an edge
     * @param originInd the index of the origin vertex
     * @param destInd the index of the destination vertex
     */
    public void addEdge(int originInd, int destInd){
        changeRect(vertices.get(originInd));
        changeRect(vertices.get(destInd));

        edgesF.add(new edge(originInd, destInd));
        edgesB.add(new edge(originInd, destInd));

        vertices.get(originInd).inc_Edge = numE; //we go counter clockwise

        edgesB.get(numE).twin = numE;
        edgesF.get(numE).twin = numE;

        numE++;
    }

    public void removeEdge(int e){
        for(int i = e; i < numE; i++){
            vertices.get(edgesF.get(e).origin).setIncEdge(e-1);
        }
        edgesF.remove(e);
    }

    //Adds face assoc to the latest edge
    /**
     * @brief Adds a face associated to the last edge
     */
    public void addFaceWithLastEdge(){
        faces.add(new face(numE-1));
        faces.add(new face(1-numE));
    }

    //Adds face assoc with given edge
    /**
     * @brief Adds a face associated with the given edge
     */
    public void addFace(int e){
        faces.add(new face(e));
    }

    //Goes through verts and sets rBL and rTR
    /**
     * @brief Sets the rectangle, initially
     */
    public void setRect(){
        for(vertex v: vertices){
            changeRect(v);
        }
    }

    // changes it
    /**
     * @brief Checks the rectangle against given vertex and changes it
     * @param v new vertex to be checked against
     */
    public void changeRect(vertex v){
        if(v.x > rTR.x) rTR.x = v.x;
        if(v.y > rTR.y) rTR.y = v.y;
        if(v.x < rBL.x) rBL.x = v.x;
        if(v.y < rBL.y) rBL.y = v.y;
    }

    //Prints all vertices
    /**
     * @brief Prints all the vertices
     */
    public void printVertices(){
        System.out.print("\nVertices: \n");
        for(int i = 0; i < numV; i++){
            System.out.print(i + ":" + vertices.get(i).stringForm() + "\n");
        }
        System.out.println("\n");
    }

    //Prints all edges
    /**
     * @brief Prints all the edges
     */
    public void printEdges(){
        System.out.println("Edges: ");
        for(int i = 0; i < numE; i++){
            edgesF.get(i).printEdge(vertices, i);
        }
    }

    //Prints all faces
    /**
     * @brief Prints all the faces
     */
    public void printFaces(){
        System.out.println("\nFaces: ");
        for(face f: faces){
            f.printFace();
        }
    }

    //Prints the rect
    /**
     * @brief Prints the bounding rectangle
     */
    public void printRect(){
        System.out.println("\nRect: \nBottom Left: " + rBL.stringForm());
        System.out.println("Top Right: " + rTR.stringForm());
    }

    //Prints the whole polygon
    /**
     * @brief Prints the vertices, edges, faces and the bounding rectangle of the polygon
     */
    public void printPoly(){
        this.printVertices();
        this.printEdges();
        this.printFaces();
        this.printRect();
    }

}

/**
 * @brief A class describing a face
 */
class face{
    int edge; //only needs assoc edge

    /**
     * @brief Creates a face associated with the given edge
     */
    public face(int e){
        edge = e < 0 ? e-1:e+1; //adds one to edge value to move to 1-indexing
    }

    /**
     * @brief Prints the face's details
     */
    public void printFace(){
        if(edge >= 0)
            System.out.println("Face with bounding edge: Forward " + edge);
        if(edge < 0)
            System.out.println("Face with bounding edge: Backward " + edge*-1);
    }
}

/**
 * @brief A class describing a vertex
 */
class vertex{
    double x, y; //coordinates
    int id; //ID for findign index
    int inc_Edge;   //edge

    /**
     * @brief Creates a null vertex
     */
    public vertex(){}

    /**
     * @brief Creates a vertex using the given x and y coordinates and id
     * @param x the x coordinate
     * @param y the y coordinate
     * @param id the id (used to find the index)
     */
    public vertex(double x, double y, int id){
        // coord = new int[2];
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * @brief Creates a vertex using the vertices of passed vertex
     * @param v the vertex we wish to copy
     * @param id the id (used to find the index)
     */
    public vertex(vertex v, int id){
        this.id = id;
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * @brief Sets the inc_edge variable
     * @param e the edge we want to associate the vertex with
     */
    public void setIncEdge(int e){
        inc_Edge = e;
    }

    /**
     * @brief Returns the details of the vertex in the form of a string
     */
    public String stringForm(){
        return "(" + x + ", " + y + ") and edge: " + inc_Edge;
    }
}

/**
 * @brief A class describing an edge
 */
class edge{
    int twin;   //twin index
    int next;   //next index
    int prev;   //prev index

    int origin; //origin index
    int dest;   //dest index

    /**
     * @brief Creates an edge from given origin to given destination
     * @param o the index of the origin vertex
     * @param d the index of the destination vertex
     */
    public edge(int o, int d){
        origin = o;
        dest = d;
    }

    //prints edge
    /**
     * @brief Prints the edge
     * @param vi the list of vertices, so we can get teh origin and destination vertices' info
     * @param i just so its more readable, we pass a int
     */
    public void printEdge(ArrayList<vertex> vi, int i){
        System.out.println(
                ++i + ". Edge with origin: " + vi.get(origin).stringForm() +
                        " and dest: " + vi.get(dest).stringForm() +
                        " with next: " + next +
                        " and prev: " + prev
        );
    }

    //set next
    /**
     * @brief Sets teh next of the edge, and its twin
     * @param edgesTwin the arraylist containing the twin edges
     * @param e the new next edge
     */
    public void setNext(ArrayList<edge> edgesTwin, int e){
        next = e;
        edgesTwin.get(twin).prev = e;
    }

    //set prev
    /**
     * @brief Sets the prev of the edge, and its twin
     * @param edgesTwin the arraylist containing the twin edges
     * @param e the new prev edge
     */
    public void setPrev(ArrayList<edge> edgesTwin, int e){
        prev = e;
        edgesTwin.get(twin).next = e;
    }
}