package CONTROLLER;

import MODEL.Arc;
import MODEL.CPP;
import MODEL.Graph;
import VIEW.VizualizacaoGrafo;
import VIEW.FramePainel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class GraphController{
    private FramePainel PainelView;
    private Graph GraphIn;
    private VizualizacaoGrafo GraphView;
    private VizualizacaoGrafo GraphSolucaoView;
    private CPP CarteiroChines;
    
    public GraphController(FramePainel Painel) {
        PainelView=Painel;
        GraphIn=new Graph();
        GraphView=new VizualizacaoGrafo("View Grafo Entrada");
    }

    public void ShowHideGraphView() {
        GraphView.MostrarFrame();
    }

    public void addVertex() {
        GraphIn.AddVertice();
        GraphView.AddVertice(String.valueOf(GraphIn.getVertexQty()-1));
    }

    public void addEdge() {
        try{
        int v1, v2;
        float custo;
        if (GraphIn.getVertexQty()<=0) throw new IllegalArgumentException("Grafo Vazio.");
        v1=VerticesDaAresta("Partida"); if(v1>=GraphIn.getVertexQty() || v1<0 ) throw new IllegalArgumentException("Vértice inexistente.");
        v2=VerticesDaAresta("Destino"); if(v2>=GraphIn.getVertexQty() || v2<0 ) throw new IllegalArgumentException("Vértice inexistente.");
        custo=CustoDaAresta();
        //adiciona na estrutura de arcos para o cpp e armazenar no bd
        GraphIn.AddEdge(v1, v2, custo);
        //adiciona na vizualiação o arco
        GraphView.AddAresta(v1, v2);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(PainelView, "Erro, insira valor valido", "Erro grafo inválido", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(PainelView, "Erro! "+e.getMessage(), "Erro grafo inválido", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void addEdge(int v1, int v2, float custo){
        GraphIn.AddEdge(v1, v2, custo);
        GraphView.AddAresta(v1, v2);
    }
    
    private int VerticesDaAresta(String a) {
        return Integer.parseInt(JOptionPane.showInputDialog(PainelView, "Vertice " + a + ", "
                + "Número do vértice:", "Vertice" + a + ":",
                JOptionPane.INFORMATION_MESSAGE));
    }

    private float CustoDaAresta() {
        return Float.parseFloat(JOptionPane.showInputDialog(PainelView, "Custo do "
                + "vértice (float):", "Custo do vértice:",
                JOptionPane.INFORMATION_MESSAGE));
    }

    public void LimparGraphIn() {
        GraphIn.ResetGGraph();
        GraphView.MostrarFrame(false);
        GraphView=new VizualizacaoGrafo("View Grafo Entrada");
    }
    
    public void GravarGrafo(){
        try {
            String nome=JOptionPane.showInputDialog(PainelView, "Nome para salvar o grafo:", "Insira nome do grafo:", JOptionPane.WARNING_MESSAGE);
            if (nome != null){
                if(nome.trim().equals("")) throw new IllegalArgumentException("Nome invalido");
                Connection c = new ConnectionFactory().getConnection();
                System.out.println("Opened database successfully");
                Statement stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Grafos "
                        + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " NOME TEXT NOT NULL UNIQUE,"
                        + " VERTICES INTEGER     NOT NULL,"
                        + " ARCOS BLOB)";
                stmt.executeUpdate(sql);
                stmt.close();

                //converter objeto arcs em byte[]
                byte[] byteArrayObject = null;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(GraphIn.getAdjList());
                oos.close();
                bos.close();
                byteArrayObject = bos.toByteArray();

                //insert db
                sql = "INSERT INTO Grafos (NOME,VERTICES,ARCOS) "
                        + "VALUES (?, ?, ?);";
                PreparedStatement insertStmt = c.prepareStatement(sql);

                insertStmt.setString(1, nome);
                insertStmt.setInt(2, GraphIn.getVertexQty());
                insertStmt.setBytes(3, byteArrayObject);

                insertStmt.executeUpdate();
                insertStmt.close();
                c.close();
            }
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e){
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e){
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void CarregarGrafo() {
        try {
            //Lista para salvar query dos nomes
            ArrayList<String> Grafos = new ArrayList();

            Connection c = new ConnectionFactory().getConnection();
            System.out.println("Opened database successfully");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome FROM Grafos");
            while (rs.next()) {
                Grafos.add(rs.getString(1));
            }
            stmt.close();
            rs.close();
            c.close();

            //list to array
            String[] array = Grafos.toArray(new String[0]);

            //JOptionPane escolher grafo para carregar
            String GrafoSelecionado = (String) JOptionPane.showInputDialog(PainelView,
                    "Qual Grafo Carregar",
                    "Escolha Grafo para carregar",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    array,
                    array[0]);
            System.out.println(GrafoSelecionado);

            if (GrafoSelecionado != null) {
                c = new ConnectionFactory().getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery("SELECT * FROM Grafos WHERE NOME ='" + GrafoSelecionado + "'");
                byte[] st = (byte[]) rs.getObject(4);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                Vector DBAdjList = new Vector();
                DBAdjList = (Vector) ois.readObject();

                //Elimina e cria nova view, reseta grafo
                GraphView.MostrarFrame(false);
                GraphView = new VizualizacaoGrafo("View Grafo Entrada");
                GraphView.MostrarFrame(false);
                GraphIn.ResetGGraph();

                //Adiciona vertices e arestas do grafo carregado
                for (int i = 0; i < rs.getInt(3); i++) {
                    addVertex();
                }
                for (int i = 0; i < DBAdjList.size(); i++) {
                    Arc aresta = (Arc) DBAdjList.elementAt(i);
                    addEdge(aresta.getU(), aresta.getV(), aresta.getCost());
                }
                stmt.close();
                rs.close();
                c.close();
                GraphView.MostrarFrame();
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e){
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(PainelView, "Exception: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void CPP() {        
        try {
            int p1 = 0;
            if (PainelView.getPausado()) {
                String tempo=JOptionPane.showInputDialog(PainelView, "Construção da solução com pausas de Quanto tempo (s)?", "Pausa para ver construção?", JOptionPane.WARNING_MESSAGE);
                if(tempo == null) return; else p1 = Integer.valueOf(tempo);
            }
            GraphSolucaoView = new VizualizacaoGrafo("Grafo Solução");
            for (int i = 0; i < GraphIn.getVertexQty(); i++) {
                GraphSolucaoView.AddVertice(String.valueOf(i));
            }
            CarteiroChines = new CPP(GraphIn.getVertexQty(), p1, GraphSolucaoView);
            for (int i = 0; i < GraphIn.getEdgeQty(); i++) {
                Arc aresta = (Arc) GraphIn.getAdjList().elementAt(i);
                CarteiroChines.addArc(aresta.getlab(), aresta.getU(), aresta.getV(), aresta.getCost());
            }
            CarteiroChines.solve();
            CarteiroChines.printCPT(0);
            System.out.println("Cost = " + CarteiroChines.cost());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(PainelView, "Erro! Valor inválido", "Erro valor inválido", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            GraphSolucaoView.MostrarFrame(false);
            JOptionPane.showMessageDialog(PainelView, "Erro! " + e.getMessage(), "Erro grafo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(PainelView, "Erro! " + e.getMessage(), "Erro grafo inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    
private class ConnectionFactory{
    public Connection getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:Grafo.db");            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
    
}