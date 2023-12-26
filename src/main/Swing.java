package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Swing extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    JTable table1 = new JTable(model);
    int selected = 0;
    int rankA=0, rankB=0, rankC=0, rankD=0, rankF=0;
    public Swing(){
        setTitle("2019E7009 구동엽 기말과제");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        panel3.setLayout(new GridLayout(8,1,5,10));

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

        JButton btnModify = new JButton("회원 수정");
        btnModify.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btnModify);

        JButton btnAverge = new JButton("과목 평균");
        btnAverge.setFont(new Font("고딕", Font.BOLD, 15));
        panel3.add(btnAverge);


        add(panel3, BorderLayout.EAST);
        //end panel3

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1,8,5,5));


        JTextField searchTextField = new JTextField(10);
        panel4.add(searchTextField);

        JButton btnSearch = new JButton("회원 검색");
        btnSearch.setFont(new Font("고딕", Font.BOLD, 15));
        panel4.add(btnSearch);

        JButton btnDel = new JButton("회원 삭제");
        btnDel.setFont(new Font("고딕", Font.BOLD, 15));
        panel4.add(btnDel);

        JButton btnGrp = new JButton("등급 분표도");
        btnGrp.setFont(new Font("고딕", Font.BOLD, 15));
        panel4.add(btnGrp);

        JLabel lblSID = new JLabel("작성자 : 구동엽(2019E7009)");
        lblSID.setFont(new Font("고딕", Font.BOLD, 20));
        panel4.add(lblSID);

        add(panel4, BorderLayout.SOUTH);



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

                int score1 = Integer.parseInt(txtSCORE1.getText());
                int score2 = Integer.parseInt(txtSCORE2.getText());
                int score3 = Integer.parseInt(txtSCORE3.getText());

                int total = score1 + score2 + score3;
                double average = total / 3.0;

                table1.setValueAt(total, R, 5);
                table1.setValueAt(average, R, 6);

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

                table1.setValueAt(grade, R, 7);
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
                        writer.println("학번             이름   전공     교양    시사     합계     평균                   등급     석차");
                        for (int row = 0; row < table1.getRowCount(); row++) {
                            for (int col = 0; col < table1.getColumnCount(); col++) {
                                Object value = table1.getValueAt(row, col);
                                writer.print(value + "\t");
                            }
                            writer.println();
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

                Arrays.sort(tableData, new Comparator<Object[]>() {
                    @Override
                    public int compare(Object[] row1, Object[] row2) {
                        String name1 = (String) row1[1];
                        String name2 = (String) row2[1];
                        return name1.compareTo(name2);
                    }
                });

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
                        writer.println("학번             이름   전공     교양    시사     합계     평균                   등급     석차");
                        for (int row = 0; row < table1.getRowCount(); row++) {
                            for (int col = 0; col < table1.getColumnCount(); col++) {
                                Object value = table1.getValueAt(row, col);
                                writer.print(value + "\t");
                            }
                            writer.println();
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

                StringBuilder htmlContent = new StringBuilder();
                htmlContent.append("<html><body><table>");

                htmlContent.append("<tr>");
                for (int col = 0; col < colCount; col++) {
                    htmlContent.append("<th>").append(model.getColumnName(col)).append("</th>");
                }
                htmlContent.append("</tr>");

                for (int row = 0; row < rowCount; row++) {
                    htmlContent.append("<tr>");
                    for (int col = 0; col < colCount; col++) {
                        htmlContent.append("<td>").append(tableData[row][col]).append("</td>");
                    }
                    htmlContent.append("</tr>");
                }

                htmlContent.append("</table></body></html>");

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
                int rowCount = model.getRowCount();
                int columnCount = model.getColumnCount();
                String[] columnNames = new String[columnCount];
                Object[][] tableData = new Object[rowCount][columnCount];

                for (int col = 0; col < columnCount; col++) {
                    columnNames[col] = model.getColumnName(col);
                }

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                        tableData[row][col] = model.getValueAt(row, col);
                    }
                }

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
                        for (int col = 0; col < columnCount; col++) {
                            writer.print(columnNames[col]);
                            if (col < columnCount - 1) {
                                writer.print(",");
                            }
                        }
                        writer.println();

                        for (int row = 0; row < rowCount; row++) {
                            for (int col = 0; col < columnCount; col++) {
                                writer.print(tableData[row][col]);
                                if (col < columnCount - 1) {
                                    writer.print(",");
                                }
                            }
                            writer.println();
                        }

                        JOptionPane.showMessageDialog(null, "CSV 파일이 성공적으로 저장되었습니다.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "CSV 파일 저장 중 오류가 발생했습니다.");
                    }
                }
            }
        });

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                table1.setRowSorter(sorter);

                ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
                int columnIndexToSort = 0;
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

                sorter.setSortKeys(sortKeys);
                sorter.sort();
            }
        });

        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow != -1) {
                    Object[] rowData = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        rowData[i] = table1.getValueAt(selectedRow, i);
                    }

                    editDataDialog(rowData, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "편집할 행을 선택하세요.");
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchTextField.getText().trim().toLowerCase();

                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "검색어를 입력하세요.");
                    return;
                }

                boolean found = false;
                for (int row = 0; row < table1.getRowCount(); row++) {
                    String name = table1.getValueAt(row, 1).toString().toLowerCase(); // Assuming the 2nd column is for names
                    if (name.contains(searchText)) {
                        // Select all rows that match the search
                        selected++;
                        table1.addRowSelectionInterval(row, row);
                        table1.scrollRectToVisible(table1.getCellRect(row, 0, true));
                        found = true;
                        System.out.println(selected);
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "검색어와 일치하는 학생을 찾을 수 없습니다.");
                }
            }
        });


        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table1.getSelectedRows();

                if (selected > 1) {
                    String studentIDToDelete = JOptionPane.showInputDialog(null, "삭제할 학번을 입력하세요:");

                    if (studentIDToDelete != null && !studentIDToDelete.isEmpty()) {
                        for (int row = table1.getRowCount() - 1; row >= 0; row--) {
                            String currentStudentID = table1.getValueAt(row, 0).toString(); // Assuming the 1st column is for student ID
                            if (currentStudentID.equals(studentIDToDelete)) {
                                ((DefaultTableModel) table1.getModel()).removeRow(row);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "학번을 입력하세요.");
                    }
                } else if (selected == 1) {
                    ((DefaultTableModel) table1.getModel()).removeRow(selectedRows[0]);
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 항목을 선택하세요.");
                }
                selected =0;
            }
        });

        btnGrp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGradeCounts();
                showGradeGraph();
            }
        });



        btnRank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                int[] ranks = new int[rowCount];

                for (int i = 0; i < rowCount; i++) {
                    ranks[i] = 1;
                }

                for (int i = 0; i < rowCount; i++) {
                    double currentAverage = (double) table1.getValueAt(i, 6);

                    for (int j = 0; j < rowCount; j++) {
                        if (i != j) {
                            double otherAverage = (double) table1.getValueAt(j, 6);

                            if (currentAverage < otherAverage) {
                                ranks[i]++;
                            }
                        }
                    }
                }

                for (int i = 0; i < rowCount; i++) {
                    table1.setValueAt(ranks[i], i, 8);
                }
            }
        });

        btnAverge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();

                if (rowCount > 0) {
                    int sumMajor = 0;
                    int sumGeneral = 0;
                    int sumCurrentAffairs = 0;

                    for (int row = 0; row < rowCount; row++) {
                        sumMajor += Integer.parseInt(table1.getValueAt(row, 2).toString());
                        sumGeneral += Integer.parseInt(table1.getValueAt(row, 3).toString());
                        sumCurrentAffairs += Integer.parseInt(table1.getValueAt(row, 4).toString());
                    }

                    double averageMajor = (double) sumMajor / rowCount;
                    double averageGeneral = (double) sumGeneral / rowCount;
                    double averageCurrentAffairs = (double) sumCurrentAffairs / rowCount;

                    JOptionPane.showMessageDialog(null, "전공 평균: " + averageMajor + "\n교양 평균: " + averageGeneral + "\n시사 평균: " + averageCurrentAffairs);
                } else {
                    JOptionPane.showMessageDialog(null, "테이블에 데이터가 없습니다.");
                }
            }
        });


        setSize(1000,500);
        setVisible(true);
    }


    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionMenu = new JMenu("Option");
        JMenu changeMenu = new JMenu("Change");

        JMenuItem openDataItem = new JMenuItem("데이터 불러오기");
        JMenuItem saveDataItem = new JMenuItem("데이터 저장");
        JMenuItem newDataItem = new JMenuItem("새 파일");
        JMenuItem exitDataItem = new JMenuItem("종료");


        optionMenu.add(newDataItem);
        optionMenu.addSeparator();
        optionMenu.add(openDataItem);
        optionMenu.addSeparator();
        optionMenu.add(saveDataItem);
        optionMenu.addSeparator();
        optionMenu.add(exitDataItem);


        openDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt", "csv");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(Swing.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                        model.setRowCount(0);

                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split("\t"); // 탭으로 데이터 분리
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

                        for (int row = 0; row < table1.getRowCount(); row++) {
                            for (int col = 0; col < table1.getColumnCount(); col++) {
                                Object value = table1.getValueAt(row, col);
                                writer.print(value + "\t");
                            }
                            writer.println();
                        }

                        JOptionPane.showMessageDialog(null, "파일이 성공적으로 저장되었습니다.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
                    }
                }
            }
        });



        menuBar.add(optionMenu);
        menuBar.add(changeMenu);
        setJMenuBar(menuBar);
    }

    private void editDataDialog(Object[] rowData, int selectedRow) {
        JDialog editDialog = new JDialog(this, "데이터 수정", true);
        editDialog.setLayout(new GridLayout(0, 2));

        JTextField[] textFields = new JTextField[model.getColumnCount()];
        for (int i = 0; i < model.getColumnCount(); i++) {
            JLabel label = new JLabel(model.getColumnName(i));
            textFields[i] = new JTextField(rowData[i].toString());
            editDialog.add(label);
            editDialog.add(textFields[i]);
        }

        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < model.getColumnCount(); i++) {
                    String newText = textFields[i].getText();
                    table1.setValueAt(newText, selectedRow, i);
                }

                int score1 = Integer.parseInt(table1.getValueAt(selectedRow, 2).toString());
                int score2 = Integer.parseInt(table1.getValueAt(selectedRow, 3).toString());
                int score3 = Integer.parseInt(table1.getValueAt(selectedRow, 4).toString());

                int total = score1 + score2 + score3;
                double average = total / 3.0;

                table1.setValueAt(total, selectedRow, 5);
                table1.setValueAt(average, selectedRow, 6);

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

                table1.setValueAt(grade, selectedRow, 7);

                editDialog.dispose();
            }
        });
        editDialog.add(confirmButton);

        editDialog.setSize(400, 200);
        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    private void calculateGradeCounts() {
        for (int row = 0; row < table1.getRowCount(); row++) {
            String grade = table1.getValueAt(row, 7).toString();
            switch (grade) {
                case "A":
                    rankA++;
                    break;
                case "B":
                    rankB++;
                    break;
                case "C":
                    rankC++;
                    break;
                case "D":
                    rankD++;
                    break;
                case "F":
                    rankF++;
                    break;
            }
        }
    }

    class MyDialog extends JDialog {
        public MyDialog() {
            setTitle("Grade Distribution");
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setSize(400, 350);

            JPanel graphPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    int barWidth = 25;

                    for(int i=0; i<12; i++){
                        g.drawString(i + " ", 25, 255-(20*i));
                        g.drawLine(50,250-(20*i),350,250-(20*i));
                    }

                    g.drawString("F",105,265);
                    g.drawString("D",155,265);
                    g.drawString("C",205,265);
                    g.drawString("B",255,265);
                    g.drawString("A",305,265);

                    g.setColor(Color.BLUE);
                    drawBar(g, barWidth, rankF, 100);
                    g.setColor(Color.YELLOW);
                    drawBar(g, barWidth, rankD, 150);
                    g.setColor(Color.GREEN);
                    drawBar(g, barWidth, rankC, 200);
                    g.setColor(Color.RED);
                    drawBar(g, barWidth, rankB, 250);
                    g.setColor(Color.MAGENTA);
                    drawBar(g, barWidth, rankA, 300);
                }

                private void drawBar(Graphics g, int barWidth, int count, int x) {
                    int barHeight = count * 20;
                    g.fillRect(x, 250 - barHeight, barWidth, barHeight);
                }
            };


            add(graphPanel);
        }
    }

    private void showGradeGraph() {
        MyDialog dialog = new MyDialog();
        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        new Swing();
    }
}
