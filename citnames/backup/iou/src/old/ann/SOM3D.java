/*
 * Decompiled by Mocha from KFM3D.class
 */
/*
 * Originally compiled from KFM3D.java
 */
package old.ann;

import java.awt.*;
import java.lang.String;
import java.applet.Applet;
/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class SOM3D extends Applet implements Runnable {
  final Color sandColor = new Color(192, 192, 192);
  final Color boxColor = new Color(160, 160, 160);
  final Font statusFont = new Font("Helvetica", 0, 12);
  final Font statusTextFont = new Font("Helvetica", 1, 12);
  final Font headingFont = new Font("Helvetica", 1, 14);
  Thread somThread;
  SOM som;
  ParametersFrame parametersFrame;
  InputMatrix im;
  Canvas drawCanvas;
  Panel statusPanel;
  Panel buttonsPanel;
  ThreeD td;
  Label statusLabel[];
  Button startButton;
  Button stopButton;
  Button resetButton;
  Button parametersButton;
  Button infoButton;
  Button exitButton;
  Image offscreen;
  Dimension d;
  int xSize;
  int ySize;
  int zSize;
  int mapSizeX;
  int mapSizeY;
  int inputSize;
  int inputDimension;
  int inputX[];
  int inputY[];
  int inputZ[];
  int maxCycle;
  int displayStep;
  double stopArea;
  Point3D boundingBox[];
  Point3D inputs[];
  Point3D weights[];
  Point boundingBoxDraw[];
  Point inputsDraw[];
  Point weightsDraw[];
  boolean itLearns;

  public SOM3D() {
  }

  public void setup() {
    setLayout(new BorderLayout());
    drawCanvas = new Canvas();
    statusPanel = new Panel();
    statusPanel.setFont(statusFont);
    statusPanel.setBackground(Color.lightGray);
    statusPanel.setLayout(new GridLayout(4, 4, 4, 0));
    statusLabel = new Label[8];
    for (int i = 0; i < 8; i++) {
      statusLabel[i] = new Label("", 0);
      statusLabel[i].setFont(statusTextFont);
    }
    statusPanel.add(new Label("input values:", 2));
    statusPanel.add(statusLabel[0]);
    statusPanel.add(new Label("learning cycle:", 2));
    statusPanel.add(statusLabel[1]);
    statusPanel.add(new Label("input layer:", 2));
    statusPanel.add(statusLabel[2]);
    statusPanel.add(new Label("learning rate:", 2));
    statusPanel.add(statusLabel[3]);
    statusPanel.add(new Label("map size:", 2));
    statusPanel.add(statusLabel[4]);
    statusPanel.add(new Label("activation area:", 2));
    statusPanel.add(statusLabel[5]);
    statusPanel.add(new Label("weights:", 2));
    statusPanel.add(statusLabel[6]);
    statusPanel.add(new Label("elapsed time:", 2));
    statusPanel.add(statusLabel[7]);
    buttonsPanel = new Panel();
    buttonsPanel.setFont(statusFont);
    buttonsPanel.setLayout(new GridLayout(1, 6, 2, 4));
    startButton = new Button("Start learning");
    stopButton = new Button("Stop learning");
    resetButton = new Button("Reset");
    parametersButton = new Button("Parameters");
    exitButton = new Button("Exit");
    buttonsPanel.add(startButton);
    buttonsPanel.add(stopButton);
    buttonsPanel.add(resetButton);
    buttonsPanel.add(parametersButton);
    buttonsPanel.add(exitButton);
    Panel panel = new Panel();
    panel.setLayout(new BorderLayout());
    panel.setBackground(Color.lightGray);
    panel.add("South", buttonsPanel);
    panel.add("Center", statusPanel);
    add("South", panel);
    add("Center", drawCanvas);
    show();
    itLearns = false;
    startButton.enable();
    stopButton.disable();
  }

  public void setNetParameters() {
    d = drawCanvas.size();
    offscreen = drawCanvas.createImage(d.width + 1, d.height + 1);
    som = new SOM();
    boundingBox = new Point3D[8];
    boundingBoxDraw = new Point[8];
    inputSize = parametersFrame.getInputValues();
    im = new InputMatrix(inputSize, 3);
    inputX = new int[inputSize];
    inputY = new int[inputSize];
    inputZ = new int[inputSize];
    inputs = new Point3D[inputSize];
    inputsDraw = new Point[inputSize];
    inputDimension = parametersFrame.getDim();
    xSize = parametersFrame.getRangeX();
    ySize = parametersFrame.getRangeY();
    zSize = parametersFrame.getRangeZ();
    td = new ThreeD(d.width + 1, d.height + 1, xSize, ySize, zSize);
    mapSizeX = parametersFrame.getMapSizeX();
    mapSizeY = parametersFrame.getMapSizeY();
    weights = new Point3D[mapSizeX * mapSizeY];
    weightsDraw = new Point[mapSizeX * mapSizeY];
    if (mapSizeY == 0) {
      som.createMapLayer(mapSizeX, mapSizeX);
    } else {
      som.createMapLayer(mapSizeX, mapSizeY);
    }
    som.setInitLearningRate(parametersFrame.getInitRate());
    som.setInitActivationArea(parametersFrame.getInitArea());
    maxCycle = parametersFrame.getStopCycle();
    som.setMaxLearningCycles(maxCycle);
    stopArea = parametersFrame.getStopArea();
    som.setStopArea(stopArea);
    displayStep = parametersFrame.getDisplayStep();
    som.setDisplayStep(displayStep);
    for (int i = 0; i < inputSize; i++) {
      inputX[i] = (int) (Math.random() * (xSize << 1) - xSize);
      inputY[i] = (int) (Math.random() * (ySize << 1) - ySize);
      inputZ[i] = (int) (Math.random() * (zSize << 1) - zSize);
      inputs[i] = new Point3D(inputX[i], inputY[i], inputZ[i]);
    }
    im.setInputValues(inputX, inputY, inputZ);
    som.connectLayers(im);
    statusLabel[0].setText(String.valueOf(inputs.length));
    statusLabel[2].setText(String.valueOf(inputDimension + "  neurons"));
    statusLabel[4].setText(mapSizeX + "x" + mapSizeY + "  neurons");
    statusLabel[6].setText(String.valueOf(mapSizeX * mapSizeY * inputDimension));
    boundingBox[0] = new Point3D(xSize, ySize, zSize);
    boundingBox[1] = new Point3D(xSize, ySize, -zSize);
    boundingBox[2] = new Point3D(-xSize, ySize, zSize);
    boundingBox[3] = new Point3D(-xSize, ySize, -zSize);
    boundingBox[4] = new Point3D(xSize, -ySize, zSize);
    boundingBox[5] = new Point3D(xSize, -ySize, -zSize);
    boundingBox[6] = new Point3D(-xSize, -ySize, zSize);
    boundingBox[7] = new Point3D(-xSize, -ySize, -zSize);
    td.setAngles(0, 0, 0);
    td.setAngleSteps(0, 0, 0);
    setWeights(som.getWeightValues());
    updateStatus(som.getLearningCycle(), som.getLearningRate(), som.getActivationArea(), som.getElapsedTime());
    draw();
    td.setAngleSteps(parametersFrame.getSpeedX(), parametersFrame.getSpeedY(), parametersFrame.getSpeedZ());
  }

  public void setWeights(float aaf[][]) {
    for (int i = 0; i < weights.length; i++) {
      weights[i] = new Point3D((int) aaf[0][i], (int) aaf[1][i], (int) aaf[2][i]);
    }
  }

  public String getAppletInfo() {
    String string = "";
    string += "\nName:  3D Kohonen Feature Map";
    string += "\n\nDescription:\nThis applet demonstrates a Kohonen Feature Map Neural Net";
    string += "\nwith a 2- or 3-dimensional input neuron layer.";
    string += "\nIt uses specific classes for neural net components which I";
    string += "\nimplemented for my diploma at the Fachhochschule Regensburg.";
    string += "\n\nAuthor:  Jochen Froehlich  ( jochen.froehlich@stud.fh-regensburg.de )";
    string += "\n\nLast modified:  18-Nov-1996";
    string += "\n\nCopyright 1996 by Jochen Froehlich";
    string += "\nAll rights reserved";
    return string;
  }

  public String[][] getParameterInfo() {
    String aastring[][] = {{"", "", ""}};
    return aastring;
  }

  public void init() {
    if (parametersFrame == null) {
      parametersFrame = new ParametersFrame(600, 10);
    }
    if (somThread == null) {
      somThread = new Thread(this, "somThread");
      somThread.setPriority(10);
    }
    setup();
  }

  public void start() {
    if (itLearns) {
      if (somThread == null) {
        somThread = new Thread(this, "somThread");
        somThread.setPriority(10);
      }
      somThread.start();
      som.resetTime();
      return;
    }
    setNetParameters();
  }

  public void stop() {
    if (somThread != null) {
      somThread.stop();
      somThread = null;
    }
  }

  public void destroy() {
    if (parametersFrame != null) {
      parametersFrame.dispose();
      parametersFrame = null;
    }
    if (somThread != null) {
      somThread.stop();
      somThread = null;
    }
  }

  public void run() {
    double d;
    while (Thread.currentThread() == somThread) {
      while (!som.finishedLearning()) {
        som.learn();
        if (som.displayNow()) {
          try {
            setWeights(som.getWeightValues());
            draw();
            updateStatus(som.getLearningCycle(), som.getLearningRate(), som.getActivationArea(), som.getElapsedTime());
            Thread.sleep(25);
          } catch (InterruptedException e1) {
          }
        }
      }
      startButton.disable();
      stopButton.disable();
      updateStatus(som.getLearningCycle(), som.getLearningRate(), som.getActivationArea(), som.getElapsedTime());
      for (d = som.getActivationArea(); d > 0.0; ) {
        try {
          draw();
          Thread.sleep(25);
        } catch (InterruptedException e2) {
        }
      }
    }
  }

  public void updateStatus(int i, double d1, double d2, String string) {
    setFont(statusTextFont);
    statusLabel[1].setText(String.valueOf(i));
    statusLabel[3].setText(String.valueOf(d1));
    statusLabel[5].setText(String.valueOf(d2));
    statusLabel[7].setText(string + "  sec");
  }

  public final synchronized void draw() {
    td.changeAngles();
    boundingBoxDraw = td.rotate3Dto2D(boundingBox);
    inputsDraw = td.rotate3Dto2D(inputs);
    weightsDraw = td.rotate3Dto2D(weights);
    Graphics g = offscreen.getGraphics();
    g.setColor(sandColor);
    g.fillRect(0, 0, d.width + 1, d.height + 1);
    g.setColor(Color.blue);
    for (int i1 = 0; i1 < inputsDraw.length; i1++) {
      g.fillRect(inputsDraw[i1].x - 2, inputsDraw[i1].y - 2, 4, 4);
    }
    g.setColor(boxColor);
    for (int j1 = 0; j1 < 7; j1 += 2) {
      g.drawLine(boundingBoxDraw[j1].x, boundingBoxDraw[j1].y, boundingBoxDraw[j1 + 1].x, boundingBoxDraw[j1 + 1].y);
    }
    for (int k1 = 0; k1 < 4; k1++) {
      g.drawLine(boundingBoxDraw[k1].x, boundingBoxDraw[k1].y, boundingBoxDraw[k1 + 4].x, boundingBoxDraw[k1 + 4].y);
    }
    g.drawLine(boundingBoxDraw[0].x, boundingBoxDraw[0].y, boundingBoxDraw[2].x, boundingBoxDraw[2].y);
    g.drawLine(boundingBoxDraw[1].x, boundingBoxDraw[1].y, boundingBoxDraw[3].x, boundingBoxDraw[3].y);
    g.drawLine(boundingBoxDraw[4].x, boundingBoxDraw[4].y, boundingBoxDraw[6].x, boundingBoxDraw[6].y);
    g.drawLine(boundingBoxDraw[5].x, boundingBoxDraw[5].y, boundingBoxDraw[7].x, boundingBoxDraw[7].y);
    g.setColor(Color.darkGray);
    for (int i2 = 1; i2 < weightsDraw.length; i2++) {
      if (i2 % mapSizeY != 0) {
        g.drawLine(weightsDraw[i2 - 1].x, weightsDraw[i2 - 1].y, weightsDraw[i2].x, weightsDraw[i2].y);
      }
    }
    for (int j2 = 0; j2 < mapSizeY; j2++) {
      for (int k2 = 1; k2 < mapSizeX; k2++) {
        int i3 = (k2 - 1) * mapSizeY + j2;
        int j3 = k2 * mapSizeY + j2;
        g.drawLine(weightsDraw[i3].x, weightsDraw[i3].y, weightsDraw[j3].x, weightsDraw[j3].y);
      }
    }
    g = drawCanvas.getGraphics();
    g.drawImage(offscreen, 0, 0, drawCanvas);
  }

  public boolean handleEvent(Event event) {
    if (event.id == 201) {
      if (parametersFrame != null) {
        parametersFrame.dispose();
        parametersFrame = null;
      }
      stop();
      System.exit(0);
    }
    return super.handleEvent(event);
  }

  public boolean action(Event event, Object object) {
    if (event.target == startButton) {
      itLearns = true;
      startButton.disable();
      stopButton.enable();
      start();
      return false;
    }
    if (event.target == resetButton) {
      itLearns = false;
      if (somThread != null) {
        somThread.stop();
        somThread = null;
      }
      startButton.enable();
      stopButton.disable();
      setNetParameters();
      return false;
    }
    if (event.target == stopButton) {
      startButton.disable();
      stopButton.disable();
      maxCycle = som.getLearningCycle();
      return false;
    }
    if (event.target == parametersButton) {
      if (parametersFrame == null) {
        parametersFrame = new ParametersFrame(600, 10);
      } else if (parametersFrame.isShowing()) {
        parametersFrame.show();
      }
      return false;
    }
    if (event.target == exitButton) {
      parametersFrame.dispose();
      parametersFrame = null;
      if (somThread != null) {
        somThread.stop();
        somThread = null;
      }
      stop();
      System.exit(0);
    }
    if (event.id == 201) {
      if (parametersFrame != null) {
        parametersFrame.dispose();
        parametersFrame = null;
      }
      stop();
      System.exit(0);
    }
    return false;
  }
}
