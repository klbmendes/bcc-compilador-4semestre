import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class InterfaceCompilador extends JFrame {
    private JLabel labelStatus;

    public InterfaceCompilador() {
        setTitle("Compilador");
        setSize(1500, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        FlowLayout layoutSemEspaco = new FlowLayout(FlowLayout.LEFT, 0, 0);
        JPanel barraFerramentas = new JPanel(layoutSemEspaco);
        barraFerramentas.setPreferredSize(new Dimension(1500, 70));
        barraFerramentas.setBackground(Color.LIGHT_GRAY);

        String[] nomesArquivos = { "new", "open", "save", "copy", "paste", "cutting", "compile", "equipe" };
        String[] labels = { "Novo [ctrl-n]", "Abrir [ctrl-o]", "Salvar [ctrl-s]", "Copiar [ctrl-c]",
                "Colar [ctrl-v]", "Recortar [ctrl-x]", "Compilar [F7]", "Equipe [F1]" };

       for (int i = 0; i < labels.length; i++) {
    java.net.URL imgURL = getClass().getResource("/icons/" + nomesArquivos[i] + ".png");
    
    ImageIcon icone;
    if (imgURL != null) {
        icone = new ImageIcon(imgURL);
    } else {
        System.err.println("ERRO: Recurso não encontrado: /icons/" + nomesArquivos[i] + ".png");
        icone = new ImageIcon(); 
        }

    JButton btn = new JButton(labels[i], icone);
    
    btn.setPreferredSize(new Dimension(110, 70));
    btn.setMargin(new Insets(0, 0, 0, 0));
    btn.setFocusable(false);
    btn.setVerticalTextPosition(SwingConstants.BOTTOM);
    btn.setHorizontalTextPosition(SwingConstants.CENTER);
    btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    //btn.setFont(new Font("Arial", Font.PLAIN, 10));

    barraFerramentas.add(btn);
}

        add(barraFerramentas, BorderLayout.NORTH);

        JTextArea editor = new JTextArea();
        JTextArea numLinhas = new JTextArea("1  ");
        numLinhas.setBackground(new Color(230, 230, 230));
        numLinhas.setEditable(false);
        numLinhas.setFocusable(false);

        JScrollPane scrollEditor = new JScrollPane(editor, 22, 32);
        scrollEditor.setRowHeaderView(numLinhas);

        JTextArea mensagens = new JTextArea();
        mensagens.setEditable(false);
        JScrollPane scrollMensagens = new JScrollPane(mensagens, 22, 32);

        JSplitPane divisor = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollEditor, scrollMensagens);
        divisor.setDividerLocation(500);
        divisor.setOneTouchExpandable(true);
        add(divisor, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());

        JPanel barraStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraStatus.setPreferredSize(new Dimension(1500, 25));
        barraStatus.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        labelStatus = new JLabel("pasta\\nome do arquivo");
        barraStatus.add(labelStatus);

        painelInferior.add(barraStatus, BorderLayout.SOUTH);
        add(painelInferior, BorderLayout.SOUTH);

        editor.addCaretListener(e -> {
            int total = Math.max(1, editor.getLineCount());
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= total; i++)
                sb.append(i).append("\n");
            numLinhas.setText(sb.toString());
        });
    }


  private void processarComando(String comando) {
        if (comando.equals("equipe")) {
            exibirEquipe();
        } else if (comando.equals("compile")) {
            JOptionPane.showMessageDialog(this, "Compilando o código...");
        } else {
            System.out.println("Botão pressionado: " + comando);
        }
    }

    private void exibirEquipe() {
        JOptionPane.showMessageDialog(this, 
            "Equipe:\n- Karina 1\n- Yuri", 
            "Equipe [F1]", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // MÉTODO PARA ATALHOS F1 E F7
    private void configurarAtalhos() {
        // F1 - Equipe
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("F1"), "equipeAction");
        this.getRootPane().getActionMap().put("equipeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { exibirEquipe(); }
        });

        // F7 - Compilar
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("F7"), "compileAction");
        this.getRootPane().getActionMap().put("compileAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                processarComando("compile"); 
            }
        });
    }

}
