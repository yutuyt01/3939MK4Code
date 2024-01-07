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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
      // Instantiate our RobotContainer. This will perform all our button bindings,
      // and put our
      // autonomous chooser on the dashboard.
      m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
      // Runs the Scheduler. This is responsible for polling buttons, adding
      // newly-scheduled
      // commands, running already-scheduled commands, removing finished or
      // interrupted commands,
      // and running subsystem periodic() methods. This must be called from the
      // robot's periodic
      // block in order for anything in the Command-based framework to work.
      CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
      m_autonomousCommand = m_robotContainer.getAutonomousCommand();

      // schedule the autonomous command (example)
      if (m_autonomousCommand != null) {
          m_autonomousCommand.schedule();
      }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
      // This makes sure that the autonomous stops running when
      // teleop starts running. If you want the autonomous to
      // continue until interrupted by another command, remove
      // this line or comment it out.
      if (m_autonomousCommand != null) {
          m_autonomousCommand.cancel();
      }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
      // Cancels all running commands at the start of test mode.
      CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
// /**
//  * The VM is configured to automatically run this class, and to call the
//  * functions corresponding to each mode, as described in the TimedRobot
//  * documentation. If you change the name of this class or the package after
//  * creating this project, you must also update the build.gradle file in the
//  * project.
//  */

//  public class Robot extends TimedRobot {
//   final int PRINTOUT_DELAY = 100; // in Milliseconds
//   //WPI_CANCoder FrontLeftCANCoder = new WPI_CANCoder(2, "rio"); // Rename "rio" to match the CANivore device name if using a CANivore
//   WPI_CANCoder BackLeftCANCoder = new WPI_CANCoder(3, "rio");
//   WPI_CANCoder BackRightCANCoder = new WPI_CANCoder(4, "rio");
//   WPI_CANCoder FrontRightCANCoder = new WPI_CANCoder(5, "rio");
//   CANCoderConfiguration FrontLeftcanCoderConfiguration = new CANCoderConfiguration();
//   CANCoderConfiguration BackLeftcanCoderConfiguration = new CANCoderConfiguration();
//   CANCoderConfiguration BackRightcanCoderConfiguration = new CANCoderConfiguration();
//   CANCoderConfiguration FrontRightcanCoderConfiguration = new CANCoderConfiguration();
//   Joystick joy = new Joystick(0);

//   /**  
//    * Doing lots of printing in Java creates a large overhead 
//    * This Instrument class is designed to put that printing in a seperate thread
//    * That way we can prevent loop overrun messages from occurring
//    */
//   class Instrument extends Thread {
//     void printFaults(CANCoderFaults faults) {
//       System.out.printf("Hardware fault: %s\t    Under Voltage fault: %s\t    Reset During Enable fault: %s\t    API Error fault: %s%n", 
//         faults.HardwareFault ? "True " : "False",
//         faults.UnderVoltage ? "True " : "False",
//         faults.ResetDuringEn ? "True " : "False",
//         faults.APIError ? "True " : "False");
//     }
//     void printFaults(CANCoderStickyFaults faults) {
//       System.out.printf("Hardware fault: %s\t    Under Voltage fault: %s\t    Reset During Enable fault: %s\t     API Error fault: %s%n", 
//         faults.HardwareFault ? "True " : "False",
//         faults.UnderVoltage ? "True " : "False",
//         faults.ResetDuringEn ? "True " : "False",
//         faults.APIError ? "True " : "False");
//     }
//     void printValue(double val, String units, double timestamp) {
//       System.out.printf("%20f %-20s @ %f%n", val, units, timestamp);
//     }
//     void printValue(MagnetFieldStrength val, String units, double timestamp) {
//       System.out.printf("%20s %-20s @ %f%n", val.toString(), units, timestamp);
//     }

//     public void run() {
//       /* Report position, absolute position, velocity, battery voltage */
      
//       //Front Left
//       // double FLposValue = FrontLeftCANCoder.getPosition();
//       // String FLposUnits = FrontLeftCANCoder.getLastUnitString();
//       // double FLposTstmp = FrontLeftCANCoder.getLastTimestamp();
      
//       // double FLabsValue = FrontLeftCANCoder.getAbsolutePosition();
//       // String FLabsUnits = FrontLeftCANCoder.getLastUnitString();
//       // double FLabsTstmp = FrontLeftCANCoder.getLastTimestamp();
      
//       // double FLvelValue = FrontLeftCANCoder.getVelocity();
//       // String FLvelUnits = FrontLeftCANCoder.getLastUnitString();
//       // double FLvelTstmp = FrontLeftCANCoder.getLastTimestamp();
      
//       // double FLbatValue = FrontLeftCANCoder.getBusVoltage();
//       // String FLbatUnits = FrontLeftCANCoder.getLastUnitString();
//       // double FLbatTstmp = FrontLeftCANCoder.getLastTimestamp();


//       //Back Left
//       double BLposValue = BackLeftCANCoder.getPosition();
//       String BLposUnits = BackLeftCANCoder.getLastUnitString();
//       double BLposTstmp = BackLeftCANCoder.getLastTimestamp();
      
//       double BLabsValue = BackLeftCANCoder.getAbsolutePosition();
//       String BLabsUnits = BackLeftCANCoder.getLastUnitString();
//       double BLabsTstmp = BackLeftCANCoder.getLastTimestamp();
      
//       double BLvelValue = BackLeftCANCoder.getVelocity();
//       String BLvelUnits = BackLeftCANCoder.getLastUnitString();
//       double BLvelTstmp = BackLeftCANCoder.getLastTimestamp();
      
//       double BLbatValue = BackLeftCANCoder.getBusVoltage();
//       String BLbatUnits = BackLeftCANCoder.getLastUnitString();
//       double BLbatTstmp = BackLeftCANCoder.getLastTimestamp();


//       //Back Right
//       double BRposValue = BackRightCANCoder.getPosition();
//       String BRposUnits = BackRightCANCoder.getLastUnitString();
//       double BRposTstmp = BackRightCANCoder.getLastTimestamp();
      
//       double BRabsValue = BackRightCANCoder.getAbsolutePosition();
//       String BRabsUnits = BackRightCANCoder.getLastUnitString();
//       double BRabsTstmp = BackRightCANCoder.getLastTimestamp();
      
//       double BRvelValue = BackRightCANCoder.getVelocity();
//       String BRvelUnits = BackRightCANCoder.getLastUnitString();
//       double BRvelTstmp = BackRightCANCoder.getLastTimestamp();
      
//       double BRbatValue = BackRightCANCoder.getBusVoltage();
//       String BRbatUnits = BackRightCANCoder.getLastUnitString();
//       double BRbatTstmp = BackRightCANCoder.getLastTimestamp();


//       //Front Right
//       double FRposValue = FrontRightCANCoder.getPosition();
//       String FRposUnits = FrontRightCANCoder.getLastUnitString();
//       double FRposTstmp = FrontRightCANCoder.getLastTimestamp();
      
//       double FRabsValue = FrontRightCANCoder.getAbsolutePosition();
//       String FRabsUnits = FrontRightCANCoder.getLastUnitString();
//       double FRabsTstmp = FrontRightCANCoder.getLastTimestamp();
      
//       double FRvelValue = FrontRightCANCoder.getVelocity();
//       String FRvelUnits = FrontRightCANCoder.getLastUnitString();
//       double FRvelTstmp = FrontRightCANCoder.getLastTimestamp();
      
//       double FRbatValue = FrontRightCANCoder.getBusVoltage();
//       String FRbatUnits = FrontRightCANCoder.getLastUnitString();
//       double FRbatTstmp = FrontRightCANCoder.getLastTimestamp();
      



//       /* Report miscellaneous attributes about the CANCoder */
//       //Front Left
//       // MagnetFieldStrength FLmagnetStrength = FrontLeftCANCoder.getMagnetFieldStrength();
//       // String FLmagnetStrengthUnits = FrontLeftCANCoder.getLastUnitString();
//       // double FLmagnetStrengthTstmp = FrontLeftCANCoder.getLastTimestamp();
      

//       //Back Left
//       MagnetFieldStrength BLmagnetStrength = BackLeftCANCoder.getMagnetFieldStrength();
//       String BLmagnetStrengthUnits = BackLeftCANCoder.getLastUnitString();
//       double BLmagnetStrengthTstmp = BackLeftCANCoder.getLastTimestamp();


//       //Back Right
//       MagnetFieldStrength BRmagnetStrength = BackRightCANCoder.getMagnetFieldStrength();
//       String BRmagnetStrengthUnits = BackRightCANCoder.getLastUnitString();
//       double BRmagnetStrengthTstmp = BackRightCANCoder.getLastTimestamp();


//       //Front Right
//       MagnetFieldStrength FRmagnetStrength = FrontRightCANCoder.getMagnetFieldStrength();
//       String FRmagnetStrengthUnits = FrontRightCANCoder.getLastUnitString();
//       double FRmagnetStrengthTstmp = FrontRightCANCoder.getLastTimestamp();

      
//       /*Front Left
//       System.out.print("FL Position: ");
//       printValue(FLposValue, FLposUnits, FLposTstmp);
//       System.out.print("FL Abs Pos : ");
//       printValue(FLabsValue, FLabsUnits, FLabsTstmp);
//       System.out.print("FL Velocity: ");
//       printValue(FLvelValue, FLvelUnits, FLvelTstmp);
//       System.out.print(" FL Battery : ");
//       printValue(FLbatValue, FLbatUnits, FLbatTstmp);
//       System.out.print("FL Strength: ");
//       printValue(FLmagnetStrength, FLmagnetStrengthUnits, FLmagnetStrengthTstmp);


//       //Back Left
//       System.out.print("BL Position: ");
//       printValue(BLposValue, BLposUnits, BLposTstmp);
//       System.out.print("BL Abs Pos : ");
//       printValue(BLabsValue, BLabsUnits, BLabsTstmp);
//       System.out.print("BL Velocity: ");
//       printValue(BLvelValue, BLvelUnits, BLvelTstmp);
//       System.out.print(" BL Battery : ");
//       printValue(BLbatValue, BLbatUnits, BLbatTstmp);
//       System.out.print("BL Strength: ");
//       printValue(BLmagnetStrength, BLmagnetStrengthUnits, BLmagnetStrengthTstmp);


//       //Back Right
//       System.out.print("BR Position: ");
//       printValue(BRposValue, BRposUnits, BRposTstmp);
//       System.out.print("BR Abs Pos : ");
//       printValue(BRabsValue, BRabsUnits, BRabsTstmp);
//       System.out.print("BR Velocity: ");
//       printValue(BRvelValue, BRvelUnits, BRvelTstmp);
//       System.out.print(" BR Battery : ");
//       printValue(BRbatValue, BRbatUnits, BRbatTstmp);
//       System.out.print("BR Strength: ");
//       printValue(BRmagnetStrength, BRmagnetStrengthUnits, BRmagnetStrengthTstmp);


//       //Front Right
//       System.out.print("FR Position: ");
//       printValue(FRposValue, FRposUnits, FRposTstmp);
//       System.out.print("FR Abs Pos : ");
//       printValue(FRabsValue, FRabsUnits, FRabsTstmp);
//       System.out.print("FR Velocity: ");
//       printValue(FRvelValue, FRvelUnits, FRvelTstmp);
//       System.out.print(" FR Battery : ");
//       printValue(FRbatValue, FRbatUnits, FRbatTstmp);
//       System.out.print("FR Strength: ");
//       printValue(FRmagnetStrength, FRmagnetStrengthUnits, FRmagnetStrengthTstmp);
//       */


//       /* Fault reporting
//       CANCoderFaults faults = new CANCoderFaults();
//       _CANCoder.getFaults(faults);
//       CANCoderStickyFaults stickyFaults = new CANCoderStickyFaults();
//       _CANCoder.getStickyFaults(stickyFaults);

//       System.out.println("Faults:");
//       printFaults(faults);
//       System.out.println("Sticky Faults:");
//       printFaults(stickyFaults);

//       System.out.println();
//       System.out.println(); */
//     }
//   }

//   /**
//    * This function is run when the robot is first started up and should be used
//    * for any initialization code.
//    */
//   @Override
//   public void robotInit() {
//     /* Change the configuration configs as needed here */
//     FrontLeftcanCoderConfiguration.unitString = "Penguins";
//     BackLeftcanCoderConfiguration.unitString = "Penguins";
//     BackRightcanCoderConfiguration.unitString = "Penguins";
//     FrontRightcanCoderConfiguration.unitString = "Penguins";

//     //FrontLeftCANCoder.configAllSettings(FrontLeftcanCoderConfiguration);
//     BackLeftCANCoder.configAllSettings(BackLeftcanCoderConfiguration);
//     BackRightCANCoder.configAllSettings(BackRightcanCoderConfiguration);
//     FrontRightCANCoder.configAllSettings(FrontRightcanCoderConfiguration);
//   }

//   int count = 0;
//   @Override
//   public void robotPeriodic() {
//     if(count++ >= PRINTOUT_DELAY / 10) {
//       count = 0;

//       /**  
//        * Doing lots of printing in Java creates a large overhead 
//        * This Instrument class is designed to put that printing in a seperate thread
//        * That way we can prevent loop overrun messages from occurring
//        */
//       new Instrument().start();
//     }
//   }

//   @Override
//   public void autonomousInit() {
//   }

//   @Override
//   public void autonomousPeriodic() {
//   }

//   @Override
//   public void teleopInit() {
//   }

//   @Override
//   public void teleopPeriodic() {
//   }

//   @Override
//   public void testInit() {
//   }

//   @Override
//   public void testPeriodic() {
//   } 
// */
// } 
