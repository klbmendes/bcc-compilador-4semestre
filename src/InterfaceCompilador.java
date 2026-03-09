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
        // JPanel barraFerramentas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraFerramentas.setPreferredSize(new Dimension(1500, 70));
        barraFerramentas.setBackground(Color.LIGHT_GRAY);

        String[] nomesArquivos = { "novo", "open", "save", "files", "paste", "cutting", "compile", "partners" };
        String[] labels = { "Novo [ctrl-n]", "Abrir [ctrl-o]", "Salvar [ctrl-s]", "Copiar [ctrl-c]",
                "Colar [ctrl-v]", "Recortar [ctrl-x]", "Compilar [F7]", "Equipe [F1]" };

        for (int i = 0; i < labels.length; i++) {
            String path = "src/icons/" + nomesArquivos[i] + ".png";

            ImageIcon icone = new ImageIcon(path);

            
            if (icone.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.err.println("ERRO: Não foi possível encontrar o ícone em: " + path);
                // Se falhar com "src/icons/", tente carregar apenas com "icons/"
                icone = new ImageIcon("icons/" + nomesArquivos[i] + ".png");
            }

            JButton btn = new JButton(labels[i], icone);

            // Estética idêntica à Figura 1
            btn.setPreferredSize(new Dimension(110, 70));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Ajuste de fonte para o texto caber (opcional)
            btn.setFont(new Font("calibri", Font.PLAIN, 10));

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
}