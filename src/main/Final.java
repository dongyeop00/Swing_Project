package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Final {
    public Final(){
        JFrame myGUI4 = new JFrame();
        myGUI4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGUI4.setLocationRelativeTo(null);

        myGUI4.setSize(700, 500);
        myGUI4.setTitle("구동엽의 네번째 GUI");

        //start panel1
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Top Field"));
        myGUI4.add(panel1, BorderLayout.NORTH);

        panel1.add(new JLabel("학번:"));
        JTextField txtSID = new JTextField(6);
        txtSID.setFont(new Font("고딕", Font.BOLD, 15));
        panel1.add(txtSID);

        panel1.add(new JLabel("이름:"));
        JTextField txtNAME = new JTextField(5);
        txtNAME.setFont(new Font("고딕", Font.BOLD, 15));
        panel1.add(txtNAME);

        panel1.add(new JLabel("전공:"));
        JTextField txtSCORE1 = new JTextField(3);
        txtSCORE1.setFont(new Font("고딕", Font.BOLD, 15));
        panel1.add(txtSCORE1);

        panel1.add(new JLabel("교양:"));
        JTextField txtSCORE2 = new JTextField(3);
        txtSCORE2.setFont(new Font("고딕", Font.BOLD, 15));
        panel1.add(txtSCORE2);

        panel1.add(new JLabel("시사:"));
        JTextField txtSCORE3 = new JTextField(3);
        txtSCORE3.setFont(new Font("고딕", Font.BOLD,15));
        panel1.add(txtSCORE3);

        JButton btnINSERT = new JButton("INSERT");
        btnINSERT.setFont(new Font("고딕", Font.BOLD, 13));
        btnINSERT.setBackground(Color.green);
        panel1.add(btnINSERT);

        JButton btnDELETE = new JButton("DELETE");
        btnDELETE.setFont(new Font("고딕", Font.BOLD, 13));
        btnDELETE.setBackground(Color.red);
        panel1.add(btnDELETE);
        //end panel1

        //start panel2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 1, 5, 10));

        JButton btn1 = new JButton("행 추가");
        btn1.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn1);

        JButton btn2 = new JButton("행 삭제");
        btn2.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn2);

        JButton btn3 = new JButton("처리내용-3");
        btn3.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn3);

        JButton btn4 = new JButton("처리내용-4");
        btn4.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn4);

        JButton btn5 = new JButton("처리내용-5");
        btn5.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn5);

        JButton btn6 = new JButton("처리내용-6");
        btn6.setFont(new Font("고딕", Font.BOLD, 15));
        panel2.add(btn6);

        myGUI4.add(panel2, BorderLayout.WEST);
        //end panel2

        //start panel3
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(6,1,5,10));

        JButton btn7 = new JButton("처리내용-7");
        btn7.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn7);

        JButton btn8 = new JButton("TXT로 저장");
        btn8.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn8);

        JButton btn9 = new JButton("처리내용-9");
        btn9.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn9);

        JButton btn10 = new JButton("처리내용-10");
        btn10.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn10);

        JButton btn11 = new JButton("처리내용-11");
        btn11.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn11);

        JButton btn12 = new JButton("처리내용-12");
        btn12.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn12);

        myGUI4.add(panel3, BorderLayout.EAST);
        //end panel3

        JLabel lblSID = new JLabel("작성자 : 구동엽(2019E7009)");
        lblSID.setFont(new Font("고딕", Font.BOLD, 20));

        myGUI4.add(lblSID, BorderLayout.SOUTH);


        String header[] = { "학번", "이름", "전공", "교양", "시사", "합계", "평가" };
        DefaultTableModel model = new DefaultTableModel();
        JTable table1 = new JTable(model);
        for (int C = 0; C < header.length; C++) {
            model.addColumn(header[C]);
        }
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setLocation(10, 50);
        scrollPane.setSize(300, 200);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Score Table"));
        myGUI4.add(scrollPane, BorderLayout.CENTER);

        //panel1 Listener
        //Insert, Delete
        btnINSERT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int R;
                model.addRow(new Object[]{});

                R = table1.getRowCount() - 1;
                table1.setValueAt(txtSID.getText(), R, 0);
                table1.setValueAt(txtNAME.getText(), R, 1);
                table1.setValueAt(txtSCORE1.getText(), R, 2);
                table1.setValueAt(txtSCORE2.getText(), R, 3);
                table1.setValueAt(txtSCORE3.getText(), R, 4);

                // Calculate total and average
                int score1 = Integer.parseInt(txtSCORE1.getText());
                int score2 = Integer.parseInt(txtSCORE2.getText());
                int score3 = Integer.parseInt(txtSCORE3.getText());

                int total = score1 + score2 + score3;
                double average = total / 3.0;

                table1.setValueAt(total, R, 5);
                table1.setValueAt(average, R, 6);
            }
        });

        btnDELETE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] rows = table1.getSelectedRows();
                for (int x = 0; x < rows.length; x++) {
                    model.removeRow(rows[x] - x);
                }
            }
        });

        //panel2 button listener
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[] {});
            }
        });

        // btn2에 대한 ActionListener 추가
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputName = JOptionPane.showInputDialog("삭제할 이름을 입력하세요:");

                if (inputName != null && !inputName.isEmpty()) {
                    boolean isDuplicate = false;
                    ArrayList<Integer> duplicateRows = new ArrayList<>();

                    // 중복 검사
                    for (int row = 0; row < model.getRowCount(); row++) {
                        String nameInRow = (String) model.getValueAt(row, 1); // 이름이 두 번째 열에 저장되어 있다고 가정
                        if (nameInRow != null && nameInRow.equals(inputName)) {
                            duplicateRows.add(row);
                            isDuplicate = true;
                        }
                    }

                    if (isDuplicate) {
                        String inputID = JOptionPane.showInputDialog("학번을 입력하세요:");
                        if (inputID != null && !inputID.isEmpty()) {
                            // 학번을 입력받아 해당하는 행 삭제
                            for (int row : duplicateRows) {
                                String idInRow = (String) model.getValueAt(row, 0); // 학번이 첫 번째 열에 저장되어 있다고 가정
                                if (idInRow != null && idInRow.equals(inputID)) {
                                    model.removeRow(row);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "학번을 입력하세요.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "입력한 이름이 존재하지 않습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
                }
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt", "csv");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                }
            }
        });

        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
                    }

                    try (PrintWriter writer = new PrintWriter(selectedFile)) {
                        // 입력 필드의 값도 함께 저장
                        writer.println("학번             이름   전공     교양    시사     합계     평균");

                        // 파일에 테이블 데이터를 저장합니다.
                        for (int row = 0; row < table1.getRowCount(); row++) {
                            for (int col = 0; col < table1.getColumnCount(); col++) {
                                Object value = table1.getValueAt(row, col);
                                writer.print(value + "\t");
                            }
                            writer.println(); // 다음 행으로 이동
                        }

                        JOptionPane.showMessageDialog(null, "파일이 성공적으로 저장되었습니다.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
                    }
                }
            }
        });


        myGUI4.setVisible(true);
    }



    public static void main(String[] args) {
        new Final();
    }
}

