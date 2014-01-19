import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/*
 * MenuSelect.java
 *
 * Created on Nov 20, 2011, 2:29:33 PM
 */

// The class MenuSelect that contains all of the windows, JButtons, JFrames,
// JLabels etc.
@SuppressWarnings("serial")
public class MenuSelect extends JFrame implements WindowListener {
    private static final Font FONT_PLAIN = new java.awt.Font("Times New Roman",
	    1, 14);
    private static final String STRING_ERR_WRITE_FAILED = "could not write backup";
    private static final String STRING_MSG_WRITING_NEW_DATABASE = "writing new database";
    private static final String STRING_ERR_CLOSE_FAILED = "could not close stream";
    private static final String STRING_ERR_READ_FAILED_RENAMING_TO = "database could not be read; renaming over to ";
    private static final String STRING_DELETE_AN_EMPLOYEE_TITLE = "Delete an Employee";
    private static final String STRING_EMPLOYEE_EARNINGS_TITLE = "Employee Earnings";
    private static final String STRING_EDIT_EMPLOYEE_TITLE = "Edit an Employee";
    private static final String STRING_CREATE_EMPLOYEE_TITLE = "Create Employee";
    private static final String STRING_MAIN_WINDOW_TITLE = "Employee Database Menu Selection";
    public static final String STRING_EMPLOYEE_DATABASE_FILE = "database_backup.xml";
    EmployeeDatabase database = null;

    // Backup the database, if there is no file, create a new one
    public MenuSelect() {
	super(STRING_MAIN_WINDOW_TITLE);
	FileInputStream is;
	try {
	    is = new FileInputStream(STRING_EMPLOYEE_DATABASE_FILE);
	    try {
		database = EmployeeDatabase.readFromStream(is);
		is.close();
	    } catch (Throwable e) {
		final String newName = STRING_EMPLOYEE_DATABASE_FILE + ".old."
			+ System.currentTimeMillis();
		System.err
			.println(STRING_ERR_READ_FAILED_RENAMING_TO + newName);
		new File(STRING_EMPLOYEE_DATABASE_FILE).renameTo(new File(
			newName));
	    } finally {
		try {
		    is.close();
		} catch (IOException e) {
		    System.err.println(STRING_ERR_CLOSE_FAILED);
		    e.printStackTrace();
		}
	    }
	} catch (FileNotFoundException e1) {
	    System.err.println("could not read backup");
	    e1.printStackTrace();
	}
	if (database == null) {
	    FileOutputStream fos;
	    try {
		fos = new FileOutputStream(STRING_EMPLOYEE_DATABASE_FILE);
		try {
		    System.out.println(STRING_MSG_WRITING_NEW_DATABASE);
		    (database = new EmployeeDatabase()).writeToStream(fos);
		} finally {
		    try {
			fos.close();
		    } catch (IOException e) {
			System.err.println(STRING_ERR_CLOSE_FAILED);
			e.printStackTrace();
		    }
		}
	    } catch (FileNotFoundException e1) {
		System.err.println(STRING_ERR_WRITE_FAILED);
		e1.printStackTrace();
	    }
	}
	createEmployeeButtonCreate
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			createEmployeeButtonActionPerformed(evt);
		    }
		});
	employeeNumberCreate
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			employeeNumberActionPerformed(evt);
		    }
		});
	createEmployeeLabelCreate.setFont(FONT_PLAIN);
	salaryCreate.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		salaryActionPerformed(evt);
	    }
	});
	fullTimePartTimeButtonCreate.add(fullTimeRadioButtonCreate);
	fullTimeRadioButtonCreate
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			fullTimeRadioButtonCreateActionPerformed(evt);
		    }
		});
	fullTimePartTimeButtonCreate.add(partTimeRadioButtonCreate);
	partTimeRadioButtonCreate
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			partTimeRadioButtonCreateActionPerformed(evt);
		    }
		});
	workLocationComboBoxCreate.setModel(new DefaultComboBoxModel(
		Employee.validWorkLocations));
	workLocationEditComboBoxEdit.setModel(new DefaultComboBoxModel(
		Employee.validWorkLocations));
	employeeSexButtonCreate.add(employeeSexMaleCreate);
	employeeSexButtonCreate.add(employeeSexFemaleCreate);
	GroupLayout createEmployeeFrameLayout = new GroupLayout(
		createEmployeeFrame.getContentPane());
	createEmployeeFrame.getContentPane().setLayout(
		createEmployeeFrameLayout);
	//@formatter:off
	createEmployeeFrameLayout.setHorizontalGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addContainerGap().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING,false).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lastNameLabelCreate).addComponent(middleNameLabelCreate).addComponent(employeeNumLabelCreate).addComponent(employeeSexLabelCreate).addComponent(firstNameLabelCreate)).addGap(18,18,18).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addComponent(employeeSexMaleCreate).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(employeeSexFemaleCreate)).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING,false).addComponent(employeeLastNameCreate).addComponent(employeeMiddleNameCreate).addComponent(employeeFirstNameCreate).addComponent(employeeNumberCreate,GroupLayout.PREFERRED_SIZE,115,GroupLayout.PREFERRED_SIZE)))).addGroup(createEmployeeFrameLayout.createSequentialGroup().addComponent(workLocationLabelCreate).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(workLocationComboBoxCreate,GroupLayout.PREFERRED_SIZE,115,GroupLayout.PREFERRED_SIZE))).addGap(18,18,18).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING,false).addComponent(fullTimeRadioButtonCreate).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(vacationDaysLabelCreate).addComponent(yearsOfServiceLabelCreate).addComponent(salaryLabelCreate).addComponent(deductionsLabelCreate)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(vacationDaysCreate,GroupLayout.DEFAULT_SIZE,64,Short.MAX_VALUE).addComponent(yearsOfServiceCreate,GroupLayout.DEFAULT_SIZE,64,Short.MAX_VALUE).addComponent(deductionsRateCreate,GroupLayout.DEFAULT_SIZE,64,Short.MAX_VALUE).addComponent(salaryCreate,GroupLayout.DEFAULT_SIZE,64,Short.MAX_VALUE))).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hoursAWeekLabelCreate).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(weeksAYearLabelCreate).addGroup(GroupLayout.Alignment.TRAILING,createEmployeeFrameLayout.createSequentialGroup().addComponent(hourlyWageLabelCreate).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,4,GroupLayout.PREFERRED_SIZE)))).addGap(28,28,28).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hourlyWageCreate,GroupLayout.DEFAULT_SIZE,73,Short.MAX_VALUE).addComponent(hoursAWeekCreate).addComponent(weeksAYearCreate,GroupLayout.DEFAULT_SIZE,73,Short.MAX_VALUE))).addGroup(createEmployeeFrameLayout.createSequentialGroup().addComponent(deductionsRateLabelPartCreate).addGap(18,18,18).addComponent(deductionsRatePartCreate))).addComponent(partTimeRadioButtonCreate)).addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)).addGroup(GroupLayout.Alignment.TRAILING,createEmployeeFrameLayout.createSequentialGroup().addComponent(createEmployeeButtonCreate).addGap(159,159,159)).addGroup(GroupLayout.Alignment.TRAILING,createEmployeeFrameLayout.createSequentialGroup().addComponent(createEmployeeLabelCreate).addGap(147,147,147)))));
	createEmployeeFrameLayout.setVerticalGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addContainerGap().addComponent(createEmployeeLabelCreate).addGap(18,18,18).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addComponent(fullTimeRadioButtonCreate).addGap(6,6,6).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(salaryCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(salaryLabelCreate))).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGap(60,60,60).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(deductionsRateCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(deductionsLabelCreate)).addGap(11,11,11).addComponent(yearsOfServiceCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addGap(3,3,3)).addComponent(yearsOfServiceLabelCreate)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vacationDaysLabelCreate).addComponent(vacationDaysCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))).addGap(43,43,43).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(hourlyWageLabelCreate).addComponent(hourlyWageCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(hoursAWeekLabelCreate).addComponent(hoursAWeekCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(13,13,13).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(weeksAYearLabelCreate,GroupLayout.PREFERRED_SIZE,14,GroupLayout.PREFERRED_SIZE).addComponent(weeksAYearCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(deductionsRateLabelPartCreate).addComponent(deductionsRatePartCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))).addGroup(createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeNumLabelCreate).addComponent(employeeNumberCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(18,18,18).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeSexLabelCreate).addComponent(employeeSexMaleCreate).addComponent(employeeSexFemaleCreate)).addGap(18,18,18).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstNameLabelCreate).addComponent(employeeFirstNameCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(23,23,23).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(middleNameLabelCreate).addComponent(employeeMiddleNameCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(15,15,15).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lastNameLabelCreate,GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING,createEmployeeFrameLayout.createSequentialGroup().addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeLastNameCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(partTimeRadioButtonCreate)).addGap(3,3,3))).addGap(11,11,11).addGroup(createEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(workLocationLabelCreate).addComponent(workLocationComboBoxCreate,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))).addGap(18,18,18).addComponent(createEmployeeButtonCreate).addContainerGap(29,Short.MAX_VALUE)));
	//@formatter:on
	saveChangesButtonEdit
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			saveChangesButtonActionPerformed(evt);
		    }
		});
	employeeNumEdit.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		employeeNum1ActionPerformed(evt);
	    }
	});
	editEmployeeLabelEdit.setFont(FONT_PLAIN);
	findEmployeeButtonEdit
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			findEmployeeButtonModifyActionPerformed(evt);
		    }
		});
	fullTimePartTimeButtonCreate.add(partTimeRadioButtonEdit);
	partTimeRadioButtonEdit
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			partTimeRadioButtonModifyActionPerformed(evt);
		    }
		});
	salaryEdit.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		salary1ActionPerformed(evt);
	    }
	});
	fullTimePartTimeButtonCreate.add(fullTimeRadioButtonEdit);
	fullTimeRadioButtonEdit
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			fullTimeRadioButtonModifyActionPerformed(evt);
		    }
		});
	employeeSexButtonEdit.add(employeeSexFemaleEdit);
	employeeSexButtonEdit.add(employeeSexMaleEdit);
	GroupLayout editEmployeeFrameLayout = new GroupLayout(
		editEmployeeFrame.getContentPane());
	editEmployeeFrame.getContentPane().setLayout(editEmployeeFrameLayout);
	//@formatter:off
	editEmployeeFrameLayout.setHorizontalGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(24,24,24).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(54,54,54).addComponent(editEmployeeLabelEdit)).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(10,10,10).addComponent(employeeNumLabel1).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING,editEmployeeFrameLayout.createSequentialGroup().addGap(18,18,18).addComponent(employeeNumEdit,GroupLayout.PREFERRED_SIZE,114,GroupLayout.PREFERRED_SIZE)).addGroup(GroupLayout.Alignment.LEADING,editEmployeeFrameLayout.createSequentialGroup().addGap(20,20,20).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeSexMaleEdit).addComponent(findEmployeeButtonEdit,GroupLayout.DEFAULT_SIZE,161,Short.MAX_VALUE))))))).addGroup(editEmployeeFrameLayout.createSequentialGroup().addContainerGap().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(middleNameLabelEdit).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(10,10,10).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeSexLabelEdit).addComponent(firstNameLabel1)).addComponent(lastNameLabelEdit)).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(26,26,26).addComponent(employeeLastNameEdit,GroupLayout.DEFAULT_SIZE,175,Short.MAX_VALUE)).addGroup(GroupLayout.Alignment.TRAILING,editEmployeeFrameLayout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(employeeSexFemaleEdit).addGap(80,80,80)))).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(77,77,77).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(middleNameEdit,GroupLayout.DEFAULT_SIZE,175,Short.MAX_VALUE).addComponent(employeeFirstNameEdit,GroupLayout.DEFAULT_SIZE,175,Short.MAX_VALUE))))).addGroup(editEmployeeFrameLayout.createSequentialGroup().addComponent(workLocationLabelEdit).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addComponent(saveChangesButtonEdit).addGap(1,1,1)).addComponent(workLocationEditComboBoxEdit,GroupLayout.DEFAULT_SIZE,176,Short.MAX_VALUE)))))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING,false).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hoursAWeekLabelEdit).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(weeksAYearLabelEdit).addGroup(GroupLayout.Alignment.TRAILING,editEmployeeFrameLayout.createSequentialGroup().addComponent(hourlyWageLabelEdit).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,4,GroupLayout.PREFERRED_SIZE)))).addGap(28,28,28).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hourlyWageEdit,GroupLayout.DEFAULT_SIZE,73,Short.MAX_VALUE).addComponent(hoursAWeekEdit).addComponent(weeksAYearEdit,GroupLayout.DEFAULT_SIZE,73,Short.MAX_VALUE))).addGroup(editEmployeeFrameLayout.createSequentialGroup().addComponent(deductionsRateLabelEdit).addGap(18,18,18).addComponent(deductionsRateEdit))).addComponent(partTimeRadioButtonEdit)).addContainerGap()).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(vacationDaysLabelEdit).addComponent(fullTimeRadioButtonEdit).addComponent(deductionsLabelEdit).addComponent(yearsOfServiceLabelEdit).addComponent(salaryLabelEdit)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(salaryEdit,GroupLayout.DEFAULT_SIZE,83,Short.MAX_VALUE).addComponent(vacationDaysEdit,GroupLayout.DEFAULT_SIZE,83,Short.MAX_VALUE).addComponent(yearsOfServiceEdit,GroupLayout.DEFAULT_SIZE,83,Short.MAX_VALUE).addComponent(deductionsRatePartEdit,GroupLayout.DEFAULT_SIZE,83,Short.MAX_VALUE)).addGap(27,27,27)))));
	editEmployeeFrameLayout.setVerticalGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(23,23,23).addComponent(editEmployeeLabelEdit).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeNumLabel1).addComponent(employeeNumEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(findEmployeeButtonEdit)).addGroup(editEmployeeFrameLayout.createSequentialGroup().addComponent(fullTimeRadioButtonEdit).addGap(13,13,13).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(salaryEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(salaryLabelEdit)))).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addComponent(employeeSexLabelEdit).addGap(20,20,20).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(firstNameLabel1).addComponent(employeeFirstNameEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(15,15,15).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(middleNameLabelEdit).addComponent(middleNameEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lastNameLabelEdit).addComponent(employeeLastNameEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(workLocationLabelEdit).addComponent(workLocationEditComboBoxEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(saveChangesButtonEdit)).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(deductionsRatePartEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(deductionsLabelEdit).addComponent(employeeSexFemaleEdit).addComponent(employeeSexMaleEdit)).addGap(11,11,11).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(yearsOfServiceEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(yearsOfServiceLabelEdit)).addGap(14,14,14).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vacationDaysLabelEdit).addComponent(vacationDaysEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(18,18,18).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(editEmployeeFrameLayout.createSequentialGroup().addGap(34,34,34).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(hourlyWageLabelEdit).addComponent(hourlyWageEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(hoursAWeekLabelEdit).addComponent(hoursAWeekEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(13,13,13).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(weeksAYearLabelEdit,GroupLayout.PREFERRED_SIZE,14,GroupLayout.PREFERRED_SIZE).addComponent(weeksAYearEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(editEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(deductionsRateLabelEdit).addComponent(deductionsRateEdit,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))).addComponent(partTimeRadioButtonEdit)))).addContainerGap(22,Short.MAX_VALUE)));
	//@formatter:on
	employeeNumEarnings
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			employeeNum2ActionPerformed(evt);
		    }
		});
	createEmployeeLabelEarnings.setFont(FONT_PLAIN);
	calculateButtonEarnings
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			calculateButtonActionPerformed(evt);
		    }
		});
	GroupLayout earningsFrameLayout = new GroupLayout(
		earningsFrame.getContentPane());
	earningsFrame.getContentPane().setLayout(earningsFrameLayout);
	//@formatter:off
	earningsFrameLayout.setHorizontalGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(earningsFrameLayout.createSequentialGroup().addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(earningsFrameLayout.createSequentialGroup().addGap(70,70,70).addComponent(createEmployeeLabelEarnings)).addGroup(earningsFrameLayout.createSequentialGroup().addGap(20,20,20).addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeNumLabelEarnings).addComponent(grossPayLabelEarnings).addComponent(netPayLabelEarnings)).addGap(32,32,32).addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(earningsFrameLayout.createSequentialGroup().addComponent(calculateButtonEarnings).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)).addGroup(GroupLayout.Alignment.TRAILING,earningsFrameLayout.createSequentialGroup().addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING,earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING,false).addComponent(employeeNumEarnings,GroupLayout.Alignment.LEADING).addComponent(grossPayEarnings,GroupLayout.Alignment.LEADING).addComponent(netPayEarnings,GroupLayout.Alignment.LEADING,GroupLayout.DEFAULT_SIZE,122,Short.MAX_VALUE))).addGap(23,23,23))))).addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)));
	earningsFrameLayout.setVerticalGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,earningsFrameLayout.createSequentialGroup().addGap(22,22,22).addComponent(createEmployeeLabelEarnings).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,36,Short.MAX_VALUE).addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeNumLabelEarnings).addComponent(employeeNumEarnings,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(18,18,18).addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(grossPayLabelEarnings).addComponent(grossPayEarnings,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addGap(47,47,47).addComponent(calculateButtonEarnings).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(earningsFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(netPayEarnings,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addComponent(netPayLabelEarnings)).addGap(25,25,25)));
	//@formatter:on
	deleteButtonDelete
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			deleteButtonActionPerformed(evt);
		    }
		});
	employeeNumDelete
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			employeeNum3ActionPerformed(evt);
		    }
		});
	editEmployeeLabelDelete.setFont(FONT_PLAIN);
	findEmployeeButtonDelete
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			findEmployeeButtonDeleteActionPerformed(evt);
		    }
		});
	employeeSexButtonDelete.add(employeeSexMaleDelete);
	employeeSexButtonDelete.add(employeeSexFemaleDelete);
	GroupLayout deleteEmployeeFrameLayout = new GroupLayout(
		deleteEmployeeFrame.getContentPane());
	deleteEmployeeFrame.getContentPane().setLayout(
		deleteEmployeeFrameLayout);
	//@formatter:off
	deleteEmployeeFrameLayout.setHorizontalGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addGap(63,63,63).addComponent(editEmployeeLabelDelete)).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addGap(20,20,20).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeNumLabelDelete).addComponent(employeeSexLabelDelete).addComponent(firstNameLabelDelete).addComponent(lastNameLabelDelete)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeLastNameDelete,GroupLayout.DEFAULT_SIZE,144,Short.MAX_VALUE).addComponent(employeeFirstNameDelete,GroupLayout.DEFAULT_SIZE,144,Short.MAX_VALUE).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addComponent(employeeSexMaleDelete).addGap(17,17,17).addComponent(employeeSexFemaleDelete)).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addGap(10,10,10).addComponent(findEmployeeButtonDelete)).addComponent(employeeNumDelete,GroupLayout.PREFERRED_SIZE,122,GroupLayout.PREFERRED_SIZE))).addGroup(GroupLayout.Alignment.TRAILING,deleteEmployeeFrameLayout.createSequentialGroup().addContainerGap(73,Short.MAX_VALUE).addComponent(deleteButtonDelete).addGap(95,95,95))).addContainerGap()));
	deleteEmployeeFrameLayout.setVerticalGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,deleteEmployeeFrameLayout.createSequentialGroup().addGap(25,25,25).addComponent(editEmployeeLabelDelete).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,43,Short.MAX_VALUE).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeNumLabelDelete).addComponent(employeeNumDelete,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(findEmployeeButtonDelete).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeSexMaleDelete).addComponent(employeeSexFemaleDelete)).addComponent(employeeSexLabelDelete)).addGap(13,13,13).addGroup(deleteEmployeeFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addComponent(firstNameLabelDelete).addGap(24,24,24).addComponent(lastNameLabelDelete)).addGroup(deleteEmployeeFrameLayout.createSequentialGroup().addComponent(employeeFirstNameDelete,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addGap(18,18,18).addComponent(employeeLastNameDelete,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))).addGap(18,18,18).addComponent(deleteButtonDelete).addGap(25,25,25)));
	//@formatter:on
	employeeSexMaleDelete.setEnabled(false);
	employeeSexFemaleDelete.setEnabled(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	addEmployeeButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			addEmployeeButtonActionPerformed(evt);
		    }
		});
	modifyEmployeeButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			modifyEmployeeButtonActionPerformed(evt);
		    }
		});
	backupButton.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		backupButtonActionPerformed(evt);
	    }
	});
	earningsEmployeeButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			earningsEmployeeButtonActionPerformed(evt);
		    }
		});
	mainMenuLabel.setFont(FONT_PLAIN);
	deleteEmployeeButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			deleteEmployeeButtonActionPerformed(evt);
		    }
		});
	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	//@formatter:off
	layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(52,52,52).addComponent(addEmployeeButton)).addComponent(mainMenuLabel))).addGroup(layout.createSequentialGroup().addGap(20,20,20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(modifyEmployeeButton).addComponent(earningsEmployeeButton))).addGroup(layout.createSequentialGroup().addGap(62,62,62).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(deleteEmployeeButton).addComponent(backupButton)))).addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)));
	layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(49,49,49).addComponent(mainMenuLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,35,Short.MAX_VALUE).addComponent(addEmployeeButton).addGap(18,18,18).addComponent(modifyEmployeeButton).addGap(18,18,18).addComponent(earningsEmployeeButton).addGap(19,19,19).addComponent(deleteEmployeeButton).addGap(18,18,18).addComponent(backupButton).addContainerGap()));
	//@formatter:on
	pack();
	addWindowListener(this);
    }

    // All these overridden methods are to implement WindowListener
    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    // Save database when the main menu window is closed
    @Override
    public void windowClosing(WindowEvent e) {
	try {
	    database.writeToStream(new FileOutputStream(
		    STRING_EMPLOYEE_DATABASE_FILE));
	} catch (IOException exception) {
	}
    }

    // Create an employee to the database button
    private void createEmployeeButtonActionPerformed(
	    java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createEmployeeButtonActionPerformed
	try {
	    // Get all of the information from the textfields, radio buttons and
	    // combobox
	    int empNumber = Integer.parseInt(employeeNumberCreate.getText());
	    boolean male = employeeSexMaleCreate.isSelected();
	    if (!male && !employeeSexFemaleCreate.isSelected()) {
		return;
	    }
	    final String firstName = employeeFirstNameCreate.getText(), middleName = employeeMiddleNameCreate
		    .getText(), lastName = employeeLastNameCreate.getText(), workLocation = workLocationComboBoxCreate
		    .getSelectedItem().toString();
	    final float deductions = Float.parseFloat(deductionsRateCreate
		    .getText() + deductionsRatePartCreate.getText());
	    // If the full-time or part-time buttons are selected, add a new
	    // part-time or full time-employee
	    final Employee emp;
	    if (fullTimeRadioButtonCreate.isSelected())// fulltime
	    {
		emp = new FullTimeEmployee(empNumber, male, firstName,
			middleName, lastName, workLocation, deductions,
			Integer.parseInt(salaryCreate.getText()),
			Integer.parseInt(yearsOfServiceCreate.getText()),
			Integer.parseInt(vacationDaysCreate.getText()));

	    } else if (partTimeRadioButtonCreate.isSelected()) {
		emp = new PartTimeEmployee(empNumber, male, firstName,
			middleName, lastName, workLocation, deductions,
			Float.parseFloat(hourlyWageCreate.getText()),
			Float.parseFloat(hoursAWeekCreate.getText()),
			Integer.parseInt(weeksAYearCreate.getText()));
	    } else
		return;
	    if (emp.checkInvariants()) {
		database.add(emp);
		// When the employee has been made, make the window not visible
		// to
		// the user
		createEmployeeFrame.setVisible(false);
	    }
	    // Catch any errors to prevent crashing
	} catch (NumberFormatException e) {
	}
    }// GEN-LAST:event_createEmployeeButtonActionPerformed

    // Button to show delete the employee window

    private void deleteEmployeeButtonActionPerformed(
	    java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteEmployeeButtonActionPerformed
	// Set the visible properties of the window
	deleteEmployeeFrame.setSize(250, 350);
	deleteEmployeeFrame.setVisible(true);
	employeeNumDelete.setText(null);
	employeeFirstNameDelete.setText(null);
	employeeLastNameDelete.setText(null);
	employeeFirstNameDelete.setEditable(false);
	employeeLastNameDelete.setEditable(false);
	employeeSexButtonDelete.clearSelection();
    }// GEN-LAST:event_deleteEmployeeButtonActionPerformed
     // Backup database button

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backupButtonActionPerformed
	// Write to the backup file & catch any errors
	try {
	    database.writeToStream(new FileOutputStream(
		    STRING_EMPLOYEE_DATABASE_FILE));
	} catch (IOException e) {
	}
    }// GEN-LAST:event_backupButtonActionPerformed
     // Delete an employee button

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
	// Remove the employee from the database & catch any erros
	try {
	    if (database.remove(Integer.parseInt(employeeNumDelete.getText())))
		// Make the frame invisible to the user after deletion
		deleteEmployeeFrame.setVisible(false);
	} catch (NumberFormatException e) {
	}
    }// GEN-LAST:event_deleteButtonActionPerformed
     // Calculate the gross and net pay of an employee button

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_calculateButtonActionPerformed
	// Get the info of that employee number
	final Employee e = database.get(Integer.parseInt(employeeNumEarnings
		.getText()));
	// Calculate the net and gross pay & set that text to the fields
	if (e == null) {
	    grossPayEarnings.setText("not found");
	    netPayEarnings.setText("not found");
	    return;
	}
	Float gross = e.getGrossPay();
	Float net = e.getNetPay();
	grossPayEarnings.setText(Float.toString(gross));
	netPayEarnings.setText(Float.toString(net));
    }// GEN-LAST:event_calculateButtonActionPerformed
     // Button to show add employee frame

    private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
	// Set the visible properties of the frame
	employeeFirstNameCreate.setText(null);
	employeeMiddleNameCreate.setText(null);
	employeeLastNameCreate.setText(null);
	hourlyWageCreate.setText(null);
	hoursAWeekCreate.setText(null);
	weeksAYearCreate.setText(null);
	deductionsRatePartCreate.setText(null);
	salaryCreate.setText(null);
	deductionsRateCreate.setText(null);
	yearsOfServiceCreate.setText(null);
	vacationDaysCreate.setText(null);
	employeeNumberCreate.setEditable(false);
	employeeSexButtonCreate.clearSelection();
	fullTimePartTimeButtonCreate.clearSelection();
	int empId;
	// Generate a random 6 digit integer not being used currently
	while (database
		.contains(empId = (int) (Math.random() * 900000) + 100000))
	    ;
	employeeNumberCreate.setText(Integer.toString(empId));
	createEmployeeFrame.setSize(500, 500);
	createEmployeeFrame.setVisible(true);
	hourlyWageCreate.setEditable(false);
	hoursAWeekCreate.setEditable(false);
	weeksAYearCreate.setEditable(false);
	deductionsRatePartCreate.setEditable(false);
	salaryCreate.setEditable(false);
	deductionsRateCreate.setEditable(false);
	yearsOfServiceCreate.setEditable(false);
	vacationDaysCreate.setEditable(false);
    }

    private void employeeNumberActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // The button to show the modify employee window
    private void modifyEmployeeButtonActionPerformed(
	    java.awt.event.ActionEvent evt) {
	// Set the visible components to the window
	employeeFirstNameEdit.setText(null);
	employeeNumEdit.setText(null);
	middleNameEdit.setText(null);
	employeeLastNameEdit.setText(null);
	hourlyWageEdit.setText(null);
	hoursAWeekEdit.setText(null);
	weeksAYearEdit.setText(null);
	deductionsRateEdit.setText(null);
	salaryEdit.setText(null);
	deductionsRatePartEdit.setText(null);
	yearsOfServiceEdit.setText(null);
	vacationDaysEdit.setText(null);
	workLocationEditComboBoxEdit.setSelectedIndex(-1);
	workLocationEditComboBoxEdit.setEnabled(false);
	employeeSexButtonEdit.clearSelection();
	employeeNumberCreate.setEditable(false);
	editEmployeeFrame.setSize(500, 500);
	editEmployeeFrame.setVisible(true);
	employeeNumEdit.setEditable(true);
	hourlyWageEdit.setEditable(false);
	hoursAWeekEdit.setEditable(false);
	weeksAYearEdit.setEditable(false);
	deductionsRateEdit.setEditable(false);
	salaryEdit.setEditable(false);
	deductionsRatePartEdit.setEditable(false);
	yearsOfServiceEdit.setEditable(false);
	vacationDaysEdit.setEditable(false);
    }

    private void employeeNum1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Button to find info of an employee using their number to modify them
    private void findEmployeeButtonModifyActionPerformed(
	    java.awt.event.ActionEvent evt) {
	try {
	    // Get the info of that employee number
	    final Employee e = database.get(Integer.parseInt(employeeNumEdit
		    .getText()));
	    if (e == null)// employee not found
		return;
	    (e.isMale ? employeeSexMaleEdit : employeeSexFemaleEdit)
		    .setSelected(true);
	    employeeFirstNameEdit.setText(e.firstName);
	    middleNameEdit.setText(e.middleName);
	    employeeLastNameEdit.setText(e.lastName);
	    for (int i = 0; i < Employee.validWorkLocations.length; ++i)
		if (e.workLocation.equals(Employee.validWorkLocations[i])) {
		    workLocationEditComboBoxEdit.setSelectedIndex(i);
		    workLocationEditComboBoxEdit.setEnabled(true);
		}
	    employeeNumEdit.setEditable(false);
	    // If the employee is of subclass full time or part time, display
	    // the corresponding info
	    if (e instanceof FullTimeEmployee) {
		fullTimeRadioButtonEdit.setSelected(true);
		fullTimeRadioButtonModifyActionPerformed(null);
		salaryEdit.setText(Integer
			.toString(((FullTimeEmployee) e).salary));
		deductionsRatePartEdit.setText(Float
			.toString(((FullTimeEmployee) e).deductionsRate));
		yearsOfServiceEdit.setText(Integer
			.toString(((FullTimeEmployee) e).yearsOfService));
		vacationDaysEdit.setText(Integer
			.toString(((FullTimeEmployee) e).daysVacPerYear));
	    } else if (e instanceof PartTimeEmployee) {
		partTimeRadioButtonEdit.setSelected(true);
		partTimeRadioButtonModifyActionPerformed(null);
		hourlyWageEdit.setText(Float
			.toString(((PartTimeEmployee) e).hourlyWage));
		hoursAWeekEdit.setText(Float
			.toString(((PartTimeEmployee) e).hrsPerWeek));
		weeksAYearEdit.setText(Float
			.toString(((PartTimeEmployee) e).weeksPerYear));
		deductionsRateEdit.setText(Float
			.toString(((PartTimeEmployee) e).deductionsRate));
	    } else
		System.err.println("unknown employee type " + e.getName());
	} catch (NumberFormatException e) {
	}
    }

    private void employeeNum2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // The button to show the earnings frame of an employee
    private void earningsEmployeeButtonActionPerformed(
	    java.awt.event.ActionEvent evt) {
	// Set visible components
	employeeNumEarnings.setText(null);
	grossPayEarnings.setText(null);
	netPayEarnings.setText(null);
	netPayEarnings.setEditable(false);
	grossPayEarnings.setEditable(false);
	earningsFrame.setSize(260, 300);
	earningsFrame.setVisible(true);
    }

    private void employeeNum3ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Find the info of the employee number to delete
    private void findEmployeeButtonDeleteActionPerformed(
	    java.awt.event.ActionEvent evt) {
	try {
	    final Employee e = database.get(Integer.parseInt(employeeNumDelete
		    .getText()));
	    if (e == null)
		return;
	    (e.isMale ? employeeSexMaleDelete : employeeSexFemaleDelete)
		    .setSelected(true);
	    employeeFirstNameDelete.setText(e.firstName);
	    employeeLastNameDelete.setText(e.lastName);
	} catch (NumberFormatException e) {
	}
    }

    private void salaryActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // The full time radio button in create employee
    private void fullTimeRadioButtonCreateActionPerformed(
	    java.awt.event.ActionEvent evt) {
	// Disable and clear the part time employee fields
	hourlyWageCreate.setText(null);
	hoursAWeekCreate.setText(null);
	weeksAYearCreate.setText(null);
	deductionsRatePartCreate.setText(null);
	hourlyWageCreate.setEditable(false);
	hoursAWeekCreate.setEditable(false);
	weeksAYearCreate.setEditable(false);
	deductionsRatePartCreate.setEditable(false);
	salaryCreate.setEditable(true);
	deductionsRateCreate.setEditable(true);
	yearsOfServiceCreate.setEditable(true);
	vacationDaysCreate.setEditable(true);
    }

    // Button for the part time radio button in create employee
    private void partTimeRadioButtonCreateActionPerformed(
	    java.awt.event.ActionEvent evt) {// GEN-FIRST:event_partTimeRadioButtonActionPerformed
	// Disable and clear the full time employee fields
	salaryCreate.setText(null);
	deductionsRateCreate.setText(null);
	yearsOfServiceCreate.setText(null);
	vacationDaysCreate.setText(null);
	salaryCreate.setEditable(false);
	deductionsRateCreate.setEditable(false);
	yearsOfServiceCreate.setEditable(false);
	vacationDaysCreate.setEditable(false);
	hourlyWageCreate.setEditable(true);
	hoursAWeekCreate.setEditable(true);
	weeksAYearCreate.setEditable(true);
	deductionsRatePartCreate.setEditable(true);
    }

    // The save changes button to modify an employee
    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {
	try {
	    // Get the employee number info
	    final Employee oldEmployee = database.get(Integer
		    .parseInt(employeeNumEdit.getText()));
	    int number = Integer.parseInt(employeeNumEdit.getText());
	    String first = employeeFirstNameEdit.getText();
	    String middle = middleNameEdit.getText();
	    String last = employeeLastNameEdit.getText();
	    Boolean isMale = employeeSexMaleEdit.isSelected();
	    String location = workLocationEditComboBoxEdit.getSelectedItem()
		    .toString();
	    // Remake the employee with modified info and possibly different
	    // subclass
	    // (part time or full time)
	    // Remove original employee
	    final Employee newEmployee;
	    if (partTimeRadioButtonEdit.isSelected() == true) {
		Float rate = Float.parseFloat(deductionsRateEdit.getText());
		Float wage = Float.parseFloat(hourlyWageEdit.getText());
		Float hrs = Float.parseFloat(hoursAWeekEdit.getText());
		int weeks = Integer.parseInt(weeksAYearEdit.getText());
		// Remake employee
		newEmployee = new PartTimeEmployee(number, isMale, first,
			middle, last, location, rate, wage, hrs, weeks);
	    } else if (fullTimeRadioButtonEdit.isSelected() == true) {
		int sal = Integer.parseInt(salaryEdit.getText());
		int service = Integer.parseInt(yearsOfServiceEdit.getText());
		int daysVacPerYear = Integer.parseInt(vacationDaysEdit
			.getText());
		Float deductions = Float.parseFloat(deductionsRatePartEdit
			.getText());
		newEmployee = new FullTimeEmployee(number, isMale, first,
			middle, last, location, deductions, sal, service,
			daysVacPerYear);
	    } else {
		// Return if there is no selection of employee subclass (we
		// should never get here)
		// Also, the employee was removed, so we need to un-remove it
		System.err.println("Could not find type for modified employee");
		database.add(oldEmployee);
		return;
	    }
	    if (newEmployee.checkInvariants()) {
		database.remove(oldEmployee);
		database.add(newEmployee);
		// Exit the frame (invisible to user)
		editEmployeeFrame.setVisible(false);
	    }
	} catch (NumberFormatException e) {// invalid number for some field
	} catch (ArrayIndexOutOfBoundsException e) {// no work location
	}
    }

    // Part time radio button for the modify employee window
    private void partTimeRadioButtonModifyActionPerformed(
	    java.awt.event.ActionEvent evt) {
	// Disable and clear the full time fields
	salaryEdit.setText(null);
	deductionsRatePartEdit.setText(null);
	yearsOfServiceEdit.setText(null);
	vacationDaysEdit.setText(null);
	salaryEdit.setEditable(false);
	deductionsRatePartEdit.setEditable(false);
	yearsOfServiceEdit.setEditable(false);
	vacationDaysEdit.setEditable(false);
	hourlyWageEdit.setEditable(true);
	hoursAWeekEdit.setEditable(true);
	weeksAYearEdit.setEditable(true);
	deductionsRateEdit.setEditable(true);
    }

    private void salary1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Full time radio button for the modify employee window
    private void fullTimeRadioButtonModifyActionPerformed(
	    java.awt.event.ActionEvent evt) {
	// Disable and clear the part time fields
	hourlyWageEdit.setText(null);
	hoursAWeekEdit.setText(null);
	weeksAYearEdit.setText(null);
	deductionsRateEdit.setText(null);
	hourlyWageEdit.setEditable(false);
	hoursAWeekEdit.setEditable(false);
	weeksAYearEdit.setEditable(false);
	deductionsRateEdit.setEditable(false);
	salaryEdit.setEditable(true);
	deductionsRatePartEdit.setEditable(true);
	yearsOfServiceEdit.setEditable(true);
	vacationDaysEdit.setEditable(true);
    }

    public static void main(String args[]) {
	try {
	    for (UIManager.LookAndFeelInfo info : UIManager
		    .getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {// logging boilerplate
	    java.util.logging.Logger.getLogger(MenuSelect.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MenuSelect.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MenuSelect.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	} catch (UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MenuSelect.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	// </editor-fold>
	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    // Create a new menuselect and make it visible
	    @Override
	    public void run() {
		new MenuSelect().setVisible(true);
	    }
	});
    }

    //@formatter:off
    private JFrame createEmployeeFrame=new JFrame(STRING_CREATE_EMPLOYEE_TITLE),deleteEmployeeFrame=new JFrame(STRING_DELETE_AN_EMPLOYEE_TITLE),earningsFrame=new JFrame(STRING_EMPLOYEE_EARNINGS_TITLE),editEmployeeFrame=new JFrame(STRING_EDIT_EMPLOYEE_TITLE);
    private JTextField deductionsRateCreate=new JTextField(),deductionsRatePartCreate=new JTextField(),deductionsRateEdit=new JTextField(),deductionsRatePartEdit=new JTextField(),employeeFirstNameCreate=new JTextField(),employeeFirstNameEdit=new JTextField(),employeeFirstNameDelete=new JTextField(),employeeLastNameCreate=new JTextField(),employeeLastNameEdit=new JTextField(),employeeLastNameDelete=new JTextField(),employeeMiddleNameCreate=new JTextField(),employeeNumEdit=new JTextField(),employeeNumEarnings=new JTextField(),employeeNumDelete=new JTextField(),employeeNumberCreate=new JTextField(),grossPayEarnings=new JTextField(),hourlyWageCreate=new JTextField(),hourlyWageEdit=new JTextField(),hoursAWeekCreate=new JTextField(),hoursAWeekEdit=new JTextField(),middleNameEdit=new JTextField(),netPayEarnings=new JTextField(),salaryCreate=new JTextField(),salaryEdit=new JTextField(),vacationDaysCreate=new JTextField(),vacationDaysEdit=new JTextField(),weeksAYearCreate=new JTextField(),weeksAYearEdit=new JTextField(),yearsOfServiceCreate=new JTextField(),yearsOfServiceEdit=new JTextField();
    private JRadioButton employeeSexFemaleCreate=new JRadioButton("F"),employeeSexFemaleEdit=new JRadioButton("F"),employeeSexFemaleDelete=new JRadioButton("F"),employeeSexMaleCreate=new JRadioButton("M"),employeeSexMaleEdit=new JRadioButton("M"),employeeSexMaleDelete=new JRadioButton("M"),fullTimeRadioButtonCreate=new JRadioButton("Full Time"),fullTimeRadioButtonEdit=new JRadioButton("Full Time"),partTimeRadioButtonCreate=new JRadioButton("Part Time"),partTimeRadioButtonEdit=new JRadioButton("Part Time");
    private JLabel createEmployeeLabelCreate=new JLabel(STRING_CREATE_EMPLOYEE_TITLE),createEmployeeLabelEarnings=new JLabel(STRING_EMPLOYEE_EARNINGS_TITLE),deductionsLabelCreate=new JLabel("Deductions Rate"),deductionsLabelEdit=new JLabel("Deductions Rate"),deductionsRateLabelPartCreate=new JLabel("Deductions Rate"),deductionsRateLabelEdit=new JLabel("Deductions Rate"),editEmployeeLabelEdit=new JLabel(STRING_EDIT_EMPLOYEE_TITLE),editEmployeeLabelDelete=new JLabel(STRING_DELETE_AN_EMPLOYEE_TITLE),employeeNumLabelCreate=new JLabel("Employee #"),employeeNumLabel1=new JLabel("Employee #"),employeeNumLabelEarnings=new JLabel("Employee #"),employeeNumLabelDelete=new JLabel("Employee #"),employeeSexLabelCreate=new JLabel("Sex (M/F)"),employeeSexLabelEdit=new JLabel("Sex (M/F)"),employeeSexLabelDelete=new JLabel("Sex (M/F)"),firstNameLabelCreate=new JLabel("First Name"),firstNameLabel1=new JLabel("First Name"),firstNameLabelDelete=new JLabel("First Name"),grossPayLabelEarnings=new JLabel("Gross Pay"),hourlyWageLabelCreate=new JLabel("Hourly Wage"),hourlyWageLabelEdit=new JLabel("Hourly Wage"),hoursAWeekLabelCreate=new JLabel("Hours a Week"),hoursAWeekLabelEdit=new JLabel("Hours a Week"),lastNameLabelCreate=new JLabel("Last Name"),lastNameLabelEdit=new JLabel("Last Name"),lastNameLabelDelete=new JLabel("Last Name"),mainMenuLabel=new JLabel(STRING_MAIN_WINDOW_TITLE),middleNameLabelCreate=new JLabel("Middle Name"),middleNameLabelEdit=new JLabel("Middle Name"),netPayLabelEarnings=new JLabel("Net Pay"),salaryLabelCreate=new JLabel("Salary"),salaryLabelEdit=new JLabel("Salary"),vacationDaysLabelCreate=new JLabel("Vacation Days/Year"),vacationDaysLabelEdit=new JLabel("Vacation Days/Year"),weeksAYearLabelCreate=new JLabel("Weeks a Year"),weeksAYearLabelEdit=new JLabel("Weeks a Year"),workLocationLabelCreate=new JLabel("Work Location"),workLocationLabelEdit=new JLabel("Work Location"),yearsOfServiceLabelCreate=new JLabel("Years of Service"),yearsOfServiceLabelEdit=new JLabel("Years of Service");
    private JComboBox workLocationComboBoxCreate=new JComboBox(),workLocationEditComboBoxEdit=new JComboBox();
    private JButton addEmployeeButton=new JButton("Add an Employee"),backupButton=new JButton("Backup Database"),calculateButtonEarnings=new JButton("Calculate"),createEmployeeButtonCreate=new JButton(STRING_CREATE_EMPLOYEE_TITLE),deleteButtonDelete=new JButton("Delete"),deleteEmployeeButton=new JButton("Delete Employee"),earningsEmployeeButton=new JButton("Report on Employee Earnings"),findEmployeeButtonEdit=new JButton("Find Employee"),findEmployeeButtonDelete=new JButton("Find Employee"),modifyEmployeeButton=new JButton("Modify Data for an Employee"),saveChangesButtonEdit=new JButton("Save Changes");
    private ButtonGroup employeeSexButtonCreate=new ButtonGroup(),employeeSexButtonEdit=new ButtonGroup(),employeeSexButtonDelete=new ButtonGroup(),fullTimePartTimeButtonCreate=new ButtonGroup();
    //@formatter:on
}
