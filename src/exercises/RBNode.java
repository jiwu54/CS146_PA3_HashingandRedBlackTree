package exercises;

/**
 * FileName: RBNode.java
 *
 * Node object for Red Black Tree
 * Contain a url, a page rank score and color
 *
 * @author JianBin Wu
 *
 */
public class RBNode<T> {
    /** Url string */
    private T url;
    /** color of the node */
    private String color;
    /** page rank score*/
    private int pageRank;
    /** parent of the node */
    private RBNode<T> parent;
    /** left child of the node */
    private RBNode<T> leftChild ;
    /** right child of the node*/
    private RBNode<T> rightChild ;

    /**
     * Construct a Red Black Node with url and page rank score
     *
     * @param url url string
     * @param pageRank page rank score
     */
    public RBNode(T url,int pageRank){
        this.url = url;
        this.pageRank  = pageRank;
        this.color = "BLACK";
    }

    /**
     * Get the color of the node
     * @return the color of the node
     */
    public String getColor()
    {
        return color;
    }


    /**
     * Get the url of the node
     * @return the url of the node
     */
    public T getUrl()
    {
        return url;
    }

    /**
     * Get the page rank score of the node
     *
     * @return the page rank score of the node
     */
    public int getPageRank()
    {
        return pageRank;
    }

    /**
     * Set the color of the node to different color
     * @param color color being set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the left child of the node
     *
     * @return the left child of the node
     */
    public RBNode<T> getLeftChild()
    {
        return leftChild;
    }

    /**
     * Get the right child of the node
     *
     * @return the right child of the node
     */
    public RBNode<T> getRightChild()
    {
        return rightChild;
    }

    /**
     * Set the right child of the node to other node
     * @param rightChild the right child want to set
     */
    public void setRightChild(RBNode<T> rightChild)
    {
        this.rightChild = rightChild;
    }

    /**
     * Get the parent of the node
     * @return the parent of the node
     */
    public RBNode<T> getParent()
    {
        return parent;
    }

    /**
     * Set the parent of the node to other node
     * @param parent the parent want to set
     */
    public void setParent(RBNode<T> parent)
    {
        this.parent = parent;
    }

    /**
     * Set the left child  of the node to other node
     * @param leftChild the left child want to set
     */
    public void setLeftChild(RBNode<T> leftChild) {
        this.leftChild = leftChild;
    }
}

