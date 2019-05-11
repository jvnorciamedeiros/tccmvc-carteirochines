package VIEW;

import VIEW.*;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import java.awt.Dimension;
import org.jgrapht.graph.*;
import javax.swing.JFrame;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;

    public class VizualizacaoGrafo {
        private final ListenableGraph<String, DefaultEdge> graph;
        private final JGraphXAdapter<String, DefaultEdge> jgxAdapter;
        private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
        private final JFrame frame;
        public VizualizacaoGrafo(String Titulo) {
            graph = new ListenableDirectedMultigraph<>(DefaultEdge.class);
            jgxAdapter = new JGraphXAdapter<>(graph);
            // positioning via jgraphx layouts
            new mxCircleLayout(jgxAdapter).execute(jgxAdapter.getDefaultParent());
            frame = new JFrame();
            frame.getContentPane().add(new mxGraphComponent(jgxAdapter));
            frame.setTitle(Titulo);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    public void AddVertice(String V){
            graph.addVertex(V);
            new mxCircleLayout(jgxAdapter).execute(jgxAdapter.getDefaultParent());
            frame.pack();
    }
    public void AddAresta(int v1,int v2){
            graph.addEdge(String.valueOf(v1), String.valueOf(v2));
            new mxParallelEdgeLayout(jgxAdapter).execute(jgxAdapter.getDefaultParent());
            frame.pack();
    }
    public void MostrarFrame(){
        frame.setVisible(!frame.isVisible());
    }
    public void MostrarFrame(boolean a){
        frame.setVisible(a);
    }
    
    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedWeightedPseudograph<>(edgeClass));
        }
    }
    }