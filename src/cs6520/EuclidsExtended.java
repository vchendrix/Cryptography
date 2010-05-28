package cs6520;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class EuclidsExtended extends JApplet
{
    /**
     * 
     */
    private static final long serialVersionUID = -5147818679690906015L;

    // Variables declaration
    private JLabel jTitle;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jResults;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton jButton1;
    private JPanel contentPane;

    // End of variables declaration

    // Called when this applet is loaded into the browser.
    public void init()
    {
        // Execute a job on the event-dispatching thread; creating this applet's
        // GUI.
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    jTitle = new JLabel();
                    jLabel1 = new JLabel();
                    jLabel2 = new JLabel();
                    jResults = new JLabel();
                    jTextField1 = new JTextField();
                    jTextField2 = new JTextField();
                    jButton1 = new JButton();
                    contentPane = (JPanel) getContentPane();

                    //
                    // jTitle
                    //
                    jTitle.setHorizontalAlignment(SwingConstants.LEFT);
                    jTitle.setFont(Font.decode("Times New Roman-bold-14"));
                    jTitle.setForeground(new Color(0, 0, 255));
                    jTitle.setText("Euclid's Extended Algorithm");

                    //
                    // jLabel1
                    //
                    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
                    jLabel1.setFont(Font.decode("Times New Roman-bold-14"));
                    jLabel1.setForeground(new Color(0, 0, 255));
                    jLabel1.setText("p");
                    //
                    // jLabel2
                    //
                    jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
                    jLabel2.setFont(Font.decode("Times New Roman-bold-14"));
                    jLabel2.setForeground(new Color(0, 0, 255));
                    jLabel2.setText("q");
                    //

                    //
                    // jResults
                    //
                    jResults.setHorizontalAlignment(SwingConstants.LEFT);
                    jResults.setFont(Font.decode("Courier New-bold-12"));
                    jResults.setForeground(new Color(0, 0, 0));
                    //
                    // jTextField1
                    //
                    jTextField1.setForeground(new Color(0, 0, 255));
                    jTextField1.setSelectedTextColor(new Color(0, 0, 255));
                    jTextField1.setToolTipText("Enter some p");
                    jTextField1.addActionListener(new ActionListener()
                    {

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            jTextField1_actionPerformed(e);

                        }

                    });
                    //
                    // jTextField2
                    //
                    jTextField2.setForeground(new Color(0, 0, 255));
                    jTextField2.setSelectedTextColor(new Color(0, 0, 255));
                    jTextField2.setToolTipText("Enter some q");
                    jTextField2.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            jTextField2_actionPerformed(e);
                        }

                    });
                    //
                    // jButton1
                    //
                    jButton1.setBackground(new Color(204, 204, 204));
                    jButton1.setForeground(new Color(0, 0, 255));
                    jButton1.setText("Calculate");
                    jButton1.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            jButton1_actionPerformed(e);
                        }

                    });
                    //
                    // contentPane
                    //
                    contentPane.setLayout(null);
                    contentPane.setBorder(BorderFactory.createEtchedBorder());
                    contentPane.setBackground(new Color(204, 204, 204));

                    addComponent(contentPane, jTitle, 5, 10, 300, 18);
                    addComponent(contentPane, jLabel1, 25, 47, 30, 18);
                    addComponent(contentPane, jLabel2, 25, 84, 30, 18);
                    addComponent(contentPane, jTextField1, 35, 47, 100, 22);
                    addComponent(contentPane, jTextField2, 35, 82, 100, 22);
                    addComponent(contentPane, jResults, 10, 145, 200, 36);
                    addComponent(contentPane, jButton1, 40, 110, 83, 28);
                    //
                    // login
                    //

                }
            });
        } catch (Exception e)
        {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    /** Add Component Without a Layout Manager (Absolute Positioning) */
    private void addComponent(Container container, Component c, int x, int y,
            int width, int height)
    {
        c.setBounds(x, y, width, height);
        container.add(c);
    }

    private void jTextField1_actionPerformed(ActionEvent e)
    {

    }

    private void jTextField2_actionPerformed(ActionEvent e)
    {

    }

    private void jButton1_actionPerformed(ActionEvent e)
    {
        System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
        String pStr = new String(jTextField1.getText());
        String qStr = new String(jTextField2.getText());

        if (pStr.equals("") || qStr.equals(""))
        {
            jButton1.setEnabled(false);
            JLabel errorFields = new JLabel(
                    "<HTML><FONT COLOR = Blue>You must enter a p and q</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            jTextField1.setText("");
            jTextField2.setText("");
            jButton1.setEnabled(true);
            this.setVisible(true);
        } else
        {

            try
            {
                int p = Integer.parseInt(pStr);
                int q = Integer.parseInt(qStr);
                int vals[] = Algorithms.euclid(p, q);

                System.out.println("gcd(" + p + ", " + q + ") = " + vals[0]);
                System.out.println(vals[1] + "(" + p + ") + " + vals[2] + "("
                        + q + ") = " + vals[0]);

                jResults.setText("<HTML>gcd(" + p + ", " + q + ") = " + vals[0]
                        + "<br/>" + vals[1] + "(" + p + ") + " + vals[2] + "("
                        + q + ") = " + vals[0] + "</HTML>");
            } catch (Exception exp)
            {
                jButton1.setEnabled(false);
                JLabel errorFields = new JLabel(
                        "<HTML><FONT COLOR = RED>You must enter valid integers p and q</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);
                jTextField1.setText("");
                jTextField2.setText("");
                jButton1.setEnabled(true);
                this.setVisible(true);
            }

        }
    }

}