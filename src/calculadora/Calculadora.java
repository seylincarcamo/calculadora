package calculadora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import operations.Resolve;
import resources.GUI;

/**
 *
 *
 * @author Seilyn
 */
public class Calculadora extends javax.swing.JFrame implements ActionListener, KeyListener {

    private int x, y;
    private boolean esperarNuevo;
    private boolean esperarOperacion;
    private boolean esperarIgual;
    private String signoAnterior;
    private Resolve calcular;

    /**
     * Creates new form Calculadora
     */
    public Calculadora() {
        Timer t;
        t = new Timer(500, this);
        t.start();
        esperarNuevo = false;
        esperarOperacion = false;
        esperarIgual = true;
        signoAnterior = "";
        calcular = new Resolve();
        initComponents();
        setSize(326, 570);
        initButtons();
        setLocationRelativeTo(null);
    }

    /*METODOS PARA TODOS LOS BOTONES**/
    private void btn_mouseEntry(JButton btn) {
        btn.setBackground(new Color(104, 104, 104));
    }

    private void btn_mouseExited(JButton btn) {
        btn.setBackground(new Color(24, 24, 24));
    }

    private void btn_numerics_mouseExited(JButton btn) {
        btn.setBackground(new Color(0, 0, 0));
    }

    private void btn_igual_mouseEntry(JButton btn) {
        btn.setBackground(new Color(61, 187, 61));
    }

    private void btn_igual_mouseExited(JButton btn) {
        btn.setBackground(new Color(36, 108, 36));
    }

    private boolean isEmptyIngreso() {
        return (txt_ingresar.getText().length() == 0) ? true : false;
    }

    private boolean esperarIgual() {
        return esperarIgual;
    }

    private void esperarIgual(boolean b) {
        esperarIgual = b;
    }

    private boolean esperarNuevo() {
        return esperarNuevo;
    }

    private void esperarNuevo(boolean b) {
        esperarNuevo = b;
    }

    private boolean esperarOperacion() {
        return esperarOperacion;
    }

    private void esperarOperacion(boolean b) {
        esperarOperacion = b;
    }

    public String getSignoAnterior() {
        return signoAnterior;
    }

    public void setSignoAnterior(String signo) {
        signoAnterior = signo;
    }

    private void set_ingreso_Numero_Signo(String ns) {
        if (esperarNuevo()) {
            esperarOperacion(false);
            esperarNuevo(false);
            esperarIgual(false);
            txt_ingresar.setText(ns);
        } else {
            esperarOperacion(false);
            String numeros_anteriores = txt_ingresar.getText();
            txt_ingresar.setText(numeros_anteriores + ns);
            existCeroPrimero();
        }
    }

    private void acumular(String s) {
        String acumulado;
        String ingresado;
        //casos especiales
        specialCases();
        //Si no hay signo anterior debe almacenarse el nuevo
        if (getSignoAnterior().length() == 0 && !esperarOperacion()) {
            setSignoAnterior(s);
            ingresado = txt_ingresar.getText();

            calcular.acumular(ingresado.trim());
            txt_acumular.setText(ingresado + s);
            txt_ingresar.setText(calcular.getTotal() + "");
            esperarOperacion(true);

        } else if (getSignoAnterior().equals("=")) {
            ingresado = txt_ingresar.getText();
            txt_acumular.setText(ingresado + s);
            setSignoAnterior(s);
            esperarOperacion(true);
            esperarNuevo(true);
            esperarIgual(true);
        } else if (!esperarOperacion()) {
            acumulado = txt_acumular.getText();
            ingresado = txt_ingresar.getText();
            calcular.acumular(getSignoAnterior(), ingresado.trim());
            if (ingresado.length() >= 2 && ingresado.charAt(0) == '-') {
                ingresado = "(" + ingresado + ")";
            }
            txt_acumular.setText(acumulado + ingresado + s);
            txt_ingresar.setText(calcular.getTotal() + "");
            esperarOperacion(true);
            setSignoAnterior(s);
        } else {
            setSignoAnterior(s);
            acumulado = txt_acumular.getText();
            txt_acumular.setText(acumulado.substring(0, acumulado.length() - 1) + getSignoAnterior());
        }

    }

    //Eliminar un cero al inicio del numero
    private void existCeroPrimero() {
        String numeros_anteriores = txt_ingresar.getText();
        if (numeros_anteriores.length() >= 2) {
            if (numeros_anteriores.charAt(0) == '0') {
                if ((numeros_anteriores.charAt(1) >= '0') && (numeros_anteriores.charAt(1) <= '9')) {
                    txt_ingresar.setText(numeros_anteriores.substring(1));
                }
            } else if (numeros_anteriores.charAt(0) == '-') {
                if (numeros_anteriores.length() >= 3) {
                    if (numeros_anteriores.charAt(1) == '0') {
                        if (numeros_anteriores.charAt(2) != '.') {
                            txt_ingresar.setText("-" + numeros_anteriores.substring(2));
                        }
                    }
                }
            }
        }
    }

    private boolean existPoint() {
        String numeros_actual = txt_ingresar.getText();
        for (int x = 0; x <= numeros_actual.length() - 1; x++) {
            if (numeros_actual.charAt(x) == '.') {
                return true;
            }
        }
        return false;
    }

    private void ordenarNegative() {
        String save = txt_ingresar.getText();
        txt_ingresar.setText("-" + save.replace("-", ""));
    }

    private boolean existNegative() {
        String numeros_actual = txt_ingresar.getText();
        for (int x = 0; x <= numeros_actual.length() - 1; x++) {
            if (numeros_actual.charAt(x) == '-') {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        panel_main = new javax.swing.JPanel();
        panel_titlebar = new javax.swing.JPanel();
        btn_exit = new javax.swing.JButton();
        btn_minimize = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panel_mode = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_mode = new javax.swing.JButton();
        txt_ingresar = new javax.swing.JTextField();
        panel_buttons = new javax.swing.JPanel();
        btn_porcentaje = new javax.swing.JButton();
        btn_borraringresado = new javax.swing.JButton();
        btn_borrartodo = new javax.swing.JButton();
        btn_borraruno = new javax.swing.JButton();
        btn_reciproc = new javax.swing.JButton();
        btn_elevarcuadrado = new javax.swing.JButton();
        btn_raizcuadrada = new javax.swing.JButton();
        btn_division = new javax.swing.JButton();
        btn_siete = new javax.swing.JButton();
        btn_ocho = new javax.swing.JButton();
        btn_nueve = new javax.swing.JButton();
        btn_multiplicacion = new javax.swing.JButton();
        btn_cuatro = new javax.swing.JButton();
        btn_cinco = new javax.swing.JButton();
        btn_seis = new javax.swing.JButton();
        btn_resta = new javax.swing.JButton();
        btn_uno = new javax.swing.JButton();
        btn_dos = new javax.swing.JButton();
        btn_tres = new javax.swing.JButton();
        btn_suma = new javax.swing.JButton();
        btn_negate = new javax.swing.JButton();
        btn_cero = new javax.swing.JButton();
        btn_punto = new javax.swing.JButton();
        btn_igual = new javax.swing.JButton();
        txt_acumular = new javax.swing.JTextField();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(31, 31, 31));
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(327, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(327, 540));

        panel_main.setBackground(new java.awt.Color(31, 31, 31));

        panel_titlebar.setAlignmentX(0.0F);
        panel_titlebar.setOpaque(false);
        panel_titlebar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_titlebarMouseDragged(evt);
            }
        });
        panel_titlebar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_titlebarMousePressed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(255, 255, 255));
        btn_exit.setFont(new java.awt.Font("Humanst521 BT", 0, 25)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("x");
        btn_exit.setAlignmentY(0.0F);
        btn_exit.setContentAreaFilled(false);
        btn_exit.setFocusPainted(false);
        btn_exit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_exit.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_exit.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_exit.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exitMouseExited(evt);
            }
        });

        btn_minimize.setFont(new java.awt.Font("Humanst521 BT", 0, 25)); // NOI18N
        btn_minimize.setForeground(new java.awt.Color(255, 255, 255));
        btn_minimize.setText("-");
        btn_minimize.setAlignmentY(0.0F);
        btn_minimize.setContentAreaFilled(false);
        btn_minimize.setFocusPainted(false);
        btn_minimize.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_minimize.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_minimize.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_minimize.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_minimizeMouseExited(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Calculadora");

        javax.swing.GroupLayout panel_titlebarLayout = new javax.swing.GroupLayout(panel_titlebar);
        panel_titlebar.setLayout(panel_titlebarLayout);
        panel_titlebarLayout.setHorizontalGroup(
            panel_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_titlebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(btn_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_titlebarLayout.setVerticalGroup(
            panel_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_titlebarLayout.createSequentialGroup()
                .addGroup(panel_titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btn_exit.getAccessibleContext().setAccessibleParent(panel_titlebar);
        btn_minimize.getAccessibleContext().setAccessibleParent(panel_titlebar);

        panel_mode.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Estándar");

        btn_mode.setBackground(new java.awt.Color(31, 31, 31));
        btn_mode.setFont(new java.awt.Font("Humanst521 BT", 0, 25)); // NOI18N
        btn_mode.setForeground(new java.awt.Color(255, 255, 255));
        btn_mode.setText("•");
        btn_mode.setToolTipText("");
        btn_mode.setAlignmentY(0.0F);
        btn_mode.setContentAreaFilled(false);
        btn_mode.setFocusPainted(false);
        btn_mode.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_mode.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_mode.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_mode.setOpaque(true);
        btn_mode.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_mode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_modeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_modeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_modeLayout = new javax.swing.GroupLayout(panel_mode);
        panel_mode.setLayout(panel_modeLayout);
        panel_modeLayout.setHorizontalGroup(
            panel_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_modeLayout.createSequentialGroup()
                .addComponent(btn_mode, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_modeLayout.setVerticalGroup(
            panel_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(btn_mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.getAccessibleContext().setAccessibleParent(panel_mode);
        btn_mode.getAccessibleContext().setAccessibleParent(panel_mode);

        txt_ingresar.setBackground(new java.awt.Color(31, 31, 31));
        txt_ingresar.setFont(new java.awt.Font("Nirmala UI", 1, 45)); // NOI18N
        txt_ingresar.setForeground(new java.awt.Color(255, 255, 255));
        txt_ingresar.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt_ingresar.setText("0");
        txt_ingresar.setBorder(null);
        txt_ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txt_ingresar.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_ingresar.setSelectionColor(new java.awt.Color(61, 187, 61));
        txt_ingresar.setVerifyInputWhenFocusTarget(false);

        panel_buttons.setBackground(new java.awt.Color(31, 31, 31));
        panel_buttons.setOpaque(false);
        panel_buttons.setLayout(new java.awt.GridLayout(6, 4, 2, 2));

        btn_porcentaje.setBackground(new java.awt.Color(24, 24, 24));
        btn_porcentaje.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_porcentaje.setForeground(new java.awt.Color(245, 245, 245));
        btn_porcentaje.setText("%");
        btn_porcentaje.setAlignmentY(0.0F);
        btn_porcentaje.setContentAreaFilled(false);
        btn_porcentaje.setFocusPainted(false);
        btn_porcentaje.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_porcentaje.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_porcentaje.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_porcentaje.setOpaque(true);
        btn_porcentaje.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_porcentaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_porcentajeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_porcentajeMouseExited(evt);
            }
        });
        panel_buttons.add(btn_porcentaje);

        btn_borraringresado.setBackground(new java.awt.Color(24, 24, 24));
        btn_borraringresado.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_borraringresado.setForeground(new java.awt.Color(255, 255, 255));
        btn_borraringresado.setText("CE");
        btn_borraringresado.setAlignmentY(0.0F);
        btn_borraringresado.setContentAreaFilled(false);
        btn_borraringresado.setFocusPainted(false);
        btn_borraringresado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_borraringresado.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_borraringresado.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_borraringresado.setOpaque(true);
        btn_borraringresado.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_borraringresado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_borraringresadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_borraringresadoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_borraringresado);

        btn_borrartodo.setBackground(new java.awt.Color(24, 24, 24));
        btn_borrartodo.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_borrartodo.setForeground(new java.awt.Color(255, 255, 255));
        btn_borrartodo.setText("C");
        btn_borrartodo.setToolTipText("");
        btn_borrartodo.setAlignmentY(0.0F);
        btn_borrartodo.setContentAreaFilled(false);
        btn_borrartodo.setFocusPainted(false);
        btn_borrartodo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_borrartodo.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_borrartodo.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_borrartodo.setOpaque(true);
        btn_borrartodo.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_borrartodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_borrartodoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_borrartodoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_borrartodoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_borrartodo);

        btn_borraruno.setBackground(new java.awt.Color(24, 24, 24));
        btn_borraruno.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btn_borraruno.setForeground(new java.awt.Color(255, 255, 255));
        btn_borraruno.setText("←");
        btn_borraruno.setToolTipText("");
        btn_borraruno.setAlignmentY(0.0F);
        btn_borraruno.setContentAreaFilled(false);
        btn_borraruno.setFocusPainted(false);
        btn_borraruno.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_borraruno.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_borraruno.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_borraruno.setOpaque(true);
        btn_borraruno.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_borraruno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_borrarunoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_borrarunoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_borraruno);

        btn_reciproc.setBackground(new java.awt.Color(24, 24, 24));
        btn_reciproc.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_reciproc.setForeground(new java.awt.Color(245, 245, 245));
        btn_reciproc.setText("1/x");
        btn_reciproc.setAlignmentY(0.0F);
        btn_reciproc.setContentAreaFilled(false);
        btn_reciproc.setFocusPainted(false);
        btn_reciproc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_reciproc.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_reciproc.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_reciproc.setOpaque(true);
        btn_reciproc.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_reciproc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reciprocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reciprocMouseExited(evt);
            }
        });
        panel_buttons.add(btn_reciproc);

        btn_elevarcuadrado.setBackground(new java.awt.Color(24, 24, 24));
        btn_elevarcuadrado.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_elevarcuadrado.setForeground(new java.awt.Color(245, 245, 245));
        btn_elevarcuadrado.setText("x²");
        btn_elevarcuadrado.setAlignmentY(0.0F);
        btn_elevarcuadrado.setContentAreaFilled(false);
        btn_elevarcuadrado.setFocusPainted(false);
        btn_elevarcuadrado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_elevarcuadrado.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_elevarcuadrado.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_elevarcuadrado.setOpaque(true);
        btn_elevarcuadrado.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_elevarcuadrado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_elevarcuadradoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_elevarcuadradoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_elevarcuadrado);

        btn_raizcuadrada.setBackground(new java.awt.Color(24, 24, 24));
        btn_raizcuadrada.setFont(new java.awt.Font("Humanst521 BT", 0, 18)); // NOI18N
        btn_raizcuadrada.setForeground(new java.awt.Color(245, 245, 245));
        btn_raizcuadrada.setText("²√x");
        btn_raizcuadrada.setAlignmentY(0.0F);
        btn_raizcuadrada.setContentAreaFilled(false);
        btn_raizcuadrada.setFocusPainted(false);
        btn_raizcuadrada.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_raizcuadrada.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_raizcuadrada.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_raizcuadrada.setOpaque(true);
        btn_raizcuadrada.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_raizcuadrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_raizcuadradaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_raizcuadradaMouseExited(evt);
            }
        });
        panel_buttons.add(btn_raizcuadrada);

        btn_division.setBackground(new java.awt.Color(24, 24, 24));
        btn_division.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        btn_division.setForeground(new java.awt.Color(255, 255, 255));
        btn_division.setText("÷");
        btn_division.setToolTipText("");
        btn_division.setAlignmentY(0.0F);
        btn_division.setContentAreaFilled(false);
        btn_division.setFocusPainted(false);
        btn_division.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_division.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_division.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_division.setOpaque(true);
        btn_division.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_divisionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_divisionMouseExited(evt);
            }
        });
        panel_buttons.add(btn_division);

        btn_siete.setBackground(new java.awt.Color(0, 0, 0));
        btn_siete.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_siete.setForeground(new java.awt.Color(255, 255, 255));
        btn_siete.setText("7");
        btn_siete.setAlignmentY(0.0F);
        btn_siete.setContentAreaFilled(false);
        btn_siete.setFocusPainted(false);
        btn_siete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_siete.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_siete.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_siete.setOpaque(true);
        btn_siete.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_siete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sieteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sieteMouseExited(evt);
            }
        });
        panel_buttons.add(btn_siete);

        btn_ocho.setBackground(new java.awt.Color(0, 0, 0));
        btn_ocho.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_ocho.setForeground(new java.awt.Color(255, 255, 255));
        btn_ocho.setText("8");
        btn_ocho.setAlignmentY(0.0F);
        btn_ocho.setContentAreaFilled(false);
        btn_ocho.setFocusPainted(false);
        btn_ocho.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_ocho.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_ocho.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_ocho.setOpaque(true);
        btn_ocho.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_ocho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ochoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ochoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_ocho);

        btn_nueve.setBackground(new java.awt.Color(0, 0, 0));
        btn_nueve.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_nueve.setForeground(new java.awt.Color(255, 255, 255));
        btn_nueve.setText("9");
        btn_nueve.setAlignmentY(0.0F);
        btn_nueve.setContentAreaFilled(false);
        btn_nueve.setFocusPainted(false);
        btn_nueve.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_nueve.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_nueve.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_nueve.setOpaque(true);
        btn_nueve.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_nueve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nueveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_nueveMouseExited(evt);
            }
        });
        panel_buttons.add(btn_nueve);

        btn_multiplicacion.setBackground(new java.awt.Color(24, 24, 24));
        btn_multiplicacion.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        btn_multiplicacion.setForeground(new java.awt.Color(255, 255, 255));
        btn_multiplicacion.setText("x");
        btn_multiplicacion.setToolTipText("");
        btn_multiplicacion.setAlignmentY(0.0F);
        btn_multiplicacion.setContentAreaFilled(false);
        btn_multiplicacion.setFocusPainted(false);
        btn_multiplicacion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_multiplicacion.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_multiplicacion.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_multiplicacion.setOpaque(true);
        btn_multiplicacion.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_multiplicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_multiplicacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_multiplicacionMouseExited(evt);
            }
        });
        panel_buttons.add(btn_multiplicacion);

        btn_cuatro.setBackground(new java.awt.Color(0, 0, 0));
        btn_cuatro.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_cuatro.setForeground(new java.awt.Color(255, 255, 255));
        btn_cuatro.setText("4");
        btn_cuatro.setAlignmentY(0.0F);
        btn_cuatro.setContentAreaFilled(false);
        btn_cuatro.setFocusPainted(false);
        btn_cuatro.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_cuatro.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_cuatro.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_cuatro.setOpaque(true);
        btn_cuatro.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_cuatro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cuatroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cuatroMouseExited(evt);
            }
        });
        panel_buttons.add(btn_cuatro);

        btn_cinco.setBackground(new java.awt.Color(0, 0, 0));
        btn_cinco.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_cinco.setForeground(new java.awt.Color(255, 255, 255));
        btn_cinco.setText("5");
        btn_cinco.setAlignmentY(0.0F);
        btn_cinco.setContentAreaFilled(false);
        btn_cinco.setFocusPainted(false);
        btn_cinco.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_cinco.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_cinco.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_cinco.setOpaque(true);
        btn_cinco.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cincoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cincoMouseExited(evt);
            }
        });
        btn_cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cincoActionPerformed(evt);
            }
        });
        panel_buttons.add(btn_cinco);

        btn_seis.setBackground(new java.awt.Color(0, 0, 0));
        btn_seis.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_seis.setForeground(new java.awt.Color(255, 255, 255));
        btn_seis.setText("6");
        btn_seis.setAlignmentY(0.0F);
        btn_seis.setContentAreaFilled(false);
        btn_seis.setFocusPainted(false);
        btn_seis.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_seis.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_seis.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_seis.setOpaque(true);
        btn_seis.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_seis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_seisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_seisMouseExited(evt);
            }
        });
        panel_buttons.add(btn_seis);

        btn_resta.setBackground(new java.awt.Color(24, 24, 24));
        btn_resta.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        btn_resta.setForeground(new java.awt.Color(255, 255, 255));
        btn_resta.setText("-");
        btn_resta.setAlignmentY(0.0F);
        btn_resta.setContentAreaFilled(false);
        btn_resta.setFocusPainted(false);
        btn_resta.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_resta.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_resta.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_resta.setOpaque(true);
        btn_resta.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_resta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_restaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_restaMouseExited(evt);
            }
        });
        panel_buttons.add(btn_resta);

        btn_uno.setBackground(new java.awt.Color(0, 0, 0));
        btn_uno.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_uno.setForeground(new java.awt.Color(255, 255, 255));
        btn_uno.setText("1");
        btn_uno.setAlignmentY(0.0F);
        btn_uno.setContentAreaFilled(false);
        btn_uno.setFocusPainted(false);
        btn_uno.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_uno.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_uno.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_uno.setOpaque(true);
        btn_uno.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_uno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_unoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_unoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_uno);

        btn_dos.setBackground(new java.awt.Color(0, 0, 0));
        btn_dos.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_dos.setForeground(new java.awt.Color(255, 255, 255));
        btn_dos.setText("2");
        btn_dos.setAlignmentY(0.0F);
        btn_dos.setContentAreaFilled(false);
        btn_dos.setFocusPainted(false);
        btn_dos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_dos.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_dos.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_dos.setOpaque(true);
        btn_dos.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dosMouseExited(evt);
            }
        });
        panel_buttons.add(btn_dos);

        btn_tres.setBackground(new java.awt.Color(0, 0, 0));
        btn_tres.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_tres.setForeground(new java.awt.Color(255, 255, 255));
        btn_tres.setText("3");
        btn_tres.setAlignmentY(0.0F);
        btn_tres.setContentAreaFilled(false);
        btn_tres.setFocusPainted(false);
        btn_tres.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_tres.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_tres.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_tres.setOpaque(true);
        btn_tres.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_tres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_tresMouseExited(evt);
            }
        });
        panel_buttons.add(btn_tres);

        btn_suma.setBackground(new java.awt.Color(24, 24, 24));
        btn_suma.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        btn_suma.setForeground(new java.awt.Color(255, 255, 255));
        btn_suma.setText("+");
        btn_suma.setAlignmentY(0.0F);
        btn_suma.setContentAreaFilled(false);
        btn_suma.setFocusPainted(false);
        btn_suma.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_suma.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_suma.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_suma.setOpaque(true);
        btn_suma.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_suma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sumaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sumaMouseExited(evt);
            }
        });
        panel_buttons.add(btn_suma);

        btn_negate.setBackground(new java.awt.Color(0, 0, 0));
        btn_negate.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_negate.setForeground(new java.awt.Color(255, 255, 255));
        btn_negate.setText("±");
        btn_negate.setAlignmentY(0.0F);
        btn_negate.setContentAreaFilled(false);
        btn_negate.setFocusPainted(false);
        btn_negate.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_negate.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_negate.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_negate.setOpaque(true);
        btn_negate.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_negate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_negateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_negateMouseExited(evt);
            }
        });
        panel_buttons.add(btn_negate);

        btn_cero.setBackground(new java.awt.Color(0, 0, 0));
        btn_cero.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_cero.setForeground(new java.awt.Color(255, 255, 255));
        btn_cero.setText("0");
        btn_cero.setAlignmentY(0.0F);
        btn_cero.setContentAreaFilled(false);
        btn_cero.setFocusPainted(false);
        btn_cero.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_cero.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_cero.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_cero.setOpaque(true);
        btn_cero.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_cero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ceroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ceroMouseExited(evt);
            }
        });
        panel_buttons.add(btn_cero);

        btn_punto.setBackground(new java.awt.Color(0, 0, 0));
        btn_punto.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_punto.setForeground(new java.awt.Color(255, 255, 255));
        btn_punto.setText(".");
        btn_punto.setAlignmentY(0.0F);
        btn_punto.setContentAreaFilled(false);
        btn_punto.setFocusPainted(false);
        btn_punto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_punto.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_punto.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_punto.setOpaque(true);
        btn_punto.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_punto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_puntoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_puntoMouseExited(evt);
            }
        });
        panel_buttons.add(btn_punto);

        btn_igual.setBackground(new java.awt.Color(36, 108, 36));
        btn_igual.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        btn_igual.setForeground(new java.awt.Color(255, 255, 255));
        btn_igual.setText("=");
        btn_igual.setAlignmentY(0.0F);
        btn_igual.setContentAreaFilled(false);
        btn_igual.setFocusPainted(false);
        btn_igual.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_igual.setMaximumSize(new java.awt.Dimension(40, 35));
        btn_igual.setMinimumSize(new java.awt.Dimension(40, 35));
        btn_igual.setOpaque(true);
        btn_igual.setPreferredSize(new java.awt.Dimension(40, 35));
        btn_igual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_igualMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_igualMouseExited(evt);
            }
        });
        panel_buttons.add(btn_igual);

        txt_acumular.setEditable(false);
        txt_acumular.setBackground(new java.awt.Color(31, 31, 31));
        txt_acumular.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        txt_acumular.setForeground(new java.awt.Color(204, 204, 204));
        txt_acumular.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt_acumular.setText("");
        txt_acumular.setBorder(null);

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_titlebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_buttons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_mode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ingresar)
                    .addComponent(txt_acumular))
                .addContainerGap())
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addComponent(panel_titlebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_acumular, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_buttons, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseExited
        btn_exit.setBackground(new Color(35, 35, 35));
    }//GEN-LAST:event_btn_exitMouseExited

    private void btn_exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseEntered
        btn_exit.setOpaque(true);
        btn_exit.setBackground(new Color(255, 81, 81));
    }//GEN-LAST:event_btn_exitMouseEntered

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizeMouseExited
        btn_minimize.setBackground(GUI.color_background);
    }//GEN-LAST:event_btn_minimizeMouseExited

    private void btn_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizeMouseEntered
        btn_minimize.setOpaque(true);
        btn_minimize.setBackground(new Color(160, 160, 160));
    }//GEN-LAST:event_btn_minimizeMouseEntered

    private void btn_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizeMouseClicked
        this.setExtendedState(this.CROSSHAIR_CURSOR);
    }//GEN-LAST:event_btn_minimizeMouseClicked

    private void panel_titlebarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_titlebarMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panel_titlebarMousePressed

    private void panel_titlebarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_titlebarMouseDragged
        int xMouse = evt.getXOnScreen();
        int yMouse = evt.getYOnScreen();
        this.setLocation(xMouse - x, yMouse - y);
    }//GEN-LAST:event_panel_titlebarMouseDragged

    private void btn_porcentajeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_porcentajeMouseEntered
        btn_mouseEntry(btn_porcentaje);
    }//GEN-LAST:event_btn_porcentajeMouseEntered

    private void btn_porcentajeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_porcentajeMouseExited
        btn_mouseExited(btn_porcentaje);
    }//GEN-LAST:event_btn_porcentajeMouseExited

    private void btn_borraringresadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borraringresadoMouseEntered
        btn_mouseEntry(btn_borraringresado);
    }//GEN-LAST:event_btn_borraringresadoMouseEntered

    private void btn_borraringresadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borraringresadoMouseExited
        btn_mouseExited(btn_borraringresado);
    }//GEN-LAST:event_btn_borraringresadoMouseExited

    private void btn_borrartodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrartodoMouseClicked
        disable_enableAllButtons(true);
        esperarIgual(false);
        esperarNuevo(false);
        esperarOperacion(false);
        setSignoAnterior("");
        calcular.reset();
        txt_acumular.setText("");
        txt_ingresar.setText("0");
    }//GEN-LAST:event_btn_borrartodoMouseClicked

    private void btn_borrartodoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrartodoMouseEntered
        btn_mouseEntry(btn_borrartodo);
    }//GEN-LAST:event_btn_borrartodoMouseEntered

    private void btn_borrartodoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrartodoMouseExited
        btn_mouseExited(btn_borrartodo);
    }//GEN-LAST:event_btn_borrartodoMouseExited

    private void btn_borrarunoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrarunoMouseEntered
        btn_mouseEntry(btn_borraruno);
    }//GEN-LAST:event_btn_borrarunoMouseEntered

    private void btn_borrarunoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrarunoMouseExited
        btn_mouseExited(btn_borraruno);
    }//GEN-LAST:event_btn_borrarunoMouseExited

    private void btn_reciprocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reciprocMouseEntered
        btn_mouseEntry(btn_reciproc);
    }//GEN-LAST:event_btn_reciprocMouseEntered

    private void btn_reciprocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reciprocMouseExited
        btn_mouseExited(btn_reciproc);
    }//GEN-LAST:event_btn_reciprocMouseExited

    private void btn_elevarcuadradoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_elevarcuadradoMouseEntered
        btn_mouseEntry(btn_elevarcuadrado);
    }//GEN-LAST:event_btn_elevarcuadradoMouseEntered

    private void btn_elevarcuadradoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_elevarcuadradoMouseExited
        btn_mouseExited(btn_elevarcuadrado);
    }//GEN-LAST:event_btn_elevarcuadradoMouseExited

    private void btn_raizcuadradaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_raizcuadradaMouseEntered
        btn_mouseEntry(btn_raizcuadrada);
    }//GEN-LAST:event_btn_raizcuadradaMouseEntered

    private void btn_raizcuadradaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_raizcuadradaMouseExited
        btn_mouseExited(btn_raizcuadrada);// TODO add your handling code here:
    }//GEN-LAST:event_btn_raizcuadradaMouseExited

    private void btn_divisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_divisionMouseEntered
        btn_mouseEntry(btn_division);
    }//GEN-LAST:event_btn_divisionMouseEntered

    private void btn_divisionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_divisionMouseExited
        btn_mouseExited(btn_division);// TODO add your handling code here:
    }//GEN-LAST:event_btn_divisionMouseExited

    private void btn_sieteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sieteMouseEntered
        btn_mouseEntry(btn_siete); // TODO add your handling code here:
    }//GEN-LAST:event_btn_sieteMouseEntered

    private void btn_sieteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sieteMouseExited
        btn_numerics_mouseExited(btn_siete);
    }//GEN-LAST:event_btn_sieteMouseExited

    private void btn_ochoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ochoMouseEntered
        btn_mouseEntry(btn_ocho); // TODO add your handling code here:
    }//GEN-LAST:event_btn_ochoMouseEntered

    private void btn_ochoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ochoMouseExited
        btn_numerics_mouseExited(btn_ocho);
    }//GEN-LAST:event_btn_ochoMouseExited

    private void btn_nueveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nueveMouseEntered
        btn_mouseEntry(btn_nueve); // TODO add your handling code here:
    }//GEN-LAST:event_btn_nueveMouseEntered

    private void btn_nueveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nueveMouseExited
        btn_numerics_mouseExited(btn_nueve);
    }//GEN-LAST:event_btn_nueveMouseExited

    private void btn_multiplicacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_multiplicacionMouseEntered
        btn_mouseEntry(btn_multiplicacion);// TODO add your handling code here:
    }//GEN-LAST:event_btn_multiplicacionMouseEntered

    private void btn_multiplicacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_multiplicacionMouseExited
        btn_mouseExited(btn_multiplicacion);
    }//GEN-LAST:event_btn_multiplicacionMouseExited

    private void btn_cuatroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cuatroMouseEntered
        btn_mouseEntry(btn_cuatro);// TODO add your handling code here:
    }//GEN-LAST:event_btn_cuatroMouseEntered

    private void btn_cuatroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cuatroMouseExited
        btn_numerics_mouseExited(btn_cuatro);
    }//GEN-LAST:event_btn_cuatroMouseExited

    private void btn_cincoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cincoMouseEntered
        btn_mouseEntry(btn_cinco);// TODO add your handling code here:
    }//GEN-LAST:event_btn_cincoMouseEntered

    private void btn_cincoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cincoMouseExited
        btn_numerics_mouseExited(btn_cinco);
    }//GEN-LAST:event_btn_cincoMouseExited

    private void btn_seisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seisMouseEntered
        btn_mouseEntry(btn_seis);// TODO add your handling code here:
    }//GEN-LAST:event_btn_seisMouseEntered

    private void btn_seisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seisMouseExited
        btn_numerics_mouseExited(btn_seis);
    }//GEN-LAST:event_btn_seisMouseExited

    private void btn_restaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_restaMouseEntered
        btn_mouseEntry(btn_resta);
    }//GEN-LAST:event_btn_restaMouseEntered

    private void btn_restaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_restaMouseExited
        btn_mouseExited(btn_resta);// TODO add your handling code here:
    }//GEN-LAST:event_btn_restaMouseExited

    private void btn_unoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_unoMouseEntered
        btn_mouseEntry(btn_uno); // TODO add your handling code here:
    }//GEN-LAST:event_btn_unoMouseEntered

    private void btn_unoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_unoMouseExited
        btn_numerics_mouseExited(btn_uno);
    }//GEN-LAST:event_btn_unoMouseExited

    private void btn_dosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dosMouseEntered
        btn_mouseEntry(btn_dos); // TODO add your handling code here:
    }//GEN-LAST:event_btn_dosMouseEntered

    private void btn_dosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dosMouseExited
        btn_numerics_mouseExited(btn_dos);
    }//GEN-LAST:event_btn_dosMouseExited

    private void btn_tresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tresMouseEntered
        btn_mouseEntry(btn_tres); // TODO add your handling code here:
    }//GEN-LAST:event_btn_tresMouseEntered

    private void btn_tresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tresMouseExited
        btn_numerics_mouseExited(btn_tres);
    }//GEN-LAST:event_btn_tresMouseExited

    private void btn_sumaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumaMouseEntered
        btn_mouseEntry(btn_suma);
    }//GEN-LAST:event_btn_sumaMouseEntered

    private void btn_sumaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sumaMouseExited
        btn_mouseExited(btn_suma);// TODO add your handling code here:
    }//GEN-LAST:event_btn_sumaMouseExited

    private void btn_negateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_negateMouseEntered
        btn_mouseEntry(btn_negate);// TODO add your handling code here:
    }//GEN-LAST:event_btn_negateMouseEntered

    private void btn_negateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_negateMouseExited
        btn_numerics_mouseExited(btn_negate);
    }//GEN-LAST:event_btn_negateMouseExited

    private void btn_ceroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ceroMouseEntered
        btn_mouseEntry(btn_cero);// TODO add your handling code here:
    }//GEN-LAST:event_btn_ceroMouseEntered

    private void btn_ceroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ceroMouseExited
        btn_numerics_mouseExited(btn_cero);
    }//GEN-LAST:event_btn_ceroMouseExited

    private void btn_puntoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_puntoMouseEntered
        btn_mouseEntry(btn_punto);// TODO add your handling code here:
    }//GEN-LAST:event_btn_puntoMouseEntered

    private void btn_puntoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_puntoMouseExited
        btn_numerics_mouseExited(btn_punto);
    }//GEN-LAST:event_btn_puntoMouseExited

    private void btn_igualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_igualMouseEntered
        btn_igual_mouseEntry(btn_igual);
    }//GEN-LAST:event_btn_igualMouseEntered

    private void btn_igualMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_igualMouseExited
        btn_igual_mouseExited(btn_igual);
    }//GEN-LAST:event_btn_igualMouseExited

    private void btn_cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cincoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cincoActionPerformed

    private void btn_modeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modeMouseClicked
        msg("Solo esta disponible la version Estandar");
    }//GEN-LAST:event_btn_modeMouseClicked

    private void btn_modeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modeMouseEntered
        btn_mouseEntry(btn_mode);
    }//GEN-LAST:event_btn_modeMouseEntered

    private void btn_modeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modeMouseExited
        btn_mouseExited(btn_mode);
    }//GEN-LAST:event_btn_modeMouseExited

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculadora().setVisible(true);
            }
        });
    }
    private boolean active_operation_igual = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_borraringresado;
    private javax.swing.JButton btn_borrartodo;
    private javax.swing.JButton btn_borraruno;
    private javax.swing.JButton btn_cero;
    private javax.swing.JButton btn_cinco;
    private javax.swing.JButton btn_cuatro;
    private javax.swing.JButton btn_division;
    private javax.swing.JButton btn_dos;
    private javax.swing.JButton btn_elevarcuadrado;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_igual;
    private javax.swing.JButton btn_minimize;
    private javax.swing.JButton btn_mode;
    private javax.swing.JButton btn_multiplicacion;
    private javax.swing.JButton btn_negate;
    private javax.swing.JButton btn_nueve;
    private javax.swing.JButton btn_ocho;
    private javax.swing.JButton btn_porcentaje;
    private javax.swing.JButton btn_punto;
    private javax.swing.JButton btn_raizcuadrada;
    private javax.swing.JButton btn_reciproc;
    private javax.swing.JButton btn_resta;
    private javax.swing.JButton btn_seis;
    private javax.swing.JButton btn_siete;
    private javax.swing.JButton btn_suma;
    private javax.swing.JButton btn_tres;
    private javax.swing.JButton btn_uno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel panel_buttons;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_mode;
    private javax.swing.JPanel panel_titlebar;
    private javax.swing.JTextField txt_acumular;
    private javax.swing.JTextField txt_ingresar;
    // End of variables declaration//GEN-END:variables
    private void disable_enableAllButtons(boolean b) {
        //numericos
        btn_cero.setEnabled(b);
        btn_uno.setEnabled(b);
        btn_dos.setEnabled(b);
        btn_tres.setEnabled(b);
        btn_cuatro.setEnabled(b);
        btn_cinco.setEnabled(b);
        btn_seis.setEnabled(b);
        btn_siete.setEnabled(b);
        btn_ocho.setEnabled(b);
        btn_nueve.setEnabled(b);
        //operaciones
        btn_suma.setEnabled(b);
        btn_resta.setEnabled(b);
        btn_multiplicacion.setEnabled(b);
        btn_division.setEnabled(b);
        //Funciones
        btn_igual.setEnabled(b);
        btn_punto.setEnabled(b);
        btn_borrartodo.setEnabled(b);
        btn_borraruno.setEnabled(b);
        btn_negate.setEnabled(b);
        btn_raizcuadrada.setEnabled(b);
        btn_elevarcuadrado.setEnabled(b);
        btn_reciproc.setEnabled(b);
        btn_porcentaje.setEnabled(b);
        btn_borraruno.setEnabled(b);
        btn_borraringresado.setEnabled(b);
        txt_ingresar.setEnabled(b);

    }

    private void initButtons() {
        //Numericos
        btn_cero.addActionListener(this);
        btn_uno.addActionListener(this);
        btn_dos.addActionListener(this);
        btn_tres.addActionListener(this);
        btn_cuatro.addActionListener(this);
        btn_cinco.addActionListener(this);
        btn_seis.addActionListener(this);
        btn_siete.addActionListener(this);
        btn_ocho.addActionListener(this);
        btn_nueve.addActionListener(this);
        //funciones
        btn_suma.addActionListener(this);
        btn_resta.addActionListener(this);
        btn_multiplicacion.addActionListener(this);
        btn_division.addActionListener(this);
        btn_igual.addActionListener(this);
        btn_punto.addActionListener(this);
        btn_negate.addActionListener(this);
        btn_raizcuadrada.addActionListener(this);
        btn_elevarcuadrado.addActionListener(this);
        btn_porcentaje.addActionListener(this);
        btn_borraruno.addActionListener(this);
        btn_borraringresado.addActionListener(this);
        btn_reciproc.addActionListener(this);
        txt_ingresar.addKeyListener(this);
    }

    private void borrarUno() {
        if (txt_ingresar.getText().length() != 0) {
            String save = txt_ingresar.getText();
            txt_ingresar.setText(save.substring(0, save.length() - 1));
            if (txt_ingresar.getText().length() == 0) {
                txt_ingresar.setText("0");
            }
        }
    }

    private void borrarIngresado() {
        txt_ingresar.setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_nueve) {
            set_ingreso_Numero_Signo("9");
        } else if (e.getSource() == btn_ocho) {
            set_ingreso_Numero_Signo("8");
        } else if (e.getSource() == btn_siete) {
            set_ingreso_Numero_Signo("7");
        } else if (e.getSource() == btn_seis) {
            set_ingreso_Numero_Signo("6");
        } else if (e.getSource() == btn_cinco) {
            set_ingreso_Numero_Signo("5");
        } else if (e.getSource() == btn_cuatro) {
            set_ingreso_Numero_Signo("4");
        } else if (e.getSource() == btn_tres) {
            set_ingreso_Numero_Signo("3");
        } else if (e.getSource() == btn_dos) {
            set_ingreso_Numero_Signo("2");
        } else if (e.getSource() == btn_uno) {
            set_ingreso_Numero_Signo("1");
        } else if (e.getSource() == btn_cero) {
            set_ingreso_Numero_Signo("0");
        }

        if (e.getSource() == btn_suma) {
            esperarNuevo(true);
            acumular("+");
        } else if (e.getSource() == btn_resta) {
            esperarNuevo(true);
            acumular("-");
        } else if (e.getSource() == btn_multiplicacion) {
            esperarNuevo(true);
            acumular("x");
        } else if (e.getSource() == btn_division) {
            esperarNuevo(true);
            acumular("÷");
        } else if (e.getSource() == btn_punto) {
            point();
        } else if (e.getSource() == btn_negate) {
            negate();
        } else if (e.getSource() == btn_raizcuadrada) {
            sqrt("raiz");
        } else if (e.getSource() == btn_elevarcuadrado) {
            sqrt("elevadoalcuadrado");
        } else if (e.getSource() == btn_reciproc) {
            reciproc();
        } else if (e.getSource() == btn_porcentaje) {
            porcentaje();
        }

        //llamar funciones independientes
        if (e.getSource() == btn_igual) {
            igual();
        }
        if (e.getSource() == btn_borraruno) {
            borrarUno();
        }
        if (e.getSource() == btn_borraringresado) {
            borrarIngresado();
        }
        //Decoracion division entre 0
        if (txt_ingresar.getText().equals("No se puede dividir entre cero")) {
            Font f = new Font("Nirmala UI 45 Bold", 0, 20);
            txt_ingresar.setFont(f);
        } else if (txt_ingresar.getText().length() > 11) {
            Font f = new Font("Nirmala UI 45 Bold", Font.BOLD, 30);
            txt_ingresar.setFont(f);
        } else {
            Font f = new Font("Nirmala UI 45 Bold", Font.BOLD, 45);
            txt_ingresar.setFont(f);
        }

        if (txt_ingresar.getText().equals("No se puede dividir entre cero")) {
            disable_enableAllButtons(false);
        }
        //fin decoracion division entre 0
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
        if ('v' == e.getKeyChar() ){
            e.consume();
        }
        if (e.isControlDown() ){
            e.consume();
        }
        
        
        char caracter = e.getKeyChar();
        if ((caracter >= '0') && (caracter <= '9')) {
            set_ingreso_Numero_Signo("" + caracter);
        } else if (caracter == '.') {
            point();
        } else if (caracter == '-') {
            if (!esperarNuevo()) {
                if (!existNegative()) {
                    String ingresado = txt_ingresar.getText();
                    txt_ingresar.setText("-" + ingresado);
                }
            } else {
                esperarOperacion(true);
                esperarNuevo(false);
                txt_ingresar.setText("-");
            }
        } else if (caracter == '+') {
            esperarNuevo(true);
            acumular("" + caracter);
        } else if ((caracter == '*')) {
            esperarNuevo(true);
            acumular("x");
        } else if (caracter == '/') {
            esperarNuevo(true);
            acumular("÷");
        }
        e.consume();  // ignorar el evento de teclado        
         
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if ('v' == e.getKeyChar() ){
            e.consume();
        }     
       if (e.isControlDown() ){
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      if ('v' == e.getKeyChar() ){
            e.consume();
        }
      if (e.isControlDown() ){
            e.consume();
        }
      
        
    }

    private void msg(String msg) {
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }

    //Funciones 
    private void point() {
        warningLetter();
        if (!isEmptyIngreso()) {
            if (!existPoint()) {
                set_ingreso_Numero_Signo("0.");
            }
        } else {
            esperarOperacion(true);
            esperarNuevo(false);
            txt_ingresar.setText("0.");
        }
    }

    private void porcentaje() {
        warningLetter();
        if (txt_acumular.getText().length() == 0) {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() == 0) {
                ingresado = "0";
            }
            txt_acumular.setText(calcular.getTotal() + "%" + ingresado + "" + "=");
            calcular.porcentaje(ingresado);
            txt_ingresar.setText(calcular.getTotal());
        } else {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() > 0) {
                setSignoAnterior("=");
                txt_acumular.setText(calcular.getTotal() + "%" + ingresado + "" + "=");
                calcular.porcentaje(ingresado);
                txt_ingresar.setText(calcular.getTotal());
                esperarOperacion(true);
                esperarNuevo(true);
                esperarIgual(false);
            } else {
                ingresado = calcular.getTotal();
                setSignoAnterior("=");
                txt_acumular.setText(calcular.getTotal() + "%" + ingresado + "" + "=");
                calcular.porcentaje(ingresado);
                txt_ingresar.setText(calcular.getTotal());
            }
        }
    }

    private void reciproc() {
        warningLetter();
        if (txt_acumular.getText().length() == 0) {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() == 0) {
                ingresado = "0";
            }
            calcular.acumular(ingresado);
            calcular.reciproc();
            txt_acumular.setText("reciproc(" + ingresado + ")" + "=");
            txt_ingresar.setText(calcular.getTotal());
        } else {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() > 0) {
                if (getSignoAnterior().equals("=")) {
                    calcular.reciproc();
                    txt_acumular.setText("reciproc(" + ingresado + ")" + "=");
                    txt_ingresar.setText(calcular.getTotal());
                    esperarOperacion(true);
                    esperarNuevo(true);
                    esperarIgual(false);
                } else {
                    if (!esperarNuevo()) {
                        acumular(getSignoAnterior());
                        calcular.reciproc();
                        txt_acumular.setText("reciproc(" + ingresado + ")" + "=");
                        txt_ingresar.setText(calcular.getTotal());
                        esperarOperacion(true);
                        esperarNuevo(true);
                        esperarIgual(false);
                    } else {
                        calcular.reciproc();
                        txt_acumular.setText("reciproc(" + ingresado + ")" + "=");
                        txt_ingresar.setText(calcular.getTotal());
                        esperarOperacion(true);
                        esperarNuevo(true);
                        esperarIgual(false);
                    }
                }
            } else {
                ingresado = calcular.getTotal();
                calcular.reciproc();
                txt_acumular.setText("reciproc(" + ingresado + ")" + "=");
                txt_ingresar.setText(calcular.getTotal());
            }
        }
    }//finreciproco

    private void sqrt(String tipo) {//raiz cuadrada / elevado al cuadrado
        warningLetter();
        if (txt_acumular.getText().length() == 0) {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() == 0) {
                ingresado = "0";
            }
            calcular.acumular(ingresado);
            calcular.sqrt(tipo);
            txt_acumular.setText("sqrt(" + ingresado + ")" + "=");
            txt_ingresar.setText(calcular.getTotal());
        } else {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() > 0) {
                if (getSignoAnterior().equals("=")) {
                    calcular.sqrt(tipo);
                    txt_acumular.setText("sqrt(" + ingresado + ")" + "=");
                    txt_ingresar.setText(calcular.getTotal());
                    esperarOperacion(true);
                    esperarNuevo(true);
                    esperarIgual(false);
                } else {
                    if (!esperarNuevo()) {
                        
                        acumular(getSignoAnterior());
                        calcular.sqrt(tipo);
                        txt_acumular.setText("sqrt(" + ingresado + ")" + "=");
                        txt_ingresar.setText(calcular.getTotal());
                        esperarOperacion(true);
                        esperarNuevo(true);
                        esperarIgual(false);
                    } else {
                        
                        ingresado = calcular.getTotal();//
                        calcular.sqrt(tipo);
                        txt_acumular.setText("sqrt(" + ingresado + ")" + "=");
                        txt_ingresar.setText(calcular.getTotal());
                        esperarOperacion(true);
                        esperarNuevo(true);
                        esperarIgual(false);
                    }
                }
            } else {
                ingresado = calcular.getTotal();
                calcular.sqrt(tipo);
                txt_acumular.setText("sqrt(" + ingresado + ")" + "=");
                txt_ingresar.setText(calcular.getTotal());
            }
        }
    }//fin sqrt

    private void negate() {
        warningLetter();
        if (txt_acumular.getText().length() == 0) {
            if (txt_ingresar.getText().length() > 0) {
                if (!existNegative()) {
                    String ingresado = txt_ingresar.getText();
                    txt_ingresar.setText("-" + ingresado);
                } else {
                    String ingresado = txt_ingresar.getText();
                    txt_ingresar.setText(ingresado.substring(1));
                }
            } else {
                txt_ingresar.setText("0");
            }
        } else {
            String ingresado = txt_ingresar.getText();
            if (ingresado.length() > 0) {
                if (getSignoAnterior().equals("=")) {
                    calcular.negate();
                    txt_acumular.setText("negate(" + ingresado + ")" + "=");
                    txt_ingresar.setText(calcular.getTotal());
                    esperarOperacion(true);
                    esperarNuevo(true);
                    esperarIgual(false);
                } else {
                    if (!existNegative()) {
                        ingresado = txt_ingresar.getText();
                        txt_ingresar.setText("-" + ingresado);
                    } else {
                        ingresado = txt_ingresar.getText();
                        txt_ingresar.setText(ingresado.substring(1));
                    }
                }
            } else {
                ingresado = calcular.getTotal();
                calcular.negate();
                txt_acumular.setText("negate(" + ingresado + ")" + "=");
                txt_ingresar.setText(calcular.getTotal());
                esperarOperacion(true);
                esperarNuevo(true);
                esperarIgual(false);
            }
        }
    }//fin negate

    private void igual() {
        String acumulado = txt_acumular.getText();
        String ingresado = "";
        //casos especiales
        specialCases();
        //ingresado = warningLetter2();
        if ((acumulado.length() == 0) || getSignoAnterior().equals("=")) {
            ingresado = txt_ingresar.getText();
            calcular.acumular(ingresado);
            txt_acumular.setText(ingresado + "=");
            setSignoAnterior("=");
            esperarNuevo(true);
            esperarOperacion(false);
        } else if (!esperarIgual() && !esperarNuevo()) {
            esperarNuevo(false);
            acumular(getSignoAnterior());
            acumulado = txt_acumular.getText();
            txt_acumular.setText(acumulado.substring(0, acumulado.length() - 1) + "=");
            setSignoAnterior("=");
            esperarNuevo(true);
            esperarOperacion(false);
            esperarIgual(false);
        } else {
            acumulado = txt_acumular.getText();
            txt_acumular.setText(acumulado.substring(0, acumulado.length() - 1) + "=");
            txt_ingresar.setText(calcular.getTotal() + "");
            setSignoAnterior("=");
            esperarNuevo(true);
            esperarOperacion(false);
        }
    }//fin igual

    private void specialCases() {
        String tmp = txt_ingresar.getText();
        if (txt_ingresar.getText().equals("0.")) {
            txt_ingresar.setText(tmp + "0");
        } else if (txt_ingresar.getText().equals(".")) {
            txt_ingresar.setText(tmp + "0.0");
        } else if (txt_ingresar.getText().equals("-0.")) {
            txt_ingresar.setText(tmp + "0");
        } else if (txt_ingresar.getText().equals("-")) {
            txt_ingresar.setText(tmp + "-0");
        }
        // warningLetter();
        //String tmp = txt_ingresar.getText();
        for (int x = 0; x <= tmp.length() - 1; x++) {
            char c = tmp.charAt(x);
            if ((c >= '0' && c <= '9') || (c == '.') || (c == '-') || (c == 'E') || (c == ',')) {
                //no es una letra
            } else {
                //existe una letra
                disable_enableAllButtons(false);
                msg("Datos ingresados no validos");
                txt_ingresar.setText("0");
                disable_enableAllButtons(true);
                break;
            }
        }
        //Elimina ',' para evitar conflictos
        txt_ingresar.setText(tmp.replaceAll(",", ""));
    }

    private void warningLetter() {
        String tmp = txt_ingresar.getText();
        for (int x = 0; x <= tmp.length() - 1; x++) {
            char c = tmp.charAt(x);
            if ((c >= '0' && c <= '9') || (c == '.') || (c == '-') || (c == 'E') || (c == ',')) {
                //no es una letra
            } else {
                //existe una letra
                disable_enableAllButtons(false);
                msg("Danos no validos");
                txt_ingresar.setText("0");
                disable_enableAllButtons(true);
                break;
            }
        }
        //Elimina ',' para evitar conflictos
        txt_ingresar.setText(tmp.replaceAll(",", ""));
    }

    private String warningLetter2() {
        String tmp = txt_ingresar.getText();
        for (int x = 0; x <= tmp.length() - 1; x++) {
            char c = tmp.charAt(x);
            if ((c >= '0' && c <= '9') || (c == '.') || (c == '-') || (c == 'E') || (c == ',')) {
                //no es una letra
            } else {
                //existe una letra
                disable_enableAllButtons(false);
                //msg("Danos no validos");
                txt_ingresar.setText("0");
                disable_enableAllButtons(true);
                return "0";
            }
        }
        //Elimina ',' para evitar conflictos
        txt_ingresar.setText(tmp.replaceAll(",", ""));
        tmp = tmp.replaceAll(",", "");
        return tmp;
    }
}
