package Doctorado;

import java.util.Stack;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Write;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.pattern.ElmanPattern;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;


public class ProcesaMusicNN {

 static BasicNetwork ElmanNetwork;


 private int numeroNotas=36; //3 octavas
	
	public Stack<Object> abc2Vector(String str, int tipo)
	{
		Stack<Object> stOut=new Stack<Object>();
		 for(int i=0;i<(str.length());i++)
		  {
			 String strNota=str.substring(i, i+1);
			 if(strNota.equals(" ")||strNota.equals("|")||strNota.equals("\n")||strNota.equals("\r"))
				 {
				 	continue;
				 }
			 else if ((i<str.length()-2)&&(str.substring(i+1,i+2).equals(",")))
			 {
				 strNota=str.substring(i, i+2);
				 i++;
			 }
			 else if ((i<str.length()-2)&&(str.substring(i+2,i+3).equals(",")))
			 {
				 strNota=str.substring(i, i+3);
				 i+=2;
			 }
			 else if (str.substring(i, i+1).equals("^")|| str.substring(i, i+1).equals("_"))
			{
				strNota=str.substring(i, i+2);
				i++;
			}
			 else if (str.substring(i, i+1).equals("="))
			{
				strNota=str.substring(i+1, i+2);
				i++;
			}
			
		   //array de 0,1
		   if(tipo==0)
			   {
			   double[] boolNota=convert2Array(strNota);  
			   stOut.push(boolNota);
			   }
		   //enteros
		   if(tipo==1)
			   {
			   int intNota=convert2Int(strNota);  
			   stOut.push(intNota);
			   }
			   
		   
		  
		  }
		 
	return stOut;
	    }
	
	public double[][] stack2Array(Stack<?> st,int init, int fin)
	{
		 double[][] arrayOut=new double[fin-init][numeroNotas];
		 int i=0;
		for(int n=init;n<fin;n++)
		   {
			
				double notas[]=(double[]) st.get(n);
				arrayOut[i]=notas;
				i++;
	
		   }
		return arrayOut;
	}
	
	public void salvarMidi(String strABC, String path)
	{

				Stack<Object> stMusic=abc2Vector(strABC,1);
				Score s = new Score("Texturas");
				Part p = new Part("Melody", 1, 0);
				Phrase phr = new Phrase();
				for(int i=0;i<stMusic.size();i++)
				{
					int intNota=Integer.parseInt(stMusic.get(i).toString());
					Note note = new Note(intNota, 0.25,2);
				    phr.addNote(note);
				}
				       
				p.addPhrase(phr);

				s.addPart(p);
				Write.midi(s, path);

	}
	public double[] convert2Array(String strInput)
	{
		double[] intOut=new double[numeroNotas];
		for(int i=0;i<intOut.length;i++)
		{
			intOut[i]=0;
		}
		if(strInput.equals("C,")) intOut[0]=1;
		if(strInput.equals("^C,")) intOut[1]=1;
		if(strInput.equals("D,")) intOut[2]=1;
		if(strInput.equals("^D,")) intOut[3]=1;
		if(strInput.equals("E,")) intOut[4]=1;
		if(strInput.equals("F,")) intOut[5]=1;
		if(strInput.equals("^F,")) intOut[6]=1;
		if(strInput.equals("G,")) intOut[7]=1;
		if(strInput.equals("^G,")) intOut[8]=1;
		if(strInput.equals("A,")) intOut[9]=1;
		if(strInput.equals("^A,")) intOut[10]=1;
		if(strInput.equals("B,")) intOut[11]=1;
		
		if(strInput.equals("C")) intOut[12]=1;
		if(strInput.equals("^C")) intOut[13]=1;
		if(strInput.equals("D")) intOut[14]=1;
		if(strInput.equals("^D")) intOut[15]=1;
		if(strInput.equals("E")) intOut[16]=1;
		if(strInput.equals("F")) intOut[17]=1;
		if(strInput.equals("^F")) intOut[18]=1;
		if(strInput.equals("G")) intOut[19]=1;
		if(strInput.equals("^G")) intOut[20]=1;
		if(strInput.equals("A")) intOut[21]=1;
		if(strInput.equals("^A")) intOut[22]=1;
		if(strInput.equals("B")) intOut[23]=1;
		
		if(strInput.equals("c")) intOut[24]=1;
		if(strInput.equals("^c")) intOut[25]=1;
		if(strInput.equals("d")) intOut[26]=1;
		if(strInput.equals("^d")) intOut[27]=1;
		if(strInput.equals("e")) intOut[28]=1;
		if(strInput.equals("f")) intOut[29]=1;
		if(strInput.equals("^f")) intOut[30]=1;
		if(strInput.equals("g")) intOut[31]=1;
		if(strInput.equals("^g")) intOut[32]=1;
		if(strInput.equals("a")) intOut[33]=1;
		if(strInput.equals("^a"))intOut[34]=1;
		if(strInput.equals("b"))intOut[35]=1;
		
		
		return intOut;
		
	}
	public int convert2Int(String strInput)
	{
		int intOut=0;
		if(strInput.equals("C,")) intOut=36;
		if(strInput.equals("^C,")) intOut=37;
		if(strInput.equals("D,")) intOut=38;
		if(strInput.equals("^D,")) intOut=39;
		if(strInput.equals("E,")) intOut=40;
		if(strInput.equals("F,")) intOut=41;
		if(strInput.equals("^F,")) intOut=42;
		if(strInput.equals("G,")) intOut=43;
		if(strInput.equals("^G,")) intOut=44;
		if(strInput.equals("A,")) intOut=45;
		if(strInput.equals("^A,")) intOut=46;
		if(strInput.equals("B,")) intOut=47;
		
		if(strInput.equals("c")) intOut=60;
		if(strInput.equals("^c")) intOut=61;
		if(strInput.equals("d")) intOut=62;
		if(strInput.equals("^d")) intOut=63;
		if(strInput.equals("e")) intOut=64;
		if(strInput.equals("f")) intOut=65;
		if(strInput.equals("^f")) intOut=66;
		if(strInput.equals("g")) intOut=67;
		if(strInput.equals("^g")) intOut=68;
		if(strInput.equals("a")) intOut=69;
		if(strInput.equals("^a")) intOut=70;
		if(strInput.equals("b")) intOut=71;
		
		if(strInput.equals("B")) intOut=59;
		if(strInput.equals("^A")) intOut=58;
		if(strInput.equals("A")) intOut=57;
		if(strInput.equals("^G")) intOut=56;
		if(strInput.equals("G")) intOut=55;
		if(strInput.equals("^F")) intOut=54;
		if(strInput.equals("F")) intOut=53;
		if(strInput.equals("E")) intOut=52;
		if(strInput.equals("^D")) intOut=51;
		if(strInput.equals("D")) intOut=50;
		if(strInput.equals("^C")) intOut=49;
		if(strInput.equals("C")) intOut=48;
		
		
		intOut+=12;
		return intOut;
		
	}
	public void entrenaElmanNN(double[][] input,double[][] output, int hiddenLayers, double errorOut, int iteraciones)
	{
				
				// construct an Elman type network
				ElmanPattern pattern = new ElmanPattern();
				pattern.setActivationFunction(new ActivationSigmoid());
				pattern.setInputNeurons(numeroNotas);
				pattern.addHiddenLayer(hiddenLayers);
				pattern.setOutputNeurons(numeroNotas);
				ElmanNetwork= (BasicNetwork)pattern.generate();
				
				// create training data
				MLDataSet trainingSet = new BasicMLDataSet(input,output);

				// train the neural network
				ResilientPropagation train = new ResilientPropagation(ElmanNetwork, trainingSet);
				int epoch = 1;

				GraficaEntrenamiento grafica=new GraficaEntrenamiento();
				
				
				do {
					train.iteration();
					double error=train.getError();
					if (error<1)grafica.pinta(epoch,(int)(error*1000));
					
					System.out
							.println("Epoch #" + epoch + " Error:" + train.getError());
					epoch++;
				} while((train.getError() > errorOut)&& epoch<iteraciones);
				
	
	}
	public double[][] getMusicNetElman(double[][] input,int notasSalida)
	{
		double[][] arrSalida=new double[notasSalida][numeroNotas];
		MLDataSet pruebaSet = new BasicMLDataSet(input,input);
		int i=0;
		for(MLDataPair pair: pruebaSet ) {
			MLData output = ElmanNetwork.compute(pair.getInput());
			if(i<input.length-1)
			{
				arrSalida[i]=pair.getInputArray();
				
			}
			else
			{
				
				// MLData output = ElmanNetwork.compute(pair.getInput());
				if(i==(input.length-1))arrSalida[i]=pair.getInputArray();
				double arrOut[]= output.getData();
				double arrFiltrado[][]=filtroArr(arrOut);
				MLDataSet pruebaSetFiltrado = new BasicMLDataSet(arrFiltrado,arrFiltrado);
				

				for(MLDataPair pair2: pruebaSetFiltrado ) {

					
					pruebaSet.add(pair2.getInput());
				}
				
				arrSalida[i+1]=output.getData();
				
			}
			i++;
			if(i>=arrSalida.length-1)break;
		}
		


		return arrSalida;
	}
	private double[][] filtroArr(double[] array)
	{
	
			double valorMayor=0;
			int indiceMayor=0;
			for(int n=0;n<array.length;n++)
			{
				
				if(array[n]>valorMayor)
					{
					valorMayor=array[n];
					indiceMayor=n;
					
					}
				
			}
			double[][] arrOut=new double[1][array.length];
			arrOut[0]=getArrOut(indiceMayor);
			
		return arrOut;
	}
	private double[] getArrOut(int indice)
	{
		double[] out=new double[numeroNotas];
		for(int i=0;i<out.length;i++)
		{
			out[i]=0;
		}
		out[indice]=1;
		return out;

	}
			
	public String array2ABC(double[][] array)
	{
		String strOut="";
		for(int i=0;i<array.length;i++)
		{
			double valorMayor=0;
			int indiceMayor=0;
			for(int n=0;n<array[i].length;n++)
			{
				
				if(array[i][n]>valorMayor)
					{
					valorMayor=array[i][n];
					indiceMayor=n;
					
					}

			}
			if(i%4==0)strOut+=" ";
			if(i%16==0)strOut+="\n";
			strOut+=getNota(indiceMayor);
			
		}
		return strOut;
	}
	private String getNota(int indice)
	{
		String nota="";
		if(indice==0)nota="C,";
		if(indice==1)nota="^C,";
		if(indice==2)nota="D,";
		if(indice==3)nota="^D,";
		if(indice==4)nota="E,";
		if(indice==5)nota="F,";
		if(indice==6)nota="^F,";
		if(indice==7)nota="G,";
		if(indice==8)nota="^G,";
		if(indice==9)nota="A,";
		if(indice==10)nota="^A,";
		if(indice==11)nota="B,";
		
		if(indice==12)nota="C";
		if(indice==13)nota="^C";
		if(indice==14)nota="D";
		if(indice==15)nota="^D";
		if(indice==16)nota="E";
		if(indice==17)nota="F";
		if(indice==18)nota="^F";
		if(indice==19)nota="G";
		if(indice==20)nota="^G";
		if(indice==21)nota="A";
		if(indice==22)nota="^A";
		if(indice==23)nota="B";
		
		if(indice==24)nota="c";
		if(indice==25)nota="^c";
		if(indice==26)nota="d";
		if(indice==27)nota="^d";
		if(indice==28)nota="e";
		if(indice==29)nota="f";
		if(indice==30)nota="^f";
		if(indice==31)nota="g";
		if(indice==32)nota="^g";
		if(indice==33)nota="a";
		if(indice==34)nota="^a";
		if(indice==35)nota="b";

		return nota;
	}
	public void saveData(File filename)
	{
		EncogDirectoryPersistence.saveObject(filename, ElmanNetwork);

	}
	public void loadData(File filename)
	{
		ElmanNetwork = (BasicNetwork)EncogDirectoryPersistence.loadObject(filename);

	}
	
}
