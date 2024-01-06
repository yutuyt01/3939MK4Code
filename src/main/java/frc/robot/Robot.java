// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderFaults;
import com.ctre.phoenix.sensors.CANCoderStickyFaults;
import com.ctre.phoenix.sensors.MagnetFieldStrength;
import com.ctre.phoenix.sensors.WPI_CANCoder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  final int PRINTOUT_DELAY = 100; // in Milliseconds
  WPI_CANCoder FrontLeftCANCoder = new WPI_CANCoder(2, "rio"); // Rename "rio" to match the CANivore device name if using a CANivore
  WPI_CANCoder BackLeftCANCoder = new WPI_CANCoder(3, "rio");
  WPI_CANCoder BackRightCANCoder = new WPI_CANCoder(4, "rio");
  WPI_CANCoder FrontRightCANCoder = new WPI_CANCoder(5, "rio");
  CANCoderConfiguration FrontLeftcanCoderConfiguration = new CANCoderConfiguration();
  CANCoderConfiguration BackLeftcanCoderConfiguration = new CANCoderConfiguration();
  CANCoderConfiguration BackRightcanCoderConfiguration = new CANCoderConfiguration();
  CANCoderConfiguration FrontRightcanCoderConfiguration = new CANCoderConfiguration();
  Joystick joy = new Joystick(0);

  /**  
   * Doing lots of printing in Java creates a large overhead 
   * This Instrument class is designed to put that printing in a seperate thread
   * That way we can prevent loop overrun messages from occurring
   */
  class Instrument extends Thread {
    void printFaults(CANCoderFaults faults) {
      System.out.printf("Hardware fault: %s\t    Under Voltage fault: %s\t    Reset During Enable fault: %s\t    API Error fault: %s%n", 
        faults.HardwareFault ? "True " : "False",
        faults.UnderVoltage ? "True " : "False",
        faults.ResetDuringEn ? "True " : "False",
        faults.APIError ? "True " : "False");
    }
    void printFaults(CANCoderStickyFaults faults) {
      System.out.printf("Hardware fault: %s\t    Under Voltage fault: %s\t    Reset During Enable fault: %s\t     API Error fault: %s%n", 
        faults.HardwareFault ? "True " : "False",
        faults.UnderVoltage ? "True " : "False",
        faults.ResetDuringEn ? "True " : "False",
        faults.APIError ? "True " : "False");
    }
    void printValue(double val, String units, double timestamp) {
      System.out.printf("%20f %-20s @ %f%n", val, units, timestamp);
    }
    void printValue(MagnetFieldStrength val, String units, double timestamp) {
      System.out.printf("%20s %-20s @ %f%n", val.toString(), units, timestamp);
    }

    public void run() {
      /* Report position, absolute position, velocity, battery voltage */
      double posValue = _CANCoder.getPosition();
      String posUnits = _CANCoder.getLastUnitString();
      double posTstmp = _CANCoder.getLastTimestamp();
      
      double absValue = _CANCoder.getAbsolutePosition();
      String absUnits = _CANCoder.getLastUnitString();
      double absTstmp = _CANCoder.getLastTimestamp();
      
      double velValue = _CANCoder.getVelocity();
      String velUnits = _CANCoder.getLastUnitString();
      double velTstmp = _CANCoder.getLastTimestamp();
      
      double batValue = _CANCoder.getBusVoltage();
      String batUnits = _CANCoder.getLastUnitString();
      double batTstmp = _CANCoder.getLastTimestamp();
      

      /* Report miscellaneous attributes about the CANCoder */
      MagnetFieldStrength magnetStrength = _CANCoder.getMagnetFieldStrength();
      String magnetStrengthUnits = _CANCoder.getLastUnitString();
      double magnetStrengthTstmp = _CANCoder.getLastTimestamp();

      System.out.print("Position: ");
      printValue(posValue, posUnits, posTstmp);
      System.out.print("Abs Pos : ");
      printValue(absValue, absUnits, absTstmp);
      System.out.print("Velocity: ");
      printValue(velValue, velUnits, velTstmp);
      System.out.print("Battery : ");
      printValue(batValue, batUnits, batTstmp);
      System.out.print("Strength: ");
      printValue(magnetStrength, magnetStrengthUnits, magnetStrengthTstmp);

      /* Fault reporting */
      CANCoderFaults faults = new CANCoderFaults();
      _CANCoder.getFaults(faults);
      CANCoderStickyFaults stickyFaults = new CANCoderStickyFaults();
      _CANCoder.getStickyFaults(stickyFaults);

      System.out.println("Faults:");
      printFaults(faults);
      System.out.println("Sticky Faults:");
      printFaults(stickyFaults);

      System.out.println();
      System.out.println();
    }
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    /* Change the configuration configs as needed here */
    _canCoderConfiguration.unitString = "Penguins";

    _CANCoder.configAllSettings(_canCoderConfiguration);
  }

  int count = 0;
  @Override
  public void robotPeriodic() {
    if(count++ >= PRINTOUT_DELAY / 10) {
      count = 0;

      /**  
       * Doing lots of printing in Java creates a large overhead 
       * This Instrument class is designed to put that printing in a seperate thread
       * That way we can prevent loop overrun messages from occurring
       */
      new Instrument().start();
    }
    if(joy.getRawButton(1)) {
      _CANCoder.clearStickyFaults();
    }
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
