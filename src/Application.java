import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Application implements ActionListener, ChangeListener  {
    JFrame frame;
    JPanel mainPanel, firstPanel, secondPanel, thirdPanel, fourthPanel,
            panel11, panel1, panel2, panel3, tmp1Panel, tmp2Panel;
    JLabel label1, label2, label3;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, helpMenu;
    JMenuItem loadItem, saveItem, exitItem;
    JCheckBox checkBox;
    JComboBox comboBox;
    JProgressBar progressBar;
    JScrollPane scroll;
    JTextField textField;
    JTextArea textArea;
    JSlider slider;
    JTabbedPane tabbedPane;
    JRadioButton option1, option2, option3;
    JButton button1, button2, button3;
    ButtonGroup group;
    ImageIcon hand;
    MyPanel panelR;
    GradientPaint gradient = draw();
    int counter = 1;

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public void run() {

        // division of the main panel into 4 panels
        frame = new JFrame();
        mainPanel = new JPanel();
        firstPanel = new JPanel();
        secondPanel = new JPanel();
        thirdPanel = new JPanel();
        fourthPanel = new JPanel();
        tmp1Panel = new JPanel();
        tmp2Panel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2,10,10));
        firstPanel.setLayout(new BorderLayout());
        secondPanel.setLayout(new BorderLayout());
        fourthPanel.setLayout(new BorderLayout());
        tmp1Panel.setLayout(new GridLayout(2,1,5,5));
        tmp2Panel.setLayout(new GridLayout(1,2,5,0));
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        firstPanel.setBorder(BorderFactory.createTitledBorder("Panel"));
        secondPanel.setBorder(BorderFactory.createTitledBorder("Gradient"));
        thirdPanel.setBorder(BorderFactory.createTitledBorder("Thermometer"));
        tmp1Panel.add(tmp2Panel);
        firstPanel.add(tmp1Panel);
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        mainPanel.add(fourthPanel);

        // menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        loadItem.setMnemonic(KeyEvent.VK_L);
        saveItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_E);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // checkBox
        checkBox = new JCheckBox();
        checkBox.setText("I'm not a robot.");
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("Consolas", Font.PLAIN,15));

        // comboBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        comboBox = new JComboBox(items);
        comboBox.addActionListener(this);
        tmp2Panel.add(comboBox);

        // radioButton
        option1 = new JRadioButton("option 1");
        option2 = new JRadioButton("option 2");
        option3 = new JRadioButton("option 3");
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        option1.setFocusable(false);
        option2.setFocusable(false);
        option3.setFocusable(false);
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);
        panel11 = new JPanel();
        panel11.add(option1);
        panel11.add(option2);
        panel11.add(option3);
        tmp2Panel.add(panel11);

        // progress
        progressBar = new JProgressBar(0,100);
        progressBar.setValue(0);
        progressBar.setBounds(0,0,420,50);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("MV Boli",Font.BOLD,25));
        progressBar.setPreferredSize(new Dimension(100,40));
        tmp1Panel.add(progressBar);

        // gradient
        button3 = new JButton("Color change");
        button3.setFocusable(false);
        button3.addActionListener(new ButtonResponse1());
        panelR = new MyPanel();
        secondPanel.add(BorderLayout.SOUTH, button3);
        secondPanel.add(BorderLayout.CENTER, panelR);

        // thermometer
        label2 = new JLabel();
        slider = new JSlider(-60,60,0);
        slider.setPreferredSize(new Dimension(300,185));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(15);
        slider.setPaintLabels(true);
        slider.setFont(new Font("MV Boli",Font.PLAIN,15));
        slider.setOrientation(SwingConstants.VERTICAL);
        label2.setFont(new Font("MV Boli",Font.PLAIN,20));
        label2.setText("°C = " + slider.getValue());
        slider.addChangeListener(this);
        thirdPanel.add(BorderLayout.WEST,slider);
        thirdPanel.add(BorderLayout.CENTER,label2);

        // division of panel 4 into 2 tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(50,50,250,250);
        fourthPanel.add(BorderLayout.CENTER,tabbedPane);
        panel1 = new JPanel();
        panel2 = new JPanel();
        tabbedPane.add("Page 1",panel1);
        tabbedPane.add("Page 2",panel2);

        // customizing the text field in tab 1
        panel3 = new JPanel();
        button1 = new JButton("Click me!");
        button1.setFocusable(false);
        button1.addActionListener(new ButtonResponse2());
        textArea = new JTextArea(10,20);
        textArea.setLineWrap(true);
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel3.add(scroll);
        panel1.add(BorderLayout.CENTER, panel3);
        panel1.add(BorderLayout.SOUTH, button1);

        // customizing the text field in tab 2
        hand = new ImageIcon("hi3.png");
        label3 = new JLabel();
        label3.setText("Enter nickname:");
        label3.setHorizontalTextPosition(JLabel.CENTER);
        label3.setVerticalTextPosition(JLabel.CENTER);
        label3.setFont(new Font("Consolas", Font.PLAIN,20));
        panel2.add(label3);
        button2 = new JButton("Confirm");
        button2.setFocusable(false);
        button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(150,40));
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Consolas", Font.PLAIN,20));
        textField.setForeground(Color.black);
        textField.setBackground(Color.white);
        textField.setCaretColor(Color.black);
        textField.setText("username");
        panel2.add(textField);
        panel2.add(button2);
        label1 = new JLabel();
        label1.setIcon(hand);
        label1.setVisible(false);
        panel2.add(label1);

        // customization of the main frame
        frame.setIconImage(new ImageIcon("icon.jpg").getImage());
        frame.setTitle("Application Demo");
        frame.setResizable(false);
        frame.add(BorderLayout.CENTER, mainPanel);
        frame.add(BorderLayout.NORTH, checkBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(615, 615);
        frame.setVisible(true);

        fill();
    }

    // changing the status of the progress bar in cell 1.
    public void fill() {

        int i = 0;
        while(i <= 100) {
            progressBar.setValue(i);
            try {Thread.sleep(250);}
            catch (InterruptedException ex) {ex.printStackTrace();}
            i += 1;
        }
        progressBar.setString("Finish!");
    }

    // gradient draw
    public GradientPaint draw() {
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        Color firstColor = new Color(red,green,blue);
        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);
        Color lastColor = new Color(red,green,blue);
        return gradient = new GradientPaint(70,70,firstColor,150,150, lastColor);
    }

    public void actionPerformed(ActionEvent e) {

        // menu
        if (e.getSource() == loadItem) {
            System.out.println("Loaded a file");
        }
        else if (e.getSource() == saveItem) {
            System.out.println("Saved a file");
        }
        else if (e.getSource() == exitItem) {
            System.exit(0);
        }
        // comboBox in cell 1. cell 1.
        else if (e.getSource() == comboBox) {
            System.out.println(comboBox.getSelectedItem());
        }
        // radioButtons in cell 2. cell 1.
        else if (e.getSource() == option1) {
            System.out.println("Options 1 selected.");
        }
        else if (e.getSource() == option2) {
            System.out.println("Options 2 selected.");
        }
        else if (e.getSource() == option3) {
            System.out.println("Options 3 selected.");
        }
        // button in tab 2. cells 4.
        else if (e.getSource() == button2) {
            System.out.println("\nHello " + textField.getText() + "!");
            label1.setVisible(true);
            button2.setEnabled(false);
            textField.setEditable(false);
        }
    }

    // thermometer reading
    public void stateChanged(ChangeEvent e) {
        label2.setText("°C = " + slider.getValue());
    }

    // inner class painting a circle with a gradient
    class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.white);
            g2d.fillRect(0,0,this.getWidth(), this.getHeight());
            g2d.setPaint(gradient);
            g2d.fillOval(100,50,100,100);
        }
    }

    //event handling using internal classes
    class ButtonResponse1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gradient = draw();
            secondPanel.repaint();
        }
    }

    class ButtonResponse2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea.append("The button was clicked " + counter + " time.\n");
            counter++;
        }
    }
}