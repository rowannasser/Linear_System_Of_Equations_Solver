package com.example.phase2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicArrowButton;

public class GUI extends JFrame implements ActionListener {

//    Window window = new Window();

    private final JPanel linearContentPane;
    private  JPanel nonLinearContentPane;
    private  JPanel defaultContentPane;
    private final JPanel cards = new JPanel(new CardLayout());

    private final CardLayout cl;

    private final BasicArrowButton backButton;
    private final JButton solveLinearButton;
    private final JButton deleteButton;
    private final JButton editButton;
    private final JButton addButton;
    private final JButton resetButton;
    private final JButton drawButton;
    private final JButton solveNonLinearButton;

    private final JTextField equationField;
    private final JTextField precisionField;
    private JTextField initGuess;
    private JTextField iterNum;
    private JTextField error;

    private final JLabel solutionLabel;
    private JLabel LUFormat;
    private JLabel initGuessLabel;
    private JLabel iterNumLabel;
    private JLabel errorLabel;
    private final JLabel lSolutionLabel;
    private final JLabel runtimeLabel;
    private final JLabel methodsLabel;
    private final JLabel precisionLabel;
    private final JLabel optionalLabel;
    private final JLabel optionalLabel1;
    private final JLabel optionalLabel2;
    private final JLabel sigLabel;
    private final JLabel equationLabel;
    private final JLabel listLabel;
    private final JLabel epsLabel;

    private JComboBox LUOptions;
    private final JComboBox linearMethods;
    private final JComboBox nonLinearMethods;
    private final JComboBox system;

    private final JList equations;
    private final JList solution;
    private final JList lSolution;
    private final JList runtime;

    private final JScrollPane solveScrollPane;
    private final JScrollPane runtimeScrollPane;
    private final JScrollPane lSolutionScrollPane;
    private final JScrollPane equationScrollPane;

    private LinkedList eq = new LinkedList();


    public GUI() {

        //creat three panels
        linearContentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = getWidth();
                int y = getHeight();
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(new Point(0, 0), new Color(61, 126, 170),
                        new Point(x, y), new Color(255, 228, 122), false));
                g2.fillRect(0, 0, x, y);
            }
        };

        defaultContentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = getWidth();
                int y = getHeight();
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(new Point(0, 0), new Color(61, 126, 170),
                        new Point(x, y), new Color(255, 228, 122), false));
                g2.fillRect(0, 0, x, y);
            }
        };

        nonLinearContentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = getWidth();
                int y = getHeight();
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(new Point(0, 0), new Color(61, 126, 170),
                        new Point(x, y), new Color(255, 228, 122), false));
                g2.fillRect(0, 0, x, y);
            }
        };

        defaultContentPane.setLayout(null);
        linearContentPane.setLayout(null);
        nonLinearContentPane.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(800, 550));
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //add panel
        cards.add(defaultContentPane,"defaultContentPane");
        cards.add(linearContentPane,"linearContentPane");
        cards.add(nonLinearContentPane,"nonLinearContentPane");
        setContentPane(cards);

//Default content pane
//////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to our System Solver app");
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(0, 8, 57));
        welcomeLabel.setBounds(120, 80, 800, 50);
        defaultContentPane.add(welcomeLabel);

        //system label
        JLabel systemLabel = new JLabel("What kind of system do you want to solve ?");
        systemLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        systemLabel.setForeground(new Color(247, 56, 89));
        systemLabel.setBounds(200, 200, 800, 50);
        defaultContentPane.add(systemLabel);

        // System combo box
        system = new JComboBox();
        system.setModel(new DefaultComboBoxModel(new String[]{"Linear System", "Non-Linear System"}));
        system.setBackground(new Color(234, 236, 198));
        system.setFont(new Font("Tahoma", Font.BOLD, 20));
        // methods.setForeground(new Color(234, 236, 198));
        system.setBounds(290, 300, 230, 30);
        defaultContentPane.add(system);
        cl = (CardLayout)(cards.getLayout());

        //On choosing method
        system.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMethod = (String) system.getSelectedItem();
                //on choosing LinearSystem
                if (selectedMethod.equals("Linear System")) {
                    resetLinear();
                    linearContentPane.add(backButton);
                    linearContentPane.add(methodsLabel);
                    linearContentPane.add(solveLinearButton);
                    linearContentPane.add(precisionField);
                    linearContentPane.add(precisionLabel);
                    linearContentPane.add(optionalLabel);
                    linearContentPane.add(sigLabel);
                    linearContentPane.add(equationField);
                    linearContentPane.add(equationLabel);
                    linearContentPane.add(addButton);
                    linearContentPane.add(equations);
                    linearContentPane.add(deleteButton);
                    linearContentPane.add(listLabel);
                    linearContentPane.add(equationScrollPane);
                    linearContentPane.add(editButton);
                    resetButton.setActionCommand("resetLinear");
                    linearContentPane.add(resetButton);
                    linearContentPane.add(solution);
                    linearContentPane.add(solveScrollPane);
                    linearContentPane.add(solutionLabel);
                    linearContentPane.add(lSolution);
                    linearContentPane.add(lSolutionScrollPane);
                    linearContentPane.add(lSolutionLabel);
                    linearContentPane.add(runtimeLabel);
                    linearContentPane.add(runtime);
                    linearContentPane.add(runtimeScrollPane);
                    linearContentPane.add(iterNumLabel);
                    linearContentPane.add(iterNum);
                    linearContentPane.add(error);
                    linearContentPane.add(errorLabel);

                    cl.show(cards, "linearContentPane");
                }
                //on choosing NonLinearSystem
                else if (selectedMethod.equals("Non-Linear System")) {
                    resetLinear();
                    nonLinearContentPane.add(backButton);
                    nonLinearContentPane.add(methodsLabel);
                    nonLinearContentPane.add(solveNonLinearButton);
                    nonLinearContentPane.add(precisionField);
                    nonLinearContentPane.add(precisionLabel);
                    nonLinearContentPane.add(optionalLabel);
                    nonLinearContentPane.add(sigLabel);
                    nonLinearContentPane.add(equationField);
                    nonLinearContentPane.add(equationLabel);
                    resetButton.setActionCommand("resetNonLinear");
                    nonLinearContentPane.add(resetButton);
                    nonLinearContentPane.add(solution);
                    nonLinearContentPane.add(solveScrollPane);
                    nonLinearContentPane.add(solutionLabel);
                    nonLinearContentPane.add(lSolution);
                    nonLinearContentPane.add(lSolutionScrollPane);
                    nonLinearContentPane.add(lSolutionLabel);
                    nonLinearContentPane.add(drawButton);
                    nonLinearContentPane.add(runtimeLabel);
                    nonLinearContentPane.add(runtime);
                    nonLinearContentPane.add(runtimeScrollPane);
                    nonLinearContentPane.add(epsLabel);
                    nonLinearContentPane.add(optionalLabel);
                    nonLinearContentPane.add(iterNumLabel);
                    nonLinearContentPane.add(iterNum);
                    nonLinearContentPane.add(error);
                    nonLinearContentPane.add(optionalLabel1);
                    nonLinearContentPane.add(optionalLabel2);
                    iterNumLabel.setVisible(true);
                    iterNum.setVisible(true);
                    error.setVisible(true);
                    epsLabel.setVisible(true);

                    cl.show(cards,"nonLinearContentPane");
                }
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////

        //back button
        backButton = new BasicArrowButton(BasicArrowButton.WEST);
        backButton.setBounds(10, 5, 20, 20);
        backButton.setBackground(new Color(247, 56, 89));
        backButton.setForeground(new Color(0, 8, 57));
        backButton.addActionListener(this);
        backButton.setActionCommand("back");

        //Linear Methods combo box
        linearMethods = new JComboBox();
        linearMethods.setModel(new DefaultComboBoxModel(new String[]{"Gauss Elimination", "Gauss-Jordan", "LU Decomposition", "Gauss-Seidil", "Jacobi-Iteration"}));
        linearMethods.setBackground(new Color(234, 236, 198));
        // methods.setForeground(new Color(234, 236, 198));
        linearMethods.setBounds(60, 30, 162, 21);
        linearContentPane.add(linearMethods);

        //nonLinear Methods combo box
        nonLinearMethods = new JComboBox();
        nonLinearMethods.setModel(new DefaultComboBoxModel(new String[]{"Bisection", "False-Position", "Fixed point", "Newton-Raphson", "Secant Method"}));
        nonLinearMethods.setBackground(new Color(234, 236, 198));
        // methods.setForeground(new Color(234, 236, 198));
        nonLinearMethods.setBounds(60, 30, 162, 21);
        nonLinearContentPane.add(nonLinearMethods);

        //methods label
        methodsLabel = new JLabel("Method");
        methodsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        methodsLabel.setForeground(new Color(0, 8, 57));
        methodsLabel.setBounds(10, 15, 300, 50);

        //On choosing method
        linearMethods.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formatParam();
                String selectedMethod = (String) linearMethods.getSelectedItem();
                //on choosing LinearSystem.LU Decomposition another list will appear to choose the format
                if (selectedMethod.equals("LU Decomposition")) {
                    LUOptions = new JComboBox();
                    LUOptions.setModel(new DefaultComboBoxModel(new String[]{"Downlittle Form", "Crout Form"}));
                    LUOptions.setBackground(new Color(234, 236, 198));
                    // LUOptions.setForeground(new Color(234, 236, 198));
                    LUOptions.setBounds(295, 30, 130, 21);

                    LUFormat = new JLabel("LU format ");
                    LUFormat.setFont(new Font("Tahoma", Font.BOLD, 12));
                    LUFormat.setBounds(230, 15, 300, 50);
                    LUFormat.setForeground(new Color(0, 8, 57));
                    linearContentPane.add(LUFormat);
                    linearContentPane.add(LUOptions);
                    linearContentPane.updateUI();
                }

                //on choosing Gauss Seidil or jacobi iteration initial guess text field
                // and number of iterations text field
                // and absoluter relative error text field appear

                else if (selectedMethod.equals("Gauss-Seidil") || selectedMethod.equals("Jacobi-Iteration")) {
                    //initial guess text field
                    initGuess = new JTextField ();
                    initGuess.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(56, 66, 89), new Color(56, 66, 89)));
                    initGuess.setBounds(325, 30, 130, 21);
                    initGuess.setColumns(1);
                    initGuess.setBackground(new Color(234, 236, 198));

                    //initial guess label
                    initGuessLabel = new JLabel("Initial guess ");
                    initGuessLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
                    initGuessLabel.setForeground(new Color(0, 8, 57));
                    initGuessLabel.setBounds(242, 15, 300, 50);

                    iterNum.setVisible(true);
                    iterNumLabel.setVisible(true);
                    error.setVisible(true);
                    errorLabel.setVisible(true);

                    linearContentPane.add(initGuessLabel);
                    linearContentPane.add(initGuess);
                    linearContentPane.updateUI();

                }


            }
        });

        ///SolveLinearButton
        solveLinearButton = new JButton("Solve");
        solveLinearButton.setBounds(112, 450, 104, 30);
        solveLinearButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        solveLinearButton.setBackground(new Color(56, 66, 89));
        solveLinearButton.setForeground(new Color(234, 236, 198));

        //set action listeners for buttons
        solveLinearButton.addActionListener(this);
        // define a custom short action command for the button
        solveLinearButton.setActionCommand("solveLinear");

        ///SolveNonLinearButton
        solveNonLinearButton = new JButton("Solve");
        solveNonLinearButton.setBounds(330, 150, 70, 30);
        solveNonLinearButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        solveNonLinearButton.setBackground(new Color(56, 66, 89));
        solveNonLinearButton.setForeground(new Color(234, 236, 198));

        //set action listeners for buttons
        solveNonLinearButton.addActionListener(this);
        // define a custom short action command for the button
        solveNonLinearButton.setActionCommand("solveNonLinear");

        // precision text field
        precisionField = new JTextField();
        precisionField.setBounds(75, 70, 30, 30);
        precisionField.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        precisionField.setBackground(new Color(234, 236, 198));
        precisionField.setColumns(1);


        //precision label
        precisionLabel = new JLabel("Precision");
        precisionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        precisionLabel.setBounds(10, 60, 300, 50);
        precisionLabel.setForeground(new Color(0, 8, 57));


        //precision optional label
        optionalLabel = new JLabel("(optional)");
        optionalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        optionalLabel.setForeground(new Color(247, 56, 89));
        optionalLabel.setBounds(264, 60, 300, 50);
        // optional label
        optionalLabel1 = new JLabel("(optional)");
        optionalLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
        optionalLabel1.setForeground(new Color(247, 56, 89));
        optionalLabel1.setBounds(650, 15 ,300, 50);
        // optional label
        optionalLabel2 = new JLabel("(optional)");
        optionalLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
        optionalLabel2.setForeground(new Color(247, 56, 89));
        optionalLabel2.setBounds(650, 60, 300, 50);


        //number of significant figures
        sigLabel = new JLabel("Number of significant figures");
        sigLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        sigLabel.setForeground(new Color(0, 8, 57));
        sigLabel.setBounds(115, 60, 300, 50);


        // Equation text field
        equationField = new JTextField();
        equationField.setBounds(70, 150, 250, 30);
        equationField.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        equationField.setBackground(new Color(234, 236, 198));
        equationField.setColumns(1);


        //Equation label
        equationLabel = new JLabel("Equation");
        equationLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        equationLabel.setBounds(10, 140, 300, 50);
        equationLabel.setForeground(new Color(0, 8, 57));

        //addButton
        addButton = new JButton("Add");
        addButton.setBounds(330, 150, 70, 30);
        addButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        addButton.setBackground(new Color(56, 66, 89));
        addButton.setForeground(new Color(234, 236, 198));        //set action listeners for buttons
        addButton.addActionListener(this);
        addButton.setActionCommand("add");


        //list of entered equations
        equations = new JList();
        equations.setBounds(70, 200, 200, 200);
        equations.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        equations.setForeground(new Color(56, 66, 89));
        equations.setBackground(new Color(234, 236, 198));
        equations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //to display the list of the entered equations
        equationScrollPane = new JScrollPane(equations);
        equationScrollPane.setBounds(70, 200, 200, 200);
        equationScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        equationScrollPane.setForeground(new Color(56, 66, 89));
        equationScrollPane.setBackground(new Color(234, 236, 198));


        //list of equations' label
        listLabel = new JLabel("List of equations");
        listLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        listLabel.setBounds(70, 169, 300, 50);
        equationLabel.setForeground(new Color(0, 8, 57));

        ///deleteButton
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(90, 410, 70, 30);
        deleteButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        deleteButton.setBackground(new Color(56, 66, 89));
        deleteButton.setForeground(new Color(234, 236, 198));
        //set action listeners for buttons
        deleteButton.addActionListener(this);
        deleteButton.setVisible(false);
        deleteButton.setActionCommand("delete");

        ///editButton
        editButton = new JButton("Edit");
        editButton.setBounds(180, 410, 70, 30);
        editButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        editButton.setBackground(new Color(56, 66, 89));
        editButton.setForeground(new Color(234, 236, 198));
        editButton.addActionListener(this);
        editButton.setVisible(false);
        editButton.setActionCommand("edit");


        ///editButton
        drawButton = new JButton("Draw");
        drawButton.setBounds(700, 400, 70, 30);
        drawButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        drawButton.setBackground(new Color(56, 66, 89));
        drawButton.setForeground(new Color(234, 236, 198));
        drawButton.setVisible(false);
        drawButton.addActionListener(this);
        drawButton.setActionCommand("draw");

        ///resetButton
        resetButton = new JButton("Reset");
        resetButton.setBounds(713, 20, 70, 30);
        resetButton.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(234, 236, 198), new Color(247, 56, 89)));
        resetButton.setBackground(new Color(56, 66, 89));
        resetButton.setForeground(new Color(234, 236, 198));
        resetButton.addActionListener(this);

        //solution
        solution = new JList();
        solution.setBounds(420, 120, 300, 270);
        solution.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        solution.setSelectionModel(new NoSelectionModel());
        solution.setForeground(new Color(56, 66, 89));
        solution.setBackground(new Color(234, 236, 198));
        solution.setVisible(false);

        //to display solution
        solveScrollPane = new JScrollPane(solution);
        solveScrollPane.setBounds(420, 120, 300, 270);
        solveScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        solveScrollPane.setForeground(new Color(56, 66, 89));
        solveScrollPane.setBackground(new Color(234, 236, 198));
        solveScrollPane.setVisible(false);

        //solution label
        solutionLabel = new JLabel("Solution steps");
        solutionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        solutionLabel.setBounds(530, 83, 300, 50);
        solutionLabel.setForeground(new Color(0, 8, 57));
        solutionLabel.setVisible(false);


        //Last solution
        lSolution = new JList();
        lSolution.setBounds(420, 410, 300, 70);
        lSolution.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        lSolution.setSelectionModel(new NoSelectionModel());
        lSolution.setForeground(new Color(56, 66, 89));
        lSolution.setBackground(new Color(234, 236, 198));
//        lSolution.setVisible(false);


        //to display last solution
        lSolutionScrollPane = new JScrollPane(lSolution);
        lSolutionScrollPane.setBounds(420, 410, 300, 70);
        lSolutionScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        lSolutionScrollPane.setForeground(new Color(56, 66, 89));
        lSolutionScrollPane.setBackground(new Color(234, 236, 198));
        lSolutionScrollPane.setVisible(false);

        //last solution label
        lSolutionLabel = new JLabel("Final answer");
        lSolutionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lSolutionLabel.setBounds(530, 373, 300, 50);
        lSolutionLabel.setForeground(new Color(0, 8, 57));
        lSolutionLabel.setVisible(false);

        //runtime label
        runtimeLabel = new JLabel("Runtime");
        runtimeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        runtimeLabel.setBounds(365, 470, 300, 50);
        runtimeLabel.setForeground(new Color(0, 8, 57));
        runtimeLabel.setVisible(false);

        //runtime
        runtime = new JList();
        runtime.setBounds(420, 485, 300, 25);
        runtime.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        runtime.setSelectionModel(new NoSelectionModel());
        runtime.setForeground(new Color(234, 236, 198));
        runtime.setBackground(new Color(56, 66, 89));
        runtime.setVisible(false);


        //to display last solution
        runtimeScrollPane = new JScrollPane(runtime);
        runtimeScrollPane.setBounds(420, 485, 300, 25);
        runtimeScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        runtimeScrollPane.setForeground(new Color(234, 236, 198));
        runtimeScrollPane.setBackground(new Color(56, 66, 89));
        runtimeScrollPane.setVisible(false);

        //Number of iterations text field
        iterNum = new JTextField();
        iterNum.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(56, 66, 89), new Color(56, 66, 89)));
        iterNum.setBackground(new Color(234, 236, 198));
        iterNum.setBounds(617, 27, 30, 30);
        iterNum.setColumns(1);
        iterNum.setVisible(false);

        //Number of iterations label
        iterNumLabel = new JLabel("Number of iterations ");
        iterNumLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        iterNumLabel.setForeground(new Color(0, 8, 57));
        iterNumLabel.setBounds(475, 15, 300, 50);
        iterNumLabel.setVisible(false);

        //Absolute relative error text field
        error = new JTextField();
        error.setBorder(new BevelBorder(BevelBorder.LOWERED,new Color(56, 66, 89),new Color(56, 66, 89)));
        error.setBackground(new Color(234, 236, 198));
        error.setBounds(617, 70, 30, 30);
        error.setColumns(1);
        error.setVisible(false);

        //eps label
        epsLabel = new JLabel("epslon");
        epsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        epsLabel.setBounds(562, 60, 300, 50);
        epsLabel.setForeground(new Color(0, 8, 57));

        //Absolute relative error label
        errorLabel = new JLabel("Absolute relative error");
        errorLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        errorLabel.setForeground(new Color(0, 8, 57));
        errorLabel.setBounds(475, 60, 300, 50);
        errorLabel.setVisible(false);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch (action) {
            case "back":
                cl.show(cards, "defaultContentPane");
                break;
            case "solveLinear":
                if (entryCheck()) {
                    String[] param;
                    String equationsContent = "";
                    for (int i = 0; i < eq.size() - 1; i++) {
                        equationsContent += eq.get(i) + ",";
                    }
                    equationsContent += eq.get(eq.size() - 1);
                    if (linearMethods.getSelectedItem().equals("Gauss Elimination") || linearMethods.getSelectedItem().equals("Gauss-Jordan")) {
                        param = new String[1];
                        if (precisionField.getText().equals("")) {
                            param[0] = "null";
                        } else {
                            param[0] = precisionField.getText();
                        }
                    } else if (linearMethods.getSelectedItem().equals("Gauss-Seidil") || linearMethods.getSelectedItem().equals("Jacobi-Iteration")) {
                        param = new String[4];
                        param[0] = initGuess.getText();
                        param[1] = iterNum.getText();
                        param[2] = error.getText();
                        if (precisionField.getText().equals("")) {
                            param[3] = "null";
                        } else {
                            param[3] = precisionField.getText();
                        }
                    } else {
                        param = new String[2];
                        param[0] = (String) LUOptions.getSelectedItem();
                        if (precisionField.getText().equals("")) {
                            param[1] = "null";
                        } else {
                            param[1] = precisionField.getText();
                        }
                    }
                    backEnd back = new backEnd();
                    String[] solutions = back.solveLinear((String) linearMethods.getSelectedItem(), param, equationsContent);


                    if (!solutions[0].equals("null")) {
                        String[] finalSol = {solutions[0]};
                        lSolution.setListData(finalSol);
                        lSolutionScrollPane.setVisible(true);
                        lSolutionLabel.setVisible(true);
                        lSolution.setVisible(true);

                    }
                    if (!solutions[1].equals("null")) {
                        String stepSol = solutions[1];
                        String[] stepsArr = stepSol.split("&");
                        solution.setListData(stepsArr);
                        solveScrollPane.setVisible(true);
                        solutionLabel.setVisible(true);
                        solution.setVisible(true);
                    }
                    if (!solutions[2].equals("null")) {
                        String[] time = {solutions[2]};
                        runtime.setListData(time);
                        runtimeScrollPane.setVisible(true);
                        runtimeLabel.setVisible(true);
                        runtime.setVisible(true);

                    }

                    if (solutions[1].equals("null") && solutions[0].equals("null")){
                        resetLinear();
                    }
                    solveLinearButton.setEnabled(false);
                }
                break;
            case "solveNonLinear":
                if(!equationField.getText().equals("")){
                    String[] inputs = new String[4];
                    inputs[0] = equationField.getText();
                    if(!precisionField.getText().equals("")){
                        inputs[1] = precisionField.getText();
                    }
                    else {
                        inputs[1] = "null";
                    }
                    if(!iterNum.getText().equals("")){
                        inputs[2] = iterNum.getText();
                    }
                    else{
                        inputs[2] = "50";
                    }
                    if(!error.getText().equals("")){
                        inputs[3] = error.getText();
                    }
                    else {
                        inputs[3] = "0.00001";
                    }
                    backEnd back = new backEnd();
                    String[] solutions = new String[0];
                    try {
                        solutions = back.solveNonLinear(inputs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (!solutions[0].equals("null")) {
                        String[] finalSol = {solutions[0]};
                        lSolution.setListData(finalSol);
                        lSolutionScrollPane.setVisible(true);
                        lSolutionLabel.setVisible(true);
                        lSolution.setVisible(true);

                    }
                    if (!solutions[1].equals("null")) {
                        String stepSol = solutions[1];
                        String[] stepsArr = stepSol.split("&");
                        solution.setListData(stepsArr);
                        solveScrollPane.setVisible(true);
                        solutionLabel.setVisible(true);
                        solution.setVisible(true);
                    }
                    if (!solutions[2].equals("null")) {
                        String[] time = {solutions[2]};
                        runtime.setListData(time);
                        runtimeScrollPane.setVisible(true);
                        runtimeLabel.setVisible(true);
                        runtime.setVisible(true);
                    }
                    if (solutions[1].equals("null") && solutions[0].equals("null")){
                        resetLinear();
                    }
                    solveNonLinearButton.setEnabled(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Enter the equation!");
                }
                break;

            case "add":
                if (!(equationField.getText().equals(""))) {
                    eq.add(equationField.getText());
                    String[] content = new String[eq.size()];
                    for (int i = 0; i < eq.size(); i++) {
                        content[i] = (String) eq.get(i);
                    }
                    equations.setListData(content);
                    equations.setEnabled(true);
                    equationField.setText("");
                    editButton.setVisible(true);
                    deleteButton.setVisible(true);
                    linearContentPane.updateUI();
                }
                break;
            case "delete": {
                eq.remove(equations.getSelectedValue());
                String[] content = new String[eq.size()];
                for (int i = 0; i < eq.size(); i++) {
                    content[i] = (String) eq.get(i);
                }
                equations.setListData(content);
                if (eq.size() == 0) {
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                }
                linearContentPane.updateUI();
                break;
            }
            case "edit": {
                String s = (String) equations.getSelectedValue();
                eq.remove(equations.getSelectedValue());
                String[] content = new String[eq.size()];
                for (int i = 0; i < eq.size(); i++) {
                    content[i] = (String) eq.get(i);
                }
                equations.setListData(content);
                equationField.setText(s);
                if (eq.size() == 0) {
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                }
                linearContentPane.updateUI();
                break;
            }
            case "resetLinear":{
                resetLinear();
                break;
            }
            case "resetNonLinear":{
                resetNonLinear();
                break;
            }
            case "draw":{
//                initiateThread();
                break;
            }
        }
    }

    protected static void createAndShowGUI() {

        JFrame frame = new GUI();
        frame.setTitle("System Solver");
        frame.setBackground(new Color(56, 66, 89));
        frame.setForeground(new Color(56, 66, 89));
        frame.setFont(new Font("Tahoma", Font.BOLD, 20));
        frame.pack();

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void formatParam(){
        if (initGuess != null){
            initGuess.setText("");
            initGuess.setVisible(false);
            initGuessLabel.setVisible(false);
            iterNum.setText("");
            iterNum.setVisible(false);
            iterNumLabel.setVisible(false);
            error.setText("");
            error.setVisible(false);
            errorLabel.setVisible(false);
        }
        if (!(precisionField.getText().equals(""))){
            precisionField.setText("");
        }
        if (LUOptions != null){
            LUOptions.setVisible(false);
            LUFormat.setVisible(false);
        }
    }

    private boolean entryCheck(){
        if (eq.size() == 0){
            JOptionPane.showMessageDialog(null, "      Enter your equations");
            return false;
        }
        if ((linearMethods.getSelectedItem().equals("Gauss-Seidil")) || (linearMethods.getSelectedItem().equals("Jacobi-Iteration"))){
            if (initGuess.getText().equals("")){
                JOptionPane.showMessageDialog(null, "    Enter yor initial guess");
                return false;
            }
            if (iterNum.getText().equals("")){
                JOptionPane.showMessageDialog(null, "    Enter number of iterations");
                return false;
            }
            if (error.getText().equals("")){
                JOptionPane.showMessageDialog(null, " Enter the absolute relative error");
                return false;
            }
            String init = initGuess.getText();
            String er = error.getText();
            String iter = iterNum.getText();

            if (!isNumeric(er) || Double.parseDouble(String.valueOf(er)) < 0){
                JOptionPane.showMessageDialog(null, "Error must be positive number!");
                return false;
            }
            if (!(isInteger(iter,10)) || (Integer.parseInt(String.valueOf(iter)) < 0)){
                JOptionPane.showMessageDialog(null, "Number of iterations must be positive number!");
                return false;
            }

            String[] initArr = init.split("[,  ]");
            for (String s : initArr) {
                if (!isNumeric(s)) {
                    JOptionPane.showMessageDialog(null, "initial guess must be number! please enter in format: [x1,x2,x3,..]");
                    return false;
                }
            }
        }
        if (!(precisionField.getText().equals(""))){
            if (!isInteger(precisionField.getText(),10)  || !(Integer.parseInt(String.valueOf(precisionField.getText())) < 0)){
                JOptionPane.showMessageDialog(null, "Precision must be positive integer number!");
                return false;
            }
        }

        return true;
    }

    private void resetLinear(){
        //reset equation list
        if (eq.size() != 0 ){
            eq.clear();
            String[] temp = new String[0];
            equations.setListData(temp);
        }

        //reset solution field
        String[] temp = new String[0];
        if (lSolution.getSelectedValue() != null) {
            lSolution.setListData(temp);
        }
        if (solution.getSelectedValue() != null) {
            solution.setListData(temp);
        }

        lSolution.setVisible(false);
        lSolutionLabel.setVisible(false);
        lSolutionScrollPane.setVisible(false);
        solution.setVisible(false);
        solveScrollPane.setVisible(false);
        solutionLabel.setVisible(false);
        runtimeScrollPane.setVisible(false);
        iterNum.setVisible(false);
        iterNumLabel.setVisible(false);
        error.setVisible(false);
        errorLabel.setVisible(false);
        runtime.setVisible(false);
        runtimeLabel.setVisible(false);
        //reset parameter fields
        formatParam();
        linearMethods.setSelectedItem("Gauss Elimination");
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        solveLinearButton.setEnabled(true);
    }

    private void resetNonLinear(){
        //reset equation list
        if (!equationField.getText().equals("")){
            equationField.setText("");
        }
        if (!error.getText().equals("")){
            error.setText("");
        }
        if (!iterNum.getText().equals("")){
            iterNum.setText("");
        }

        //reset solution field
        String[] temp = new String[0];
        if (lSolution.getSelectedValue() != null) {
            lSolution.setListData(temp);
        }
        if (solution.getSelectedValue() != null) {
            solution.setListData(temp);
        }

        lSolution.setVisible(false);
        lSolutionLabel.setVisible(false);
        lSolutionScrollPane.setVisible(false);
        solution.setVisible(false);
        solveScrollPane.setVisible(false);
        solutionLabel.setVisible(false);
        runtimeScrollPane.setVisible(false);
        runtime.setVisible(false);
        runtimeLabel.setVisible(false);
        nonLinearMethods.setSelectedItem("Bisection");
        solveNonLinearButton.setEnabled(true);
    }

    private static class NoSelectionModel extends DefaultListSelectionModel {

        @Override
        public void setAnchorSelectionIndex(final int anchorIndex) {}

        @Override
        public void setLeadAnchorNotificationEnabled(final boolean flag) {}

        @Override
        public void setLeadSelectionIndex(final int leadIndex) {}

        @Override
        public void setSelectionInterval(final int index0, final int index1) { }
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static boolean isInteger(String s,int radix) {
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

}