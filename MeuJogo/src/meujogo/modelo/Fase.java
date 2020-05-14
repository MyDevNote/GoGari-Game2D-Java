package meujogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean emJogo;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\telafundo.png"); // fundo da fase
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this); // Velocidade do Jogo
		timer.start();

		emJogo = true;

		inicializaStars();
		inicializaInimigos();

	}// constructor

	public void inicializaInimigos() {
		int coordenadas[] = new int[300]; // 40 é o número de inimigos
		enemy1 = new ArrayList<Enemy1>();

		for (int k = 0; k < coordenadas.length; k++) {
			int r = (int) (Math.random() * 8000 + 1024); // escolha de coordenadas aleatórias para o aparecimento de
			int s = (int) (Math.random() * 650 + 30);
			enemy1.add(new Enemy1(r, s));
		} // for

	}// inicializaInimigos

	public void inicializaStars() {
		int coordenadas[] = new int[8];
		stars = new ArrayList<Stars>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 1050 + 1024);
			int y = (int) ((Math.random() * 768) - (Math.random() * 768));
			stars.add(new Stars(x, y));
		} // for
	}// inicializaNebulas

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null); // Printar a iagem do fundo na tela

			for (int p = 0; p < stars.size(); p++) {
				Stars q = stars.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			} // for

			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			} // for

			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			} // for
		} // if
		else {
			ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png"); // imagem para fim de jogo
			graficos.drawImage(fimJogo.getImage(), 0, 0, 1024, 728, null);
		} // else

		g.dispose();

	}// paint

	@Override
	public void actionPerformed(ActionEvent e) { // Código para atualizar a tela
		player.update();
		repaint();
		for (int p = 0; p < stars.size(); p++) {	
			Stars on = stars.get(p);
			if (on.isVisivel()) {
				on.update();
			} // if
			else {
				stars.remove(p);
			} // else
		} // for

		List<Tiro> tiros = player.getTiros(); // instala os tiros na fase
		for (int i = 0; i < tiros.size(); i++) { ///
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} // if
			else {
				tiros.remove(i);
			} // else
		} // for

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisivel()) {
				in.update();
			} // if
			else {
				enemy1.remove(o);
			} // else

		} // for

		checarColisoes();

		repaint();

	}// actionPerformed

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i); // aqui estamos pegando todos os amigos que vão aparecer
			formaEnemy1 = tempEnemy1.getBounds(); // ==retangulo
			if (formaNave.intersects(formaEnemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false; // Colisão entre player e o inimigo
			} // if
		} // for

		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();
				if (formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				} // if
			} // for
		} // for

	}// checarColisoes

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}// keyPressed

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}// keyRelease

	}// class TecladoAdapter

}// class
