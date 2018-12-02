/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGame;

import infinitygame.InfinityGame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio
 */
public class Ventana extends JFrame implements ActionListener {

    private InfinityGame ifgame;
    private crearInfinityGame crearIf;
    private JLabel textoBienvenida;
    private JLabel turno;
    private JLabel numeroTurno;
    private JLabel nombreJugador;
    private JButton botonInicio;
    private JButton realizarAccion;
    private JButton mostrarTablero;
    private int cantidadCasillas;
    private int cantidadJugadores;
    private boolean estadoJuego;

    public Ventana() {

        this.estadoJuego = false;

        this.setLayout(null);
        this.setTitle("InfinityGame");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        textoBienvenida = new JLabel("Bienvenido a InfinityGame, presione el botón para comenzar.");
        textoBienvenida.setFont(new Font("Serif", Font.PLAIN, 32));
        textoBienvenida.setBounds(0, 0, 800, 50);
        this.add(textoBienvenida);

        turno = new JLabel("El turno actual es el :");
        turno.setFont(new Font("Serif", Font.PLAIN, 32));
        turno.setBounds(0, 0, 800, 50);
        this.add(turno);
        turno.setVisible(false);

        numeroTurno = new JLabel("");
        numeroTurno.setFont(new Font("Serif", Font.PLAIN, 32));
        numeroTurno.setBounds(300, 0, 100, 50);
        this.add(numeroTurno);
        numeroTurno.setVisible(false);

        nombreJugador = new JLabel("");
        nombreJugador.setFont(new Font("Serif", Font.PLAIN, 32));
        nombreJugador.setBounds(500, 0, 800, 50);
        this.add(nombreJugador);
        nombreJugador.setVisible(false);

        botonInicio = new JButton("Comenzar el juego");
        botonInicio.setBounds(300, 100, 150, 20);
        this.add(botonInicio);
        botonInicio.addActionListener(this);

        realizarAccion = new JButton("Realizar acción");
        realizarAccion.setBounds(300, 300, 150, 20);
        this.add(realizarAccion);
        realizarAccion.setVisible(false);
        realizarAccion.addActionListener(this);

        mostrarTablero = new JButton("Realizar acción");
        mostrarTablero.setBounds(300, 400, 150, 20);
        this.add(mostrarTablero);
        mostrarTablero.setVisible(false);
        mostrarTablero.addActionListener(this);
    }

    public void Inicializar() {

    }

    private void preguntarDatos() {
        this.cantidadCasillas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de casillas, se necesita un mínimo de 25"));
        this.cantidadJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores que van a jugar"));
        this.ifgame = new InfinityGame(this.cantidadCasillas, this.cantidadJugadores);
        mostrarTablero();
    }

    private void agregarJugadores() {
        for (int i = 0; i < this.cantidadJugadores; i++) {
            String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador número " + (i + 1));
            int clase = Integer.parseInt(JOptionPane.showInputDialog("Eliga la clase del jugador :" + '\n' + "1) Guerrero" + '\n' + "2) Mago"));
            this.ifgame.agregarJugadores(clase, nombreJugador, this.cantidadCasillas);
        }
    }

    private void turno() {
        int contadorTurno = 0;
        turno.setVisible(true);
        numeroTurno.setVisible(true);
        nombreJugador.setVisible(true);
        realizarAccion.setVisible(true);
        mostrarTablero.setVisible(true);
        for (int i = 0; i < this.ifgame.players.size(); i++) {
            contadorTurno++;
            nombreJugador.setText(this.ifgame.players.get(i).getName());
            numeroTurno.setText(Integer.toString(contadorTurno));
            int sucesoGuardian = this.ifgame.habilidadGuardian(i);
            if (sucesoGuardian == 1) {
                JOptionPane.showMessageDialog(this, "El guardian ha activado su habilidad furia!" + '\n' + "Se ha restado 1 de vida a todos los jugadores");
            }
            int accion = Integer.parseInt(JOptionPane.showInputDialog("Que acción desea realizar?" + '\n' + "1)Lanzar Dados" + '\n' + "2)Meditar" + '\n' + "3)Usar Hablidad Especial"));
        }
    }

    private void mostrarTablero() {
        JOptionPane.showMessageDialog(this, "El arreglo se ha creado exitosamente " + '\n' + Arrays.toString(this.ifgame.getTablero()));
    }

    public static void main(String[] args) {
        Ventana vn = new Ventana();
        vn.Inicializar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonInicio) {
            preguntarDatos();
            botonInicio.setVisible(false);
            textoBienvenida.setVisible(false);
            agregarJugadores();
            turno();
        }

        if (e.getSource() == realizarAccion) {

        }

        if (e.getSource() == mostrarTablero) {

        }
    }

}
