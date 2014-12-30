/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JDGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import Gui.Controller;
import Gui.MyParser;
import Gui.WeightedEdge;

/**
 *
 * @author JanDennis
 */
public class GUI extends javax.swing.JFrame {
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
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
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

		ToolbarPanel = new javax.swing.JPanel();
		jComboAlgo = new javax.swing.JComboBox();
		jPanel3 = new javax.swing.JPanel();
		jButSetEnde = new javax.swing.JButton();
		jButSetStart = new javax.swing.JButton();
		jTextFieldStart = new javax.swing.JTextField();
		jTextFieldEnde = new javax.swing.JTextField();
		jButtonRunAlgo = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jPanelGraph = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane = new javax.swing.JTextPane();
		jLabel1 = new javax.swing.JLabel();
		jMenuMain = new javax.swing.JMenuBar();
		jMenuDatei = new javax.swing.JMenu();
		jItemDateiNeu = new javax.swing.JMenuItem();
		jItemDateiLaden = new javax.swing.JMenuItem();
		jItemDateiSpeichern = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ToolbarPanel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		jComboAlgo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Breath First Search", "Dijkstra", "Floyd Warshall" }));

		jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jButSetEnde.setText("Set End");
		jButSetEnde.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButSetEndeActionPerformed(evt);
			}
		});

		jButSetStart.setText("Set Start");
		jButSetStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButSetStartActionPerformed(evt);
			}
		});

		jTextFieldStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextFieldStartActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																jTextFieldStart)
														.addComponent(
																jButSetStart))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jButSetEnde,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jTextFieldEnde,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																69,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButSetStart)
														.addComponent(
																jButSetEnde))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jTextFieldStart,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTextFieldEnde,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		jButtonRunAlgo.setText("Run");
		jButtonRunAlgo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout ToolbarPanelLayout = new javax.swing.GroupLayout(
				ToolbarPanel);
		ToolbarPanel.setLayout(ToolbarPanelLayout);
		ToolbarPanelLayout
				.setHorizontalGroup(ToolbarPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								ToolbarPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												ToolbarPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jComboAlgo,
																javax.swing.GroupLayout.Alignment.TRAILING,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap())
						.addGroup(
								ToolbarPanelLayout
										.createSequentialGroup()
										.addGap(61, 61, 61)
										.addComponent(jButtonRunAlgo)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		ToolbarPanelLayout
				.setVerticalGroup(ToolbarPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								ToolbarPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(25, 25, 25)
										.addComponent(
												jComboAlgo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonRunAlgo)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanelGraphLayout = new javax.swing.GroupLayout(
				jPanelGraph);
		jPanelGraph.setLayout(jPanelGraphLayout);
		jPanelGraphLayout.setHorizontalGroup(jPanelGraphLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 617, Short.MAX_VALUE));
		jPanelGraphLayout.setVerticalGroup(jPanelGraphLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 574, Short.MAX_VALUE));

		// jPanelGraph.setEditable(false);
		jPanelGraph.setFocusable(false);

		jScrollPane3.setViewportView(jPanelGraph);

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jTextPane.setEditable(false);
		jTextPane.setFocusable(false);
		jScrollPane1.setViewportView(jTextPane);

		jLabel1.setText("Output:");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1)
				.addGroup(
						jPanel2Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(jLabel1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												362,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jMenuDatei.setText("Datei");

		jItemDateiNeu.setText("Neu");
		jItemDateiNeu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jItemDateiNeuActionPerformed(evt);
			}
		});
		jMenuDatei.add(jItemDateiNeu);

		jItemDateiLaden.setText("Laden");
		jItemDateiLaden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jItemDateiLadenActionPerformed(evt);
			}
		});
		jMenuDatei.add(jItemDateiLaden);

		jItemDateiSpeichern.setText("Speichern");
		jItemDateiSpeichern
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jItemDateiSpeichernActionPerformed(evt);
					}
				});
		jMenuDatei.add(jItemDateiSpeichern);

		jMenuMain.add(jMenuDatei);

		setJMenuBar(jMenuMain);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jScrollPane3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										619,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														ToolbarPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jPanel2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane3)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(ToolbarPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jTextFieldStartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldStartActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldStartActionPerformed

	private void jItemDateiSpeichernActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jItemDateiSpeichernActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jItemDateiSpeichernActionPerformed

	private void jItemDateiLadenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jItemDateiLadenActionPerformed
		// TODO add your handling code here:
		createGraph();
	}// GEN-LAST:event_jItemDateiLadenActionPerformed

	private void jItemDateiNeuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jItemDateiNeuActionPerformed
		// TODO add your handling code here:
		jGraph = new JGraph();
	}// GEN-LAST:event_jItemDateiNeuActionPerformed

	private void jButSetStartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButSetStartActionPerformed
		startVertex = jTextFieldStart.getText();
		jTextFieldStart.setText("");
		System.out.println(startVertex);
	}// GEN-LAST:event_jButSetStartActionPerformed

	private void jButSetEndeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButSetEndeActionPerformed
		endVertex = jTextFieldEnde.getText();
		jTextFieldEnde.setText("");
		System.out.println(endVertex);
	}// GEN-LAST:event_jButSetEndeActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		if (startVertex != "" && endVertex != "") {
			switch (jComboAlgo.getSelectedIndex()) {
			case 0: // BFS
				runBFS();
				break;
			case 1: // Dijkstra
				runDijkstra();
				break;
			case 2: // Ford Warshall
				runFord();
				break;

			}
		}
	}// GEN-LAST:event_jButton3ActionPerformed

	private void runFord() {
		// TODO Auto-generated method stub

	}

	private void runDijkstra() {
		// TODO Auto-generated method stub

	}

	private void runBFS() {
		// TODO Auto-generated method stub
		System.out
		.println(controller.breadthFirstSearch(
				myParser.parseTextFromTextFileToGraph(), startVertex,
				endVertex));

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
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
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel ToolbarPanel;
	private javax.swing.JButton jButSetEnde;
	private javax.swing.JButton jButSetStart;
	private javax.swing.JButton jButtonRunAlgo;
	private javax.swing.JComboBox jComboAlgo;
	private javax.swing.JMenuItem jItemDateiLaden;
	private javax.swing.JMenuItem jItemDateiNeu;
	private javax.swing.JMenuItem jItemDateiSpeichern;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JMenu jMenuDatei;
	private javax.swing.JMenuBar jMenuMain;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanelGraph;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextField jTextFieldEnde;
	private javax.swing.JTextField jTextFieldStart;
	private javax.swing.JTextPane jTextPane;

	// End of variables declaration//GEN-END:variables

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
		jGraph.setBounds(0, 0, 930, 600);

		graphColorChanges(jGraph);
		jPanelGraph.add(jGraph);
		// setSize(DEFAULT_SIZE);
		// Updatet das Mainwindow
		SwingUtilities.updateComponentTreeUI(jPanelGraph);
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
