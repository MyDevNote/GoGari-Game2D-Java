package meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {
	
	private Image imagem; // imagem do tiro
	private int x, y;
	private int largura, altura;
	private boolean isVisivel; //Quando o tiro chega no limite da tela ou no inimigo a imagem some
	
	private static final int LARGURA = 938; // número ideal para o tiro sumir adequando a resolução da tela
	private static int VELOCIDADE = 2; // velocidade do tiro
	
	public Tiro (int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}//constructor
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\unnamed.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}//load
	
	public void update() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA) {
			isVisivel = false; //Se o tiro ultrapassar a largura de 938 ele vai sumir
		}//if
	}//update
	
	

	public Rectangle getBounds() { //Cria um retangulo em volta da nave, esse elemento envolve o enemy e o player para efeito de colisoes
		return new Rectangle(x, y, largura, altura);
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
}//class