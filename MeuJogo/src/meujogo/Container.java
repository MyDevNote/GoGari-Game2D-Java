package meujogo;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import meujogo.modelo.Fase;


public class Container extends JFrame{
  
	public Container (){
		add(new Fase());
    	setTitle("Meu jogo de Espaconave");
    	ImageIcon icone = new ImageIcon("res\\background.png");
        this.setIconImage(icone.getImage());

		setSize(1024,728); //Defifição do tamanho da resolução da tela do jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha a janela
		setLocationRelativeTo(null);// Ponto da tela onde o jogo vai iniciar
		this.setResizable(false); //controle de maximização da tela 
		setVisible(true);
	}// constructor
             
	public static void main (String [] args){
		new Container();
	}// main
    
	
  
}//class Container