package Doctorado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import abc.midi.TunePlayer;
import abc.notation.Tune;
import abc.parser.TuneBook;
import abc.parser.TuneParser;
import abc.ui.swing.JScoreComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Stack;


public class Principal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textFieldPrueba;
	ProcesaMusicNN pm=new ProcesaMusicNN();
	TextArea textAreaABC = new TextArea();
	ScrollPane scrollMusic = new ScrollPane();
	ScrollPane scrollMusicOut = new ScrollPane();
	
	String strOutNet="";
	TunePlayer player;
	private JTextField txtHiddenLayers;
	private JTextField txtPathMidi;
	private JTextField txtNotasSalida;
	private JTextField txtError;
	private JTextField txtIteraciones;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 985, 781);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		textAreaABC.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent arg0) {
				visualizaMusic();
			}
		});
		
		
		textAreaABC.setText("CDEFGAB");
		visualizaMusic();
		
		JButton btnConvertir = new JButton("Entrenar");

		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setEntrenar();
				
			}
		});

		btnConvertir.setBounds(331, 640, 138, 43);
		frame.getContentPane().add(btnConvertir);
		
		JLabel lblScore = new JLabel("Entrenamiento");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblScore.setBounds(27, 34, 132, 14);
		frame.getContentPane().add(lblScore);
		
		JLabel lblAbcMusic = new JLabel("ABC Music (entrada)");
		lblAbcMusic.setBounds(27, 429, 132, 14);
		frame.getContentPane().add(lblAbcMusic);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(0, 0, 0, 0);
		textPane.setText("CDEFGABcdefgab");
		frame.getContentPane().add(textPane);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textFieldPrueba = new JTextField();
		textFieldPrueba.setText("CD");
		textFieldPrueba.setBounds(498, 449, 442, 20);
		frame.getContentPane().add(textFieldPrueba);
		textFieldPrueba.setColumns(10);
		
		JButton btnNewButton = new JButton("Probar");

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setProbar();
			}
		});
		btnNewButton.setBounds(808, 512, 132, 38);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(113, 390, 144, -38);
		frame.getContentPane().add(scrollPane);
		
		
		textAreaABC.setBounds(27, 449, 442, 146);
		frame.getContentPane().add(textAreaABC);
		
		
		scrollMusic.setBounds(27, 54, 442, 369);
		frame.getContentPane().add(scrollMusic);
		
		
		scrollMusicOut.setBounds(498, 54, 442, 369);
		frame.getContentPane().add(scrollMusicOut);
		
		txtHiddenLayers = new JTextField();
		txtHiddenLayers.setText("50");
		txtHiddenLayers.setBounds(135, 618, 86, 20);
		frame.getContentPane().add(txtHiddenLayers);
		txtHiddenLayers.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Capas ocultas");
		lblNewLabel.setBounds(27, 618, 98, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblError = new JLabel("Error");
		lblError.setBounds(27, 644, 98, 14);
		frame.getContentPane().add(lblError);
		
		JLabel lblIteracionesMximo = new JLabel("Iteraciones M\u00E1ximo");
		lblIteracionesMximo.setBounds(27, 669, 98, 14);
		frame.getContentPane().add(lblIteracionesMximo);
		
		JLabel lblResultados = new JLabel("Resultados");
		lblResultados.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResultados.setBounds(500, 34, 132, 14);
		frame.getContentPane().add(lblResultados);
		
		JLabel label = new JLabel("ABC Music (entrada)");
		label.setBounds(500, 429, 132, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Path de salida");
		label_1.setBounds(498, 601, 117, 14);
		frame.getContentPane().add(label_1);
		
		txtPathMidi = new JTextField();
		txtPathMidi.setText("default.mid");
		txtPathMidi.setColumns(10);
		txtPathMidi.setBounds(498, 618, 442, 20);
		frame.getContentPane().add(txtPathMidi);
		
		txtNotasSalida = new JTextField();
		txtNotasSalida.setText("50");
		txtNotasSalida.setColumns(10);
		txtNotasSalida.setBounds(606, 480, 86, 20);
		frame.getContentPane().add(txtNotasSalida);
		
		JLabel lblNotasDeSalida = new JLabel("Notas de salida");
		lblNotasDeSalida.setBounds(498, 480, 98, 14);
		frame.getContentPane().add(lblNotasDeSalida);
		
		txtError = new JTextField();
		txtError.setText("0.01");
		txtError.setColumns(10);
		txtError.setBounds(135, 641, 86, 20);
		frame.getContentPane().add(txtError);
		
		txtIteraciones = new JTextField();
		txtIteraciones.setText("5000");
		txtIteraciones.setColumns(10);
		txtIteraciones.setBounds(135, 666, 86, 20);
		frame.getContentPane().add(txtIteraciones);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(482, 0, 10, 711);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(482, 561, 487, 14);
		frame.getContentPane().add(separator_1);
		
		JButton btnSalvar = new JButton("Salvar .mid");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				pm.salvarMidi(strOutNet, txtPathMidi.getText());
			}
		});
		btnSalvar.setBounds(808, 650, 132, 43);
		frame.getContentPane().add(btnSalvar);
		
		JLabel lblGuardarDatosMidi = new JLabel("Guardar Datos Midi");
		lblGuardarDatosMidi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGuardarDatosMidi.setBounds(498, 576, 186, 14);
		frame.getContentPane().add(lblGuardarDatosMidi);
		
		JButton btnPlayInput = new JButton("Play");
		btnPlayInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playInput();
			}
		});
		btnPlayInput.setBounds(331, 606, 69, 23);
		frame.getContentPane().add(btnPlayInput);
		
		JButton btnPlayOutput = new JButton("Play");
		btnPlayOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				playOutput();
			}
		});
		btnPlayOutput.setBounds(808, 480, 69, 23);
		frame.getContentPane().add(btnPlayOutput);
		
		JButton btnStopInput = new JButton("Stop");
		btnStopInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//player.stop();
				player.stopPlaying();
			}
		});
		btnStopInput.setBounds(400, 606, 69, 23);
		frame.getContentPane().add(btnStopInput);
		
		JButton btnStopOutput = new JButton("Stop");
		btnStopOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//player.stop();
				player.stopPlaying();
			}
		});
		btnStopOutput.setBounds(880, 480, 60, 23);
		frame.getContentPane().add(btnStopOutput);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nueva Red");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				nuevaRed();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir Red...");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				abrirRed();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmSaveNet = new JMenuItem("Guardar Red...");
		mntmSaveNet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				salvarRed();
				
				
			}
		});
		mnNewMenu.add(mntmSaveNet);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Salir");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Proceso");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmEntrenar = new JMenuItem("Entrenar...");
		mntmEntrenar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setEntrenar();
			}
		});
		mnNewMenu_1.add(mntmEntrenar);
		
		JMenuItem mntmProbar = new JMenuItem("Probar...");
		mntmProbar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setProbar();
			}
		});
		mnNewMenu_1.add(mntmProbar);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Salvar .mid");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				pm.salvarMidi(strOutNet, txtPathMidi.getText());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Acerca de...");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
			
		
	}
	private void playInput()
	{
		String tuneAsString="X:2\nM:2/4\nK:C\n";
		tuneAsString += textAreaABC.getText().toString();
		tuneAsString+="\n";
		Tune tune = new TuneParser().parse(tuneAsString);

		//creates a midi player to play tunes
		player = new TunePlayer();
		//starts the player and play the tune
		player.start();
		player.play(tune);

	}
	private void playOutput()
	{
		String tuneAsString="X:2\nM:2/4\nK:C\n";
		tuneAsString += strOutNet;
		tuneAsString+="\n";
		Tune tune = new TuneParser().parse(tuneAsString);

		//creates a midi player to play tunes
		player = new TunePlayer();
		//starts the player and play the tune
		player.start();
		player.play(tune);
	}
	private void salvarRed()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int seleccion = fileChooser.showSaveDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
		   File fichero = fileChooser.getSelectedFile();
		   try{
			   pm.saveData(fichero);
		   }
		   catch(Exception error){
			   
		   }
		   
		}
	}
	private void abrirRed()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int seleccion = fileChooser.showOpenDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
	
			   File fichero = fileChooser.getSelectedFile();
			   pm.loadData(fichero);
			  

		}
	}
	private void nuevaRed()
	{
		ProcesaMusicNN.ElmanNetwork=null;
		scrollMusic.removeAll();
		textAreaABC.setText("");
		scrollMusicOut.removeAll();
	}
	private void visualizaMusic()
	{
		String tuneAsString="X:2\nM:2/4\nK:C\n";
		tuneAsString += textAreaABC.getText().toString();
		tuneAsString+="\n";
		Tune tuneFinal = new TuneParser().parse(tuneAsString);
		JScoreComponent scoreUI =new JScoreComponent();
		scoreUI.setTune(tuneFinal);

		scrollMusic.removeAll();
		scrollMusic.add(scoreUI);
		scrollMusic.repaint();
		scrollMusic.setVisible(true);
	}
	/*private String visualizaArray(double[][] arrayOut)
	{
		String strOut="";
		for(int i=0;i<arrayOut.length;i++)
		{
			for(int n=0;n<arrayOut[i].length;n++)
			{
				strOut+=arrayOut[i][n];
			}
			strOut+="\n";
		}
		return strOut;
	}*/
	private void setEntrenar()
	{
	
		visualizaMusic();
		ProcesaMusicNN pm=new ProcesaMusicNN();
		Stack<Object> stMusic=pm.abc2Vector(textAreaABC.getText(),0);
		double[][] arrayIn=pm.stack2Array(stMusic,0,stMusic.size()-1);

		double[][] arrayOut=pm.stack2Array(stMusic,1,stMusic.size());
		int nodosOcultos=Integer.parseInt(txtHiddenLayers.getText());
		double error=Double.valueOf(txtError.getText());
		int iteraciones=Integer.parseInt(txtIteraciones.getText());
		
		pm.entrenaElmanNN(arrayIn,arrayOut,nodosOcultos,error,iteraciones);
		
		
	}
	
	private void setProbar()
	{
		
		Stack<Object> stMusic=pm.abc2Vector(textFieldPrueba.getText(),0);
		double[][] arrayTest=pm.stack2Array(stMusic,0,stMusic.size());
		int notasSalida=Integer.parseInt(txtNotasSalida.getText());
		double[][] arrSalida=pm.getMusicNetElman(arrayTest,notasSalida);
		strOutNet=pm.array2ABC(arrSalida);
		visualizaMusicOut(strOutNet);

	}
	
	private void visualizaMusicOut(String abc)
	{
		String tuneAsString="X:2\nM:2/4\nK:C\n";
		tuneAsString += abc;
		tuneAsString+="\n";
		Tune tuneFinal = new TuneParser().parse(tuneAsString);
		JScoreComponent scoreUI =new JScoreComponent();
		scoreUI.setTune(tuneFinal);

		scrollMusicOut.removeAll();
		scrollMusicOut.add(scoreUI);
		scrollMusicOut.repaint();
		scrollMusicOut.setVisible(true);
	}
}
