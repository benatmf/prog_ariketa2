import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Panela extends JFrame {

    private JComboBox<String> comboBox;
    private JTextArea textArea;

    public Panela() {
        super("Mi GUI");

        // crea el JComboBox con los nombres de archivo
        String[] filenames = {"python.txt", "c.txt", "java.txt"};
        comboBox = new JComboBox<String>(filenames);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el nombre de archivo seleccionado en el JComboBox
                String filename = (String) comboBox.getSelectedItem();

                // Intenta leer el archivo y mostrar su contenido en el JTextArea
                try {
                    File file = new File(filename);
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    StringBuilder stringBuilder = new StringBuilder();
                    String ls = System.getProperty("line.separator");
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append(ls);
                    }
                    reader.close();
                    textArea.setText(stringBuilder.toString());
                } catch (Exception ex) {
                    // Si el archivo no existe, muestra una ventana emergente con un mensaje de error
                    JOptionPane.showMessageDialog(Panela.this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // crea el JTextArea para mostrar el contenido del archivo
        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // crea el botón "Borrar"
        JButton clearButton = new JButton("Borrar");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        // crea el botón "Cerrar"
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // crea el panel para los botones y el JComboBox
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(comboBox);
        buttonPanel.add(clearButton);

        // crea el panel para el botón "Cerrar"
        JPanel closePanel = new JPanel();
        closePanel.add(closeButton);

        // agrega los componentes a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(closePanel, BorderLayout.SOUTH);

        // configura la ventana
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
