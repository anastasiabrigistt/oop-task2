import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Contact extends JDialog {
    private JPanel contentPane;
    private JLabel nameField;
    private JTextArea infoField;
    private JSpinner ageSpinner;
    private JLabel ageText;
    private JComboBox genderChoice;
    private JButton approveButton;
    private JButton denyButton;
    private JLabel iconLabel;
    private JTabbedPane tabbedPane1;
    private JPanel searchWindow;
    private JPanel markedPeople;
    private JTable markedList;
    private JTextArea interestField;
    private List<Person> persons;
    private List<Person> sentRequests;
    private int currentPersonIndex;
    private List<Person> filteredPersons;

    public Contact() {
        setContentPane(contentPane);
        setModal(true);
        ageSpinner.setValue(30);
        persons = new ArrayList<>();
        sentRequests = new ArrayList<>();
        persons.add(new Person("Джошуа", 21, "Мужской", "хоккей", "Привет! Меня зовут Джошуа!", new ImageIcon("src/avatars/6620-today-is-for-relaxing-zoom_90.jpg")));
        persons.add(new Person("Анастасия", 22, "Женский", "Рисование", "Привет! Меня зовут Настя!", new ImageIcon("src/avatars/nastya.jpg")));
        persons.add(new Person("Валера", 19, "Мужской", "Кулинария, спорт", "Привет! Меня зовут Валера! Но ты меня можешь звать Валера Патрон ;)", new ImageIcon("src/avatars/photo_2023-11-16_21-54-42.jpg")));
        persons.add(new Person("Марина", 19, "Женский", "Музыка, вольный образ жизни", "Привет! Меня зовут Марина! Мне недавно исполнилось 19!", new ImageIcon("src/avatars/photo_2023-11-16_21-56-31.jpg")));
        persons.add(new Person("Лара", 31, "Женский", "Актёрство, кино", "Привет! Меня зовут Лара! Я снималась в Шерлоке Холмсе!", new ImageIcon("src/avatars/ded426fab91b2e5d1b82bed4f7148f1c-e1644570208825.jpg")));
        persons.add(new Person("Геннадий", 40, "Мужской", "Спорт, развитие", "Привет! Меня зовут Гиганадий!", new ImageIcon("src/avatars/загружено.jpg")));
        currentPersonIndex = -1;
        searchWindow.setBackground(new Color(60, 63, 65));
        approveButton.setBackground(new Color(43, 43, 43));
        denyButton.setBackground(new Color(43, 43, 43));
        ageSpinner.setBackground(new Color(60, 63, 65));
        markedPeople.setBackground(new Color(60, 63, 65));
        markedList.setBackground(new Color(60, 63, 65));
        markedList.setForeground(Color.white);
        nameField.setText("Для продолжение выберите фильтры и нажмите на галочку");
        filteredPersons = new ArrayList<>();
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPersonIndex == -1) {
                    filteredPersons = filtered(persons, genderChoice.getSelectedItem().toString(), (int) ageSpinner.getValue());
                    currentPersonIndex++;
                    updateUI();
                } else {
                    if (currentPersonIndex < filteredPersons.size()) {
                        sentRequests.add(filteredPersons.get(currentPersonIndex));
                        for (Person p : sentRequests){
                            System.out.println(p.getName());
                        }
                        currentPersonIndex++;
                        updateUI();
                    }
                }
            }
        });

        denyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPersonIndex < filteredPersons.size()) {
                    currentPersonIndex++;
                    updateUI();
                }
            }
        });


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });


        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        dispose();
    }

    private void onCancel() {

        dispose();
    }

    public static void main(String[] args) {
        Contact dialog = new Contact();
        dialog.setMinimumSize(new Dimension(1000, 1000));
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private List<Person> filtered(List<Person> persons, String gender, int age) {
        List<Person> output = new ArrayList<>();
        for (Person p : persons) {
            if ((gender.equals(p.getGender()) || gender.equals("Не важно")) && age <= p.getAge()) {
                output.add(p);
            }
        }
        return output;
    }

    private void updateUI() {
        if (currentPersonIndex < filteredPersons.size()) {

            Person currentPerson = filteredPersons.get(currentPersonIndex);
            nameField.setText(currentPerson.getName());
            infoField.setText("Обо мне: " + currentPerson.getShortInfo() + " Мои интересы: " + currentPerson.getInterest());
            Image scaledImage = currentPerson.getAvatar().getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            iconLabel.setIcon(resizedIcon);
            markedList.setVisible(true);
            JTableUtils.writeArrayToJTable(markedList, getInfoFromList(sentRequests));
        } else {
            nameField.setText("Анкеты с данным фильтрами кончились!");
            currentPersonIndex = -1;
        }
    }
    private String[][] getInfoFromList(List<Person> persons){
        String[][] out = new String[persons.size()][5];
        for (int i = 0; i< out.length; i++){
                String[] temp = new String[]{persons.get(i).getName(),String.valueOf(persons.get(i).getAge()),persons.get(i).getGender(),persons.get(i).getInterest(),persons.get(i).getInterest()};
                out[i] = temp;
            }
        return out;
    }

}
