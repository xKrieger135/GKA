/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.Pseudograph;

/**
 * 
 * @author Paddy-Gaming
 */
public class Gui extends javax.swing.JFrame {

	private JGraphModelAdapter<String, WeightedEdge> jGraphModelAdapter;
	private JGraph jGraph;
	private Controller controller;
	private MyParser myParser;
	private String startVertex;
	private String endVertex;

	private static final Color DEFAULT_BG_COLOR = Color.decode("#c7d2ff");
	private static final Color DEFAULT_BG_COLOR2 = Color.decode("#fffff");
	private static final Dimension DEFAULT_SIZE = new Dimension(1200, 800);

	/**
	 * Creates new form Gui
	 */
	public Gui(Controller controller) {
		this.controller = controller;
		initComponents();
		this.setSize(DEFAULT_SIZE);
		createGraph();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanelForEverything = new javax.swing.JPanel();
		jButtonSetStart = new javax.swing.JButton();
		jButtonSetEnd = new javax.swing.JButton();
		jButtonStartBFS = new javax.swing.JButton();
		jButtonStartDijkstra = new javax.swing.JButton();
		jButtonWriteBIGIntoFile = new javax.swing.JButton();
		jPanelForGraph = new javax.swing.JPanel();
		jTextFieldForBFSOutput = new javax.swing.JTextField();
		jTextFieldSetStart = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jTextFieldSetEndKnoten = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		jPanelForEverything.setLayout(null);

		jButtonSetStart.setText("SetStart");
		jButtonSetStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSetStartActionPerformed(evt);
			}
		});
		jPanelForEverything.add(jButtonSetStart);
		jButtonSetStart.setBounds(1010, 90, 110, 40);

		jButtonSetEnd.setText("SetEnd");
		jButtonSetEnd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSetEndActionPerformed(evt);
			}
		});
		jPanelForEverything.add(jButtonSetEnd);
		jButtonSetEnd.setBounds(1010, 260, 110, 40);

		jButtonStartBFS.setText("StartBFS");
		jPanelForEverything.add(jButtonStartBFS);
		jButtonStartBFS.setBounds(1010, 560, 110, 40);

		jButtonStartDijkstra.setText("StartDijkstra");
		jPanelForEverything.add(jButtonStartDijkstra);
		jButtonStartDijkstra.setBounds(1010, 460, 110, 40);
		
		jButtonWriteBIGIntoFile.setText("Write BIG");
		jPanelForEverything.add(jButtonWriteBIGIntoFile);
		jButtonWriteBIGIntoFile.setBounds(1010, 660, 110, 40);

		jButtonStartBFS.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonStartBFSActionPerformed(evt);
			}
		});

		jButtonStartDijkstra
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						controller.returnDistance(
								myParser.parseTextFromTextFileToGraph(),
								startVertex, endVertex);
					}
				});

		jButtonWriteBIGIntoFile
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
//						controller.writeBigIntoFile();
						controller.floydWarshall(myParser.parseTextFromTextFileToGraph(),
								startVertex, endVertex);
					}
				});

		javax.swing.GroupLayout jPanelForGraphLayout = new javax.swing.GroupLayout(
				jPanelForGraph);
		jPanelForGraph.setLayout(jPanelForGraphLayout);
		jPanelForGraphLayout.setHorizontalGroup(jPanelForGraphLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 930, Short.MAX_VALUE));
		jPanelForGraphLayout.setVerticalGroup(jPanelForGraphLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 600, Short.MAX_VALUE));

		jPanelForEverything.add(jPanelForGraph);
		jPanelForGraph.setBounds(60, 20, 930, 600);
		jPanelForGraph.setBackground(DEFAULT_BG_COLOR);

		jTextFieldForBFSOutput
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jTextFieldForBFSOutputActionPerformed(evt);
					}
				});
		jPanelForEverything.add(jTextFieldForBFSOutput);
		jTextFieldForBFSOutput.setBounds(60, 650, 930, 110);
		jPanelForEverything.add(jTextFieldSetStart);
		jTextFieldSetStart.setBounds(1010, 180, 110, 30);

		jLabel1.setText("SetStartKnotenEingabe :");
		jPanelForEverything.add(jLabel1);
		jLabel1.setBounds(1010, 150, 130, 14);

		jTextFieldSetEndKnoten
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jTextFieldSetEndKnotenActionPerformed(evt);
					}
				});
		jPanelForEverything.add(jTextFieldSetEndKnoten);
		jTextFieldSetEndKnoten.setBounds(1010, 360, 110, 30);

		jLabel2.setText("SetEndKnotenEingabe:");
		jPanelForEverything.add(jLabel2);
		jLabel2.setBounds(1014, 330, 130, 14);

		getContentPane().add(jPanelForEverything);
		jPanelForEverything.setBounds(0, 0, 1200, 800);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonStartBFSActionPerformed(java.awt.event.ActionEvent evt) {
		System.out
				.println(controller.breadthFirstSearch(
						myParser.parseTextFromTextFileToGraph(), startVertex,
						endVertex));
		// startVertex = jTextFieldSetStart.getText();
		// endVertex = jTextFieldSetEndKnoten.getText();
	}

	private void jTextFieldForBFSOutputActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldForBFSOutputActionPerformed

	}// GEN-LAST:event_jTextFieldForBFSOutputActionPerformed

	private void jButtonSetStartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSetStartActionPerformed
		startVertex = jTextFieldSetStart.getText();
		jTextFieldSetStart.setText("");
		System.out.println(startVertex);
		// myParser.writeGraphIntoFile();
	}// GEN-LAST:event_jButtonSetStartActionPerformed

	private void jButtonSetEndActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSetEndActionPerformed
		endVertex = jTextFieldSetEndKnoten.getText();
		jTextFieldSetEndKnoten.setText("");
		System.out.println(endVertex);
	}// GEN-LAST:event_jButtonSetEndActionPerformed

	private void jTextFieldSetEndKnotenActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldSetEndKnotenActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldSetEndKnotenActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonSetEnd;
	private javax.swing.JButton jButtonSetStart;
	private javax.swing.JButton jButtonStartBFS;
	private javax.swing.JButton jButtonStartDijkstra;
	private javax.swing.JButton jButtonWriteBIGIntoFile;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanelForEverything;
	private javax.swing.JPanel jPanelForGraph;
	private javax.swing.JTextField jTextFieldForBFSOutput;
	private javax.swing.JTextField jTextFieldSetEndKnoten;
	private javax.swing.JTextField jTextFieldSetStart;
	private JFrame graph;

	// End of variables declaration//GEN-END:variables

	// -------------------------------------------------------------------------------------------

	private void createGraph() {

		// Hier wird ein gerichteter graph erstellt
		myParser.readGraphFromFile();
		Graph<String, WeightedEdge> graph = myParser
				.parseTextFromTextFileToGraph();
		System.out.println(graph.vertexSet());

		// mit dem JgraphmodelAdapter werden die JGraphT Graphen dargestellt
		jGraphModelAdapter = new JGraphModelAdapter<String, WeightedEdge>(graph);

		// Anordnen der Vertexe (Graphen-Nodes) in Kreisform Code �bernahme
		double length = graph.vertexSet().size();
		Object[] vertex = graph.vertexSet().toArray();
		double pi2 = Math.PI * 2;
		for (int i = 0; i < length; i++) {
			positionVertexAt(vertex[i],
					(int) (Math.sin(pi2 * (i / length)) * 250 + 260),
					(int) (Math.cos(pi2 * (i / length)) * 250 + 260));
		}

		jGraph = new JGraph(jGraphModelAdapter);
		jGraph.setBounds(60, 20, 930, 600);

		graphColorChanges(jGraph);
		jPanelForGraph.add(jGraph);
		// setSize(DEFAULT_SIZE);
		// Updatet das Mainwindow
		SwingUtilities.updateComponentTreeUI(jPanelForGraph);
	}

	// Code �bernahme aus einem Tutorialvon JGraphT
	private void positionVertexAt(Object vertex, int x, int y) {
		DefaultGraphCell cell = jGraphModelAdapter.getVertexCell(vertex);
		Map attr = cell.getAttributes();
		Rectangle2D nodeForm = GraphConstants.getBounds(attr);

		GraphConstants.setBounds(
				attr,
				new Rectangle(x, y, (int) nodeForm.getWidth(), (int) nodeForm
						.getHeight()));

		Map cellAttr = new HashMap();
		cellAttr.put(cell, attr);
		jGraphModelAdapter.edit(cellAttr, null, null, null);
	}

	private void graphColorChanges(JGraph jGraph) {
		jGraph.setPreferredSize(DEFAULT_SIZE);

		Color c = DEFAULT_BG_COLOR;

		jGraph.setBackground(c);
	}

}
