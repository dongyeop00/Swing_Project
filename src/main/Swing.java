package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Swing extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    JTable table1 = new JTable(model);
    public Swing(){
        setTitle("2019E7009 구동엽 기말과제");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();

        //start panel1
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Top Field"));
        add(panel1, BorderLayout.NORTH);

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


        //start panel3
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(6,1,5,10));

        JButton btn1 = new JButton("합계, 평균, 등급, 석차");
        btn1.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn1);

        JButton btn2 = new JButton("이름 순서");
        btn2.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn2);

        JButton btn3 = new JButton("HTML");
        btn3.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn3);

        JButton btn4 = new JButton("CSV");
        btn4.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn4);

        JButton btn5 = new JButton("학번 순서");
        btn5.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btn5);

        JButton btnRank = new JButton("석차 계산");
        btnRank.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btnRank);


        add(panel3, BorderLayout.EAST);
        //end panel3

        JLabel lblSID = new JLabel("작성자 : 구동엽(2019E7009)");
        lblSID.setFont(new Font("고딕", Font.BOLD, 20));

        add(lblSID, BorderLayout.SOUTH);


        String header[] = { "학번", "이름", "전공", "교양", "시사", "합계", "평균", "등급", "석차"};

        for (int C = 0; C < header.length; C++) {
            model.addColumn(header[C]);
        }
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setLocation(10, 50);
        scrollPane.setSize(300, 200);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Score Table"));
        add(scrollPane, BorderLayout.CENTER);



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

                // Determine the grade based on the average
                String grade;
                if (average >= 90) {
                    grade = "A";
                } else if (average >= 80) {
                    grade = "B";
                } else if (average >= 70) {
                    grade = "C";
                } else if (average >= 60) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                // Add the grade to the table
                table1.setValueAt(grade, R, 7); // Assuming the 7th column is for grades
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

        /*
        //panel2 button listener
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[] {});
            }
        });

         */

        /*
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

         */
        btn1.addActionListener(new ActionListener() {
            @Override
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
                        writer.println("학번             이름   전공     교양    시사     합계     평균                   등급     석차");
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

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the table
                int rowCount = model.getRowCount();
                Object[][] tableData = new Object[rowCount][model.getColumnCount()];

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt");
                fileChooser.setFileFilter(filter);

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        tableData[row][col] = model.getValueAt(row, col);
                    }
                }

                // Sort the data by the second column (name)
                Arrays.sort(tableData, new Comparator<Object[]>() {
                    @Override
                    public int compare(Object[] row1, Object[] row2) {
                        String name1 = (String) row1[1];
                        String name2 = (String) row2[1];
                        return name1.compareTo(name2);
                    }
                });

                // Update the table with the sorted data
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        model.setValueAt(tableData[row][col], row, col);
                    }
                }

                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
                    }

                    try (PrintWriter writer = new PrintWriter(selectedFile)) {
                        // 입력 필드의 값도 함께 저장
                        writer.println("학번             이름   전공     교양    시사     합계     평균                   등급     석차");
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

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the table
                int rowCount = model.getRowCount();
                int colCount = model.getColumnCount();
                Object[][] tableData = new Object[rowCount][colCount];
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        tableData[row][col] = model.getValueAt(row, col);
                    }
                }

                // Create an HTML string with table tags
                StringBuilder htmlContent = new StringBuilder();
                htmlContent.append("<html><body><table>");

                // Add table headers
                htmlContent.append("<tr>");
                for (int col = 0; col < colCount; col++) {
                    htmlContent.append("<th>").append(model.getColumnName(col)).append("</th>");
                }
                htmlContent.append("</tr>");

                // Add table data
                for (int row = 0; row < rowCount; row++) {
                    htmlContent.append("<tr>");
                    for (int col = 0; col < colCount; col++) {
                        htmlContent.append("<td>").append(tableData[row][col]).append("</td>");
                    }
                    htmlContent.append("</tr>");
                }

                // Close HTML tags
                htmlContent.append("</table></body></html>");

                // Save HTML content to a file
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML 파일", "html");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().toLowerCase().endsWith(".html")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".html");
                    }

                    try (PrintWriter writer = new PrintWriter(selectedFile)) {
                        writer.println(htmlContent.toString());
                        JOptionPane.showMessageDialog(null, "HTML 파일이 성공적으로 저장되었습니다.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "HTML 파일 저장 중 오류가 발생했습니다.");
                    }
                }

                // Display HTML content in a new window
                JFrame htmlFrame = new JFrame("HTML Output");
                JEditorPane editorPane = new JEditorPane();
                editorPane.setContentType("text/html");
                editorPane.setEditable(false);
                editorPane.setText(htmlContent.toString());

                JScrollPane scrollPane = new JScrollPane(editorPane);
                htmlFrame.getContentPane().add(scrollPane);

                htmlFrame.setSize(800, 600);
                htmlFrame.setVisible(true);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the table
                int rowCount = model.getRowCount();
                int columnCount = model.getColumnCount();
                String[] columnNames = new String[columnCount];
                Object[][] tableData = new Object[rowCount][columnCount];

                // Get column names
                for (int col = 0; col < columnCount; col++) {
                    columnNames[col] = model.getColumnName(col);
                }

                // Get table data
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                        tableData[row][col] = model.getValueAt(row, col);
                    }
                }

                // Save CSV content to a file
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV 파일", "csv");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".csv");
                    }

                    try (PrintWriter writer = new PrintWriter(selectedFile)) {
                        // Write column names to CSV file
                        for (int col = 0; col < columnCount; col++) {
                            writer.print(columnNames[col]);
                            if (col < columnCount - 1) {
                                writer.print(",");
                            }
                        }
                        writer.println(); // Move to the next line

                        // Write table data to CSV file
                        for (int row = 0; row < rowCount; row++) {
                            for (int col = 0; col < columnCount; col++) {
                                writer.print(tableData[row][col]);
                                if (col < columnCount - 1) {
                                    writer.print(",");
                                }
                            }
                            writer.println(); // Move to the next line
                        }

                        JOptionPane.showMessageDialog(null, "CSV 파일이 성공적으로 저장되었습니다.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "CSV 파일 저장 중 오류가 발생했습니다.");
                    }
                }
            }
        });




        btnRank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate rank based on average
                int rowCount = model.getRowCount();
                int[] ranks = new int[rowCount];

                // Initialize ranks array with 1
                for (int i = 0; i < rowCount; i++) {
                    ranks[i] = 1;
                }

                // Compare averages to determine rank
                for (int i = 0; i < rowCount; i++) {
                    double currentAverage = (double) table1.getValueAt(i, 6); // Assuming the 6th column is for averages

                    for (int j = 0; j < rowCount; j++) {
                        if (i != j) {
                            double otherAverage = (double) table1.getValueAt(j, 6); // Assuming the 6th column is for averages

                            if (currentAverage < otherAverage) {
                                ranks[i]++;
                            }
                        }
                    }
                }

                // Add ranks to the table
                for (int i = 0; i < rowCount; i++) {
                    table1.setValueAt(ranks[i], i, 8); // Assuming the 7th column is for ranks
                }
            }
        });








        setSize(700,500);
        setVisible(true);
    }


    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Option");

        JMenuItem openDataItem = new JMenuItem("데이터 불러오기");
        JMenuItem saveDataItem = new JMenuItem("데이터 저장");
        JMenuItem newDataItem = new JMenuItem("새 파일");
        JMenuItem exitDataItem = new JMenuItem("종료");

        menu.add(newDataItem);
        menu.addSeparator();
        menu.add(openDataItem);
        menu.addSeparator();
        menu.add(saveDataItem);
        menu.addSeparator();
        menu.add(exitDataItem);

        openDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt", "csv");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(Swing.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                        // 테이블 초기화
                        model.setRowCount(0);

                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split("\t"); // 탭으로 데이터 분리
                            // 테이블 순서에 맞게 데이터 추가
                            model.addRow(new Object[]{
                                    data[0], // 학번
                                    data[1], // 이름
                                    data[2], // 전공
                                    data[3], // 교양
                                    data[4], // 시사
                                    data[5], // 합계
                                    Double.parseDouble(data[6]), // 평균 (또는 평가, 이 부분에 따라서 수정)
                                    data[7], // 등급
                                    ""      // 석차 초기값으로 빈 문자열 추가
                            });
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Swing.this, "파일을 읽는 도중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });




        newDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0); // 테이블 초기화
            }
        });

        exitDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 프로그램 종료
            }
        });

        saveDataItem.addActionListener(new ActionListener() {
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

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }


    public static void main(String[] args) {
        new Swing();
    }
}
