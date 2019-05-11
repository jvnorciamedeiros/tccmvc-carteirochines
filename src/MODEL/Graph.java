package MODEL;


import java.util.Vector;

public class Graph{
    private int VertexQty;
    private int EdgeQty;
    private Vector AdjList;
    public Graph(){
        VertexQty=0;
        EdgeQty=0;
        AdjList=new Vector();
    }
    public int getVertexQty(){
        return VertexQty;
    }
    public int getEdgeQty(){
        return EdgeQty;
    }
    public Vector getAdjList(){
        return AdjList;
    }
    public void ResetGGraph(){
        VertexQty=0;
        EdgeQty=0;
        AdjList=new Vector();
    }
    public void AddVertice(){
        VertexQty++;
    }
    public void AddEdge(int v1, int v2, float custo){
        AdjList.addElement(new Arc((String.valueOf(EdgeQty)), v1, v2, custo));
        EdgeQty++;
    }
    
}