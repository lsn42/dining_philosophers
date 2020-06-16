package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final ImageIcon CHOPSTICKS = new ImageIcon("resource/36px-chopsticks.png");
    public static final ImageIcon CHOPSTICK = new ImageIcon("resource/36px-chopstick.png");
    public static final ImageIcon NOTHING = new ImageIcon("resource/36px-null.png");
    public static final ImageIcon DIALOGRIGHT = new ImageIcon("resource/dialog-right.png");
    public static final ImageIcon DIALOGLEFT = new ImageIcon("resource/dialog-left.png");
    public static final ImageIcon NODIALOG = new ImageIcon("resource/dialog-null.png");
    public static final ImageIcon THINKING = new ImageIcon("resource/2018new_wenhao_org.png");
    public static final ImageIcon WAITING = new ImageIcon("resource/2018new_beishang_org.png");
    public static final ImageIcon EATING = new ImageIcon("resource/2018new_wenhao_org.png");
    public static final ImageIcon HOTPOT = new ImageIcon("resource/hotpot.png");
    public static final ImageIcon PLATE = new ImageIcon("resource/plate.png");
    public static final ImageIcon DESK = new ImageIcon("resource/desk.png");
    public static final ImageIcon TEST = new ImageIcon("resource/5b69a068d2ec0.gif");

    public JPanel mainPanel;

    public JPanel[] panelPhilosopher;
    public JLabel[] pictureChopstickGot;
    public JLabel[] picturePhilosopher;
    public JLabel[] pictureDialog;
    public JLabel[] labelDialog;

    public JPanel[] panelChopstick;
    public JLabel[] pictureChopstick;
    public JLabel[] labelChopstick;

    public JLabel[] picturePlate;
    public JLabel pictureDesk;
    public JLabel pictureHotpot;

    public GUI() {

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(gbl);
        this.setTitle("哲学家进餐");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(gbl);

        this.panelPhilosopher = new JPanel[5];
        this.pictureChopstickGot = new JLabel[5];
        this.picturePhilosopher = new JLabel[5];
        this.pictureDialog = new JLabel[5];
        this.labelDialog = new JLabel[5];

        this.panelChopstick = new JPanel[5];
        this.pictureChopstick = new JLabel[5];
        this.labelChopstick = new JLabel[5];

        this.picturePlate = new JLabel[5];

        for (int i = 0; i < 5; ++i) {
            this.panelPhilosopher[i] = new JPanel();
            this.panelPhilosopher[i].setLayout(gbl);
            this.pictureChopstickGot[i] = new JLabel();
            this.pictureChopstickGot[i].setIcon(GUI.NOTHING);
            this.picturePhilosopher[i] = new JLabel();
            this.picturePhilosopher[i].setIcon(GUI.THINKING);
            this.pictureDialog[i] = new JLabel();
            this.pictureDialog[i].setIcon(GUI.NODIALOG);
            // this.pictureDialog[i].setIcon(i>3?GUI.DIALOGRIGHT:GUI.DIALOGLEFT);
            this.labelDialog[i] = new JLabel();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.panelPhilosopher[i].add(this.pictureDialog[i], gbc);
            gbc.fill = GridBagConstraints.NONE;
            this.panelPhilosopher[i].add(this.labelDialog[i], gbc);
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.panelPhilosopher[i].add(this.picturePhilosopher[i], gbc);
            gbc.gridx = 1;
            this.panelPhilosopher[i].add(this.pictureChopstickGot[i], gbc);

            this.panelChopstick[i] = new JPanel();
            this.panelChopstick[i].setLayout(gbl);
            this.pictureChopstick[i] = new JLabel();
            this.pictureChopstick[i].setIcon(GUI.CHOPSTICK);
            this.labelChopstick[i] = new JLabel(i+"");
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.panelChopstick[i].add(this.pictureChopstick[i], gbc);
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            this.panelChopstick[i].add(this.labelChopstick[i], gbc);

            this.picturePlate[i] = new JLabel();
            this.picturePlate[i].setIcon(GUI.PLATE);
        }

        this.pictureDesk = new JLabel();
        this.pictureHotpot = new JLabel();
        this.pictureDesk.setIcon(GUI.DESK);
        this.pictureHotpot.setIcon(GUI.HOTPOT);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.mainPanel.add(this.panelPhilosopher[0], gbc);
        gbc.gridx = 4;
        gbc.gridy = 1;
        this.mainPanel.add(this.panelPhilosopher[1], gbc);
        gbc.gridy = 3;
        this.mainPanel.add(this.panelPhilosopher[2], gbc);
        gbc.gridx = 0;
        this.mainPanel.add(this.panelPhilosopher[3], gbc);
        gbc.gridy = 1;
        this.mainPanel.add(this.panelPhilosopher[4], gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        this.mainPanel.add(this.pictureDesk, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.mainPanel.add(this.pictureHotpot, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.mainPanel.add(this.picturePlate[0], gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.mainPanel.add(this.picturePlate[1], gbc);
        gbc.gridx = 1;
        this.mainPanel.add(this.picturePlate[4], gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.mainPanel.add(this.panelChopstick[0], gbc);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 3;
        this.mainPanel.add(this.panelChopstick[1], gbc);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridy = 2;
        this.mainPanel.add(this.panelChopstick[2], gbc);
        gbc.gridx = 1;
        this.mainPanel.add(this.panelChopstick[4], gbc);

        JPanel container = new JPanel();
        container.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(this.panelChopstick[3], gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        container.add(this.picturePlate[3], gbc);
        gbc.gridx = 2;
        container.add(this.picturePlate[2], gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        this.mainPanel.add(container, gbc);
        
        this.add(mainPanel);
    }
    
    public static void main(String args[]) {
        new GUI();
    }
}