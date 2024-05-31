

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

class ServerUi extends JFrame implements ActionListener {
    String port;
    LoginUi ui;
    Socket s;
    /////////////////////////////////////////////���� ui���
    JTextArea ta = new JTextArea();
    JTextField  chatTf;
    JScrollPane sp;
    JPanel  endP, chatP, btnP, taP;
    Container cp;
    JButton startBtn, banBtn, endBtn, clearBtn;
    JComboBox idBox;

    ServerUi(LoginUi ui) {
        this.ui = ui;
        this.port=ui.port;
        init();
        setUi();
        new LiarServer(this);
    }

    void init() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        endP = new JPanel(new BorderLayout());
        endBtn = new JButton("����");
        endP.add(endBtn, BorderLayout.WEST);
        cp.add(endP, BorderLayout.NORTH);                                                                //endP

        taP = new JPanel(new BorderLayout());
        taP.add(ta, BorderLayout.CENTER);
        sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ta.setLineWrap(true);
        taP.add(sp);
        ta.setEditable(false);
        cp.add(taP, BorderLayout.CENTER);                                                            //taP

        chatP = new JPanel(new BorderLayout());
        btnP = new JPanel(new GridLayout(1, 4));
        startBtn = new JButton("���� ����!");
        banBtn = new JButton("����");
        idBox = new JComboBox();
        clearBtn = new JButton("ä�� �����");
        btnP.add(startBtn);
        btnP.add(banBtn);
        btnP.add(idBox);
        btnP.add(clearBtn);
        chatTf = new JTextField("");
        ta.setEnabled(false);
        chatP.add(chatTf, BorderLayout.CENTER);
        chatP.add(btnP, BorderLayout.SOUTH);
        cp.add(chatP, BorderLayout.SOUTH);                                                            //chatP
        ta.setFont(new Font("���� ���",Font.BOLD,20));
        ta.setDisabledTextColor(Color.black);
        setUi();
        act();
    }

    void setUi() {
        setVisible(true);
        setTitle( port + "���� ä����..");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /*public void sendmessage() {
        try {
            string text = chattf.gettext();
            ta.append(text + "\n");


            //���α׷� ����
            system.exit(0);
        else{
                //�Էµ� �޼����� "/exit"�� �ƴ� ���( ������ �޼����� ���)
                //Ŭ���̾�Ʈ���� �޼��� ����
                dos.writeutf(text);

                //�ʱ�ȭ �� Ŀ����û
                chattf.settext("");
                chattf.requestfocus();
            }
        } catch (ioexception e) {
        }
    }*/
    void act() {
        endBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        startBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(clearBtn)) {
            ta.setText(null);
        }

    }


}