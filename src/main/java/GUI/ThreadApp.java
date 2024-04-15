package GUI;

import business_logic.Scheduler;
import business_logic.SimulationManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadApp extends JFrame{
    private JPanel panel1;
    private JTextField noCTF;
    private JTextField noQTF;
    private JTextField minATTF;
    private JTextField maxATTF;
    private JTextField minSTTF;
    private JTextField maxSTTF;
    private JButton startButton;
    private JTextArea textArea1;
    private JComboBox strategyCB;
    private JTextField simTimeTF;
    private JTextArea textArea2;
    private JTextField awtTF;
    private JTextField astTF;
    private JTextField phTF;

    public ThreadApp() {
        setTitle("Simulare ThreadApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 500);
        add(panel1);
        setVisible(true);
        ThreadApp view = this;
        //  SimulationManager simM = new SimulationManager();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.textArea1.setText("");
                view.textArea2.setText("");
               SimulationManager.main(view);
            }
        });


    }





    public String getNoCTF() {
        return noCTF.getText();
    }



    public String getNoQTF() {
        return noQTF.getText();
    }

    public void setNoQTF(JTextField noQTF) {
        this.noQTF = noQTF;
    }

    public String getMinATTF() {
        return minATTF.getText();
    }



    public String getMaxATTF() {
        return maxATTF.getText();
    }



    public String getMinSTTF() {
        return minSTTF.getText();
    }



    public String getMaxSTTF() {
        return maxSTTF.getText();
    }


    public String getTextArea1() {
        return textArea1.getText();

    }

    public void setTextArea1(String textArea1) {
        this.textArea1.append(textArea1);
    }

    public void setTextArea2(String textArea2) {
        this.textArea2.setText(textArea2);
    }

    public JComboBox getStrategyCB() {
        return strategyCB;
    }



    public String getSimTimeTF() {
        return simTimeTF.getText();
    }


    public void setAwtTF(Double awtTF) {
        this.awtTF.setText(awtTF.toString());
    }


    public void setAstTF(Double astTF) {
        this.astTF.setText(astTF.toString());
    }


    public void setPhTF(Integer phTF) {
        this.phTF.setText(phTF.toString());
    }

    public static void highlightText(int time,ThreadApp view, Scheduler scheduler) {
        Highlighter highlighter = view.getTextArea2().getHighlighter();
        Highlighter.HighlightPainter timeHighlight = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
        Highlighter.HighlightPainter queueHighlight = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        Highlighter.HighlightPainter[] taskHighlight = new Highlighter.HighlightPainter[4];
        taskHighlight[0] = new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE);
        taskHighlight[1] = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
        taskHighlight[2] = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        int timeH = 7;
        if(time >= 10)
        {
            timeH ++;
        }
        try {
            highlighter.addHighlight(0, timeH, timeHighlight);
            int k = 0;
            for(int i=0; i<scheduler.getServers().size(); i++)
            {
                int lineOffset = view.getTextArea2().getLineStartOffset(2+i);
                int lineEndOffset = view.getTextArea2().getLineEndOffset(2+i);
                int stop = 8;
                if( i >= 10)
                {
                    stop ++;
                }
                highlighter.addHighlight(lineOffset, lineOffset + stop, queueHighlight);
                int startp = 0;
                k = i%3;
                for(int j = lineOffset; j < lineEndOffset ; j++)
                {
                    if(view.getTextArea2().getText().charAt(j) == '(')
                    {
                        startp = j;
                        k = Integer.parseInt(view.getTextArea2().getText().charAt(j+1) + "")%3;
                    }else if(view.getTextArea2().getText().charAt(j) == ')')
                    {
                        highlighter.addHighlight(startp, j + 1, taskHighlight[k]);
                        if(k<2) k++;
                        else k=0;
                    }
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());


        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new ThreadApp());
    }

    public JTextArea getTextArea2() {
        return this.textArea2;
    }
}



