/*
 * Copyright (C) 2013.  sergio
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dotrow.poo;

import com.dotrow.poo.core.*;
import com.dotrow.poo.exporters.JavaExporter;
import com.dotrow.poo.utils.ImageButton;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.locators.TypeLocator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 26/05/13 02:06 PM
 * Desc: Programa principal que interpreta un archivo en json a un conjunto de
 *       componentes para dibujarlos en un panel.
 */
public class Main extends JFrame implements ActionListener, MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 3206847208968227199L;

	private JPanel mapa, opciones, toolbox;
	private JScrollPane scroll, scroll2;
	private JButton generar;
	private JTextField dx, dy;

	private JMenuBar menu;
	private JMenu archivo, edit, help;
	private JMenuItem guardar, abrir, guardarComo, importar, exportar;
	private JMenuItem settings;
	private JMenuItem about;

	private ImageButton addClase, addComposicion, addAgregacion, addHerencia;

	private final JFileChooser fcOpen = new JFileChooser();
	private final JFileChooser fcSave = new JFileChooser();

	private List<Figure> figures = new ArrayList<Figure>();
	private boolean dragClase = false;
	private boolean newClase = false;
	private int relationStage = -1;
	private int relationType = 0;

	private Relation relation;

	private Clase claseNueva;

	private String projectName = null;

	@SuppressWarnings( "deprecation" )
	public Main() {
		super("Class Manager CIC (Unknow Project)");

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new JMenuBar();
		mapa = new JPanel();
		opciones = new JPanel();
		toolbox = new JPanel();
		dx = new JTextField("2000", 4);
		dy = new JTextField("1000", 4);
		archivo = new JMenu("Archivo");
		edit = new JMenu("Editar");
		help = new JMenu("Ayuda");
		abrir = new JMenuItem("Abrir");
		guardar = new JMenuItem("Guardar");
		guardarComo = new JMenuItem("Guardar como");
		importar = new JMenuItem("Importar");
		exportar = new JMenuItem("Exportar");
		settings = new JMenuItem("Opciones");
		about = new JMenuItem("Acerca de");

		generar = new JButton("Redimensionar");
		addClase = new ImageButton(getClass().getResource("resources/clase_icon.png"), getClass().getResource("resources/clase_icon.over.png"));
		addComposicion = new ImageButton(getClass().getResource("resources/composicion_icon.png"), getClass().getResource("resources/composicion_icon.over.png"));
		addAgregacion = new ImageButton(getClass().getResource("resources/agregacion_icon.png"), getClass().getResource("resources/agregacion_icon.over.png"));
		addHerencia = new ImageButton(getClass().getResource("resources/herencia_icon.png"), getClass().getResource("resources/herencia_icon.over.png"));
		scroll = new JScrollPane(mapa);
		scroll2 = new JScrollPane(toolbox);

		generar.addActionListener(this);
		guardar.addActionListener(this);
		guardarComo.addActionListener(this);
		abrir.addActionListener(this);
		exportar.addActionListener(this);

		mapa.setPreferredSize(new Dimension(2000, 1000));
		mapa.setLayout(null);
		mapa.setBackground(Color.black);

		opciones.setLayout(new FlowLayout());
		opciones.add(new JLabel("Ancho:"));
		opciones.add(dx);
		opciones.add(new JLabel("Alto:"));
		opciones.add(dy);
		opciones.add(generar);

		toolbox.setLayout(new GridLayout(4, 1));
		toolbox.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		toolbox.setBackground(Color.black);

		addClase.init();
		addComposicion.init();
		addAgregacion.init();
		addHerencia.init();
		toolbox.add(addClase);
		toolbox.add(addComposicion);
		toolbox.add(addAgregacion);
		toolbox.add(addHerencia);

		addClase.setOpaque(false);

		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(scroll2, BorderLayout.EAST);
		getContentPane().add(opciones, BorderLayout.SOUTH);

		mapa.addMouseListener(this);
		mapa.addMouseMotionListener(this);

		addClase.addMouseListener(this);
		addAgregacion.addMouseListener(this);
		addComposicion.addMouseListener(this);
		addHerencia.addMouseListener(this);

		menu.add(archivo);
		menu.add(edit);
		menu.add(help);

		archivo.add(abrir);
		archivo.add(guardar);
		archivo.add(guardarComo);
		archivo.addSeparator();
		archivo.add(importar);
		archivo.add(exportar);
		edit.add(settings);
		help.add(about);

		setJMenuBar(menu);

		setVisible(true);
	}

	public static void main( String args[] ) {
		new Main();
	}

	/**
	 * Guarda los cambios realizados en el codigo json al archivo json.txt
	 */
	public void guardar(boolean forceDialog) {

		File file = null;

		if( forceDialog || projectName == null) {
			int option = fcSave.showSaveDialog(this);
			if( option == JFileChooser.APPROVE_OPTION ) {
				file = fcSave.getSelectedFile();
				setTitle("Class Manager CIC (" + file.getPath() + ")");
				projectName = file.getPath();
			} else {
				return;
			}
		}

		if( !forceDialog && projectName != null) {
			file = new File(projectName);
		}

		try {
			JSONSerializer serializer = new JSONSerializer();
			String out = serializer.include("dimension")
					.include("elementType")
					.include("posicion")
					.include("nombre")
					.include("atributos.*")
					.include("metodos.*")
					.include("relaciones.toClass")
					.include("relaciones.type")
					.exclude("*").prettyPrint(ClassManager.getClases());

			DataOutputStream fw = new DataOutputStream(new FileOutputStream(file));
			fw.writeBytes(out);
			fw.close();
		} catch( IOException ioe ) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Funcion que genera el mapa de componentes leidos desde el archivo json.txt
	 */
	public void abrir() {

		ClassManager.clearAll();

		int option = fcOpen.showOpenDialog(this);

		if( option == JFileChooser.APPROVE_OPTION ) {
			File file = fcOpen.getSelectedFile();

			String codigo = "";
			String line = "";
			try {
				DataInputStream fr = new DataInputStream(new FileInputStream(file));
				while( ( line = fr.readLine() ) != null )
					codigo += line + "\n";
			} catch( IOException ioe ) {
				ioe.printStackTrace();
			}

			mapa.setPreferredSize(new Dimension(Integer.parseInt(dx.getText()), Integer.parseInt(dy.getText())));
			mapa.removeAll();

			JSONDeserializer<List<Figure>> deserializer = new JSONDeserializer<List<Figure>>();

			String jsonin = codigo;
			jsonin = jsonin.replaceAll("largo:", "width:").replaceAll("alto:", "height:");

			@SuppressWarnings( "unchecked" )
			TypeLocator<String> add = new TypeLocator<String>("elementType")
					.add("CLASE", Clase.class);

			@SuppressWarnings( "unchecked" )
			TypeLocator<String> rels = new TypeLocator<String>("type")
					.add("AGGREGATION", Agregacion.class)
					.add("COMPOSITION", Composicion.class)
					.add("INHERITANCE", Herencia.class);

			List<Figure> elems = deserializer.use("values.relaciones.values", rels).use("values", add).deserialize(jsonin);

			for( Figure e : elems ) {
				if( e != null ) {
					Rectangle r = new Rectangle();
					r.setSize(e.getDimension());
					r.setLocation(e.getPosicion());
					e.setBounds(r);
					e.setSize(new Dimension(e.getDimension().width + 1, e.getDimension().height + 1));
					e.init();
					mapa.add(e);
					figures.add(e);

					if( e instanceof Clase ) {
						Clase clase = (Clase) e;
						clase.addMouseListener(this);
						ClassManager.addClase(clase);
						clase.setUi(this);
					}
				}
			}

			for( Clase c : ClassManager.getClases() ) {
				for( Relation rel : c.getRelaciones() ) {
					if( rel != null ) {
						Clase to = ClassManager.getClaseByName(rel.getToClass());
						if( to != null ) {
							rel.setFrom(c);
							rel.setTo(to);
							rel.init();

							// add move listener in order to keep updated the relation graphic
							c.addMoveListener(rel);
							to.addMoveListener(rel);

							mapa.add(rel);
						}
					}
				}
			}

			projectName = file.getPath();
			setTitle("Class Manager CIC (" + file.getPath() + ")");

			mapa.revalidate();
			mapa.repaint();
		}
	}

	public void addFigura( Figure figure ) {
		figure.init();
		mapa.add(figure);
		mapa.repaint();
	}

	public void removeFigura( Figure figure ) {
		mapa.remove(figure);
		mapa.repaint();
	}

	public void actionPerformed( ActionEvent evento ) {
		String cmd = evento.getActionCommand();
		if( cmd.equals("Generar") ) {
			abrir();
		} else if( cmd.equals("Abrir") ) {
			abrir();
		} else if( cmd.equals("Guardar") ) {
			guardar(false);
		} else if( cmd.equals("Guardar como") ) {
			guardar(true);
		} else if( cmd.equals("Exportar") ) {
			JavaExporter je = new JavaExporter(ClassManager.getClases());
			je.generate();
			System.out.println(je.getCode());
		}
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
		int x = e.getX();
		int y = e.getY();
		if( newClase ) {
			claseNueva = new Clase();
			claseNueva.setUi(this);

			Rectangle r = new Rectangle();
			r.setSize(new Dimension(200, 200));
			r.setLocation(new Point(x, y));
			claseNueva.setBounds(r);
			// for serialization
			claseNueva.setPosicion(r.getLocation());
			claseNueva.setDimension(r.getSize());

			claseNueva.addMouseListener(this);

			addFigura(claseNueva);
			mapa.revalidate();
			mapa.repaint();
			newClase = false;
			dragClase = true;

			ClassManager.addClase(claseNueva);
		}

		if( dragClase ) {
			Rectangle r = new Rectangle();
			r.setSize(new Dimension(200, 200));
			r.setLocation(new Point(x, y));
			claseNueva.setBounds(r);

			// for serialization
			claseNueva.setPosicion(r.getLocation());
			claseNueva.setDimension(r.getSize());

			mapa.revalidate();
			mapa.repaint();
		}

	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		Object source = e.getSource();
		if( source == addAgregacion ) {
			relationType = 1;
			relationStage = 0;
		}
		if( source == addComposicion ) {
			relationType = 2;
			relationStage = 0;
		}
		if( source == addHerencia ) {
			relationType = 3;
			relationStage = 0;
		} else if( source == addClase ) {
			newClase = !newClase;
		} else if( source instanceof Clase ) {
			Clase clase = (Clase) source;
			dragClase = false;
			if( relationStage == 0 ) {
				switch( relationType ) {
					case 1:
						relation = new Agregacion();
						break;
					case 2:
						relation = new Composicion();
						break;
					case 3:
						relation = new Herencia();
						break;
				}
				relation.setFrom(clase);

				clase.addRelacion(relation);
				clase.addMoveListener(relation);

				relationStage = 1;
			} else if( relationStage == 1 ) {
				relation.setTo(clase);
				clase.addMoveListener(relation);

				addFigura(relation);

				relationStage = -1;
			}
		}

	}

	@Override
	public void mousePressed( MouseEvent e ) {
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
	}

	@Override
	public void mouseEntered( MouseEvent e ) {

	}

	@Override
	public void mouseExited( MouseEvent e ) {

	}
}
