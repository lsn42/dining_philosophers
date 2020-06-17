package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;

public class GUI extends JFrame implements Listener {

    private static final long serialVersionUID = 1L;

    public static final ImageIcon CHOPSTICKS = new ImageIcon("resource/36px-chopsticks.png");
    public static final ImageIcon CHOPSTICK = new ImageIcon("resource/36px-chopstick.png");
    public static final ImageIcon NOTHING = new ImageIcon("resource/36px-null.png");
    public static final ImageIcon THINKING = new ImageIcon("resource/2018new_wenhao_org.png");
    public static final ImageIcon WAITING = new ImageIcon("resource/2018new_beishang_org.png");
    public static final ImageIcon EATING = new ImageIcon("resource/2018new_taikaixin_org.png");
    public static final ImageIcon DIALOGRIGHT = new ImageIcon("resource/dialog-right.png");
    public static final ImageIcon DIALOGLEFT = new ImageIcon("resource/dialog-left.png");
    public static final ImageIcon NODIALOG = new ImageIcon("resource/dialog-null.png");
    public static final ImageIcon HOTPOT = new ImageIcon("resource/hotpot.png");
    public static final ImageIcon PLATE = new ImageIcon("resource/plate.png");
    public static final ImageIcon DESK = new ImageIcon("resource/desk.png");

    public static final String NORMAL = "哲学家进餐问题：\n    有5位哲学家，他们每天的生活就是思考和进餐。他们围坐在一张桌子旁，桌子上有无限供应的火锅，但是只有五支筷子。哲学家进餐时，会先后拿起自己右边和左边的筷子。但是这样可能发生每个哲学家都拿起了自己右边的筷子，却又都在等待自己左边的筷子的情况，没有任何一个哲学家可以吃到东西。";
    public static final String IMPROVED = "哲学家进餐问题（改进版）：\n    有5位哲学家，他们每天的生活就是思考和进餐。他们围坐在一张桌子旁，桌子上有无限供应的火锅，但是只有五支筷子。哲学家进餐时，会先后拿起自己右边和左边的筷子。但是这样可能发生每个哲学家都拿起了自己左边的筷子，却又都在等待自己右边的筷子的情况，没有任何一个哲学家可以吃到东西。\n    解决方法：安排其中一位哲学家先拿起自己左边的筷子，阻止循环等待的发生。";

    public JPanel mainPanel;
    public JTextArea infomation;
    public JPanel ctrlPanel;
    public JButton mode0;
    public JButton mode1;

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
        this.infomation = new JTextArea();
        this.infomation.setLineWrap(true);
        this.infomation.setEditable(false);
        this.infomation.setFont(new Font("微软雅黑", Font.BOLD, 16));
        this.infomation.setText(GUI.NORMAL);
        this.infomation.setBorder(BorderFactory.createEtchedBorder());
        this.ctrlPanel = new JPanel();
        this.ctrlPanel.setBorder(BorderFactory.createEtchedBorder());
        this.mode0 = new JButton("普通模式");
        this.mode1 = new JButton("改进模式");

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
            this.picturePhilosopher[i].setIcon(GUI.NOTHING);
            this.pictureDialog[i] = new JLabel();
            this.pictureDialog[i].setIcon(GUI.NODIALOG);
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
            gbc.gridx = i>2?1:0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.panelPhilosopher[i].add(this.picturePhilosopher[i], gbc);
            gbc.gridx = i>2?0:1;
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
        
        // add 5 philosophers
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

        // desk and hotpot
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

        // plate part 1
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.mainPanel.add(this.picturePlate[0], gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.mainPanel.add(this.picturePlate[1], gbc);
        gbc.gridx = 1;
        this.mainPanel.add(this.picturePlate[4], gbc);

        // chopsticks
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.mainPanel.add(this.panelChopstick[0], gbc);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 3;
        this.mainPanel.add(this.panelChopstick[1], gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 2;
        this.mainPanel.add(this.panelChopstick[2], gbc);
        gbc.gridx = 1;
        this.mainPanel.add(this.panelChopstick[4], gbc);

        JPanel container = new JPanel();
        container.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(this.panelChopstick[3], gbc);

        // plate part 2
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        container.add(this.picturePlate[3], gbc);
        gbc.gridx = 2;
        container.add(this.picturePlate[2], gbc);

        // container
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        this.mainPanel.add(container, gbc);
        
        this.ctrlPanel.add(this.mode0);
        this.ctrlPanel.add(this.mode1);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        this.add(this.mainPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(this.infomation, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(this.ctrlPanel, gbc);
    }

    @Override
    public void actionPerformed(Event e) {
        if (e.getSource() instanceof Philosopher) {
            Philosopher source = (Philosopher)e.getSource();
            int index = source.index;
            if (e.command[0].equals("status")) {
                if (source.status.equals("thinking")) {
                    this.picturePhilosopher[index].setIcon(GUI.THINKING);
                } else if (source.status.equals("waiting")) {
                    this.picturePhilosopher[index].setIcon(GUI.WAITING);
                    //
                } else if (source.status.equals("eating")) {
                    this.picturePhilosopher[index].setIcon(GUI.EATING);
                }
            } else if (e.command[0].equals("pick")) {
                if (e.command[1].equals("right")) {
                    this.pictureChopstick[index].setIcon(GUI.NOTHING);
                } else if (e.command[1].equals("left")) {
                    this.pictureChopstick[(index+1)%5].setIcon(GUI.NOTHING);
                }
                if (this.pictureChopstickGot[index].getIcon().equals(GUI.NOTHING)) {
                    this.pictureChopstickGot[index].setIcon(GUI.CHOPSTICK);
                } else if (this.pictureChopstickGot[index].getIcon().equals(GUI.CHOPSTICK)) {
                    this.pictureChopstickGot[index].setIcon(GUI.CHOPSTICKS);
                }
            } else if (e.command[0].equals("release")) {
                if (e.command[1].equals("right")) {
                    this.pictureChopstick[index].setIcon(GUI.CHOPSTICK);
                } else if (e.command[1].equals("left")) {
                    this.pictureChopstick[(index+1)%5].setIcon(GUI.CHOPSTICK);
                }
                if (this.pictureChopstickGot[index].getIcon().equals(GUI.CHOPSTICK)) {
                    this.pictureChopstickGot[index].setIcon(GUI.NOTHING);
                } else if (this.pictureChopstickGot[index].getIcon().equals(GUI.CHOPSTICKS)) {
                    this.pictureChopstickGot[index].setIcon(GUI.CHOPSTICK);
                }
            } else if (e.command[0].equals("wait")) {
                this.pictureDialog[index].setIcon(index>2?GUI.DIALOGRIGHT:GUI.DIALOGLEFT);
                if (e.command[1].equals("right")) {
                    this.labelDialog[index].setText(index+"");
                } else if (e.command[1].equals("left")) {
                    this.labelDialog[index].setText((index+1)%5+"");
                } else if (e.command[1].equals("both")) {
                    this.labelDialog[index].setText(index+" and "+(index+1)%5);
                } else if (e.command[1].equals("end")) {
                    this.labelDialog[index].setText("");
                    this.pictureDialog[index].setIcon(GUI.NODIALOG);
                } 
            }
        }
    }
}