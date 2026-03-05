import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterfaceCompilador extends JFrame {
    public InterfaceCompilador() {

        // setTitle("Compilador");
        setSize(1500, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel barraFerramentas = new JPanel();
        barraFerramentas.setPreferredSize(new Dimension(1500, 70));
        barraFerramentas.setBackground(Color.lightGray);
        barraFerramentas.setBorder(BorderFactory.createTitledBorder("null"));
        add(barraFerramentas, BorderLayout.NORTH);

        JTextArea editor = new JTextArea();
        JScrollPane scrollEditor = new JScrollPane(editor);
        scrollEditor.setBorder(BorderFactory.createTitledBorder("Editor"));
        add(scrollEditor, BorderLayout.CENTER);

        JTextArea mensagens = new JTextArea();
        mensagens.setEditable(false);
        JScrollPane scrollMensagens = new JScrollPane(mensagens);
        // scrollMensagens = setPreferredSize(new Dimension(1500,150));
        scrollMensagens.setPreferredSize(new Dimension(1500, 150));
        scrollMensagens.setBorder(BorderFactory.createTitledBorder("msg"));



        JPanel painelInferior = new JPanel();
        painelInferior.add(scrollMensagens, BorderLayout.CENTER);
        
        JPanel barraStatus = new JPanel();
        barraStatus.setPreferredSize(new Dimension(1500,25));
        barraStatus.setBackground(Color.gray);
        barraStatus.setBorder(BorderFactory.createLoweredBevelBorder());
        painelInferior.add(barraStatus, BorderLayout.SOUTH);


add(painelInferior, BorderLayout.SOUTH);

    }
}
