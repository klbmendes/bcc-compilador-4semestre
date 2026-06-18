import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import pckanalisador.Constants;
import pckanalisador.LexicalError;
import pckanalisador.Lexico;
import pckanalisador.SemanticError;
import pckanalisador.Semantico;
import pckanalisador.Sintatico;
import pckanalisador.SyntaticError;
import pckanalisador.Token;

public class InterfaceCompilador extends JFrame {

    private JTextArea editor;
    private JTextArea mensagens;
    private JLabel labelStatus;
    private File arquivoAtual = null;

    public InterfaceCompilador() {
        setTitle("Compilador");
        setSize(1500, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Barra de ferramentas
        JPanel barraFerramentas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        barraFerramentas.setPreferredSize(new Dimension(1500, 70));
        barraFerramentas.setBackground(Color.LIGHT_GRAY);

        String[] arquivos = { "new", "open", "save", "copy", "paste", "cutting", "compile", "equipe" };
        String[] labels = { "Novo [ctrl-n]", "Abrir [ctrl-o]", "Salvar [ctrl-s]",
                "Copiar [ctrl-c]", "Colar [ctrl-v]", "Recortar [ctrl-x]",
                "Compilar [F7]", "Equipe [F1]" };

        JButton[] botoes = new JButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            java.net.URL imgURL = getClass().getResource("/icons/" + arquivos[i] + ".png");
            ImageIcon icone = (imgURL != null) ? new ImageIcon(imgURL) : new ImageIcon();

            JButton btn = new JButton(labels[i], icone);
            btn.setPreferredSize(new Dimension(110, 70));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            barraFerramentas.add(btn);
            botoes[i] = btn;
        }
        add(barraFerramentas, BorderLayout.NORTH);

        // Editor
        editor = new JTextArea();

        JTextArea numLinhas = new JTextArea("1");
        numLinhas.setBackground(new Color(230, 230, 230));
        numLinhas.setEditable(false);
        numLinhas.setFocusable(false);

        JScrollPane scrollEditor = new JScrollPane(editor,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollEditor.setRowHeaderView(numLinhas);

        // Área de mensagens
        mensagens = new JTextArea();
        mensagens.setEditable(false);
        JScrollPane scrollMensagens = new JScrollPane(mensagens,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Divisor
        JSplitPane divisor = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollEditor, scrollMensagens);
        divisor.setDividerLocation(500);
        divisor.setOneTouchExpandable(true);
        add(divisor, BorderLayout.CENTER);

        // Barra de status
        JPanel barraStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraStatus.setPreferredSize(new Dimension(1500, 25));
        barraStatus.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        labelStatus = new JLabel("");
        barraStatus.add(labelStatus);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(barraStatus, BorderLayout.SOUTH);
        add(painelInferior, BorderLayout.SOUTH);

        // Numeração de linhas
        editor.addCaretListener(e -> {
            int total = Math.max(1, editor.getLineCount());
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= total; i++)
                sb.append(i).append("\n");
            numLinhas.setText(sb.toString());
        });

        // ActionListeners dos botões
        botoes[0].addActionListener(e -> acaoNovo()); // Novo
        botoes[1].addActionListener(e -> acaoAbrir()); // Abrir
        botoes[2].addActionListener(e -> acaoSalvar()); // Salvar
        botoes[3].addActionListener(e -> editor.copy()); // Copiar
        botoes[4].addActionListener(e -> editor.paste()); // Colar
        botoes[5].addActionListener(e -> editor.cut()); // Recortar
        botoes[6].addActionListener(e -> acaoCompilar()); // Compilar
        botoes[7].addActionListener(e -> acaoEquipe()); // Equipe

        configurarAtalhos();
    }

    // Ações
    private void acaoNovo() {
        editor.setText("");
        mensagens.setText("");
        labelStatus.setText("");
        arquivoAtual = null;
    }

    private void acaoAbrir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));

        int resultado = chooser.showOpenDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION)
            return; // mantém estado anterior

        File arquivo = chooser.getSelectedFile();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            editor.read(br, null);
            mensagens.setText("");
            arquivoAtual = arquivo;
            labelStatus.setText(arquivo.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir arquivo: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acaoSalvar() {
        if (arquivoAtual == null) {
            // Arquivo novo — pede local e nome
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));

            int resultado = chooser.showSaveDialog(this);
            if (resultado != JFileChooser.APPROVE_OPTION)
                return;

            File arquivo = chooser.getSelectedFile();
            if (!arquivo.getName().toLowerCase().endsWith(".txt"))
                arquivo = new File(arquivo.getAbsolutePath() + ".txt");

            if (gravarArquivo(arquivo)) {
                arquivoAtual = arquivo;
                mensagens.setText("");
                labelStatus.setText(arquivo.getAbsolutePath());
            }
        } else {
            // Arquivo existente — salva direto, mantém status
            if (gravarArquivo(arquivoAtual)) {
                mensagens.setText("");
            }
        }
    }

    private boolean gravarArquivo(File arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            editor.write(bw);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void acaoEquipe() {
        mensagens.setText("Equipe:\n- Karina Luiza Becker Mendes\n- Yuri Ricardo Pacher Rabelo");
    }

    // Atalhos de teclado = Ctrl+C / Ctrl+V / Ctrl+X são tratados nativamente pelo
    // JTextArea
    private void configurarAtalhos() {
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "novo");
        am.put("novo", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acaoNovo();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "abrir");
        am.put("abrir", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acaoAbrir();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "salvar");
        am.put("salvar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "compilar");
        am.put("compilar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acaoCompilar();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "equipe");
        am.put("equipe", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acaoEquipe();
            }
        });
    }

    private int posicaoParaLinha(String texto, int posicao) {
        int linha = 1;
        for (int i = 0; i < posicao && i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                linha++;
            }
        }
        return linha;
    }

    private String formatarErro(String msgGals, int linha, String simbolo) {
        String msg = msgGals.toLowerCase().trim();

        if (msg.contains("cte_string")) {
            return "linha " + linha + ": constante_string inválida";
        } else if (msg.contains("cte_char")) {
            return "linha " + linha + ": constante_char inválida";
        } else if (msg.contains("cte_float")) {
            return "linha " + linha + ": constante_float inválida";
        } else if (msg.contains("identificando id")) {
            return "linha " + linha + ": identificador inválido";
        } else if (msg.contains("<ignorar>")) {
            return "linha " + linha + ": comentário inválido ou não finalizado";
        } else {
            return "linha " + linha + ": " + simbolo + " símbolo inválido";
        }
    }

    private void acaoCompilar() {
        mensagens.setText("");

        String fonte = editor.getText();
        Lexico lexico = new Lexico(new java.io.StringReader(fonte));
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();

        try {
            sintatico.parse(lexico, semantico);
            mensagens.setText("programa compilado com sucesso");
            mensagens.setFont(new Font("Monospaced", Font.PLAIN, 12));

            //salvar em il
            if (arquivoAtual != null) {
            String caminhoIL = arquivoAtual.getAbsolutePath().replace(".txt", ".il");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoIL))) {
                bw.write(semantico.getCodigoObjeto());
            } catch (IOException ex) {
                mensagens.setText("Erro ao salvar arquivo .il: " + ex.getMessage());
            }
        }
        } catch (LexicalError e) {
            int posErro = e.getPosition();
            int linhaErro = posicaoParaLinha(fonte, posErro);
            String simbolo = (posErro >= 0 && posErro < fonte.length())
                    ? String.valueOf(fonte.charAt(posErro)) : "";
            mensagens.setText(formatarErro(e.getMessage(), linhaErro, simbolo));
            mensagens.setFont(new Font("Monospaced", Font.PLAIN, 12));

        } catch (SyntaticError e) {
            int posErro = e.getPosition();
            int linhaErro = posicaoParaLinha(fonte, posErro);
            String encontrado = encontrarTokenEncontrado(fonte, posErro);
            mensagens.setText("linha " + linhaErro + ": encontrado " + encontrado + "esperado " + e.getMessage());
            mensagens.setFont(new Font("Monospaced", Font.PLAIN, 12));


            //encontrado ... esperado expressao

        } catch (SemanticError e) {
            int posErro = e.getPosition();
            int linhaErro = posErro >= 0 ? posicaoParaLinha(fonte, posErro) : -1;
            String msg = linhaErro >= 0
                    ? "linha " + linhaErro + ": erro semântico - " + e.getMessage()
                    : "erro semântico - " + e.getMessage();
            mensagens.setText(msg);
            mensagens.setFont(new Font("Monospaced", Font.PLAIN, 12));
        }
    }

    private String encontrarTokenEncontrado(String fonte, int posErro) {
        Lexico lexicoAux = new Lexico(new java.io.StringReader(fonte));
        try {
            Token t;
            while ((t = lexicoAux.nextToken()) != null) {
                if (t.getPosition() >= posErro) {
                    if (t.getId() == Constants.t_cte_string)
                        return "constante_string";
                    return t.getLexeme();
                }
            }
        } catch (LexicalError e) {
            // erro léxico ao re-escanear: não deveria ocorrer aqui
        }
        return "EOF";
    }
}