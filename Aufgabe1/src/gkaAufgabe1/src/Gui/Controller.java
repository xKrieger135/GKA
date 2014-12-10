package Gui;

import org.jgrapht.Graph;

public class Controller {
	private BreadthFirstSearchAlgorithmus bfs = new BreadthFirstSearchAlgorithmus();
	private Dijkstra dijkstra = new Dijkstra();
	private CreateBig createBig = new CreateBig();
	private FloydWarshall fw = new FloydWarshall();
	private FordFulkerson ff = new FordFulkerson();

	public Controller() {
		setLookAndFeel(this);
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
	}

	public static void setLookAndFeel(final Controller controller) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Gui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Gui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Gui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Gui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Gui(controller).setVisible(true);
			}
		});
	}

	public int breadthFirstSearch(Graph<String, WeightedEdge> graph,String start, String end) {
		return bfs.breadthFirstSearch(graph,start, end);
	}
	
	public double returnDistance(Graph<String, WeightedEdge> graph, String source, String target) {
		return dijkstra.returnDistance(graph, source, target);
	}
	
	public void writeBigIntoFile() {
		createBig.writeBigIntoFile();
	}
	
	public double floydWarshall(Graph<String, WeightedEdge> graph, String source, String target) {
		return fw.floydWarshallAlgorithmus(graph, source, target);
	}
	
	public double fordFulkerson(Graph<String, WeightedEdge> graph, String quelle, String senke) {
		return ff.fordFulkersonAlgorithmus(graph, quelle, senke);
	}

}
