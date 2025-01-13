package com.alura.hotel.views;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.alura.hotel.modelo.Reserva;

public class EditarReserva extends JDialog {
    private JTextField txtFechaEntrada;
    private JTextField txtFechaSalida;
    private JTextField txtValor;
    private JTextField txtFormaPago;
    RegistroHuesped editarHuesped;
	private Integer reservaId;

    // Constructor que recibe los datos actuales
    public EditarReserva(Frame parent, Reserva reserva) {
        super(parent, "Editar Reserva", true); // Título y modalidad
        this.reservaId = reserva.getId();
        // Configura el diseño de la interfaz de edición, por ejemplo, un GridLayout
        setLayout(new GridLayout(5, 2));

        // Inicializa los campos de edición con los datos actuales de la reserva
        txtFechaEntrada = new JTextField(reserva.getFechaDeEntrada().toString());
        txtFechaSalida = new JTextField(reserva.getFechaDeSalida().toString());
        txtValor = new JTextField(reserva.getValor());
        txtFormaPago = new JTextField(reserva.getFormaDePago());

        // Agrega los campos de edición a la interfaz
        add(new JLabel("Fecha de Entrada:"));
        add(txtFechaEntrada);
        add(new JLabel("Fecha de Salida:"));
        add(txtFechaSalida);
        add(new JLabel("Valor:"));
        add(txtValor);
        add(new JLabel("Forma de Pago:"));
        add(txtFormaPago);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes guardar los cambios en la reserva
                // Puedes obtener los nuevos valores de los campos de edición
                String nuevaFechaEntrada = txtFechaEntrada.getText();
                String nuevaFechaSalida = txtFechaSalida.getText();
                String nuevoValor = txtValor.getText();
                String nuevaFormaPago = txtFormaPago.getText();

                // Luego, actualiza la reserva con los nuevos valores en la base de datos

                // Cierra el diálogo después de guardar
                dispose();
            }
        });

        add(btnGuardar);

        pack(); // Ajusta el tamaño del diálogo automáticamente
        setLocationRelativeTo(parent); // Centra el diálogo en la ventana principal
        
        RegistroHuesped editarHuesped = new RegistroHuesped(reservaId);
        
    }
}

