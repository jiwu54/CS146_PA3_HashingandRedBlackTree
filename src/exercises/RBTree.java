package exercises;

import java.util.*;

/**
 * FileName: RBTree.java
 *
 * Implementation of Red Black Tree,
 * following the pseudo codes from the book Introduction To Algorithms Third Edition
 *
 * @author JianBin Wu
 *
 */
public class RBTree<T> {
    /** The root of the Red Black Tree */
    private RBNode root;
    /** The nil node of the Red Black Tree */
    private final RBNode nil = new RBNode(null,0);

    /**
     * Construct an empty Red Black Tree
     */
    public RBTree(){
        root = nil;
    }

    /**
     * Left rotation for a node
     * @param T The target Red Black Tree
     * @param x The target node want to rotate
     */
    public void leftRotate(RBTree T , RBNode x){
        RBNode y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if(y.getLeftChild() != T.nil){
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == T.nil){
            T.root = y;
        }
        else if(x == x.getParent().getLeftChild()){
            x.getParent().setLeftChild(y);
        }
        else{
            x.getParent().setRightChild(y);
        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    /**
     * Right rotation for a node
     * @param T The target Red Black Tree
     * @param x The target node want to rotate
     */
    public void rightRotate(RBTree T , RBNode x){
        RBNode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if(y.getRightChild() != T.nil){
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == T.nil){
            T.root = y;
        }
        else if(x == x.getParent().getRightChild()){
            x.getParent().setRightChild(y);
        }
        else{
            x.getParent().setLeftChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);
    }

    /**
     * Insert a node into the Red Black Tree
     * @param T The target Red Black Tree
     * @param z The node want to insert
     */
    public void RBInsert(RBTree T, RBNode z){
        RBNode y = T.nil;
        RBNode x = T.root;
        while (x != T.nil){
            y = x;
            if (z.getPageRank() < x.getPageRank()){
                x = x.getLeftChild();
            }
            else{
                x = x.getRightChild();
            }
        }
        z.setParent(y);
        if(y == T.nil){
            T.root = z;
        }
        else if(z.getPageRank() < y.getPageRank()){
            y.setLeftChild(z);
        }
        else{
            y.setRightChild(z);
        }
        z.setLeftChild(T.nil);
        z.setRightChild(T.nil);
        z.setColor("RED");
        RBInsertFixup(T,z);
    }

    /**
     * Fix the Red Black Tree after the node inserted, so the Red Black Tree follow the properties.
     * @param T The target Red Black Tree
     * @param z The node inserted
     */
    public void RBInsertFixup(RBTree T, RBNode z){
        while (z.getParent().getColor() == "RED"){
            if(z.getParent() == z.getParent().getParent().getLeftChild()){
                RBNode y = z.getParent().getParent().getRightChild();
                if(y.getColor() == "RED"){
                    z.getParent().setColor("BLACK"); //Case 1
                    y.setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    z = z.getParent().getParent();
                }
                else if(z == z.getParent().getRightChild()){
                    z = z.getParent(); //Case 2
                    leftRotate(T,z);
                }
                else {
                    z.getParent().setColor("BLACK"); //Case 3
                    z.getParent().getParent().setColor("RED");
                    rightRotate(T, z.getParent().getParent());
                }

            }
            else{
                RBNode y = z.getParent().getParent().getLeftChild();
                if(y.getColor() == "RED"){
                    z.getParent().setColor("BLACK"); //Case 1
                    y.setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    z = z.getParent().getParent();
                }
                else if(z == z.getParent().getLeftChild()){
                    z = z.getParent(); //Case 2
                    rightRotate(T,z);
                }
                else {
                    z.getParent().setColor("BLACK"); //Case 3
                    z.getParent().getParent().setColor("RED");
                    leftRotate(T, z.getParent().getParent());
                }
            }
        }
        T.root.setColor("BLACK");
    }

    /**
     * Transplant/Delete a node with other node
     *
     * @param T The target Red Black Tree
     * @param u node being Transplant
     * @param v node that replace u
     */
    public void RBTransplant(RBTree T,RBNode u, RBNode v){
        if(u.getParent() == T.nil){
            T.root = v;
        }
        else if(u == u.getParent().getLeftChild()){
            u.getParent().setLeftChild(v);
        }
        else{
            u.getParent().setRightChild(v);
        }
        v.setParent(u.getParent());
    }

    /**
     * Find the left-most node of a node
     *
     * @param x the node want to find
     * @return the left-most node of node x
     */
    public RBNode TreeMinimum(RBNode x){
        while(x.getLeftChild() != nil){
            x = x.getLeftChild();
        }
        return x;
    }

    /**
     * Delete a node into the Red Black Tree
     *
     * @param T The target Red Black Tree
     * @param z The node want to delete
     */
    public void RBDelete(RBTree T, RBNode z){
        RBNode y = z;
        RBNode x ;
        String yOriginalColor = y.getColor();
        if(z.getLeftChild() == T.nil){
             x = z.getRightChild();
            RBTransplant(T,z,z.getRightChild());
        }
        else if(z.getRightChild() == T.nil){
             x = z.getLeftChild();
            RBTransplant(T,z,z.getLeftChild());
        }
        else{
            y = TreeMinimum(z.getRightChild());
            yOriginalColor = y.getColor();
             x = y.getRightChild();
            if(y.getParent() == z){
                x.setParent(y);
            }
            else{
                RBTransplant(T,y,y.getRightChild());
                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }
            RBTransplant(T,z,y);
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());
        }
        if(yOriginalColor == "BLACK"){
            RBDeleteFixup(T,x);
        }
    }

    /**
     * Fix the Red Black Tree after the node deleted, so the Red Black Tree follow the properties.
     *
     * @param T The target Red Black Tree
     * @param x The node being fix
     */
    public void RBDeleteFixup(RBTree T, RBNode x){
        while(x != T.root && x.getColor() =="BLACK"){
            if(x == x.getParent().getLeftChild()){
                RBNode w = x.getParent().getRightChild();
                if(w.getColor() == "RED"){
                    w.setColor("BLACK"); //Case 1
                    x.getParent().setColor("RED");
                    leftRotate(T,x.getParent());
                    w = x.getParent().getRightChild();
                }
                if(w.getLeftChild().getColor() == "BLACK" && w.getRightChild().getColor() == "BLACK"){
                    w.setColor("RED"); //Case 2
                    x = x.getParent();
                }
                else if(w.getRightChild().getColor() == "BLACK"){
                    w.getLeftChild().setColor("BLACK"); //Case 3
                    w.setColor("RED");
                    rightRotate(T,w);
                    w = x.getParent().getRightChild();
                }
                w.setColor(x.getParent().getColor()); //Case 4
                x.getParent().setColor("BLACK");
                w.getRightChild().setColor("BLACK");
                leftRotate(T, x.getParent());
                x = T.root;
            }
            else{
                RBNode w = x.getParent().getLeftChild();
                if(w.getColor() == "RED"){
                    w.setColor("BLACK"); //Case 1
                    x.getParent().setColor("RED");
                    rightRotate(T,x.getParent());
                    w = x.getParent().getLeftChild();

                }
                if(w.getRightChild().getColor() == "BLACK" && w.getRightChild().getColor() == "BLACK"){
                    w.setColor("RED"); //Case 2
                    x = x.getParent();
                }
                else if(w.getLeftChild().getColor() == "BLACK"){
                    w.getRightChild().setColor("BLACK"); //Case 3
                    w.setColor("RED");
                    leftRotate(T,w);
                    w = x.getParent().getLeftChild();
                }
                w.setColor(x.getParent().getColor()); //Case4
                x.getParent().setColor("BLACK");
                w.getLeftChild().setColor("BLACK");
                rightRotate(T,x.getParent());
                x = T.root;
            }
        }
        x.setColor("BLACK");
    }

    /**
     * Sorting the data of each node in a Red Black Tree from large to small
     * (Reverse InorderWalk)
     *
     * @param x the root node
     */
    public void RBSort (RBNode x){
       if (x != nil){
           RBSort (x.getRightChild());
           System.out.println(x.getUrl() + " PageRank:" + x.getPageRank() + " " +  x.getColor());
           RBSort (x.getLeftChild());
       }
    }

    /**
     * Search for a node
     *
     * @param x node to start search
     * @param pageRank pageRank Socre to search
     * @return the result node
     */
    public RBNode iterativeTreeSearch(RBNode x , int pageRank){
        while (x != nil && pageRank != x.getPageRank()){
            if(pageRank < x.getPageRank()){
                x = x.getLeftChild();
            }
            else{
                x = x.getRightChild();
            }
        }
        return x;
    }

    /**
     * print Red Black Tree in Inorder walk
     * with parent, left, right children of the node.
     */
    public void printRBTree(RBNode x){
        if (x != nil){
            printRBTree (x.getLeftChild());
            System.out.println(
                    "Current: " + x.getUrl()
                            + " " + x.getPageRank()
                            + " " + x.getColor()
                            + " | Parent: " + x.getParent().getUrl() +
                            " "+ x.getParent().getPageRank() +
                            " " + x.getParent().getColor()
                    + " | Left Child: " + x.getLeftChild().getUrl()
                            + " " +  x.getLeftChild().getPageRank()
                            + " " + x.getLeftChild().getColor()
                            +  " | Right Child: " + x.getRightChild().getUrl()
                            + " " + x.getRightChild().getPageRank()
                    + " " + x.getRightChild().getColor());
            printRBTree (x.getRightChild());
        }
    }





public static void main(String args[]){
    RBTree t = new RBTree();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Google Search Engine Simulator");
    System.out.println("-----------------------------------------------");
    System.out.println("Please enter the keyword.");
    String keyword = scanner.nextLine();
    WebCrawler crawler = new WebCrawler (keyword);
    crawler.search();
    Set<Integer> s = new HashSet<>();

    //generate scores and store in a HashSet
    while(s.size() < 20){
        Random randomScore = new Random();
        int pageRank  = randomScore.nextInt(100) + 1;
        s.add(pageRank);
    }
    //save the score in the HashSet into ArrayList
    ArrayList<Integer> scores = new ArrayList<>();
    for (Integer a : s) {
        scores.add(a);
    }

    //save the clawing result into an ArrayList
    ArrayList<String> urls = new ArrayList<>();
    for (String w : crawler.getUrls()){
        urls.add(w);
    }
    //print out clawing result
    for(int i = 0 ; i < 20; i++){
        System.out.println(urls.get(i) + " PageRank:" + scores.get(i));
    }
    System.out.println("-----------------------------------------------");
    ArrayList<RBNode> nodes = new ArrayList<>();
    //creat node with urls and pageRank score and create a Red Black Tree
    for(int i = 0 ; i < 20; i++){
        RBNode node = new RBNode(urls.get(i),scores.get(i));
        t.RBInsert(t,node);
    }
    System.out.println("Here is the Red Black Tree in Inorder walk");
    t.printRBTree(t.root);
    System.out.println("-----------------------------------------------");
    System.out.println("Press 1 to insert a node");
    System.out.println("Press 2 to delete a node");
    System.out.println("Press 3 to sort the websites");
    System.out.println("-----------------------------------------------");
    int selection = scanner.nextInt();
    while(selection == 1 || selection == 2 || selection == 3){
        // allow user to insert node
        if (selection == 1){
            System.out.println("Enter url");
            String url = scanner.next();
            System.out.println("Enter PageRank Score");
            int pageRank = scanner.nextInt();
            RBNode insert = new RBNode(url,pageRank);
            t.RBInsert(t,insert);
            System.out.println("Inserted");
            System.out.println("-----------------------------------------------");
            System.out.println("Here is the Red Black Tree in Inorder walk");
            t.printRBTree(t.root);
            System.out.println("-----------------------------------------------");
        }

        // allowed user to delete node
        if(selection == 2){
            System.out.println("Enter PageRank Score");
            int pageRank = scanner.nextInt();
            RBNode delete = t.iterativeTreeSearch(t.root,pageRank);
            t.RBDelete(t,delete);
            System.out.println("Deleted");
            System.out.println("-----------------------------------------------");
            System.out.println("Here is the Red Black Tree in Inorder walk");
            t.printRBTree(t.root);
            System.out.println("-----------------------------------------------");
        }

        // perform sorting
        if(selection == 3){
            System.out.println("Here is the sorted Red Black Tree ");
            t.RBSort(t.root);
            System.out.println("-----------------------------------------------");
        }

        selection = scanner.nextInt();
    }
    if( selection != 1 && selection != 2 && selection != 3){
        System.out.println("Thanks for using! Bye! ");
    }
}

}
