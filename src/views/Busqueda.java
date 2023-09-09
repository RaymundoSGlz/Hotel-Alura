package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

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
	String reserva;
	String huesped;

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

		reservaController = new ReservaController();
		huespedController = new HuespedController();
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

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

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
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
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
		mostrarTablaReservas();

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
		mostrarTablaHuespedes();

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

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText().matches("[0-9]*")) {
					mostrarTablaHuespedesId();
					mostrarTablaReservasId();
				} else {
					mostrarTablaHuespedesApellido();
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

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarRegistro();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void eliminarTablaReservas() {
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}

	private void eliminarTablaHuespedes() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
	}

	// Método que permite buscar los datos de la tabla "Reservas"
	private List<Reserva> buscarReservas() {
		return this.reservaController.buscar();
	}

	// Método que permite buscar los datos de la tabla "Reservas" por id
	private List<Reserva> buscarReservasId() {
		return this.reservaController.buscarId(txtBuscar.getText());
	}

	private List<Huesped> buscarHuespedes() {
		return this.huespedController.buscar();
	}

	private List<Huesped> buscarHuespedesId() {
		return this.huespedController.buscarId(txtBuscar.getText());
	}

	private List<Huesped> buscarHuespedesApellido() {
		return this.huespedController.buscarApellidoHuesped(txtBuscar.getText());
	}

	private void actualizarRegistro() {
		if (tbReservas.getSelectedRow() != -1) {
			ActualizarReservas();
		} else if (tbHuespedes.getSelectedRow() != -1) {
			actualizarHuespedes();
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar");
		}
	}

	// Método que permite mostrar los datos de la tabla "Reservas "
	private void mostrarTablaReservas() {
		List<Reserva> reserva = buscarReservas();
		try {
			for (Reserva res : reserva) {
				modelo.addRow(new Object[] {
						res.getId(), res.getFechaE(), res.getFechaS(), res.getValor(), res.getFormaPago()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Método que permite mostrar los datos de la tabla "Reservas " por id
	private void mostrarTablaReservasId() {
		eliminarTablaReservas();
		List<Reserva> reserva = buscarReservasId();
		try {
			for (Reserva res : reserva) {
				modelo.addRow(new Object[] {
						res.getId(), res.getFechaE(), res.getFechaS(), res.getValor(), res.getFormaPago()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void ActualizarReservas() {
		int selectedRow = tbReservas.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar");
			return;
		}

		int id = (int) modelo.getValueAt(selectedRow, 0);
		Date fechaE = Date.valueOf(modelo.getValueAt(selectedRow, 1).toString());
		Date fechaS = Date.valueOf(modelo.getValueAt(selectedRow, 2).toString());
		String valor = (String) modelo.getValueAt(selectedRow, 3);
		String formaPago = (String) modelo.getValueAt(selectedRow, 4);

		reservaController.actualizar(fechaE, fechaS, valor, formaPago, id);
		JOptionPane.showMessageDialog(this, "Registro modificado con éxito");
		eliminarTablaReservas();
		mostrarTablaReservas();
	}

	private void mostrarTablaHuespedes() {
		List<Huesped> huespedes = buscarHuespedes();

		try {
			for (Huesped huespedes1 : huespedes) {
				modeloHuesped.addRow(new Object[] {
						huespedes1.getId(), huespedes1.getNombre(), huespedes1.getApellido(),
						huespedes1.getFechaNacimiento(), huespedes1.getNacionalidad(), huespedes1.getTelefono(),
						huespedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void mostrarTablaHuespedesId() {
		eliminarTablaHuespedes();
		List<Huesped> huespedes = buscarHuespedesId();

		try {
			for (Huesped huespedes1 : huespedes) {
				modeloHuesped.addRow(new Object[] {
						huespedes1.getId(), huespedes1.getNombre(), huespedes1.getApellido(),
						huespedes1.getFechaNacimiento(), huespedes1.getNacionalidad(), huespedes1.getTelefono(),
						huespedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void mostrarTablaHuespedesApellido() {
		eliminarTablaHuespedes();
		List<Huesped> huespedes = buscarHuespedesApellido();

		try {
			for (Huesped huespedes1 : huespedes) {
				modeloHuesped.addRow(new Object[] {
						huespedes1.getId(), huespedes1.getNombre(), huespedes1.getApellido(),
						huespedes1.getFechaNacimiento(), huespedes1.getNacionalidad(), huespedes1.getTelefono(),
						huespedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void actualizarHuespedes() {
		int selectedRow = tbHuespedes.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar");
			return;
		}

		int id = (int) modeloHuesped.getValueAt(selectedRow, 0);
		String nombre = (String) modeloHuesped.getValueAt(selectedRow, 1);
		String apellido = (String) modeloHuesped.getValueAt(selectedRow, 2);
		Date fechaNacimiento = Date.valueOf(modeloHuesped.getValueAt(selectedRow, 3).toString());
		String nacionalidad = (String) modeloHuesped.getValueAt(selectedRow, 4);
		String telefono = (String) modeloHuesped.getValueAt(selectedRow, 5);
		int idReserva = (int) modeloHuesped.getValueAt(selectedRow, 6);

		huespedController.actualizar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
		JOptionPane.showMessageDialog(this, "Registro modificado con éxito");
		eliminarTablaHuespedes();
		mostrarTablaHuespedes();
	}

}
