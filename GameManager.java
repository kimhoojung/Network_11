

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameManager {

    String topic;
    String liar;
    Scanner sc;
    ArrayList<String> topicList;
    List<String> playerList = new ArrayList();
    LiarServer ls;
    HashSet<OneClientModul> ocmSet = new HashSet<>();

    GameManager(LiarServer ls) {
        System.out.println("�׸� ����");
        this.ls = ls;
        for (OneClientModul ocm : ls.v) {
            playerList.add(ocm.chatId);
        }
        setTopic();
        setLair();
        System.out.println(liar);
        System.out.println(topic);
        for (OneClientModul ocm : ls.v) {
            gm("liar:" + liar);
            gm("topic:" + topic);
        }
        oneChat();
        vote();
        liarSelect();
        unlockAll();
        ls.sui.startBtn.setEnabled(true);
    }

    void lockChat() {
        gm("ä�ö�");
    }

    void unlockAll() {
        gm("�þ��");
    }

    void oneChat() {
        ls.ocm.broadcast("ä���� ���ϴ�.");
        ls.ocm.broadcast("10�� �� ������� ������ �Ѹ���� �����ϼ���.");
        lockChat();
        ls.sleepTh(10);
        ArrayList<Integer> idx = new ArrayList<>();
        Random r = new Random();
        System.out.println(ls.v.size());
        for (int i = 0; i < ls.v.size(); i++) {
            int z = r.nextInt(ls.v.size());
            idx.add(z);
            System.out.println(idx.get(i));
            for (int j = 0; j < i; j++) {
                if (idx.get(i) == idx.get(j)) {
                    idx.remove(i);
                    i--;
                }
            }
        }
            System.out.println(idx);
        for (int i = 0; i < ls.v.size(); i++) {
            ls.ocm.broadcast(ls.v.get(idx.get(i)).chatId+"���� �Է����Դϴ�.");
            gm("ä�þ��" + ls.v.get(idx.get(i)).chatId);
            ls.sleepTh(10);
        }
    }

    public void setTopic() {

        File topicListFile = new File("����.txt");
        try {
            sc = new Scanner(topicListFile);
        } catch (FileNotFoundException e) {
            System.out.println("������ ã�� �� �����ϴ�.");
        }

        topicList = new ArrayList<>();
        while (sc.hasNextLine()) {
            topicList.add(sc.nextLine());
        }

        Random topicGenerator = new Random();
        System.out.println(topicList.size());
        int topicListIndex = topicGenerator.nextInt(topicList.size());
        topic = topicList.get(topicListIndex);

    }

    public void setLair() {

        Random lairSelector = new Random();
        int listIndex = lairSelector.nextInt(playerList.size());
        liar = playerList.get(listIndex);

    }

    public void vote() {
        for (OneClientModul ocm : ls.v) {
            gm("list" + ocm.chatId);
        }
        gm("vote");
        ls.sleepTh(20);
        System.out.println("�������");
    }

    void liarSelect() { //fffff
        int i = 0;
        String voteId = "";
        for (OneClientModul ocm : ls.v) {
            System.out.println(ls.voteList.size());
            int j = Collections.frequency(ls.voteList, ocm.chatId);
            System.out.println(j);
            if (j > i) {
                i = j;
                voteId = ocm.chatId;
                System.out.println(i);
            }
        }
        System.out.println("Max: " + voteId);
        if (liar.equals(voteId)) {
            gm("votecom" + voteId);
            ls.ocm.broadcast("���̾ ã�ҽ��ϴ�.");
            ls.ocm.broadcast("Liar: " + liar);
            ls.ocm.broadcast("���̾ ���þ �߸����Դϴ�.");
            ls.sleepTh(10);
            String liarTopic;
            liarTopic = ls.liarTopic;
            System.out.println(liarTopic);
            if (liarTopic.equals(topic)) {
                System.out.println("���̾�¸�");
                ls.ocm.broadcast("���̾�¸�");
                result("resultliarWin");
            } else if (liarTopic.equals("10���ʰ�")) {
                ls.ocm.broadcast("���̾ ���ѽð��� ���þ �Է����� ���߽��ϴ�.");
                ls.ocm.broadcast("���̾��й�");
                result("resultliarLose");

            } else {
                ls.ocm.broadcast("���̾ ���þ ������ ���޽��ϴ�." +
                        "\n���̾� �й�");
                ls.ocm.broadcast(" ���þ� : " + topic + "\n���̾ �Է��� ���þ� : " + liarTopic);
                result("resultliarLose");
            }
            ls.liarTopic = "10���ʰ�";
        } else {
            System.out.println("���̾�¸�");
            ls.ocm.broadcast("���̾ ã�Ƴ��� ���߽��ϴ�.\n Liar: " + liar);
            ls.ocm.broadcast("���̾�¸�");
            result("resultliarWin");
        }
        //result();
    }

    void result(String str) {
        gm(str);
        ocmSet.removeAll(ocmSet);
    }
    void gm(String str) {
        ls.ocm.broadcast("gm" + str);
    }

}
