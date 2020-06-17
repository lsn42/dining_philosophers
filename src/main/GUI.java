package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import java.util.HashSet;

public class GUI extends JFrame implements Source, Listener {

    private static final long serialVersionUID = 1L;

    public static final String AUTHOR = "lsn42";

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

    public static final String NORMAL = "哲学家进餐问题：\n    有5位哲学家，他们每天的生活就是思考和进餐。他们围坐在一张桌子旁，桌子上有无限供应的火锅，但只有五支筷子。哲学家进餐时，会先后拿起自己右边和左边的筷子。但是这样可能发生死锁，即每个哲学家都拿起了自己右边的筷子，却又都在等待自己左边的筷子，哲学家们都不能吃到东西。（将速度调到最大会很快出现这种情况！）";
    public static final String IMPROVED = "哲学家进餐问题（改进版）：\n    有5位哲学家，他们每天的生活就是思考和进餐。他们围坐在一张桌子旁，桌子上有无限供应的火锅，但只有五支筷子。哲学家进餐时，会先后拿起自己右边和左边的筷子。但是这样可能发生死锁，即每个哲学家都拿起了自己左边的筷子，却又都在等待自己右边的筷子，哲学家们都不能吃到东西。\n    解决方法：安排其中一位哲学家先拿起自己左边的筷子，阻止循环等待的发生。";

    public JPanel mainPanel;
    public JPanel infoPanel;
    public JPanel ctrlPanel;

    public JTextArea author;
    public JTextArea information;
    public JLabel eaten;
    public JPanel caption;

    public JScrollBar speed;
    public JLabel labelSpeed;
    public JButton buttonResetSpeed;
    public JButton buttonRestart;
    public JButton buttonMode0;
    public JButton buttonMode1;

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

    public HashSet<Listener> listeners;

    public GUI(int mode) {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(gbl);
        this.setTitle("哲学家进餐");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(gbl);

        this.infoPanel = new JPanel();
        this.infoPanel.setLayout(gbl);
        this.infoPanel.setBorder(BorderFactory.createEtchedBorder());

        this.author = new JTextArea(GUI.AUTHOR);
        this.author.setBorder(BorderFactory.createEtchedBorder());
        this.information = new JTextArea();
        this.information.setBorder(BorderFactory.createEtchedBorder());
        this.eaten = new JLabel();
        this.caption = new JPanel();
        this.caption.setLayout(gbl);
        this.caption.setBorder(BorderFactory.createEtchedBorder());

        this.ctrlPanel = new JPanel();
        this.ctrlPanel.setLayout(gbl);
        this.ctrlPanel.setBorder(BorderFactory.createEtchedBorder());

        this.speed = new JScrollBar(JScrollBar.HORIZONTAL, 128, 0, 1, 256);
        this.labelSpeed = new JLabel("速度：");
        this.buttonResetSpeed = new JButton("重置");
        this.buttonRestart = new JButton("重新开始");
        this.buttonMode0 = new JButton("普通模式");
        this.buttonMode1 = new JButton("改进模式");

        this.panelPhilosopher = new JPanel[5];
        this.pictureChopstickGot = new JLabel[5];
        this.picturePhilosopher = new JLabel[5];
        this.pictureDialog = new JLabel[5];
        this.labelDialog = new JLabel[5];

        this.panelChopstick = new JPanel[5];
        this.pictureChopstick = new JLabel[5];
        this.labelChopstick = new JLabel[5];

        this.picturePlate = new JLabel[5];
        this.pictureDesk = new JLabel();
        this.pictureHotpot = new JLabel();

        this.listeners = new HashSet<Listener>();

        // initializing 5 philosophers, chopsticks and plates
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

        // initializing desk and hotpot
        this.pictureDesk.setIcon(GUI.DESK);
        this.pictureHotpot.setIcon(GUI.HOTPOT);
        
        // adding 5 philosophers to mainPanel
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

        // adding desk and hotpot to mainPanel
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

        // adding plate0, 1, 4 to mainPanel
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.mainPanel.add(this.picturePlate[0], gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.mainPanel.add(this.picturePlate[1], gbc);
        gbc.gridx = 1;
        this.mainPanel.add(this.picturePlate[4], gbc);

        // adding chopsticks0, 1, 2, 4 to mainPanel
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

        // adding chopstick3 and plate2, 3 to container
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

        // addding container to mainPanel
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        this.mainPanel.add(container, gbc);
        
        // configuring author bar and information bar
        this.author.setLineWrap(true);
        this.author.setEditable(false);
        this.author.setFont(new Font("宋体", Font.PLAIN, 12));
        this.information.setLineWrap(true);
        this.information.setEditable(false);
        this.information.setFont(new Font("微软雅黑", Font.BOLD, 13));
        if (mode == 0) {
            this.information.setText(GUI.NORMAL);
        } else if (mode == 1) {
            this.information.setText(GUI.IMPROVED);
        }

        // initializing cations
        JLabel captionPicture1 = new JLabel(GUI.THINKING);
        JLabel captionPicture2 = new JLabel(GUI.EATING);
        JLabel captionPicture3 = new JLabel(GUI.WAITING);
        JLabel captionText1 = new JLabel("思考");
        JLabel captionText2 = new JLabel("进餐");
        JLabel captionText3 = new JLabel("等待");

        // adding captions to caption panel
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.caption.add(captionPicture1, gbc);
        gbc.gridx = 1;
        this.caption.add(captionText1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.caption.add(captionPicture2, gbc);
        gbc.gridx = 1;
        this.caption.add(captionText2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.caption.add(captionPicture3, gbc);
        gbc.gridx = 1;
        this.caption.add(captionText3, gbc);

        // adding many information to information panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.infoPanel.add(this.author, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.infoPanel.add(this.information, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.infoPanel.add(this.eaten, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.infoPanel.add(this.caption, gbc);

        // configuring the listener of scroll bar and buttons
        this.speed.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                GUI.this.notifyAll(new Event(GUI.this, "speed", GUI.this.speed.getValue()+""));
            }
        });
        this.buttonResetSpeed.addActionListener(new ButtonListener());
        this.buttonRestart.addActionListener(new ButtonListener());
        this.buttonMode0.addActionListener(new ButtonListener());
        this.buttonMode1.addActionListener(new ButtonListener());

        // adding scroll bar and speed reset button to container2
        JPanel container2 = new JPanel();
        container2.setLayout(gbl);
        container2.setBorder(BorderFactory.createEtchedBorder());
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        container2.add(this.labelSpeed, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        container2.add(this.buttonResetSpeed, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        container2.add(this.speed, gbc);

        // adding container2 and buttons to control panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.ctrlPanel.add(container2, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 2;
        this.ctrlPanel.add(this.buttonRestart, gbc);
        gbc.gridy = 3;
        this.ctrlPanel.add(this.buttonMode0, gbc);
        gbc.gridy = 4;
        this.ctrlPanel.add(this.buttonMode1, gbc);

        // adding three parts to the main frame
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        this.add(this.mainPanel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(this.infoPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(this.ctrlPanel, gbc);
    }

    public GUI() {
        this(0);
    }

    public void setEaten(int eaten) {
        this.eaten.setText("哲学家们一共吃了 "+eaten+" 份火锅！");
    }

    public void reset() {
        for (int i = 0; i < 5; ++i) {
            this.pictureChopstickGot[i].setIcon(GUI.NOTHING);
            this.picturePhilosopher[i].setIcon(GUI.NOTHING);
            this.pictureDialog[i].setIcon(GUI.NODIALOG);
            this.pictureChopstick[i].setIcon(GUI.CHOPSTICK);
        }
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("重置")) {
                GUI.this.speed.setValue(128);
                GUI.this.notifyAll(new Event(GUI.this, "speed", "128"));
            }
            else if (command.equals("重新开始")) {
                GUI.this.notifyAll(new Event(GUI.this, "restart"));
            } else if (command.equals("改进模式")) {
                GUI.this.notifyAll(new Event(GUI.this, "mode", "1"));
                GUI.this.information.setText(GUI.IMPROVED);
            } else if (command.equals("普通模式")) {
                GUI.this.notifyAll(new Event(GUI.this, "mode", "0"));
                GUI.this.information.setText(GUI.NORMAL);
            }
        }
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

    @Override
    public void addListener(Listener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeListener(Listener l) {
        this.listeners.remove(l);
    }

    @Override
    public void notifyAll(Event e) {
        for (Listener l: this.listeners) {
            l.actionPerformed(e);
        }
    }
}