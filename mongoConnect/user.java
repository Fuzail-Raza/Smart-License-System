import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class User extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel Fathername;
    private JTextField tname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox<String> date;
    private JComboBox<String> month;
    private JComboBox<String> year;
    private JLabel add;
    private JTextArea tadd;
    private JLabel cnicLabel;
    private JTextField tFatherCnic;
    private JLabel ageLabel;
    private JTextField tage;
    private JLabel licenseLabel;
    private JComboBox<String> licenseType;
    private JLabel bloodGroupLabel;
    private JComboBox<String> bloodGroupComboBox;
    private JLabel res;
    private JTextArea resadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;

    private String dates[] = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[] = {"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[] = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023"};

    private String licenseTypes[] = {"M/CYCLE", "CARJEEP", "LTV", "HTV"};
    private String bloodGroup[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    public User() {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(400, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        Fathername = new JLabel("FatherName:");
        Fathername.setFont(new Font("Arial", Font.PLAIN, 14));
        Fathername.setSize(120, 20);
        Fathername.setLocation(100, 150);
        c.add(Fathername);

        JTextField tFathername = new JTextField();
        tFathername.setFont(new Font("Arial", Font.PLAIN, 15));
        tFathername.setSize(190, 20);
        tFathername.setLocation(200, 150);
        c.add(tFathername);

        cnicLabel = new JLabel("FATHER CNIC");
        cnicLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cnicLabel.setSize(120, 20);
        cnicLabel.setLocation(100, 200);
        c.add(cnicLabel);

        tFatherCnic = new JTextField();
        tFatherCnic.setFont(new Font("Arial", Font.PLAIN, 15));
        tFatherCnic.setSize(190, 20);
        tFatherCnic.setLocation(200, 200);
        c.add(tFatherCnic);

        ageLabel = new JLabel("Age");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ageLabel.setSize(120, 20);
        ageLabel.setLocation(100, 250);
        c.add(ageLabel);

        tage = new JTextField();
        tage.setFont(new Font("Arial", Font.PLAIN, 15));
        tage.setSize(190, 20);
        tage.setLocation(200, 250);
        c.add(tage);

        mno = new JLabel("Mobile");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(100, 300);
        c.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(150, 20);
        tmno.setLocation(200, 300);
        c.add(tmno);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 350);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 350);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 350);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 400);
        c.add(dob);

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 400);
        c.add(date);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 400);
        c.add(month);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(320, 400);
        c.add(year);

        bloodGroupLabel = new JLabel("Blood G:");
        bloodGroupLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bloodGroupLabel.setSize(150, 20);
        bloodGroupLabel.setLocation(100, 450);
        c.add(bloodGroupLabel);

        bloodGroupComboBox = new JComboBox<>(bloodGroup);
        bloodGroupComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        bloodGroupComboBox.setSize(120, 20);
        bloodGroupComboBox.setLocation(250, 450);
        c.add(bloodGroupComboBox);

        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 500);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(200, 75);
        tadd.setLocation(200, 500);
        tadd.setLineWrap(true);
        c.add(tadd);

        licenseLabel = new JLabel("License Type");
        licenseLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        licenseLabel.setSize(150, 20);
        licenseLabel.setLocation(100, 600);
        c.add(licenseLabel);

        licenseType = new JComboBox<>(licenseTypes);
        licenseType.setFont(new Font("Arial", Font.PLAIN, 15));
        licenseType.setSize(120, 20);
        licenseType.setLocation(250, 600);
        c.add(licenseType);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 650);
        c.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 700);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 700);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(tout);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(300, 400);
        scrollPane.setLocation(500, 100);
        c.add(scrollPane);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 750);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        JScrollPane scrolPane = new JScrollPane(c);
        scrolPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scrolPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (term.isSelected()) {
                String data1;
                String data
                        = "Name : "
                        + tname.getText() + "\n"
                        + "FatherName : "
                        + Fathername.getText() + "\n"
                        + "Father CNIC : "
                        + tFatherCnic.getText() + "\n"
                        + "Mobile : "
                        + tmno.getText() + "\n"
                        + "Age : "
                        + tage.getText() + "\n"
                        + "Gender : "
                        + (male.isSelected() ? "Male" : "Female") + "\n"
                        + "DOB : "
                        + (String) date.getSelectedItem()
                        + "/" + (String) month.getSelectedItem()
                        + "/" + (String) year.getSelectedItem()
                        + "\n"
                        + "License Type : "
                        + (String) licenseType.getSelectedItem() + "\n"
                        + "Blood Group : "
                        + (String) bloodGroupComboBox.getSelectedItem() + "\n";

                String data2 = "Address : " + tadd.getText();
                tout.setText(data + data2);
                tout.setEditable(false);
                res.setText("Registration Successfully..");
            } else {
                tout.setText("");
                resadd.setText("");
                res.setText("Please accept the"
                        + " terms & conditions..");
            }
        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            Fathername.setText(def);
            tFatherCnic.setText(def);
            tmno.setText(def);
            tage.setText(def);
            res.setText(def);
            tout.setText(def);
            term.setSelected(false);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            licenseType.setSelectedIndex(0);
            bloodGroupComboBox.setSelectedIndex(0);
            resadd.setText(def);
        }
    }

    public static void main(String[] args) {
        User userForm = new User();
    }
}
