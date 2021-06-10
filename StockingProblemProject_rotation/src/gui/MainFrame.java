package gui;

import algorithms.*;
import experiments.Experiment;
import experiments.ExperimentEvent;
import ga.GeneticAlgorithm;
import ga.geneticoperators.*;
import ga.selectionmethods.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

import random.RandomAlgorithm;
import stockingproblem.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainFrame extends JFrame implements AlgorithmListener {

    private static final long serialVersionUID = 1L;
    public static final int PANEL_SIZE = 250;
    private StockingProblem warehouse;
    private Algorithm<StockingProblemIndividual, StockingProblem> algorithm;
    private StockingProblemExperimentsFactory experimentsFactory;
    private PanelTextArea problemPanel;
    PanelTextArea bestIndividualPanel;
    private PanelParameters panelParameters = new PanelParameters();
    private JButton buttonDataSet = new JButton("Data set");
    private JButton buttonRun = new JButton("Run");
    private JButton buttonStop = new JButton("Stop");
    private JButton buttonExperiments = new JButton("Experiments");
    private JButton buttonRunExperiments = new JButton("Run experiments");
    private JTextField textFieldExperimentsStatus = new JTextField("", 10);
    private JPanel simulationPanel = new JPanel();
    private JScrollPane scrollablePanel = new JScrollPane();
    private XYSeries seriesBestIndividual;
    private XYSeries seriesAverage;
    private Image image;
    private SwingWorker<Void, Void> worker;
    private JLabel lblRunningTime;

    public MainFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private void jbInit() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("IA Project");

        //North Left Panel
        JPanel panelNorthLeft = new JPanel(new BorderLayout());
        panelNorthLeft.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        panelNorthLeft.add(panelParameters, java.awt.BorderLayout.WEST);
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonDataSet);
        buttonDataSet.addActionListener(new ButtonDataSet_actionAdapter(this));
        panelButtons.add(buttonRun);
        buttonRun.setEnabled(false);
        buttonRun.addActionListener(new ButtonRun_actionAdapter(this));
        panelButtons.add(buttonStop);
        buttonStop.setEnabled(false);
        buttonStop.addActionListener(new ButtonStop_actionAdapter(this));
        panelNorthLeft.add(panelButtons, java.awt.BorderLayout.SOUTH);

        //North Right Panel - Chart creation
        seriesBestIndividual = new XYSeries("Best");
        seriesAverage = new XYSeries("Average");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesBestIndividual);
        dataset.addSeries(seriesAverage);
        JFreeChart chart = ChartFactory.createXYLineChart("Evolution", // Title
                "iteration/generation", // x-axis Label
                "fitness", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 250));

        image = new BufferedImage(PANEL_SIZE, PANEL_SIZE, BufferedImage.TYPE_INT_RGB);
        scrollablePanel = new JScrollPane(simulationPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        simulationPanel.setPreferredSize(new Dimension(PANEL_SIZE*2, PANEL_SIZE));
        //North Panel
        simulationPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        scrollablePanel.setPreferredSize(new Dimension(PANEL_SIZE*2, PANEL_SIZE));

        scrollablePanel.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Graphics2D g = (Graphics2D) simulationPanel.getGraphics();
                g.drawImage(image, 0, 0, null);
            }
        });


        //North Panel
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(panelNorthLeft, java.awt.BorderLayout.WEST);
        northPanel.add(chartPanel, java.awt.BorderLayout.CENTER);
        northPanel.add(scrollablePanel, BorderLayout.EAST);

        //Center panel
        problemPanel = new PanelTextArea("Problem data: ", 20, 40);
        bestIndividualPanel = new PanelTextArea("Best solution: ", 20, 40);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(problemPanel, java.awt.BorderLayout.WEST);
        centerPanel.add(bestIndividualPanel, java.awt.BorderLayout.CENTER);

        //South Panel
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        southPanel.add(buttonExperiments);
        buttonExperiments.addActionListener(new ButtonExperiments_actionAdapter(this));
        southPanel.add(buttonRunExperiments);
        buttonRunExperiments.setEnabled(false);
        buttonRunExperiments.addActionListener(new ButtonRunExperiments_actionAdapter(this));
        southPanel.add(new JLabel("Status: "));
        southPanel.add(textFieldExperimentsStatus);
        lblRunningTime = new JLabel("Running time: ");
        southPanel.add(lblRunningTime);
        textFieldExperimentsStatus.setEditable(false);

        //Global structure
        JPanel globalPanel = new JPanel(new BorderLayout());
        globalPanel.add(northPanel, java.awt.BorderLayout.NORTH);
        globalPanel.add(centerPanel, java.awt.BorderLayout.CENTER);
        globalPanel.add(southPanel, java.awt.BorderLayout.SOUTH);
        this.getContentPane().add(globalPanel);

        pack();
    }

    public void buttonDataSet_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(new java.io.File("."));
        int returnVal = fc.showOpenDialog(this);

        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File dataSet = fc.getSelectedFile();
                warehouse = StockingProblem.buildWarehouse(dataSet);
                problemPanel.textArea.setText(warehouse.toString());
                problemPanel.textArea.setCaretPosition(0);
                buttonRun.setEnabled(true);
            }
        } catch (IOException e1) {
            e1.printStackTrace(System.err);
        } catch (java.util.NoSuchElementException e2) {
            JOptionPane.showMessageDialog(this, "File format not valid", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void jButtonRun_actionPerformed(ActionEvent e) {

        try {
            if (warehouse == null) {
                JOptionPane.showMessageDialog(this, "You must first choose a problem", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bestIndividualPanel.textArea.setText("");
            seriesBestIndividual.clear();
            seriesAverage.clear();
            switch (panelParameters.getAlgorithm()) {
                case 0:
                    algorithm = new GeneticAlgorithm<StockingProblemIndividual, StockingProblem>(
                            Integer.parseInt(panelParameters.jTextFieldN.getText()),
                            Integer.parseInt(panelParameters.jTextFieldGenerations.getText()),
                            panelParameters.getSelectionMethod(),
                            panelParameters.getRecombinationMethod(),
                            panelParameters.getMutationMethod(),
                            new Random(Integer.parseInt(panelParameters.jTextFieldSeed.getText())));

                    System.out.println(algorithm);
                    break;

                case 1:
                    algorithm = new RandomAlgorithm<StockingProblemIndividual, StockingProblem>(
                            Integer.parseInt(panelParameters.jTextFieldGenerations.getText()),
                            new Random(Integer.parseInt(panelParameters.jTextFieldSeed.getText())));
                    break;

            }
            algorithm.addListener(this);

            manageButtons(false, false, true, false, false);

            long startTime = System.currentTimeMillis();

            worker = new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {
                    try {

                        algorithm.run(warehouse);

                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                    }
                    return null;
                }

                @Override
                public void done() {
                    manageButtons(true, true, false, true, experimentsFactory != null);

                    long endTime   = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    lblRunningTime.setText("Running time: "+ (totalTime/1000d) + " segundos");

                    textFieldExperimentsStatus.setText("Finished");
                }

            };

            Graphics2D g = (Graphics2D) simulationPanel.getGraphics();
            g.drawImage(image, 0, 0, null);
            simulationPanel.repaint();

            worker.execute();

        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(this, "Wrong parameters!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void iterationEnded(AlgorithmEvent e) {
        Algorithm<StockingProblemIndividual, StockingProblem> source = e.getSource();
        bestIndividualPanel.textArea.setText(source.getGlobalBest().toString());
        seriesBestIndividual.add(source.getIteration(), source.getGlobalBest().getFitness());
        seriesAverage.add(source.getIteration(), ((Algorithm)source).getAverageFitness());
        environmentUpdated(source);
        if (worker.isCancelled()) {
            e.setStopped(true);
        }


    }

    @Override
    public void runEnded(AlgorithmEvent e) {
    }

    public void environmentUpdated(Algorithm<StockingProblemIndividual, StockingProblem> source) {
        Color colors[] = {new Color(240,232,205), new Color(219,213,185), new Color(192,186,153), new Color(254,235,201), new Color(253,202,162),new Color(252,169,133), new Color(255,255,172),
                new Color(255,250,129), new Color(255,237,81), new Color(224,243,176), new Color(191,228,118), new Color(133,202,93), new Color(207,236,207), new Color(182,225,174),
                new Color(145, 210, 144), new Color(179,226,221), new Color(134,207,190), new Color(72,181,163), new Color(204,236,239), new Color(154,206,223), new Color(111,183,214),
                new Color(191,213,232), new Color(148,168,208), new Color(117,137,191), new Color(221,212,232), new Color(193,179,215), new Color(165,137,193), new Color(253,222,238),
                new Color(251,182,209), new Color(249,140,182)};
        image = new BufferedImage((15*warehouse.getMaxMaterialWidth())+15, PANEL_SIZE, BufferedImage.TYPE_INT_RGB);
        simulationPanel.setPreferredSize(new Dimension((15*warehouse.getMaxMaterialWidth())+15, PANEL_SIZE));


        Graphics2D g = (Graphics2D) image.getGraphics();
        g.clearRect(0, 0, (15*warehouse.getMaxMaterialWidth())+15, PANEL_SIZE);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (15*warehouse.getMaxMaterialWidth())+15, PANEL_SIZE);

        if (warehouse != null) {

            g.setColor(Color.BLACK);

            /*for(int x = 0; x < warehouse.getMaxMaterialWidth(); x++){
                for (int y = 0; y < warehouse.getMaterialHeight()+1; y++){
                    g.fillOval((int) x*15, (int) y*15, 4, 4);
                }
            }*/
            g.drawLine(2,2,2,(warehouse.getMaterialHeight()*15)+2);
            g.drawLine((warehouse.getMaxMaterialWidth()*15)+2,2,(warehouse.getMaxMaterialWidth()*15)+2,(warehouse.getMaterialHeight()*15)+2);
            g.drawLine(2,2,(warehouse.getMaxMaterialWidth()*15)+2,2);
            g.drawLine(2,(warehouse.getMaterialHeight()*15)+2,(warehouse.getMaxMaterialWidth()*15)+2,(warehouse.getMaterialHeight()*15)+2);
            char[][] materialCut = source.getGlobalBest().getMaterialCut();

            for (int x = 0; x < materialCut[0].length; x++) {
                for (int y = 0; y < materialCut.length; y++) {
                    if(materialCut[y][x] != 0) {
                        g.setColor(colors[materialCut[y][x]%colors.length]);
                        //g.drawLine((int) (x * 15) + 2, (int) (y * 15) + 17, (x * 15) + 17, (int) (y * 15) + 2);
                        //g.drawLine((int) (x * 15) + 2, (int) (y * 15) + 2, (x * 15) + 17, (int) (y * 15) + 17);
                        g.fillRect((int) (x*15)+4, (int) (y*15)+4, 13, 13);
                        g.setColor(Color.BLACK);
                        g.drawString(String.valueOf(materialCut[y][x]),(x * 15)+5,(y * 15)+15);
                    }

                    g.setColor(Color.BLACK);
                    if(x == 0 && materialCut[y][x] != 0){
                        g.drawLine((int) 2, (int) (y*15)+2, 2, (int) (y*15)+17);
                    }
                    if(y == 0 && materialCut[y][x] != 0){
                        g.drawLine((int) (x*15)+2, (int) 2, (x*15)+17, (int) 2);
                    }
                    if(y == warehouse.getMaterialHeight()-1 && materialCut[y][x] != 0){
                        g.drawLine((int) (x*15)+2, (int) (warehouse.getMaterialHeight()*15)+2, (x*15)+17, (int) (warehouse.getMaterialHeight()*15)+2);
                    }



                    //corte vertical
                    if(x < materialCut[0].length-1)
                        if(materialCut[y][x] != materialCut[y][x+1]){
                            g.drawLine((int) (x*15)+17, (int) (y*15)+2, (x*15)+17, (int) (y*15)+17);
                        }

                    //corte horizontal
                    if(y < materialCut.length-1) {
                        if (materialCut[y][x] != materialCut[y+1][x]) {
                            g.drawLine((int) (x * 15) + 2, (int) (y * 15) + 17, (x * 15) + 17, (int) (y * 15) + 17);
                        }
                    }



                }
            }
            g = (Graphics2D) simulationPanel.getGraphics();
            g.drawImage(image, 0, 0, null);

        }
    }


    public void jButtonStop_actionPerformed(ActionEvent e) {
        worker.cancel(true);
    }

    public void buttonExperiments_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(new java.io.File("."));
        int returnVal = fc.showOpenDialog(this);

        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                experimentsFactory = new StockingProblemExperimentsFactory(fc.getSelectedFile());
                manageButtons(true, warehouse != null, false, true, true);
            }
        } catch (IOException e1) {
            e1.printStackTrace(System.err);
        } catch (java.util.NoSuchElementException e2) {
            JOptionPane.showMessageDialog(this, "File format not valid", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buttonRunExperiments_actionPerformed(ActionEvent e) {

        manageButtons(false, false, false, false, false);
        textFieldExperimentsStatus.setText("Running");


        worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                try {
                    while (experimentsFactory.hasMoreExperiments()) {
                        try {

                            Experiment experiment = experimentsFactory.nextExperiment();
                            experiment.run();

                        } catch (IOException e1) {
                            e1.printStackTrace(System.err);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                return null;
            }

            @Override
            public void done() {
                manageButtons(true, warehouse != null, false, true, false);

                textFieldExperimentsStatus.setText("Finished");
            }
        };
        worker.execute();
    }

    @Override
    public void experimentEnded(ExperimentEvent e) {
    }

    private void manageButtons(
            boolean dataSet,
            boolean run,
            boolean stopRun,
            boolean experiments,
            boolean runExperiments) {

        buttonDataSet.setEnabled(dataSet);
        buttonRun.setEnabled(run);
        buttonStop.setEnabled(stopRun);
        buttonExperiments.setEnabled(experiments);
        buttonRunExperiments.setEnabled(runExperiments);
    }
}

class ButtonDataSet_actionAdapter implements ActionListener {

    final private MainFrame adaptee;

    ButtonDataSet_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonDataSet_actionPerformed(e);
    }
}

class ButtonRun_actionAdapter implements ActionListener {

    final private MainFrame adaptee;

    ButtonRun_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonRun_actionPerformed(e);
    }
}

class ButtonStop_actionAdapter implements ActionListener {

    final private MainFrame adaptee;

    ButtonStop_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonStop_actionPerformed(e);
    }
}

class ButtonExperiments_actionAdapter implements ActionListener {

    final private MainFrame adaptee;

    ButtonExperiments_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonExperiments_actionPerformed(e);
    }
}

class ButtonRunExperiments_actionAdapter implements ActionListener {

    final private MainFrame adaptee;

    ButtonRunExperiments_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonRunExperiments_actionPerformed(e);
    }
}

class PanelTextArea extends JPanel {

    JTextArea textArea;

    public PanelTextArea(String title, int rows, int columns) {
        textArea = new JTextArea(rows, columns);
        setLayout(new BorderLayout());
        add(new JLabel(title), java.awt.BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        add(scrollPane);
    }
}

class PanelAtributesValue extends JPanel {

    protected String title;
    protected List<JLabel> labels = new ArrayList<>();
    protected List<JComponent> valueComponents = new ArrayList<>();

    public PanelAtributesValue() {
    }

    protected void configure() {

        //for(JComponent textField : textFields)
        //textField.setHorizontalAlignment(SwingConstants.RIGHT);

        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        //addLabelTextRows

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHEAST;
        Iterator<JLabel> itLabels = labels.iterator();
        Iterator<JComponent> itTextFields = valueComponents.iterator();

        while (itLabels.hasNext()) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            add(itLabels.next(), c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            add(itTextFields.next(), c);
        }
    }
}

class PanelParameters extends PanelAtributesValue {

    public static final int TEXT_FIELD_LENGHT = 7;
    public static final String SEED = "1";
    public static final String POPULATION_SIZE = "100";
    public static final String GENERATIONS = "100";
    public static final String TOURNAMENT_SIZE = "2";
    public static final String PROB_RECOMBINATION = "0.7";
    public static final String PROB_MUTATION = "0.1";
    JTextField jTextFieldSeed = new JTextField(SEED, TEXT_FIELD_LENGHT);
    JTextField jTextFieldN = new JTextField(POPULATION_SIZE, TEXT_FIELD_LENGHT);
    JTextField jTextFieldGenerations = new JTextField(GENERATIONS, TEXT_FIELD_LENGHT);
    String[] selectionMethods = {"Tournament", "Roulette wheel"};
    JComboBox jComboBoxSelectionMethods = new JComboBox(selectionMethods);
    JTextField jTextFieldTournamentSize = new JTextField(TOURNAMENT_SIZE, TEXT_FIELD_LENGHT);
    String[] recombinationMethods = {"PMX", "OX", "CX2"};
    JComboBox jComboBoxRecombinationMethods = new JComboBox(recombinationMethods);
    JTextField jTextFieldProbRecombination = new JTextField(PROB_RECOMBINATION, TEXT_FIELD_LENGHT);
    String[] mutationMethods = {"Insert", "Exchange", "Displacement", "Simple Inversion", "Invertion Simple", "Inversion Displacement"};
    JComboBox jComboBoxMutationMethods = new JComboBox(mutationMethods);
    JTextField jTextFieldProbMutation = new JTextField(PROB_MUTATION, TEXT_FIELD_LENGHT);
    String[] algorithms = {"GA", "Random"};
    JComboBox jComboBoxAlgorithms = new JComboBox(algorithms);

    public PanelParameters() {
        title = "Genetic algorithm parameters";

        labels.add(new JLabel("Seed: "));
        valueComponents.add(jTextFieldSeed);
        jTextFieldSeed.addKeyListener(new IntegerTextField_KeyAdapter(null));

        labels.add(new JLabel("Population size: "));
        valueComponents.add(jTextFieldN);
        jTextFieldN.addKeyListener(new IntegerTextField_KeyAdapter(null));

        labels.add(new JLabel("# of gen/iterations: "));
        valueComponents.add(jTextFieldGenerations);
        jTextFieldGenerations.addKeyListener(new IntegerTextField_KeyAdapter(null));

        labels.add(new JLabel("Selection method: "));
        valueComponents.add(jComboBoxSelectionMethods);
        jComboBoxSelectionMethods.addActionListener(new JComboBoxSelectionMethods_ActionAdapter(this));

        labels.add(new JLabel("Tournament size: "));
        valueComponents.add(jTextFieldTournamentSize);
        jTextFieldTournamentSize.addKeyListener(new IntegerTextField_KeyAdapter(null));

        labels.add(new JLabel("Recombination method: "));
        valueComponents.add(jComboBoxRecombinationMethods);

        labels.add(new JLabel("Recombination prob.: "));
        valueComponents.add(jTextFieldProbRecombination);

        labels.add(new JLabel("Mutation method: "));
        valueComponents.add(jComboBoxMutationMethods);

        labels.add(new JLabel("Mutation prob.: "));
        valueComponents.add(jTextFieldProbMutation);

        labels.add(new JLabel("Algorithms: "));
        valueComponents.add(jComboBoxAlgorithms);


        configure();
    }

    public void actionPerformedSelectionMethods(ActionEvent e) {
        jTextFieldTournamentSize.setEnabled(jComboBoxSelectionMethods.getSelectedIndex() == 0);
    }

    public SelectionMethod<StockingProblemIndividual, StockingProblem> getSelectionMethod() {
        switch (jComboBoxSelectionMethods.getSelectedIndex()) {
            case 0:
                return new Tournament<>(
                        Integer.parseInt(jTextFieldN.getText()),
                        Integer.parseInt(jTextFieldTournamentSize.getText()));
            case 1:
                return new RouletteWheel<>(
                        Integer.parseInt(jTextFieldN.getText()));
        }
        return null;
    }

    public int getAlgorithm() {
        return jComboBoxAlgorithms.getSelectedIndex();
    }

    public Recombination<StockingProblemIndividual, StockingProblem> getRecombinationMethod() {

        double recombinationProb = Double.parseDouble(jTextFieldProbRecombination.getText());

        switch (jComboBoxRecombinationMethods.getSelectedIndex()) {
            case 0:
                return new RecombinationPartialMapped<>(recombinationProb);
            case 1:
                return new RecombinationOrder<>(recombinationProb);
            case 2:
                return new RecombinationModifiedCycle<>(recombinationProb);
        }
        return null;
    }


    public Mutation<StockingProblemIndividual, StockingProblem> getMutationMethod() {

        double mutationProb = Double.parseDouble(jTextFieldProbMutation.getText());

        switch (jComboBoxMutationMethods.getSelectedIndex()) {
            case 0:
                return new MutationInsert<>(mutationProb);
            case 1:
                return new MutationExchange<>(mutationProb);
            case 2:
                return new MutationDisplacement<>(mutationProb);
            case 3:
                return new MutationSimpleInversion<>(mutationProb);
            case 4:
                return new MutationInsertionSimple<>(mutationProb);
            case 5:
                return new MutationInversionDisplacement<>(mutationProb);
        }
        return null;
    }
}

class JComboBoxSelectionMethods_ActionAdapter implements ActionListener {

    final private PanelParameters adaptee;

    JComboBoxSelectionMethods_ActionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.actionPerformedSelectionMethods(e);
    }
}


class IntegerTextField_KeyAdapter implements KeyListener {

    final private MainFrame adaptee;

    IntegerTextField_KeyAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            e.consume();
        }
    }
}
