/*
 * Decompiled by Mocha from ParametersFrame.class
 */

/*
 * Originally compiled from KFM3D.java
 */

package old.ann;

import java.awt.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
class ParametersFrame extends Frame {

  final int n = 12;

  final Font parametersFont = new Font("Helvetica", 0, 12);

  final Font statusFont = new Font("Helvetica", 1, 12);

  final int def_inputValues = 10;

  final int def_mapSizeX = 9;

  final int def_mapSizeY = 9;

  final int def_dim = 3;

  final double def_initRate = 0.6;

  final double def_initArea = 3.0;

  final double def_stopArea = 0.5;

  final int def_stopCycle = -1;

  final int def_rangeX = 120;

  final int def_rangeY = 120;

  final int def_rangeZ = 120;

  final int def_speedX = 1;

  final int def_speedY = 2;

  final int def_speedZ = -4;

  final int def_displayStep = 1;

  int p_inputValues;

  int p_mapSizeX;

  int p_mapSizeY;

  int p_dim;

  double p_initRate;

  double p_initArea;

  double p_stopArea;

  int p_stopCycle;

  int p_rangeX;

  int p_rangeY;

  int p_rangeZ;

  int p_speedX;

  int p_speedY;

  int p_speedZ;

  int p_displayStep;

  GridBagLayout gb;

  GridBagConstraints gbc;

  Panel values;

  Panel buttons;

  Panel status;

  Label label[];

  Label statusText;

  CheckboxGroup cbg;

  TextField inputValues;

  TextField mapSizeX;

  TextField mapSizeY;

  Checkbox dim2;

  Checkbox dim3;

  TextField initRate;

  TextField initArea;

  TextField stopArea;

  TextField stopCycle;

  TextField rangeX;

  TextField rangeY;

  TextField rangeZ;

  TextField displayStep;

  Label speed[];

  Scrollbar speedX;

  Scrollbar speedY;

  Scrollbar speedZ;

  Button applyButton;

  Button defaultsButton;

  Button hideButton;


  public ParametersFrame(int i, int j) {

    setTitle("Parameters");

    setLayout(new BorderLayout());

    setBackground(Color.lightGray);

    setFont(parametersFont);

    setResizable(false);

    gb = new GridBagLayout();

    gbc = new GridBagConstraints();

    values = new Panel();

    values.setLayout(gb);

    buttons = new Panel();

    buttons.setLayout(new FlowLayout(0, 4, 2));

    status = new Panel();

    status.setLayout(new BorderLayout(4, 0));

    applyButton = new Button("Apply");

    defaultsButton = new Button("Set defaults");

    hideButton = new Button("Hide");

    speed = new Label[3];

    speed[0] = new Label();

    speed[1] = new Label();

    speed[2] = new Label();

    statusText = new Label();

    statusText.setFont(statusFont);

    statusText.setText("Ready.");

    label = new Label[12];

    for (int k = 0; k < 12; k++) {

      label[k] = new Label("", 0);
    }

    label[0].setText(" input values: ");

    label[1].setText(" map size ( x / y ): ");

    label[2].setText(" input neurons: ");

    label[3].setText(" initial learning rate: ");

    label[4].setText(" initial activation area: ");

    label[5].setText(" stop, if area is smaller than: ");

    label[6].setText(" stop at learning cycle: ");

    label[7].setText(" input range ( x / y / z ): ");

    label[8].setText(" x-axis rotation speed: ");

    label[9].setText(" y-axis rotation speed: ");

    label[10].setText(" z-axis rotation speed: ");

    label[11].setText(" display step: ");

    inputValues = new TextField(4);

    mapSizeX = new TextField(4);

    mapSizeY = new TextField(4);

    initRate = new TextField(4);

    initArea = new TextField(4);

    stopArea = new TextField(4);

    stopCycle = new TextField(4);

    rangeX = new TextField(4);

    rangeY = new TextField(4);

    rangeZ = new TextField(4);

    displayStep = new TextField(4);

    speedX = new Scrollbar(0, 0, 5, -10, 10);

    speedY = new Scrollbar(0, 0, 5, -10, 10);

    speedZ = new Scrollbar(0, 0, 5, -10, 10);

    cbg = new CheckboxGroup();

    dim2 = new Checkbox("2", cbg, false);

    dim3 = new Checkbox("3", cbg, true);

    gbc.anchor = 18;

    gbc.fill = 0;

    gbc.insets = new Insets(1, 2, 1, 2);

    gbc.ipadx = 2;

    gbc.ipady = 2;

    gbc.weightx = 1.0;

    gbc.weighty = 0.0;

    gbc.gridwidth = 2;

    gb.setConstraints(label[0], gbc);

    values.add(label[0]);

    gbc.gridwidth = 0;

    gb.setConstraints(inputValues, gbc);

    values.add(inputValues);

    gbc.gridwidth = 3;

    gb.setConstraints(label[1], gbc);

    values.add(label[1]);

    gb.setConstraints(mapSizeX, gbc);

    values.add(mapSizeX);

    gbc.gridwidth = 0;

    gb.setConstraints(mapSizeY, gbc);

    values.add(mapSizeY);

    gbc.gridwidth = 3;

    gb.setConstraints(label[2], gbc);

    values.add(label[2]);

    gb.setConstraints(dim2, gbc);

    values.add(dim2);

    gbc.gridwidth = 0;

    gb.setConstraints(dim3, gbc);

    values.add(dim3);

    gbc.gridwidth = 2;

    gb.setConstraints(label[3], gbc);

    values.add(label[3]);

    gbc.gridwidth = 0;

    gb.setConstraints(initRate, gbc);

    values.add(initRate);

    gbc.gridwidth = 2;

    gb.setConstraints(label[4], gbc);

    values.add(label[4]);

    gbc.gridwidth = 0;

    gb.setConstraints(initArea, gbc);

    values.add(initArea);

    gbc.gridwidth = 2;

    gb.setConstraints(label[5], gbc);

    values.add(label[5]);

    gbc.gridwidth = 0;

    gb.setConstraints(stopArea, gbc);

    values.add(stopArea);

    gbc.gridwidth = 3;

    gb.setConstraints(label[6], gbc);

    values.add(label[6]);

    gb.setConstraints(stopCycle, gbc);

    values.add(stopCycle);

    gbc.gridwidth = 0;

    Label label = new Label("( -1 = infinite )");

    gb.setConstraints(label, gbc);

    values.add(label);

    gbc.gridwidth = 4;

    gb.setConstraints(this.label[7], gbc);

    values.add(this.label[7]);

    gb.setConstraints(rangeX, gbc);

    values.add(rangeX);

    gb.setConstraints(rangeY, gbc);

    values.add(rangeY);

    gbc.gridwidth = 0;

    gb.setConstraints(rangeZ, gbc);

    values.add(rangeZ);

    gbc.fill = 2;

    gbc.gridwidth = 3;

    gb.setConstraints(this.label[8], gbc);

    values.add(this.label[8]);

    gbc.gridwidth = -1;

    gb.setConstraints(speedX, gbc);

    values.add(speedX);

    gbc.gridwidth = 0;

    gb.setConstraints(speed[0], gbc);

    values.add(speed[0]);

    gbc.gridwidth = 3;

    gb.setConstraints(this.label[9], gbc);

    values.add(this.label[9]);

    gbc.gridwidth = -1;

    gb.setConstraints(speedY, gbc);

    values.add(speedY);

    gbc.gridwidth = 0;

    gb.setConstraints(speed[1], gbc);

    values.add(speed[1]);

    gbc.gridwidth = 3;

    gb.setConstraints(this.label[10], gbc);

    values.add(this.label[10]);

    gbc.gridwidth = -1;

    gb.setConstraints(speedZ, gbc);

    values.add(speedZ);

    gbc.gridwidth = 0;

    gb.setConstraints(speed[2], gbc);

    values.add(speed[2]);

    gbc.fill = 0;

    gbc.gridwidth = 2;

    gb.setConstraints(this.label[11], gbc);

    values.add(this.label[11]);

    gbc.gridwidth = 0;

    gb.setConstraints(displayStep, gbc);

    values.add(displayStep);

    buttons.add(applyButton);

    buttons.add(defaultsButton);

    buttons.add(hideButton);

    status.add("Center", statusText);

    add("Center", values);

    Panel panel = new Panel();

    panel.setLayout(new BorderLayout(2, 2));

    panel.add("North", buttons);

    panel.add("South", status);

    add("South", panel);

    pack();

    hide();

    move(i, j);

    setDefaults();

    statusText.setText("Ready.");

  }


  public void setDefaults() {

    statusText.setText("Ready.");

    p_inputValues = 10;

    p_mapSizeX = 9;

    p_mapSizeY = 9;

    p_dim = 3;

    p_initRate = 0.6;

    p_initArea = 3.0;

    p_stopArea = 0.5;

    p_stopCycle = -1;

    p_rangeX = 120;

    p_rangeY = 120;

    p_rangeZ = 120;

    rangeZ.setEditable(true);

    p_speedX = 1;

    p_speedY = 2;

    p_speedZ = -4;

    p_displayStep = 1;

    updateParameters();

    statusText.setText("Default parameters set.");

  }


  public int getInputValues() {

    if (p_inputValues > 0) {

      return p_inputValues;
    } else {

      return 10;
    }

  }


  public int getMapSizeX() {

    if (p_mapSizeX > 0) {

      return p_mapSizeX;
    } else {

      return 9;
    }

  }


  public int getMapSizeY() {

    if (p_mapSizeY > 0) {

      return p_mapSizeY;
    } else {

      return 9;
    }

  }


  public int getDim() {

    return p_dim;
  }


  public double getInitRate() {

    if (p_initRate > 0.0) {

      return p_initRate;
    } else {

      return 0.6;
    }

  }


  public double getInitArea() {

    if (p_initArea > 0.0 && p_initArea > p_stopArea) {

      return p_initArea;
    } else {

      return 3.0;
    }

  }


  public double getStopArea() {

    if (p_stopArea > 0.0 && p_stopArea < p_initArea) {

      return p_stopArea;
    } else {

      return 0.5;
    }

  }


  public int getStopCycle() {

    if (p_stopCycle == -1 || p_stopCycle > 0) {

      return p_stopCycle;
    } else {

      return -1;
    }

  }


  public int getRangeX() {

    if (p_rangeX > 0) {

      return p_rangeX;
    } else {

      return 120;
    }

  }


  public int getRangeY() {

    if (p_rangeY > 0) {

      return p_rangeY;
    } else {

      return 120;
    }

  }


  public int getRangeZ() {

    if (p_rangeZ > 0) {

      return p_rangeZ;
    } else {

      return 120;
    }

  }


  public int getSpeedX() {

    return p_speedX;
  }


  public int getSpeedY() {

    return p_speedY;
  }


  public int getSpeedZ() {

    return p_speedZ;
  }


  public int getDisplayStep() {

    if (p_displayStep > 0) {

      return p_displayStep;
    } else {

      return 1;
    }

  }


  public void updateParameters() {

    inputValues.setText(String.valueOf(p_inputValues));

    mapSizeX.setText(String.valueOf(p_mapSizeX));

    mapSizeY.setText(String.valueOf(p_mapSizeY));

    if (p_dim == 2) {

      dim2.setState(true);
    } else {

      dim3.setState(true);
    }

    initRate.setText(String.valueOf(p_initRate));

    initArea.setText(String.valueOf(p_initArea));

    stopArea.setText(String.valueOf(p_stopArea));

    stopCycle.setText(String.valueOf(p_stopCycle));

    rangeX.setText(String.valueOf(p_rangeX));

    rangeY.setText(String.valueOf(p_rangeY));

    rangeZ.setText(String.valueOf(p_rangeZ));

    speed[0].setText(String.valueOf(p_speedX));

    speedX.setValue(p_speedX);

    speed[1].setText(String.valueOf(p_speedY));

    speedY.setValue(p_speedY);

    speed[2].setText(String.valueOf(p_speedZ));

    speedZ.setValue(p_speedZ);

    displayStep.setText(String.valueOf(p_displayStep));

  }


  public void applyValues() {

    statusText.setText("Ready.");

    try {

      p_inputValues = Integer.valueOf(inputValues.getText()).intValue();

      p_mapSizeX = Integer.valueOf(mapSizeX.getText()).intValue();

      p_mapSizeY = Integer.valueOf(mapSizeY.getText()).intValue();

      p_dim = (dim2.getState()) ? 2 : 3;

      p_initRate = Double.valueOf(initRate.getText()).doubleValue();

      p_initArea = Double.valueOf(initArea.getText()).doubleValue();

      p_stopArea = Double.valueOf(stopArea.getText()).doubleValue();

      p_stopCycle = Integer.valueOf(stopCycle.getText()).intValue();

      p_rangeX = Integer.valueOf(rangeX.getText()).intValue();

      p_rangeY = Integer.valueOf(rangeY.getText()).intValue();

      p_rangeZ = Integer.valueOf(rangeZ.getText()).intValue();

      p_speedX = speedX.getValue();

      p_speedY = speedY.getValue();

      p_speedZ = speedZ.getValue();

      p_displayStep = Integer.valueOf(displayStep.getText()).intValue();

      statusText.setText("Parameters changed.  Hit 'Reset' button.");

    } catch (Exception e) {

      statusText.setText("Wrong input!  Parameters not changed.");

    }

    return;
  }


  public boolean handleEvent(Event event) {

    if (event.target == speedX) {

      p_speedX = speedX.getValue();

      speed[0].setText(String.valueOf(p_speedX));

      return true;
    }

    if (event.target == speedY) {

      p_speedY = speedY.getValue();

      speed[1].setText(String.valueOf(p_speedY));

      return true;
    }

    if (event.target == speedZ) {

      p_speedZ = speedZ.getValue();

      speed[2].setText(String.valueOf(p_speedZ));

      return true;
    }

    switch (event.id) {

      case 201:

        hide();

        return true;
      case 1004:

        statusText.setText("Ready.");

        return true;
      case 1005:

        statusText.setText("Ready.");

        break;
      default:

        return super.handleEvent(event);
    }

    return true;
  }


  public boolean action(Event event, Object object) {

    if (event.target == dim2) {

      p_dim = 2;

      p_speedX = 0;

      p_speedY = 0;

      p_speedZ = 0;

      p_rangeX = 190;

      p_rangeY = 190;

      p_rangeZ = 1;

      rangeX.setText(String.valueOf(p_rangeX));

      rangeY.setText(String.valueOf(p_rangeY));

      rangeZ.setText(String.valueOf(p_rangeZ));

      speed[0].setText(String.valueOf(p_speedX));

      speedX.setValue(p_speedX);

      speed[1].setText(String.valueOf(p_speedY));

      speedY.setValue(p_speedY);

      speed[2].setText(String.valueOf(p_speedZ));

      speedZ.setValue(p_speedZ);

      rangeZ.setEditable(false);

      return true;
    }

    if (event.target == dim3) {

      p_dim = 3;

      p_speedX = 1;

      p_speedY = 2;

      p_speedZ = -4;

      p_rangeX = 120;

      p_rangeY = 120;

      p_rangeZ = 120;

      rangeX.setText(String.valueOf(p_rangeX));

      rangeY.setText(String.valueOf(p_rangeY));

      rangeZ.setText(String.valueOf(p_rangeZ));

      speed[0].setText(String.valueOf(p_speedX));

      speedX.setValue(p_speedX);

      speed[1].setText(String.valueOf(p_speedY));

      speedY.setValue(p_speedY);

      speed[2].setText(String.valueOf(p_speedZ));

      speedZ.setValue(p_speedZ);

      rangeZ.setEditable(true);

      return true;
    }

    if (event.target == applyButton) {

      applyValues();

      return true;
    }

    if (event.target == defaultsButton) {

      setDefaults();

      return true;
    }

    if (event.target != hideButton) {

      return super.action(event, object);
    }

    statusText.setText("Ready.");

    hide();

    return true;
  }

}

