package com.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.HuespedController;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		txtBuscar = new JTextField();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);		

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 14));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		cargarTablaReserva();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);		
		
		componenteBuscar();
		componenteEditar();
		componenteEliminar();
	}
	
	/**
	 * Componente que obtiene los valores de busqueda
	 */
	void componenteBuscar() {
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String criterio = txtBuscar.getText();
		        if (!criterio.isEmpty()) {
		        	//TODO		            
		            cargarTablaReserva();
		            cargarTablaHuespedes();
		        } else {	
		        	var mensaje = "Debe escribir el apellido o el Id para realizar la búsqueda";	        	
		            JOptionPane.showMessageDialog(contentPane, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
		        }
		    }
			});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
	}	
	
	/**
	 * Componente que permite editar los valores de una tabla
	 */
	void componenteEditar() {
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar();
				cargarTablaReserva();
				cargarTablaHuespedes();
			}
		});
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
	}
	
	/**
	 * Componente que permite eliminar un registro de la tabla
	 */
	void componenteEliminar() {
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
				cargarTablaReserva();
				cargarTablaHuespedes();
			}
		});
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	

	private boolean tieneFilaElegida() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}

	private boolean tieneFilaElegida1() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	
	private void editar() {		
			editarTablaReserva();
			editarTablaHuesped();		
	}
	
	private void editarTablaReserva() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elija un item");
			return;
		}
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumnCount()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Date fechaDeEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
						Date fechaDeSalida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
						String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
						String formaDePago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);

						var filasModificadas = this.reservaController.modificar(id, fechaDeEntrada, fechaDeSalida,
								valor, formaDePago);
						JOptionPane.showMessageDialog(this,
								String.format("%d reserva modificada con éxito!", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elija una item"));		
	}
	
	private void editarTablaHuesped() {
		if (tieneFilaElegida1()) {
			JOptionPane.showMessageDialog(this, "Por favor, elija un item");
			return;
		} 
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumnCount()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 2);
						Date fechaDeNacimiento = (Date) modelo.getValueAt(tbHuespedes.getSelectedRow(), 3);
						String nacionalidad = (String) modelo.getValueAt(tbHuespedes.getSelectedRow(), 4);
						long telefono = (long) modelo.getValueAt(tbHuespedes.getSelectedRow(), 5);
						Integer reservaId = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

						var filasModificadas = this.huespedController.modificar(id, nombre, apellido, fechaDeNacimiento,
								nacionalidad, telefono, reservaId);
						JOptionPane.showMessageDialog(this,
								String.format("%d datos modificados con éxito!", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elija un item"));		
	}
	
	private void eliminar() {
			eliminarfilaReserva();
			eliminarfilaHuesped();		
	}
	
	private void eliminarfilaReserva() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elija una fila");
			return;
		}
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

						var cantidadEliminada = this.reservaController.eliminar(id);

						modelo.removeRow(tbReservas.getSelectedRow());

						JOptionPane.showMessageDialog(this,
								String.format("%d reserva eliminada con éxito!", cantidadEliminada));

					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elija una reserva"));
		} 
	
	private void eliminarfilaHuesped() {
		if (tieneFilaElegida1()) {
			JOptionPane.showMessageDialog(this, "Por favor, elija una fila");
			return;
		}
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

						var cantidadEliminada = this.huespedController.eliminar(id);

						modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

						JOptionPane.showMessageDialog(this,
								String.format("%d datos eliminados con éxito!", cantidadEliminada));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elija una fila"));
		}	

	/**
	 * Permite cargar los datos de la tabla Reservas de MySql
	 */
	private void cargarTablaReserva() {
		modelo.setRowCount(0);
		String criterio = txtBuscar.getText();

		if (!criterio.isEmpty()) {
			List<Reserva> datos;

			try {
				int idReserva = Integer.parseInt(criterio);
				datos = this.reservaController.buscarPorCriterio(criterio);
			} catch (NumberFormatException e) {
				// Manejo de excepción: mostrar mensaje de error al usuario
                JOptionPane.showMessageDialog(this, "Error al buscar en la tabla Reservas: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Registrar la excepción en un registro de errores si es necesario
                e.printStackTrace();
                return; // Salir de la función para evitar procesar datos incorrectos
			}
			
				datos.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaDeEntrada(),
						reserva.getFechaDeSalida(), reserva.getValor(), reserva.getFormaDePago() }));
			
		}
	}

	/**
	 * Carga la tabla de Huespedes siguiendo dos criterios de búsqueda: apellido o IdReserva
	 */
	private void cargarTablaHuespedes() {
	    modeloHuesped.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos
	    String criterio = txtBuscar.getText();
	    
	    if (!criterio.isEmpty()) {
	        List<Huesped> datos;
	        
	        try {
	            int idReserva = Integer.parseInt(criterio);
	            datos = this.huespedController.buscarPorCriterio(criterio); // Llama a buscarPorCriterio por número de ID
	        } catch (NumberFormatException e) {
	            datos = this.huespedController.buscarPorApellido(criterio); // Llama a buscarPorApellido por apellido
	        }
	        	        
	            datos.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getid(), huesped.getNombre(),
	                    huesped.getApellido(), huesped.getFechaDeNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
	                    huesped.getReservaId() }));
	        
	    }
	}


//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
