package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;

public class FrmListado extends JFrame {

	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion04");
	static EntityManager em = fabrica.createEntityManager();
	
	private JPanel contentPane;
	private JTextArea txtSalida;
	private JComboBox cboTipo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListado frame = new FrmListado();
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
	public FrmListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Listado");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Filtro :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		txtSalida = new JTextArea();
		txtSalida.setBackground(Color.CYAN);
		txtSalida.setBounds(20, 91, 404, 147);
		contentPane.add(txtSalida);
		
		cboTipo = new JComboBox();
		cboTipo.setBounds(70, 30, 129, 20);
		contentPane.add(cboTipo);
		
		llenaCombo();
		
	}
	
	
	void registrar() {
		int usuario = cboTipo.getSelectedIndex();
		
		Usuario u = em.createQuery("Select u from Usuario u  where u.cod_usua =:xusua",Usuario.class).
				setParameter("xusua", usuario)
					.getSingleResult();
		System.out.println(u);
		
			imprimir("name...: "+ u.getNom_usua());
			imprimir("last name...: "+ u.getApe_usua());
			imprimir("tipo...: "+ u.getEst_usua());
			imprimir("correo..:"+ u.getCorreo());
			imprimir("---------------------");
		
	}
		/*List<Usuario> lstUsuarios = em.createQuery("Select u from Usuario u  where u.idtipo like =:xtipo",Usuario.class).
				setParameter("xtipo", "% + nombre + %")
					.getResultList();*/
		void llenaCombo() {
			
			// Obtener un listado de las categorias
			
			List<Usuario> lstUsu = 	
					em.createQuery("select u from Usuario u", Usuario.class).getResultList();
			//pasar el listado al combo 
			cboTipo.addItem("Seleccione ...");
			for (Usuario c: lstUsu) {
				cboTipo.addItem(c.getNom_usua());
				
			}
		}
		
		void listado() {
			
			}
			
			
		
		void imprimir(String s) {
			
			txtSalida.append(s + "\n");
		}
		
		
	
}
